package com.bnta.chocolate.models;

import java.util.List;

//DTO no longer required
public class ChocolateDTO {

    private String name;
    private int cocoaPercentage;
    private List<Long> cocoaOrderIds;

    public ChocolateDTO(String name, int cocoaPercentage, List<Long> cocoaOrderIds){
        this.name = name;
        this.cocoaPercentage = cocoaPercentage;
        this.cocoaOrderIds = cocoaOrderIds;
    }

    public ChocolateDTO(){

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

    public List<Long> getCocoaOrderIds() {
        return cocoaOrderIds;
    }

    public void setCocoaOrderIds(List<Long> cocoaOrderIds) {
        this.cocoaOrderIds = cocoaOrderIds;
    }
}
