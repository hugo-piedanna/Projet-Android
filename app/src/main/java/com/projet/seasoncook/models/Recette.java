package com.projet.seasoncook.models;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recette implements Serializable{

    private final List<Etape> steps;
    private final String title;
    private final int tempsPreparation;
    private final int tempsCooking;
    private final String imageLink;
    private final CookType type;
    private final Seasons[] seasons;
    private final Map<Ingredient, Integer> ingredients;

    private final int portion;

    public Recette(String title, List<Etape> steps, Map<Ingredient, Integer> ingredients, int tempsPreparation, int tempsCooking, String imageLink, CookType type, Seasons[] seasons, int portion){
        this.title = title;
        this.steps = steps;
        this.ingredients = ingredients;
        this.tempsPreparation = tempsPreparation;
        this.tempsCooking = tempsCooking;
        this.imageLink = imageLink;
        this.type = type;
        this.seasons = seasons;
        this.portion = portion;
    }

    public String getTitle(){
        return this.title;
    }

    public List<Etape> getSteps() {
        return this.steps;
    }

    public Map<Ingredient, Integer> getIngredients(){
        return this.ingredients;
    }

    public int getTimePreparation(){
        return this.tempsPreparation;
    }

    public int getTimeCooking(){
        return this.tempsCooking;
    }

    public String getImageLink(){
        return this.imageLink;
    }

    public CookType getType(){
        return this.type;
    }

    public Seasons[] getSeasons(){
        return this.seasons;
    }

    public int getPortion(){
        return this.portion;
    }

    @Override
    public String toString(){
        return this.title;
    }
}
