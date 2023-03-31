package com.projet.seasoncook.models;

import java.io.Serializable;

public class Etape implements Serializable {

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
