package com.example.vishaalprasad.pcrapp.reactant_helpers

import android.content.res.Resources
import com.example.vishaalprasad.pcrapp.R
import java.util.ArrayList

/**
 * Handles PCR Calculations
 * Final values of each Reactant is calculated in its own {@link Reactant} class
 */
class PcrEngine {

    companion object {

        /**
         * Solves the PCR Equation with the given parameters
         *
         * @param reactants      List of Reactants
         * @param reactionVolume Total volume of reaction
         * @return A List of {@link PcrResult} Objects containing the amounts of each Reactant
         */
        fun calculatePcr(reactants: List<PcrReactable>,
                         reactionVolume: ReactionVolume,
                         res: Resources): List<PcrResult> {

            val results = ArrayList<PcrResult>()

            var reactantSum = 0.0
            for (reactable in reactants) {
                if (reactable !is Reactant) continue

                val result = PcrResult()
                result.name = reactable.getName(res)

                val finalValue = reactable.getFinalValueInMicroMolar(reactionVolume)
                result.perTube = finalValue

                results.add(result)
                reactantSum += finalValue
            }

            val waterAmount = reactionVolume.amount - reactantSum
            val waterResult = PcrResult()
            waterResult.perTube = waterAmount
            waterResult.name = res.getString(R.string.water)
            results.add(waterResult)

            return results
        }

        /**
         * Convert a __MOLAR unit to MICRO_MOLAR
         *
         * @param value to be converted
         * @param unit the original Unit
         * @return the unit converted to MICRO_MOLAR
         */
        fun toMicroMolar(value: Double, unit: UnitHelper.ConcentrationUnit): Double {

            return when (unit) {
                UnitHelper.ConcentrationUnit.NANO_MOLAR -> value / 1_000.0

                UnitHelper.ConcentrationUnit.MILLI_MOLAR -> value * 1000

                else -> value
            }
        }
    }
}