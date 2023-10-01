package com.bnta.chocolate.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity (name = "chocolates")
public class Chocolate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "cocoa_percentage")
    private int cocoaPercentage;

//    @JsonIgnoreProperties({"chocolate"})
    @OneToMany (mappedBy = "chocolate")
    private List<CocoaOrder> cocoaOrders;

    public Chocolate (String name, int cocoaPercentage){
        this.name = name;
        this.cocoaPercentage = cocoaPercentage;
        this.cocoaOrders = new ArrayList<>();
    }

    public Chocolate(){

    }

    public void addCocoaOrder(CocoaOrder cocoaOrder){
        this.cocoaOrders.add(cocoaOrder);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCocoaPercentage() {
        return cocoaPercentage;
    }

    public void setCocoaPercentage(int cocoaPercentage) {
        this.cocoaPercentage = cocoaPercentage;
    }

    public List<CocoaOrder> getCocoaOrders() {
        return cocoaOrders;
    }

    public void setCocoaOrders(List<CocoaOrder> cocoaOrders) {
        this.cocoaOrders = cocoaOrders;
    }
}
