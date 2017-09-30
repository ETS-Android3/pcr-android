package com.example.vishaalprasad.pcrapp.reactant_helpers;

import java.io.Serializable;

/**
 * Holder for a total Reaction Volume quantity
 * Only one instance per PCR Equation
 */
public class ReactionVolume implements Serializable {

    private double amount;

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public UnitHelper.VolumeUnit getUnit() {
        return UnitHelper.VolumeUnit.MICRO_LITER;
    }
}