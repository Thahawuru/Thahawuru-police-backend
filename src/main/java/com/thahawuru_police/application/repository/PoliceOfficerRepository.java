package com.thahawuru_police.application.repository;

import com.thahawuru_police.application.entity.PoliceOfficer;
import com.thahawuru_police.application.entity.Status;
import com.thahawuru_police.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PoliceOfficerRepository extends JpaRepository<PoliceOfficer, UUID>{
    Optional<PoliceOfficer> findByNic(String nic);
    Optional<PoliceOfficer>findByUser(User user);
    Optional<PoliceOfficer>findByUserId(UUID userid);
    Optional<PoliceOfficer>findByPoliceBadgeNumber(String badge);
    List<PoliceOfficer> findByStatus(Status status);
}
