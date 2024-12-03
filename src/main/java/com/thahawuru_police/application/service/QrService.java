package com.thahawuru_police.application.service;

import com.thahawuru_police.application.dto.response.QrResponseDTO;
import com.thahawuru_police.application.dto.response.WalletResponseDTO;
import com.thahawuru_police.application.entity.WantedPerson;
import com.thahawuru_police.application.exception.UserNotFoundException;
import com.thahawuru_police.application.repository.WantedPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class QrService {

    @Autowired
    private WantedPersonRepository wantedPersonRepository;

    @Value("${thahawuru.api.key}")
    private  String SYSTEM_API_KEY;
    private static final String SYSTEM_URL = "http://localhost:9000/api/v1/qr/get";

    public WalletResponseDTO getNicDetails(String nicId) {
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
            QrResponseDTO qrResponse = response.getBody();

            if (qrResponse == null) {
                throw new RuntimeException("Invalid response from QR API");
            }

            Optional<WantedPerson> wantedPersonOptional = wantedPersonRepository.findWantedPersonByNic(nicId);
            WantedPerson wantedPerson = wantedPersonOptional.orElse(null);

            return new WalletResponseDTO(qrResponse, wantedPerson);


        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404) {
                throw new UserNotFoundException("User not found for this QR: " + nicId);
            } else {
                throw new RuntimeException("Request timeout!: " + e.getStatusCode().value());
            }
        } catch (HttpServerErrorException e) {
            throw new RuntimeException("Server error: " + e.getStatusCode().value());
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }


    }
}
