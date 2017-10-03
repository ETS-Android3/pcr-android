package com.example.vishaalprasad.pcrapp.reactant_helpers;

import android.content.res.Resources;

import com.example.vishaalprasad.pcrapp.R;

import java.io.Serializable;

/**
 * Forward Primer in PCR
 * Reverse Primer relies on this reactant's Stock Concentration
 */
public class ForwardPrimerReactant extends ConcentrationReactant implements Serializable {

    @Override
    double getFinalValueInMicroMolar() {
        // TODO: 9/26/17 implement this
        return 0d;
    }

    @Override
    public String getName(Resources res) {
        return res.getString(R.string.forward_primer);
    }
}
