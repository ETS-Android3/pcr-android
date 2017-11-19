package com.example.vishaalprasad.pcrapp.reactant_helpers;

import android.content.res.Resources;

import com.example.vishaalprasad.pcrapp.reactant_helpers.UnitHelper.ConcentrationUnit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.vishaalprasad.pcrapp.reactant_helpers.UnitHelper.ConcentrationUnit.NANO_MOLAR;

/**
 * Special type of Reactant that may have a Stock Concentration
 * Stock concentration will not be used (or needed) if Reactant's unit is set to PER_X
 */
public abstract class ConcentrationReactant extends Reactant implements Serializable {

    private StockConcentration stockConcentration;

    @Override
    public List<? extends UnitHelper.Unit> getPossibleUnits() {
        return Arrays.asList(ConcentrationUnit.values());
    }

    public void setStockConcentration(StockConcentration stockConcentration) {
        this.stockConcentration = stockConcentration;
    }

    public StockConcentration getStockConcentration() {
        return stockConcentration;
    }

    @Override
    double getFinalValueInMicroMolar(ReactionVolume reactionVolume)
            throws MissingStockConcentrationException, UnitMismatchException {

        ConcentrationUnit concentrationUnit = (ConcentrationUnit) getUnit();
        double initialInMicroMolar = PcrEngine.Companion.toMicroMolar(getAmount(), (ConcentrationUnit) getUnit());

        switch (concentrationUnit) {

            case NANO_MOLAR:
            case MICRO_MOLAR:
            case MILLI_MOLAR:

                if (stockConcentration == null) {
                    throw new MissingStockConcentrationException(this);
                }

                double stockInMicroMolar = PcrEngine.Companion.toMicroMolar(stockConcentration.getAmount(),
                        (ConcentrationUnit) stockConcentration.getUnit());

                return initialInMicroMolar * reactionVolume.getAmount() / stockInMicroMolar;

            default:

                return reactionVolume.getAmount() / initialInMicroMolar;
        }
    }

    @Override
    public void setUnit(UnitHelper.Unit unit) {
        super.setUnit(unit);

        // current stock concentration is not compatible, drop it
        if (stockConcentration != null
                && (getPossibleStockConcUnits() == null
                || !getPossibleStockConcUnits().contains(stockConcentration.getUnit()))) {

            stockConcentration = null;
        }
    }

    /**
     * Get possible units for the corresponding {@link StockConcentration}
     *
     * @return a List of possible Units, null if StockConcentration is unnecessary
     */
    public List<? extends UnitHelper.Unit> getPossibleStockConcUnits() {

        if (getUnit() == ConcentrationUnit.MILLI_MOLAR
                || getUnit() == ConcentrationUnit.MICRO_MOLAR
                || getUnit() == NANO_MOLAR) {

            return Arrays.asList(ConcentrationUnit.MILLI_MOLAR, ConcentrationUnit.MICRO_MOLAR, NANO_MOLAR);
        }

        return null;
    }

    public List<CharSequence> getPossibleStockConcUnitNames(Resources res) {

        List<? extends UnitHelper.Unit> units = getPossibleStockConcUnits();

        List<CharSequence> names = new ArrayList<>(units.size());
        for (UnitHelper.Unit u : units) names.add(u.getDisplayName(res));

        return names;
    }
}