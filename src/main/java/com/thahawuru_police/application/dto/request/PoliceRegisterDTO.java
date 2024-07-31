package com.thahawuru_police.application.dto.request;


import com.thahawuru_police.application.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliceRegisterDTO {

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email Cannot be blank!")
    private String email;


    @NotNull(message = "password is required")
    @NotBlank(message = "password Cannot be blank!")
    private String password;


    @NotNull(message = "NIC is required")
    @NotBlank(message = "NIC Cannot be blank!")
    @Column(nullable = false)
    private String nic;

    @NotNull(message = "Police Badge is required")
    @NotBlank(message = "Police Badge Cannot be blank!")
    @Column(nullable = false,unique = true)
    private String policeBadgeNumber;


    private String rank;
    private String position;

    @Column(nullable = false)
    private String department;
    private String dateOfJoining;
    private String status;  //Active, On Leave, Retired
    private String photo;


}
