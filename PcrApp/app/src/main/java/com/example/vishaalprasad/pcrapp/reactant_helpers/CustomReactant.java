package com.example.vishaalprasad.pcrapp.reactant_helpers;

import android.content.res.Resources;

import java.io.Serializable;

/**
 * Any custom user-defined Reactant, can behave as a {@link ConcentrationReactant} or user can choose
 * to only use volume units (without concentration) 
 */
public class CustomReactant extends ConcentrationReactant implements Serializable {

    private String name;

    public CustomReactant(String name) { 
        this.name = name;
    }

    @Override
    double getFinalValueInMicroMolar() {
        // TODO: 9/26/17 implement this
        return 0d;
    }

    @Override
    String getName(Resources res) {
        return this.name;
    }

}
