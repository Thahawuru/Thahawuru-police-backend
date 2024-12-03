package com.thahawuru_police.application.controller;

import com.thahawuru_police.application.dto.response.ApiResponse;
import com.thahawuru_police.application.dto.response.WantedPersonResponseDTO;
import com.thahawuru_police.application.entity.WantedPerson;
import com.thahawuru_police.application.service.WantedPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/wantedPerson")
@Slf4j
public class WantedPersonController {
    @Autowired
    private WantedPersonService wantedPersonService;

    @PostMapping("/createWantedPerson")
    public ResponseEntity<ApiResponse<WantedPersonResponseDTO>> createWantedPerson(@RequestBody WantedPerson wantedPerson){

        log.info("Received request to create wanted person: {}", wantedPerson);

        ApiResponse<WantedPersonResponseDTO> response  = new ApiResponse<>(HttpStatus.CREATED.value(),wantedPersonService.createWantedPerson(wantedPerson),"created");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<WantedPersonResponseDTO>>> getWantedPersons(){
        List<WantedPersonResponseDTO> wantedPersons = wantedPersonService.allWantedPersons();
        ApiResponse<List<WantedPersonResponseDTO>> response = new ApiResponse<>(HttpStatus.OK.value(),wantedPersons,"suceess");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<ApiResponse<WantedPersonResponseDTO>> getWantedPerson(@PathVariable UUID userid){
        WantedPersonResponseDTO wantedPerson  = wantedPersonService.getWantedPerson(userid);
        ApiResponse<WantedPersonResponseDTO> response = new ApiResponse<>(HttpStatus.OK.value(),wantedPerson,"success");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/edit/{userid}")
    public ResponseEntity<ApiResponse<WantedPersonResponseDTO>> updateWantedPerson(@RequestBody WantedPerson wantedPerson, @PathVariable(name = "userid") String id){
//        wantedPerson.setId(id);
        WantedPersonResponseDTO wantedperson = wantedPersonService.updateWantedPerson(wantedPerson);
        ApiResponse<WantedPersonResponseDTO> response = new ApiResponse<>(HttpStatus.OK.value(),wantedperson,"success");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //change user to inactive mode
    @DeleteMapping("/delete/{userid}")
    public ResponseEntity<ApiResponse<WantedPersonResponseDTO>> deleteWantedPerson(@PathVariable("userid") UUID id){
        WantedPersonResponseDTO wantedPerson = wantedPersonService.deleteWantedPerson(id);
        ApiResponse<WantedPersonResponseDTO> response = new ApiResponse<>(HttpStatus.OK.value(),wantedPerson,"success");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
