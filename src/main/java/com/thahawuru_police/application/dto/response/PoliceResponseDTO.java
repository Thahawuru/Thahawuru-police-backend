package com.thahawuru_police.application.dto.response;

import com.thahawuru_police.application.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoliceResponseDTO {
    private UUID policeId;
    private String nic;
    private String policeBadgeNumber;
    private String rank;
    private String position;
    private String department;
    private String dateOfJoining;
    private Status status;  //Active, On Leave, Retired
    private String photo;


}
