package com.bnta.chocolate.models;

import java.util.List;

public class ChocolateDTO {

    private String name;
    private int cocoaPercentage;
    private Long estateId;

    public ChocolateDTO(String name, int cocoaPercentage, Long estateId){
        this.name = name;
        this.cocoaPercentage = cocoaPercentage;
        this.estateId = estateId;
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

    public Long getEstateId() {
        return estateId;
    }

    public void setEstateId(Long estateId) {
        this.estateId = estateId;
    }
}
