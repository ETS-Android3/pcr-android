package com.example.vishaalprasad.pcrapp.reactant_helpers;

import android.content.res.Resources;

import com.example.vishaalprasad.pcrapp.R;

import java.io.Serializable;

/**
 * DNTP Reactant in PCR
 * Typical Concentration Reactant
 */
public class DntpReactant extends ConcentrationReactant implements Serializable {

    @Override
    double getFinalValueInMicroMolar() {
        // TODO: 9/26/17 implement this
        return 0d;
    }

    @Override
    String getName(Resources res) {
        return res.getString(R.string.dntp);
    }
}
