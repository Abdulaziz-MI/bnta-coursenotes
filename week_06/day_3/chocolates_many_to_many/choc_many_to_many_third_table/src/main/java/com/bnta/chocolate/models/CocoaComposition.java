package com.bnta.chocolate.models;

import javax.persistence.*;
// One chocolate has many estates
// One Estate has many chocolates


@Entity
public class CocoaComposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @ManyToOne
    private Chocolate chocolate;

    @Column
    @ManyToOne
    private Estate estate;

    private int cocoaCompositionPercentage;

    public CocoaComposition(Chocolate chocolate, Estate estate, int cocoaCompositionPercentage) {
        this.chocolate = chocolate;
        this.estate = estate;
        this.cocoaCompositionPercentage = cocoaCompositionPercentage;
    }

    public CocoaComposition(){

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCocoaCompositionPercentage() {
        return cocoaCompositionPercentage;
    }

    public void setCocoaCompositionPercentage(int cocoaCompositionPercentage) {
        this.cocoaCompositionPercentage = cocoaCompositionPercentage;
    }
}
