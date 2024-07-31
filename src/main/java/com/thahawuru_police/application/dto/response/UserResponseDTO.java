package com.thahawuru_police.application.dto.response;

import com.thahawuru_police.application.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private UUID userid;
    private String email;
    private Roles role;
}
