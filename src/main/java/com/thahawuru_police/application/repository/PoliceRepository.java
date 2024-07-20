package com.thahawuru_police.application.repository;

import com.thahawuru_police.application.entity.Police;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface PoliceRepository extends JpaRepository<Police, UUID> {

    Optional<Police> findUserByNic(String nic);
}
