package com.bnta.chocolate.services;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.models.ChocolateDTO;
import com.bnta.chocolate.models.CocoaOrder;
import com.bnta.chocolate.models.Estate;
import com.bnta.chocolate.repositories.ChocolateRepository;
import com.bnta.chocolate.repositories.CocoaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChocolateService {

    @Autowired
    ChocolateRepository chocolateRepository;

    @Autowired
    CocoaOrderService cocoaOrderService;


    public Chocolate updateChocolate(ChocolateDTO chocolateDTO, Long id){
        Chocolate chocolateToUpdate = chocolateRepository.findById(id).get();
        chocolateToUpdate.setName(chocolateDTO.getName());
        chocolateToUpdate.setCocoaPercentage(chocolateDTO.getCocoaPercentage());
        for (Long cocoaOrderId : chocolateDTO.getCocoaOrderIds()){
            CocoaOrder cocoaOrder = cocoaOrderService.findCocoaOrder(cocoaOrderId);
            chocolateToUpdate.addCocoaOrder(cocoaOrder);
        }
        chocolateRepository.save(chocolateToUpdate);
        return chocolateToUpdate;
    }

    public void saveChocolate(ChocolateDTO chocolateDTO) {
        Chocolate chocolate = new Chocolate(chocolateDTO.getName(), chocolateDTO.getCocoaPercentage());
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

    public void deleteChocolate(Long chocolateId){
        //        find chocolate
        Chocolate chocolate = findChocolate(chocolateId);
    //        delete associated cocoaOrders
        for (CocoaOrder cocoaOrder : chocolate.getCocoaOrders()){
            cocoaOrderService.deleteCocoaOrder(cocoaOrder.getId());
        }
        chocolateRepository.deleteById(chocolateId);
    }



}
