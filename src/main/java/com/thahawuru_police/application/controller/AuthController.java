package com.thahawuru_police.application.controller;



import com.thahawuru_police.application.dto.response.ApiResponse;
import com.thahawuru_police.application.dto.request.LoginRequestDTO;
import com.thahawuru_police.application.dto.response.LoginResponseDTO;
import com.thahawuru_police.application.dto.response.UserResponseDTO;
import com.thahawuru_police.application.entity.User;
import com.thahawuru_police.application.service.AuthService;
import com.thahawuru_police.application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> loginUser(@Valid @RequestBody LoginRequestDTO user){
        ApiResponse<LoginResponseDTO> response  = new ApiResponse<>(HttpStatus.OK.value(),authService.login(user),"success");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDTO>> registerUser(@Valid @RequestBody User user){
        ApiResponse<UserResponseDTO> response  = new ApiResponse<>(HttpStatus.CREATED.value(),authService.registerUser(user),"created");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getme(@AuthenticationPrincipal User user){
        ApiResponse<UserResponseDTO> response =new ApiResponse<>(HttpStatus.OK.value(),new UserResponseDTO(user.getId(), user.getEmail(), user.getNic()),"success");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }



}
