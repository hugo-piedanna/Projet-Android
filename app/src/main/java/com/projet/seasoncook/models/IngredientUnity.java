package com.projet.seasoncook.models;

public enum IngredientUnity {

    Gramme("g"), Aucune(null), Gousse("gousse"), Cuillere_cafe("cuillère à café"), Pincee("pincée"), Tranche("tranche"), CentiLitre("cl"), Cuillere_soupe("couillère à soupe"), Quartier("quartier"), Brins("brins");

    private String symbole;
    IngredientUnity(String symbole){
        this.symbole = symbole;
    }

    public String getSymbole(){
        return this.symbole;
    }
}
