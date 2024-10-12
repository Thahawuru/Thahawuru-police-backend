package com.thahawuru_police.application.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "police_officers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoliceOfficer {



@GeneratedValue(generator = "UUID")
@GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
)
@Id
@Column(name = "police_id", updatable = false, nullable = false)
    private String policeId;

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

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "Password cannot be empty")
    private String password;

}
