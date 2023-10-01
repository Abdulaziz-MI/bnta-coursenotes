package com.bnta.chocolate.models;

import java.util.ArrayList;
import java.util.List;

// DTO no longer required
public class EstateDTO {

    private String name;
    private String country;
    private List<Long> cocoaOrderIds;

    public EstateDTO(String name, String country, List<Long> cocoaOrderIds) {
        this.name = name;
        this.country = country;
        this.cocoaOrderIds = cocoaOrderIds;
    }

    public EstateDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Long> getCocoaOrderIds() {
        return cocoaOrderIds;
    }

    public void setCocoaOrderIds(List<Long> cocoaOrderIds) {
        this.cocoaOrderIds = cocoaOrderIds;
    }
}
