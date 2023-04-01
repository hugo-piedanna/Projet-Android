package com.projet.seasoncook.models;

import java.io.Serializable;

public enum IngredientUnity implements Serializable {

    Gramme("g"), Aucune(""), Gousse("gousse"), Cuillere_cafe("c.à.c"), CentiLitre("cl"), Cuillere_soupe("c.à.s"), Quartier("quartier"), Brins("brins");

    private String symbole;
    IngredientUnity(String symbole){
        this.symbole = symbole;
    }

    public String getSymbole(){
        return this.symbole;
    }
}
