package com.thahawuru_police.application.controller;

import com.thahawuru_police.application.dto.response.UserResponseDTO;
import lombok.extern.slf4j.Slf4j;
import com.thahawuru_police.application.dto.response.ApiResponse;
import com.thahawuru_police.application.dto.response.PoliceResponseDTO;
import com.thahawuru_police.application.entity.Police;
import com.thahawuru_police.application.service.PoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/police")
@Slf4j
public class PoliceController {

    @Autowired
    private PoliceService policeService;

    @PostMapping("/createPolice")
    public ResponseEntity<ApiResponse<PoliceResponseDTO>> createPolice(@RequestBody Police police){

        log.info("Received request to create police: {}", police);

        ApiResponse<PoliceResponseDTO> response  = new ApiResponse<>(HttpStatus.CREATED.value(),policeService.createPolice(police),"created");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PoliceResponseDTO>>> getOfficers(){
        List<PoliceResponseDTO> officers =policeService.allOfficers();
        ApiResponse<List<PoliceResponseDTO>> response = new ApiResponse<>(HttpStatus.OK.value(),officers,"suceess");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<ApiResponse<PoliceResponseDTO>> getOfficer(@PathVariable UUID userid){
        PoliceResponseDTO officer  = policeService.getOfficer(userid);
        ApiResponse<PoliceResponseDTO> response = new ApiResponse<>(HttpStatus.OK.value(),officer,"success");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/edit/{userid}")
    public ResponseEntity<ApiResponse<PoliceResponseDTO>> updateOfficer(@RequestBody Police police, @PathVariable(name = "userid") String id){
        police.setPoliceId(id);
        PoliceResponseDTO officer = policeService.updateOfficer(police);
        ApiResponse<PoliceResponseDTO> response = new ApiResponse<>(HttpStatus.OK.value(),officer,"success");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //change user to inactive mode
    @DeleteMapping("/delete/{userid}")
    public ResponseEntity<ApiResponse<PoliceResponseDTO>> deleteOfficer(@PathVariable("userid") UUID id){
        PoliceResponseDTO officer = policeService.deleteOfficer(id);
        ApiResponse<PoliceResponseDTO> response = new ApiResponse<>(HttpStatus.OK.value(),officer,"success");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
