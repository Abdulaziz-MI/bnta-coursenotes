package com.bnta.chocolate.controllers;

import com.bnta.chocolate.models.CocoaOrder;
import com.bnta.chocolate.models.CocoaOrderDTO;
import com.bnta.chocolate.services.CocoaOrderFinderService;
import com.bnta.chocolate.services.CocoaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cocoa-orders")
public class CocoaOrderController {

    @Autowired
    CocoaOrderService cocoaOrderService;

//    @Autowired
//    CocoaOrderFinderService cocoaOrderFinderService;

//    INDEX

    @GetMapping
    public ResponseEntity<List<CocoaOrder>> getAllCocoaOrders(){
        return new ResponseEntity<>(cocoaOrderService.findAllCocoaOrders(), HttpStatus.OK);
    }

//    CREATE
    @PostMapping
    public ResponseEntity<List<CocoaOrder>> createCocoaOrder(@RequestBody CocoaOrderDTO cocoaOrderDTO){
        cocoaOrderService.saveCocoaOrder(cocoaOrderDTO);
        return new ResponseEntity<>(cocoaOrderService.findAllCocoaOrders(), HttpStatus.CREATED);
    }

//    DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteCocoaOrder(@PathVariable Long cocoaOrderId){
        cocoaOrderService.deleteCocoaOrder(cocoaOrderId);
        return new ResponseEntity<>(cocoaOrderId, HttpStatus.OK);
    }


}
