package com.thahawuru_police.application.repository;

import com.thahawuru_police.application.entity.PoliceOfficer;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface PoliceOfficerRepository extends JpaRepository<PoliceOfficer, UUID> {

    Optional<PoliceOfficer> findByNic(String nic);
    Optional<PoliceOfficer>findByPoliceBadgeNumber(String badge);
}
