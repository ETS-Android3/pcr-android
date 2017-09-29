package com.example.vishaalprasad.pcrapp.reactant_helpers;

import android.content.res.Resources;

import com.example.vishaalprasad.pcrapp.reactant_helpers.UnitHelper.Unit;

import java.io.Serializable;
import java.util.List;

/**
 * Any item that can be in a PCR Reaction
 */
public abstract class Reactant implements Serializable {

    protected double amount;
    protected Unit unit;

    abstract List<? extends Unit> getPossibleUnits();

    abstract double getFinalValueInMicroMolar();

    abstract String getName(Resources res);
}
