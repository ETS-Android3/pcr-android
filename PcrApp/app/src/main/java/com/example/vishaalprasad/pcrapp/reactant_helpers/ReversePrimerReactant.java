package com.example.vishaalprasad.pcrapp.reactant_helpers;

import android.content.res.Resources;

import com.example.vishaalprasad.pcrapp.R;

import java.io.Serializable;
import java.util.List;

/**
 *  Reverse Primer in PCR
 *  Relies heavily on a {@link ForwardPrimerReactant} for the shared {@link StockConcentration}
 */
public class ReversePrimerReactant extends Reactant implements Serializable {

    private ForwardPrimerReactant forwardPrimerReference;

    public ReversePrimerReactant(ForwardPrimerReactant forwardPrimerReactant) {
        super(forwardPrimerReactant.getPossibleUnits().get(0));

        this.forwardPrimerReference = forwardPrimerReactant;
    }

    @Override
    public List<? extends UnitHelper.Unit> getPossibleUnits() {
        return forwardPrimerReference.getPossibleUnits();
    }

    @Override
    double getFinalValueInMicroMolar() {
        // TODO: 9/26/17 implement this
        return 0d;
    }

    @Override
    public String getName(Resources res) {
        return res.getString(R.string.reverse_primer);
    }
}
