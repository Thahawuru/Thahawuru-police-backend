package com.thahawuru_police.application.entity;

import jakarta.persistence.*;




import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {


    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Id
    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID id;


    @NotNull(message = "Email is required")
    @NotBlank(message = "Email Cannot be blank!")
    @Email (message = "Email is not valid!")
    @Column(nullable = false,unique = true)
    private String email;


    @NotNull(message = "Password is required")
    @NotBlank(message = "Password Cannot be blank!")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "NIC is required")
    @NotBlank(message = "NIC Cannot be blank!")
    @Column(nullable = false)
    private String nic;

}
