package com.example.vishaalprasad.pcrapp.reactant_helpers

import java.io.Serializable

/**
 * The number of reactions (multiplicity) desired in a PCR Equation
 * Only one per PCR Equation
 */
class PcrQuantity: Serializable, PcrReactable {

    var quantity: Int = 1

}