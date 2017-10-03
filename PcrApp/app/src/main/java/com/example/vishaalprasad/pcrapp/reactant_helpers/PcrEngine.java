package com.example.vishaalprasad.pcrapp.reactant_helpers;

import java.util.ArrayList;
import java.util.List;

import static com.example.vishaalprasad.pcrapp.reactant_helpers.UnitHelper.ConcentrationUnit.MICRO_MOLAR;
import static com.example.vishaalprasad.pcrapp.reactant_helpers.UnitHelper.ConcentrationUnit.MILLI_MOLAR;
import static com.example.vishaalprasad.pcrapp.reactant_helpers.UnitHelper.ConcentrationUnit.NANO_MOLAR;

/**
 * Handles PCR Calculations
 * Final values of each Reactant is calculated in its own {@link Reactant} class
 */
public class PcrEngine {

    /**
     * Solves the PCR Equation with the given parameters
     *
     * @param reactants      List of Reactants
     * @param reactionVolume Total volume of reaction
     * @return A List of {@link PcrResult} Objects containing the amounts of each Reactant
     */
    public static List<PcrResult> calculatePcr(List<PcrReactable> reactants, ReactionVolume reactionVolume)
            throws MissingStockConcentrationException, UnitMismatchException {

        List<PcrResult> results = new ArrayList<>();

        double reactantSum = 0d;
        for (PcrReactable reactable: reactants) {
            if (!(reactable instanceof Reactant)) continue;

            Reactant reactant = (Reactant) reactable;

            PcrResult result = new PcrResult();
            result.setReactant(reactant);

            double finalValue = reactant.getFinalValueInMicroMolar(reactionVolume);
            result.setPerTube(finalValue);

            results.add(new PcrResult());
            reactantSum += finalValue;
        }

        double waterAmount = reactionVolume.getAmount() - reactantSum;
        PcrResult waterResult = new PcrResult();
        waterResult.setPerTube(waterAmount);

        return results;
    }

    /**
     * Convert a __MOLAR unit to MICRO_MOLAR
     *
     * @param value to be converted
     * @param unit the original Unit
     * @return the unit converted to MICRO_MOLAR
     */
    public static double toMicroMolar(double value, UnitHelper.ConcentrationUnit unit) {

        switch (unit) {

            case NANO_MOLAR:
                return value / 1_000d;

            case MILLI_MOLAR:
                return value * 1_000;

            default:
                return -1;
        }
    }
}