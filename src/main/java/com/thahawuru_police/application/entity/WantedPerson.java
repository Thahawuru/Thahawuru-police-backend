package com.thahawuru_police.application.entity;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Wantedpersons")
public class WantedPerson {

    @GeneratedValue(generator = "UUID")
    @Id
    @Column(nullable = false,updatable = false)
    private UUID id;
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
