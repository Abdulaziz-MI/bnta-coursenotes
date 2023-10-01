package com.bnta.chocolate.services;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.models.ChocolateDTO;
import com.bnta.chocolate.models.Estate;
import com.bnta.chocolate.repositories.ChocolateRepository;
import com.bnta.chocolate.repositories.EstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChocolateService {

    @Autowired
    ChocolateRepository chocolateRepository;

    @Autowired
    EstateRepository estateRepository;

    public Chocolate updateChocolate(ChocolateDTO chocolateDTO, Long id){
        Chocolate chocolateToUpdate = chocolateRepository.findById(id).get();
        chocolateToUpdate.setName(chocolateDTO.getName());
        chocolateToUpdate.setCocoaPercentage(chocolateDTO.getCocoaPercentage());
        chocolateToUpdate.setEstates(new ArrayList<Estate>());
        for (Long estateId : chocolateDTO.getEstateIds()){
            Estate estate = estateRepository.findById(estateId).get();
            chocolateToUpdate.addEstate(estate);
        }
        chocolateRepository.save(chocolateToUpdate);
        return chocolateToUpdate;
    }

    public void saveChocolate(ChocolateDTO chocolateDTO){
        Chocolate chocolate = new Chocolate(chocolateDTO.getName(), chocolateDTO.getCocoaPercentage());

        for (Long estateId : chocolateDTO.getEstateIds()){
            Estate estate = estateRepository.findById(estateId).get();
            chocolate.addEstate(estate);
        }
        chocolateRepository.save(chocolate);
    }

    public Chocolate findChocolate(Long id){
       return chocolateRepository.findById(id).get();
    }

    public List<Chocolate> findAllChocolates(){
        return chocolateRepository.findAll();
    }

    public List<Chocolate> findAllChocolatesOverCocoaPercentage(int percentage){
        return chocolateRepository.findByCocoaPercentageGreaterThan(percentage);
    }

    public void deleteChocolate(Long id){
        chocolateRepository.deleteById(id);
    }


}
