package com.bnta.chocolate.models;

//{
//  "chocolateId" : 2,
//  "estateId" : 1,
//  "batchNumber" : "RE345",
//  "pricePaid" : 50.98
//}
public class CocoaOrderDTO {

    private Long chocolateId;
    private Long estateId;
    private String batchNumber;
    private double pricePaid;

    public CocoaOrderDTO(Long chocolateId, Long estateId, String batchNumber, double pricePaid) {
        this.chocolateId = chocolateId;
        this.estateId = estateId;
        this.batchNumber = batchNumber;
        this.pricePaid = pricePaid;
    }

    public CocoaOrderDTO(){

    }

    public Long getChocolateId() {
        return chocolateId;
    }

    public void setChocolateId(Long chocolateId) {
        this.chocolateId = chocolateId;
    }

    public Long getEstateId() {
        return estateId;
    }

    public void setEstateId(Long estateId) {
        this.estateId = estateId;
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
