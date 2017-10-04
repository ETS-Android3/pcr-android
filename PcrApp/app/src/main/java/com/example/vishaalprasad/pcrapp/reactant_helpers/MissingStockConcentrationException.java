package com.example.vishaalprasad.pcrapp.reactant_helpers;

/**
 * Thrown by a {@link ConcentrationReactant} if is missing a {@link StockConcentration}
 */
public class MissingStockConcentrationException extends Exception {

    private String reactantName;

    public MissingStockConcentrationException(Reactant reactant){
        this.reactantName = reactant.getClass().getSimpleName();
    }

    @Override
    public String getMessage() {
        return reactantName + " reactant is missing a stock concentration";
    }
}