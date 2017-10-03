package com.example.vishaalprasad.pcrapp.reactant_helpers;

import java.io.Serializable;

/**
 * StockConcentration to be paired with a {@link ConcentrationReactant}
 */
public class StockConcentration implements Serializable {

    private double amount;
    private UnitHelper.Unit unit;

    public StockConcentration(UnitHelper.Unit unit) {
        this.unit = unit;
    }

    public double getAmount() {
        return amount;
    }

    public UnitHelper.Unit getUnit() {
        return unit;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setUnit(UnitHelper.Unit unit) {
        this.unit = unit;
    }
}
