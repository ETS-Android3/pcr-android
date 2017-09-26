package com.example.vishaalprasad.pcrapp.reactant_helpers;

import com.example.vishaalprasad.pcrapp.reactant_helpers.units.Unit;

import java.util.List;

/**
 * Any item that can be in a PCR Reaction
 */
public abstract class Reactant {

    protected double amount;
    protected Unit unit;//unit

    abstract List<Unit> getPossibleUnits();

    abstract double getFinalValueInMicroMolar();
}
