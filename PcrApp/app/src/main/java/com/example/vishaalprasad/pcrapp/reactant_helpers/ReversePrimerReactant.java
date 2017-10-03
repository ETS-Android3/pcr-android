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
    double getFinalValueInMicroMolar(ReactionVolume reactionVolume) throws MissingStockConcentrationException {

        UnitHelper.ConcentrationUnit concentrationUnit = (UnitHelper.ConcentrationUnit) getUnit();
        double initialInMicroMolar = PcrEngine.toMicroMolar(getAmount(), (UnitHelper.ConcentrationUnit) getUnit());

        StockConcentration stockConcentration = forwardPrimerReference.getStockConcentration();

        switch (concentrationUnit) {

            case NANO_MOLAR:
            case MICRO_MOLAR:
            case MILLI_MOLAR:

                if (stockConcentration == null) {
                    throw new MissingStockConcentrationException();
                }

                double stockInMicroMolar = PcrEngine.toMicroMolar(stockConcentration.getAmount(),
                        (UnitHelper.ConcentrationUnit) stockConcentration.getUnit());

                return initialInMicroMolar * reactionVolume.getAmount() / stockInMicroMolar;

            default:

                return reactionVolume.getAmount() / initialInMicroMolar;
        }
    }

    @Override
    public String getName(Resources res) {
        return res.getString(R.string.reverse_primer);
    }
}
