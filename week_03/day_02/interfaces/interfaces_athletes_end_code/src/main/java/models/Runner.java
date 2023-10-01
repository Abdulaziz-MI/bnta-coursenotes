package models;

import interfaces.IRun;

public class Runner implements IRun {

    private String name;

    public Runner(String name){
        this.name = name;
    }

    public String run(int distance){
        return this.name + " ran " + distance + "m";
    }

}
