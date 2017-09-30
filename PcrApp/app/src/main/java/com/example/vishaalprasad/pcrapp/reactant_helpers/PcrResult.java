package com.example.vishaalprasad.pcrapp.reactant_helpers;

import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Holds each PCR Result Reactant
 */
public class PcrResult implements Serializable {

    private Reactant reactant;

    private double perTube;

    public double getPerTube() {
        return perTube;
    }

    /**
     * Get the original reactant
     *
     * @return a reference to the original {@link Reactant}, null in the case of water
     */
    @Nullable public Reactant getReactant() {
        return reactant;
    }

    public void setPerTube(double perTube) {
        this.perTube = perTube;
    }

    public void setReactant(Reactant reactant) {
        this.reactant = reactant;
    }
}
