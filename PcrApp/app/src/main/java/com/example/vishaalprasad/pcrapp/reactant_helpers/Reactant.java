package com.example.vishaalprasad.pcrapp.reactant_helpers;

import android.content.res.Resources;

import com.example.vishaalprasad.pcrapp.reactant_helpers.UnitHelper.Unit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Any item that can be in a PCR Reaction
 */
public abstract class Reactant implements PcrReactable, Serializable {

    protected double amount;
    protected Unit unit;

    public Reactant() {
        setUnit(getPossibleUnits().get(0));
    }

    public Reactant(Unit unit) {
        setUnit(unit);
    }

    public abstract List<? extends Unit> getPossibleUnits();

    /**
     * Get all possible Units' names
     *
     * @param res Resources to get Strings
     * @return List<CharSequence> of all Units' names
     */
    public List<CharSequence> getPossibleUnitNames(Resources res) {

        List<? extends Unit> units = getPossibleUnits();

        List<CharSequence> names = new ArrayList<>(units.size());
        for (Unit u : units) names.add(u.getDisplayName(res));

        return names;
    }

    abstract double getFinalValueInMicroMolar();

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public abstract String getName(Resources res);

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }
}
