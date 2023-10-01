package com.bnta.chocolate.services;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.models.CocoaOrder;
import com.bnta.chocolate.models.CocoaOrderDTO;
import com.bnta.chocolate.models.Estate;
import com.bnta.chocolate.repositories.CocoaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

// This service exists to avoid circularity error
@Service
public class CocoaOrderService {

    @Autowired
    CocoaOrderRepository cocoaOrderRepository;

    private ChocolateService chocolateService;

    private EstateService estateService;

//    circular dependency workaround
    public CocoaOrderService(@Lazy ChocolateService chocolateService, @Lazy EstateService estateService){
        this.chocolateService = chocolateService;
        this.estateService = estateService;
    }

    public List<CocoaOrder> saveCocoaOrder(CocoaOrderDTO cocoaOrderDTO){
//        find chocolate
        Chocolate chocolate = chocolateService.findChocolate(cocoaOrderDTO.getChocolateId());
//        find estate
        Estate estate = estateService.findEstate(cocoaOrderDTO.getEstateId());

        CocoaOrder cocoaOrder = new CocoaOrder(chocolate, estate, cocoaOrderDTO.getBatchNumber(), cocoaOrderDTO.getPricePaid());
        cocoaOrderRepository.save(cocoaOrder);
        return cocoaOrderRepository.findAll();
    }

    public CocoaOrder findCocoaOrder(Long id){
        return cocoaOrderRepository.findById(id).get();
    }

    public List<CocoaOrder> findAllCocoaOrders(){
        return cocoaOrderRepository.findAll();
    }

    public void deleteCocoaOrder(Long id){
        cocoaOrderRepository.deleteById(id);
    }

}
