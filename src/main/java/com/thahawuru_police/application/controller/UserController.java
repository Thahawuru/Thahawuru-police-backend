package com.thahawuru_police.application.controller;


import com.thahawuru_police.application.dto.response.ApiResponse;
import com.thahawuru_police.application.dto.response.UserResponseDTO;
import com.thahawuru_police.application.entity.User;
import com.thahawuru_police.application.service.AuthService;
import com.thahawuru_police.application.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getUsers(){
        List<UserResponseDTO> users =userService.allUsers();
        ApiResponse<List<UserResponseDTO>> response = new ApiResponse<>(HttpStatus.OK.value(),users,"suceess");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUser(@PathVariable UUID userid){
        UserResponseDTO user  = userService.getUser(userid);
        ApiResponse<UserResponseDTO> response = new ApiResponse<>(HttpStatus.OK.value(),user,"success");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser( @RequestBody User user){
        log.info("Received request to create police: {}", user);

        ApiResponse<UserResponseDTO> response  = new ApiResponse<>(HttpStatus.CREATED.value(),userService.createUser(user),"created");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
//    @PostMapping
//    public ResponseEntity<String> createUser(@RequestBody Map<String,String> payload ){
//        return new ResponseEntity<String>(userService.createUser(payload.get("email"),payload.get("password")),HttpStatus.OK);
//    }




    @GetMapping("/test")
    public String test(){
        return "test route";
    }


//    @PostMapping("/adduser")
//    public ResponseEntity<String> testPost(@Valid @RequestBody User user){
//
//        var existingUser = userRepository.findUserByEmail(user.getEmail());
//        if (existingUser.isPresent()) {
//            String response =  user.getEmail() + " email is already exists!";
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
//        } else {
//            userRepository.insert(user);
//            String response = user.getEmail() + " created successfully";
//            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        }
//
//    }




}

