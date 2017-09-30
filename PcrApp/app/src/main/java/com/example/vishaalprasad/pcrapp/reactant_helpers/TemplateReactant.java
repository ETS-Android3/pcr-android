package com.example.vishaalprasad.pcrapp.reactant_helpers;

import android.content.res.Resources;

import com.example.vishaalprasad.pcrapp.R;
import com.example.vishaalprasad.pcrapp.reactant_helpers.UnitHelper.Unit;
import com.example.vishaalprasad.pcrapp.reactant_helpers.UnitHelper.VolumeUnit;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class TemplateReactant extends Reactant implements Serializable {

    @Override
    List<? extends Unit> getPossibleUnits() {
        return Arrays.asList(VolumeUnit.values());
    }

    @Override
    public double getFinalValueInMicroMolar() {
        // TODO: 9/26/17 implement this
        return 0d;
    }

    @Override
    String getName(Resources res) {
        return res.getString(R.string.template);
    }
}
