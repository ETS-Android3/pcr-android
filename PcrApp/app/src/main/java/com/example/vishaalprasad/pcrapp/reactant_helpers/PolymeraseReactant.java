package com.example.vishaalprasad.pcrapp.reactant_helpers;

import android.content.res.Resources;

import com.example.vishaalprasad.pcrapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Polymerase Reactant in PCR
 * Typical Concentration reactant except Polymerase allows the custom unit "units per reaction"
 */
public class PolymeraseReactant extends ConcentrationReactant implements Serializable {

    @Override
    List<? extends UnitHelper.Unit> getPossibleUnits() {
        List<UnitHelper.Unit> possibleUnits = new ArrayList<>();
        possibleUnits.addAll(super.getPossibleUnits());
        possibleUnits.addAll(Arrays.asList(PolymeraseUnit.values()));
        return possibleUnits;
    }

    @Override
    double getFinalValueInMicroMolar() {
        // TODO: 9/26/17 implement this
        return 0d;
    }

    @Override
    String getName(Resources res) {
        return res.getString(R.string.polymerase);
    }

    enum PolymeraseUnit implements UnitHelper.Unit {UNIT_PER_REACTION;

        @Override
        public String getDisplayName(Resources res) {
            return res.getString(R.string.unit_per_reaction);
        }
    }
}
