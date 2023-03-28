package com.projet.seasoncook.models;

public class Etape {

    private int number;
    private String description;

    public Etape(int number, String description){
        this.number = number;
        this.description = description;
    }

    public int getNumber(){
        return this.number;
    }

    public String getDescription(){
        return this.description;
    }
}
