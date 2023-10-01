package com.bnta.chocolate.services;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.models.CocoaOrder;
import com.bnta.chocolate.models.Estate;
import com.bnta.chocolate.models.EstateDTO;
import com.bnta.chocolate.repositories.CocoaOrderRepository;
import com.bnta.chocolate.repositories.EstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstateService {

    @Autowired
    EstateRepository estateRepository;

    @Autowired
    CocoaOrderService cocoaOrderService;

    public List<Estate> findAllEstates(){
        return estateRepository.findAll();
    }

    public Estate findEstate(Long id){
        return estateRepository.findById(id).get();
    }

    public void saveEstate(Estate estate){
        estateRepository.save(estate);
    }

    public void deleteEstate(Long estateId){
//        find estate
        Estate estate = findEstate(estateId);
//        delete associated cocoaOrders
        for (CocoaOrder cocoaOrder : estate.getCocoaOrders()){
            cocoaOrderService.deleteCocoaOrder(cocoaOrder.getId());
        }
        estateRepository.deleteById(estateId);
    }

    public Estate updateEstate(EstateDTO estateDTO, Long id){
        Estate estateToUpdate = estateRepository.findById(id).get();
        estateToUpdate.setName(estateDTO.getName());
        estateToUpdate.setCountry(estateDTO.getCountry());

        estateToUpdate.setCocoaOrders(new ArrayList<CocoaOrder>());
        for (Long cocoaOrderId : estateDTO.getCocoaOrderIds()){
            CocoaOrder cocoaOrder = cocoaOrderService.findCocoaOrder(cocoaOrderId);
            estateToUpdate.addCocoaOrder(cocoaOrder);
        }

        estateRepository.save(estateToUpdate);
        return estateToUpdate;
    }


}
