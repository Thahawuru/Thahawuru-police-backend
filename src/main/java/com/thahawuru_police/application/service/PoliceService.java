package com.thahawuru_police.application.service;

import com.thahawuru_police.application.dto.response.PoliceResponseDTO;
import com.thahawuru_police.application.exception.UserNotFoundException;
import com.thahawuru_police.application.entity.Police;
import com.thahawuru_police.application.repository.PoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PoliceService {

    @Autowired
    private PoliceRepository policeRepository;
    @Autowired
    private EncryptionService encryptionService;

    public PoliceResponseDTO createPolice(Police police){
        if(policeRepository.findUserByNic(police.getNic()).isPresent()){
            throw new IllegalStateException("Officer is  already exists!");
        }else{
            Police newOfficer = new Police();
            newOfficer.setNic(police.getNic());
            newOfficer.setPoliceBadgeNumber(police.getPoliceBadgeNumber());
            newOfficer.setRank(police.getRank());
            newOfficer.setPosition(police.getPosition());
            newOfficer.setDepartment(police.getDepartment());
            newOfficer.setDateOfJoining(police.getDateOfJoining());
            newOfficer.setStatus(police.getStatus());
            newOfficer.setPhoto(police.getPhoto());
            newOfficer.setPassword(encryptionService.encryptPassword(police.getPassword()));
            Police newofficer=policeRepository.save(newOfficer);

            return new PoliceResponseDTO(newofficer.getPoliceId(),newofficer.getNic(),newofficer.getPoliceBadgeNumber(),newofficer.getRank(),newofficer.getPosition(),newofficer.getDepartment(),newofficer.getDateOfJoining(),newofficer.getStatus(),newofficer.getPhoto());
        }
    }

    public List<PoliceResponseDTO> allOfficers(){
        return policeRepository.findAll().stream()
                .map(officer->new PoliceResponseDTO(officer.getPoliceId(),officer.getNic(),officer.getPoliceBadgeNumber(),officer.getRank(),officer.getPosition(),officer.getDepartment(),officer.getDateOfJoining(),officer.getStatus(),officer.getPhoto()))
                .collect(Collectors.toList());
    }

    public PoliceResponseDTO getOfficer(UUID userid){
        Police officer =  policeRepository.findById(userid).orElseThrow(()-> new UserNotFoundException("officer Not Found!"));
        return new PoliceResponseDTO(officer.getPoliceId(),officer.getNic(),officer.getPoliceBadgeNumber(),officer.getRank(),officer.getPosition(),officer.getDepartment(),officer.getDateOfJoining(),officer.getStatus(),officer.getPhoto());
    }

    public PoliceResponseDTO updateOfficer(Police police) {
        Police officer = policeRepository.save(police);
        return new PoliceResponseDTO(officer.getPoliceId(),officer.getNic(),officer.getPoliceBadgeNumber(),officer.getRank(),officer.getPosition(),officer.getDepartment(),officer.getDateOfJoining(),officer.getStatus(),officer.getPhoto());
    }

    //change user to inactive mode
    public PoliceResponseDTO deleteOfficer(UUID id) {
        Police officer = policeRepository.findById(id).orElseThrow(()-> new UserNotFoundException("officer Not Found!"));
        officer.setStatus("in-Active");
        Police officer2 = policeRepository.save(officer);
        return new PoliceResponseDTO(officer2.getPoliceId(),officer2.getNic(),officer2.getPoliceBadgeNumber(),officer2.getRank(),officer2.getPosition(),officer2.getDepartment(),officer2.getDateOfJoining(),officer2.getStatus(),officer2.getPhoto());
    }
}
