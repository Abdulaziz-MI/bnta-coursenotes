package models;

import interfaces.Cyclable;

public class Cyclist implements Cyclable {

    private String name;

    public Cyclist(String name){
        this.name = name;
    }

    public String cycle(int distance){
        return this.name + " cycled " + distance + "m";
    }

}
