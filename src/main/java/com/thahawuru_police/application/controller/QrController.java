package com.thahawuru_police.application.controller;

import com.thahawuru_police.application.dto.response.ApiResponse;
import com.thahawuru_police.application.dto.response.QrResponseDTO;
import com.thahawuru_police.application.service.QrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/qr")
public class QrController {

    @Autowired
    private QrService qrService;

//    @GetMapping("/{nic}")
//    public ResponseEntity<ApiResponse<QrResponseDTO>> getQrDetails(@PathVariable String nic){
//        ApiResponse response = new ApiResponse<>(HttpStatus.OK.value(), new QrResponseDTO("200300535"),"success");
//        return new ResponseEntity<>(response,HttpStatus.OK);
//    }
//}
@GetMapping("/{nic}")
    public ResponseEntity<ApiResponse<QrResponseDTO>> getQrDetails(@PathVariable String nic){
        ApiResponse response = new ApiResponse<>(HttpStatus.OK.value(), qrService.getNicDetails(nic),"success");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
