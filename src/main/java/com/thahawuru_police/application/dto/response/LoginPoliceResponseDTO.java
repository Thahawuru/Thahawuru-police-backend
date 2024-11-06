package com.thahawuru_police.application.dto.response;


import com.thahawuru_police.application.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginPoliceResponseDTO {
    private UserResponseDTO user;
    private PoliceResponseDTO police;
    private String token;
}
