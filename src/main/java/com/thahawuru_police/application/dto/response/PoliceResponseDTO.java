package com.thahawuru_police.application.dto.response;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PoliceResponseDTO {

    @NotNull(message = "NIC is required")
    @NotBlank(message = "NIC Cannot be blank!")
    @Column(nullable = false)
    private String nic;

    @NotNull(message = "Police Badge Number is required")
    @NotBlank(message = "Police Badge Number Cannot be blank!")
    @Column(nullable = false, unique = true)
    private String policeBadgeNumber;

    @NotNull(message = "Rank is required")
    @NotBlank(message = "Rank cannot be blank!")
    private String rank;

    @NotNull(message = "position is required")
    @NotBlank(message = "position cannot be blank!")
    private String position;

    @NotNull(message = "department is required")
    @NotBlank(message = "department cannot be blank")
    @Column(nullable = false)
    private String department;

    @NotNull(message = "date of joining required")
    @NotBlank(message = "date of joining cannot be blank")
    private String dateOfJoining;

    @NotNull(message = "status is required")
    @NotBlank(message = "status cannot be blank")
    private String status;  //Active, On Leave, Retired

    @NotNull(message = "Photo is required")
    @NotBlank(message = "Photo cannot be blank!")
    private String photo;

    public PoliceResponseDTO(String nic,String policeBadgeNumber, String rank, String position, String department, String dateOfJoining, String status, String photo) {
        this.nic = nic;
        this.policeBadgeNumber = policeBadgeNumber;
        this.rank = rank;
        this.position = position;
        this.department = department;
        this.dateOfJoining = dateOfJoining;
        this.status = status;
        this.photo = photo;
    }
}

