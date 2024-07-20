package com.thahawuru_police.application.security;

import com.auth0.jwt.exceptions.JWTDecodeException;

import com.thahawuru_police.application.entity.User;
import com.thahawuru_police.application.repository.UserRepository;
import com.thahawuru_police.application.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;


@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")){
            String token = tokenHeader.substring(7);
            try{
                UUID userid=jwtService.getUserId(token);
                Optional<User> user =userRepository.findById(userid);
                if(user.isPresent()){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.get(),null,new ArrayList<>());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }catch(JWTDecodeException ex){
                throw new JWTDecodeException("No valid token found");
            }

        }
        filterChain.doFilter(request,response);
    }
}
