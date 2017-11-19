package com.example.vishaalprasad.pcrapp.reactant_helpers

/**
 * Thrown by a [ConcentrationReactant] if is missing a [StockConcentration]
 */
class MissingStockConcentrationException(reactant: Reactant) : Exception() {

    private val reactantName: String

    init {
        this.reactantName = reactant.javaClass.simpleName
    }

    override fun getLocalizedMessage(): String {
        return reactantName + " reactant is missing a stock concentration"
    }
}