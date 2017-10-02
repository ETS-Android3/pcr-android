package com.example.vishaalprasad.pcrapp.reactant_helpers;

import com.example.vishaalprasad.pcrapp.reactant_helpers.UnitHelper.Unit;
import com.example.vishaalprasad.pcrapp.reactant_helpers.UnitHelper.ConcentrationUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Special type of Reactant that may have a Stock Concentration
 * Stock concentration will not be used (or needed) if Reactant's unit is set to PER_X
 */
public abstract class ConcentrationReactant extends Reactant {

    @Override
    List<? extends UnitHelper.Unit> getPossibleUnits() {
        return Arrays.asList(ConcentrationUnit.values());
    }
}