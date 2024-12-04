package com.thahawuru_police.application.service;

import com.thahawuru_police.application.dto.response.WantedPersonResponseDTO;
import com.thahawuru_police.application.exception.UserNotFoundException;
import com.thahawuru_police.application.entity.WantedPerson;
import com.thahawuru_police.application.repository.WantedPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WantedPersonService {

    @Autowired
    private WantedPersonRepository wantedPersonRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public WantedPersonResponseDTO createWantedPerson(WantedPerson wantedPerson) {
        if (wantedPersonRepository.findWantedPersonByName(wantedPerson.getName()).isPresent()){
            String notificationMessage = String.format("failure: Wanted Person '%s' Already Exists in Database", wantedPerson.getName());
            simpMessagingTemplate.convertAndSend("/topic/notifications", notificationMessage);
            throw new IllegalStateException("wantedPerson is  already exists!");
        }else{
            WantedPerson wantedPerson1 = new WantedPerson();
            wantedPerson1.setName(wantedPerson.getName());
            wantedPerson1.setDob(wantedPerson.getDob());
            wantedPerson1.setGender(wantedPerson.getGender());
            wantedPerson1.setNic(wantedPerson.getNic());
            wantedPerson1.setPhoto(wantedPerson.getPhoto());
            wantedPerson1.setReasonForBeingWanted(wantedPerson.getReasonForBeingWanted());
            wantedPerson1.setColor(wantedPerson.getColor());
            wantedPerson1.setHeight(wantedPerson.getHeight());
            wantedPerson1.setBodyType(wantedPerson.getBodyType());
            wantedPerson1.setOtherInfo(wantedPerson.getOtherInfo());

            WantedPerson wantedPerson2 = wantedPersonRepository.save(wantedPerson1);
            String notificationMessage = String.format("success: Wanted Person '%s' Added successfully", wantedPerson.getName());
            simpMessagingTemplate.convertAndSend("/topic/notifications", notificationMessage);
            return new WantedPersonResponseDTO(wantedPerson2.getId() ,  wantedPerson2.getName() , wantedPerson2.getDob() ,wantedPerson2.getGender() , wantedPerson2.getNic() , wantedPerson2.getPhoto() , wantedPerson2.getReasonForBeingWanted() , wantedPerson2.getColor() , wantedPerson2.getHeight() , wantedPerson2.getBodyType() , wantedPerson2.getOtherInfo() , wantedPerson2.getStatus());
        }
    }

    public List<WantedPersonResponseDTO> allWantedPersons() {
        return wantedPersonRepository.findAll().stream()
                .map(wantedPerson2->new WantedPersonResponseDTO(wantedPerson2.getId() ,wantedPerson2.getName() , wantedPerson2.getDob() ,wantedPerson2.getGender() , wantedPerson2.getNic() , wantedPerson2.getPhoto() , wantedPerson2.getReasonForBeingWanted() , wantedPerson2.getColor() , wantedPerson2.getHeight() , wantedPerson2.getBodyType() , wantedPerson2.getOtherInfo(), wantedPerson2.getStatus()))
                .collect(Collectors.toList());
    }

    public WantedPersonResponseDTO getWantedPerson(UUID userid) {
        WantedPerson wantedPerson2 =  wantedPersonRepository.findById(userid).orElseThrow(()-> new UserNotFoundException("officer Not Found!"));
        return new WantedPersonResponseDTO(wantedPerson2.getId() , wantedPerson2.getName() , wantedPerson2.getDob() ,wantedPerson2.getGender() , wantedPerson2.getNic() , wantedPerson2.getPhoto() , wantedPerson2.getReasonForBeingWanted() , wantedPerson2.getColor() , wantedPerson2.getHeight() , wantedPerson2.getBodyType() , wantedPerson2.getOtherInfo(), wantedPerson2.getStatus());
    }

    public WantedPersonResponseDTO updateWantedPerson(WantedPerson wantedPerson) {
        WantedPerson wantedPerson2 = wantedPersonRepository.save(wantedPerson);
        return new WantedPersonResponseDTO(wantedPerson2.getId() ,wantedPerson2.getName() , wantedPerson2.getDob() ,wantedPerson2.getGender() , wantedPerson2.getNic() , wantedPerson2.getPhoto() , wantedPerson2.getReasonForBeingWanted() , wantedPerson2.getColor() , wantedPerson2.getHeight() , wantedPerson2.getBodyType() , wantedPerson2.getOtherInfo(), wantedPerson2.getStatus());
    }

    public WantedPersonResponseDTO deleteWantedPerson(UUID id) {
        WantedPerson wantedPerson = wantedPersonRepository.findById(id).orElseThrow(()->new UserNotFoundException("officer Not Found!"));
        wantedPerson.setStatus("found");
        WantedPerson wantedPerson2 = wantedPersonRepository.save(wantedPerson);
        return new WantedPersonResponseDTO(wantedPerson2.getId() ,wantedPerson2.getName() , wantedPerson2.getDob() ,wantedPerson2.getGender() , wantedPerson2.getNic() , wantedPerson2.getPhoto() , wantedPerson2.getReasonForBeingWanted() , wantedPerson2.getColor() , wantedPerson2.getHeight() , wantedPerson2.getBodyType() , wantedPerson2.getOtherInfo(), wantedPerson2.getStatus());
    }
}
