package com.bnta.chocolate.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "estates")
public class Estate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String country;

//    @JsonIgnoreProperties({"estate"})
    @OneToMany(mappedBy = "estate") //what is the property in CocoaOrder this class (Estate) should map to?
    private List<CocoaOrder> cocoaOrders;

    public Estate(String name, String country) {
        this.name = name;
        this.country = country;
        this.cocoaOrders = new ArrayList<>();
    }

    public Estate() {
    }

    public void addCocoaOrder(CocoaOrder cocoaOrder){
        this.cocoaOrders.add(cocoaOrder);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public List<CocoaOrder> getCocoaOrders() {
        return cocoaOrders;
    }

    public void setCocoaOrders(List<CocoaOrder> cocoaOrders) {
        this.cocoaOrders = cocoaOrders;
    }

}
