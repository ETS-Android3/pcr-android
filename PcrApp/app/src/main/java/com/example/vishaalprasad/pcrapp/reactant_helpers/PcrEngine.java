package com.example.vishaalprasad.pcrapp.reactant_helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles PCR Calculations
 * Final values of each Reactant is calculated in its own {@link Reactant} class
 */
public class PcrEngine {

    /**
     * Does the PCR Calculation
     *
     * @param reactants List of Reactants
     * @param quantity How many reactions to be done
     * @param reactionVolume Total volume of reaction
     * @return A List of {@link PcrResult} Objects containing the amounts of each Reactant
     */
    private static List<PcrResult> calculatePcr(List<Reactant> reactants, int quantity,
                                                ReactionVolume reactionVolume) {

        List<PcrResult> results = new ArrayList<>();

        PcrResult waterresResult = new PcrResult();

        return null;

    }



}
