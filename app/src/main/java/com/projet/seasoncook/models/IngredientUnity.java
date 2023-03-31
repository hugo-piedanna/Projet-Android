package com.projet.seasoncook.models;

import java.io.Serializable;

public enum IngredientUnity implements Serializable {

    Gramme("g"), Aucune(""), Gousse("gousse"), Cuillere_cafe("cuillère à café"), Pincee("pincée"), Tranche("tranche"), CentiLitre("cl"), Cuillere_soupe("cuillère à soupe"), Quartier("quartier"), Brins("brins");

    private String symbole;
    IngredientUnity(String symbole){
        this.symbole = symbole;
    }

    public String getSymbole(){
        return this.symbole;
    }
}
