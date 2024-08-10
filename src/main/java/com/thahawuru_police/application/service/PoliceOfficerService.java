package com.thahawuru_police.application.service;

import com.thahawuru_police.application.dto.response.PoliceResponseDTO;
import com.thahawuru_police.application.exception.UserNotFoundException;
import com.thahawuru_police.application.entity.PoliceOfficer;
import com.thahawuru_police.application.repository.PoliceOfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PoliceOfficerService {

    @Autowired
    private PoliceOfficerRepository policeRepository;
    @Autowired
    private EncryptionService encryptionService;

    public PoliceResponseDTO createPolice(PoliceOfficer police){
        if(policeRepository.findByNic(police.getNic()).isPresent()){
            throw new IllegalStateException("Officer is  already exists!");
        }else{
            PoliceOfficer newOfficer = new PoliceOfficer();
            newOfficer.setNic(police.getNic());
            newOfficer.setPoliceBadgeNumber(police.getPoliceBadgeNumber());
            newOfficer.setRank(police.getRank());
            newOfficer.setPosition(police.getPosition());
            newOfficer.setDepartment(police.getDepartment());
            newOfficer.setDateOfJoining(police.getDateOfJoining());
            newOfficer.setStatus(police.getStatus());
            newOfficer.setPhoto(police.getPhoto());
            PoliceOfficer newofficer=policeRepository.save(newOfficer);

            return new PoliceResponseDTO(newofficer.getNic(),newofficer.getPoliceBadgeNumber(),newofficer.getRank(),newofficer.getPosition(),newofficer.getDepartment(),newofficer.getDateOfJoining(),newofficer.getStatus(),newofficer.getPhoto());
        }
    }

    public List<PoliceResponseDTO> allOfficers(){
        return policeRepository.findAll().stream()
                .map(officer->new PoliceResponseDTO(officer.getNic(),officer.getPoliceBadgeNumber(),officer.getRank(),officer.getPosition(),officer.getDepartment(),officer.getDateOfJoining(),officer.getStatus(),officer.getPhoto()))
                .collect(Collectors.toList());
    }

    public PoliceResponseDTO getOfficer(UUID userid){
        PoliceOfficer officer =  policeRepository.findById(userid).orElseThrow(()-> new UserNotFoundException("officer Not Found!"));
        return new PoliceResponseDTO(officer.getNic(),officer.getPoliceBadgeNumber(),officer.getRank(),officer.getPosition(),officer.getDepartment(),officer.getDateOfJoining(),officer.getStatus(),officer.getPhoto());
    }

    public PoliceResponseDTO updateOfficer(PoliceOfficer police) {
        PoliceOfficer officer = policeRepository.save(police);
        return new PoliceResponseDTO(officer.getNic(),officer.getPoliceBadgeNumber(),officer.getRank(),officer.getPosition(),officer.getDepartment(),officer.getDateOfJoining(),officer.getStatus(),officer.getPhoto());
    }

    //change user to inactive mode
    public PoliceResponseDTO deleteOfficer(UUID id) {
        PoliceOfficer officer = policeRepository.findById(id).orElseThrow(()-> new UserNotFoundException("officer Not Found!"));
        officer.setStatus("in-Active");
        PoliceOfficer officer2 = policeRepository.save(officer);
        return new PoliceResponseDTO(officer2.getNic(),officer2.getPoliceBadgeNumber(),officer2.getRank(),officer2.getPosition(),officer2.getDepartment(),officer2.getDateOfJoining(),officer2.getStatus(),officer2.getPhoto());
    }
}
