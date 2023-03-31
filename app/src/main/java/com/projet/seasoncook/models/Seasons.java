package com.projet.seasoncook.models;

import com.projet.seasoncook.R;

import java.io.Serializable;

public enum Seasons implements Serializable {

    Spring(R.id.radioButtonPrintemps), Summer(R.id.radioButtonEte), Fall(R.id.radioButtonAutomne), Winter(R.id.radioButtonHiver);

    private int id;
    Seasons(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}
