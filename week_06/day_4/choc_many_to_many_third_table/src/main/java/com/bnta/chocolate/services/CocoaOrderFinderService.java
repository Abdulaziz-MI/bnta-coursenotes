package com.bnta.chocolate.services;

import com.bnta.chocolate.models.CocoaOrder;
import com.bnta.chocolate.repositories.CocoaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocoaOrderFinderService {

    @Autowired
    CocoaOrderRepository cocoaOrderRepository;

    public CocoaOrder findCocoaOrder(Long id){
        return cocoaOrderRepository.findById(id).get();
    }

    public List<CocoaOrder> findAllCocoaOrders(){
        return cocoaOrderRepository.findAll();
    }
}
