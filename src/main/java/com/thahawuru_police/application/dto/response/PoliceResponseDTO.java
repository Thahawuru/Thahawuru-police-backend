package com.thahawuru_police.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoliceResponseDTO {

    private String policeId;
    private String nic;
    private String policeBadgeNumber;
    private String rank;
    private String position;
    private String department;
    private String dateOfJoining;
    private String status;  //Active, On Leave, Retired
    private String photo;
}
