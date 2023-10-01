package models;

import interfaces.Swimable;

public class Swimmer implements Swimable {

    private String name;

    public Swimmer(String name){
        this.name = name;
    }

    public String swim(int distance){
        return this.name + " swam " + distance + "m"; //Jane swam 10m
    }

}
