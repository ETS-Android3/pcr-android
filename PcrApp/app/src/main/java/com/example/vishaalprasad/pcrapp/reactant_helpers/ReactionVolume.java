package com.example.vishaalprasad.pcrapp.reactant_helpers;

import java.io.Serializable;

/**
 * Holder for a total Reaction Volume quantity
 * Only one per PCR Equation
 */
public class ReactionVolume implements Serializable {

    private UnitHelper.VolumeUnit unit;
    private double amount;

    public ReactionVolume() {
        unit = UnitHelper.VolumeUnit.MICRO_LITER;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setUnit(UnitHelper.VolumeUnit unit) {
        this.unit = unit;
    }

    public double getAmount() {
        return amount;
    }

    public UnitHelper.VolumeUnit getUnit() {
        return unit;
    }
}