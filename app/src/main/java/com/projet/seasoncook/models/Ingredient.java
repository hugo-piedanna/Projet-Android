package com.projet.seasoncook.models;

import java.io.Serializable;

public class Ingredient implements Serializable {

    private final String name;
    private final IngredientUnity unity;

    public Ingredient(String name, IngredientUnity unity){
        this.name = name;
        this.unity = unity;
    }

    public String getName(){
        return this.name;
    }

    public IngredientUnity getUnity(){
        return this.unity;
    }
}

