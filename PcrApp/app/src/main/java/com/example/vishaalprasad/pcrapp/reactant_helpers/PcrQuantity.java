package com.example.vishaalprasad.pcrapp.reactant_helpers;

/**
 * The number of reactions (multiplicity) desired in a PCR Equation
 * Only one per PCR Equation
 */
public class PcrQuantity implements PcrReactable {

    private int quantity;

    public PcrQuantity(){
        this.quantity = 1;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
