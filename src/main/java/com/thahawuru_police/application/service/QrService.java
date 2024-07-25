package com.thahawuru_police.application.service;

import com.thahawuru_police.application.dto.response.QrResponseDTO;
import com.thahawuru_police.application.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QrService {

    @Value("${thahawuru.api.key}")
    private  String SYSTEM_API_KEY;
    private static final String SYSTEM_URL = "http://localhost:9000/api/v1/qr/get";

    public QrResponseDTO getNicDetails(String nicId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("THAHAWURU-API-KEY", SYSTEM_API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try{

        ResponseEntity<QrResponseDTO> response = restTemplate.exchange(
                SYSTEM_URL + "/" + nicId,
                HttpMethod.GET,
                entity,
                QrResponseDTO.class
        );
            return response.getBody();
        }catch(Exception ex){
          throw new UserNotFoundException("User data not found!");
        }

//        System.out.println("this is the response"+response);

    }
}
