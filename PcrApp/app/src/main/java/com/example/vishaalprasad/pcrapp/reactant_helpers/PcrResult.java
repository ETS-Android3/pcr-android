package com.example.vishaalprasad.pcrapp.reactant_helpers;

import java.io.Serializable;

/**
 * Holds each PCR Result Reactant
 */
public class PcrResult implements Serializable {

    private String name;
    private double perTube;

    public double getPerTube() {
        return perTube;
    }

    public void setPerTube(double perTube) {
        this.perTube = perTube;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
