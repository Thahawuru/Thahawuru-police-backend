package com.thahawuru_police.application.controller;


import com.thahawuru_police.application.dto.request.PoliceRegisterDTO;
import com.thahawuru_police.application.dto.response.ApiResponse;
import com.thahawuru_police.application.dto.response.PoliceResponseDTO;
import com.thahawuru_police.application.repository.PoliceOfficerRepository;
import com.thahawuru_police.application.repository.UserRepository;
import com.thahawuru_police.application.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PoliceOfficerRepository policeRepository;

    @Autowired
    private AdminService adminService;



        @PostMapping("/createpolice")
    public ResponseEntity<ApiResponse<PoliceResponseDTO>> createPolice(@RequestBody PoliceRegisterDTO police) {
            System.out.println("POLICE CREATE CONTROLLEr");
            PoliceResponseDTO officer = adminService.createPolice(police);
            ApiResponse<PoliceResponseDTO> response = new ApiResponse<>(HttpStatus.CREATED.value(), officer, "success");
           return new ResponseEntity<>(response,HttpStatus.CREATED);
        }

}
