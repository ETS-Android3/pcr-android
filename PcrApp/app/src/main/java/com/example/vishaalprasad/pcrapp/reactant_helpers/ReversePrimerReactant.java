package com.example.vishaalprasad.pcrapp.reactant_helpers;

import android.content.res.Resources;

import com.example.vishaalprasad.pcrapp.R;

import java.io.Serializable;
import java.util.List;

/**
 *  Reverse Primer in PCR, relies on a {@link ForwardPrimerReactant} for Stock Concentration
 */
public class ReversePrimerReactant extends Reactant implements Serializable {

    private ForwardPrimerReactant forwardPrimerReference;

    public ReversePrimerReactant(ForwardPrimerReactant forwardPrimerReactant) {
        this.forwardPrimerReference = forwardPrimerReactant;
    }

    @Override
    List<? extends UnitHelper.Unit> getPossibleUnits() {
        return forwardPrimerReference.getPossibleUnits();
    }

    @Override
    double getFinalValueInMicroMolar() {
        // TODO: 9/26/17 implement this
        return 0d;
    }

    @Override
    String getName(Resources res) {
        return res.getString(R.string.reverse_primer);
    }
}
