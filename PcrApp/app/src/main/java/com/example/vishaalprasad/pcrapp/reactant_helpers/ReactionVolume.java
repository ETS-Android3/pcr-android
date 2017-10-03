package com.example.vishaalprasad.pcrapp.reactant_helpers;

import java.io.Serializable;

/**
 * Holder for a total Reaction Volume quantity
 * Only one instance per PCR Equation
 * <p>
 * Unit is inherently microliter
 */
public class ReactionVolume implements PcrReactable, Serializable {

    private double amount;

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

}