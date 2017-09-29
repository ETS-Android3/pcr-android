package com.example.vishaalprasad.pcrapp.reactant_helpers;

import java.io.Serializable;

/**
 * Holds each PCR Result Reactant
 */
public class PcrResult implements Serializable {

    private Reactant reactant; // original Reactant

    private double perTube;
    private double masterMix;

    public double getMasterMix() {
        return masterMix;
    }

    public double getPerTube() {
        return perTube;
    }

    public Reactant getReactant() {
        return reactant;
    }

    public void setMasterMix(double masterMix) {
        this.masterMix = masterMix;
    }

    public void setPerTube(double perTube) {
        this.perTube = perTube;
    }

    public void setReactant(Reactant reactant) {
        this.reactant = reactant;
    }
}
