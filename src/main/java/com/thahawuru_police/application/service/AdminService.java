package com.thahawuru_police.application.service;

import com.thahawuru_police.application.dto.request.PoliceRegisterDTO;
import com.thahawuru_police.application.dto.response.PoliceResponseDTO;
import com.thahawuru_police.application.entity.PoliceOfficer;
import com.thahawuru_police.application.entity.Roles;
import com.thahawuru_police.application.entity.User;
import com.thahawuru_police.application.repository.PoliceOfficerRepository;
import com.thahawuru_police.application.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminService {

    @Autowired
    private PoliceOfficerRepository policeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncryptionService encryptionService;

@Transactional
    public PoliceResponseDTO createPolice(PoliceRegisterDTO police){

        if(userRepository.findUserByEmail(police.getEmail().toLowerCase()).isPresent()){
            throw new IllegalStateException("Officer is  already exists!");
        }

        if(policeRepository.findByNic(police.getNic()).isPresent()){
            throw new IllegalStateException("Officer is  already exists!");
        }else{
            User newuser = new User();
            newuser.setRole(Roles.POLICEOFFICER);
            newuser.setEmail(police.getEmail().toLowerCase());
            newuser.setPassword(encryptionService.encryptPassword(police.getPassword()));
            User saveduser = userRepository.save(newuser);

            PoliceOfficer policeOfficer = new PoliceOfficer();
            policeOfficer.setUser(saveduser);
            policeOfficer.setDepartment(police.getDepartment());
            policeOfficer.setPoliceBadgeNumber(police.getPoliceBadgeNumber());
            policeOfficer.setNic(police.getNic());
            policeOfficer.setRank(police.getRank());
            policeOfficer.setPosition(police.getPosition());
            policeOfficer.setDateOfJoining(police.getDateOfJoining());
            policeOfficer.setStatus(police.getStatus());
            policeOfficer.setPhoto(police.getPhoto());

            PoliceOfficer newofficer=policeRepository.save(policeOfficer);

            return new PoliceResponseDTO(newofficer.getPoliceId(),newofficer.getNic(),newofficer.getPoliceBadgeNumber(),newofficer.getRank(),newofficer.getPosition(),newofficer.getDepartment(),newofficer.getDateOfJoining(),newofficer.getStatus(),newofficer.getPhoto());
        }
    }
}
