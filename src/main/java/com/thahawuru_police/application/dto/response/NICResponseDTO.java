package com.thahawuru_police.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NICResponseDTO {
//    private LocalDate issuedDate;
    private String identityNumber;
    private String name;
    private String otherNames;
//    private LocalDate birthDate;
    private String birthPlace;
    private String job;
    private String livingAddress;
}
