package com.thahawuru_police.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WantedPersonResponseDTO {
    private String userid;
    private String name;
    private String dob;
    private String gender;
    private String nic;
    private String photo;
    private String reasonForBeingWanted;
    private String color;
    private String height;
    private String bodyType;
    private String otherInfo;
    private String status;
}
