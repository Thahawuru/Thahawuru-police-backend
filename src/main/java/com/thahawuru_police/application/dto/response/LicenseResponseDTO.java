package com.thahawuru_police.application.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicenseResponseDTO {
    private String licenseNumber;
    private String identityNumber;
    private String name;
    private String livingAddress;
//    private LocalDate birthDate;
//    private LocalDate issuedDate;
//    private LocalDate expiryDate;
    private String blood_groLocalDate;
}
