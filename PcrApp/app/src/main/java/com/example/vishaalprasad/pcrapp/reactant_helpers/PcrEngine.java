package com.example.vishaalprasad.pcrapp.reactant_helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles PCR Calculations
 * Final values of each Reactant is calculated in its own {@link Reactant} class
 */
public class PcrEngine {

    /**
     * Solves the PCR Equation with the given parameters
     *
     * @param reactants      List of Reactants
     * @param quantity       How many reactions to be done
     * @param reactionVolume Total volume of reaction
     * @return A List of {@link PcrResult} Objects containing the amounts of each Reactant
     */
    public static List<PcrResult> calculatePcr(List<Reactant> reactants, int quantity,
                                                ReactionVolume reactionVolume) {

        List<PcrResult> results = new ArrayList<>();

        double reactantSum = 0d;
        for (Reactant reactant : reactants) {

            PcrResult result = new PcrResult();
            result.setReactant(reactant);
            result.setPerTube(reactant.getFinalValueInMicroMolar());

            results.add(new PcrResult());
            reactantSum += reactant.getFinalValueInMicroMolar();
        }

        double waterAmount = reactionVolume.getAmount() - reactantSum;
        PcrResult waterResult = new PcrResult();
        waterResult.setPerTube(waterAmount);

        return results;

    }

}