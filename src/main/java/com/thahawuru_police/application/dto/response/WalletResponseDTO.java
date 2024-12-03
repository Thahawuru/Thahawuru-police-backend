package com.thahawuru_police.application.dto.response;

import com.thahawuru_police.application.entity.WantedPerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponseDTO {
    QrResponseDTO details;
    WantedPerson wantedPerson;
}


