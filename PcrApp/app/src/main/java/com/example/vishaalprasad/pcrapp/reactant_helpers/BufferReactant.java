package com.example.vishaalprasad.pcrapp.reactant_helpers;

import android.content.res.Resources;

import com.example.vishaalprasad.pcrapp.R;

import java.io.Serializable;

/**
 * Buffer Reactant in PCR
 * Typical Concentration Reactant
 */
public class BufferReactant extends ConcentrationReactant implements Serializable {

    @Override
    String getName(Resources res) {
        return res.getString(R.string.buffer);
    }

    @Override
    double getFinalValueInMicroMolar() {
        // TODO: 9/26/17 implement this
        return 0d;
    }
}
