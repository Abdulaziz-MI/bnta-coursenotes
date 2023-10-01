package models;

import interfaces.Cyclable;
import interfaces.IRun;
import interfaces.Swimable;

import java.util.ArrayList;
import java.util.List;

public class Championship {

    private String name;
    private List<Cyclable> cyclists;
    private List<IRun> runners;
    private List<Swimable> swimmers;

    public Championship(String name){
        this.name = name;
        this.swimmers = new ArrayList<>();
        this.cyclists = new ArrayList<>();
        this.runners = new ArrayList<>();
    }

    public void addSwimmer(Swimable swimmer){
        this.swimmers.add(swimmer);
    }

    public int getSwimmerCount(){
        return this.swimmers.size();
    }

    public List<Swimable> getSwimmers() {
        return swimmers;
    }
}
