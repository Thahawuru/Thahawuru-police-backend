package com.thahawuru_police.application.dto.request;


import com.thahawuru_police.application.entity.Status;
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

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name Cannot be blanck!")
    private String name;

    @NotNull(message = "password is required")
    @NotBlank(message = "password Cannot be blank!")
    private String password;


    @NotNull(message = "NIC is required")
    @NotBlank(message = "NIC Cannot be blank!")
    @Column(nullable = false)
    private String nic;

    @NotNull(message = "Police Badge Number is required")
    @NotBlank(message = "Police Badge Number Cannot be blank!")
    @Column(nullable = false,unique = true)
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
    private Status status;  //Active, On Leave, Retired

    @NotNull(message = "Photo is required")
    @NotBlank(message = "Photo cannot be blank!")
    private String photo;

}
