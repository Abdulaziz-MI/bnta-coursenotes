package com.bnta.chocolate.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity (name = "cocoa_orders")
public class CocoaOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({ "cocoaOrders"})
    @ManyToOne // Many orders can have one chocolate
    @JoinColumn(name = "chocolate_id")
    private Chocolate chocolate;

    @JsonIgnoreProperties({ "cocoaOrders"})
    @ManyToOne // Many orders can have one estate
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @Column
    private String batchNumber;

    @Column
    private double pricePaid;

    public CocoaOrder(Chocolate chocolate, Estate estate, String batchNumber, double pricePaid) {
        this.chocolate = chocolate;
        this.estate = estate;
        this.batchNumber = batchNumber;
        this.pricePaid = pricePaid;
    }

    public CocoaOrder(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chocolate getChocolate() {
        return chocolate;
    }

    public void setChocolate(Chocolate chocolate) {
        this.chocolate = chocolate;
    }

    public Estate getEstate() {
        return estate;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }
}
