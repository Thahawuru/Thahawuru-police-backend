package com.thahawuru_police.application.repository;

import com.thahawuru_police.application.entity.WantedPerson;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface WantedPersonRepository extends JpaRepository<WantedPerson , UUID> {

    Optional<WantedPerson> findWantedPersonByName(String name);
    Optional<WantedPerson> findWantedPersonByNic(String nic);
}
