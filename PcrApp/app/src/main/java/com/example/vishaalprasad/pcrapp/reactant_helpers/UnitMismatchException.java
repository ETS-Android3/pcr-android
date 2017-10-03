package com.example.vishaalprasad.pcrapp.reactant_helpers;

/**
 * Thrown only by {@link PolymeraseReactant} if unit does not match up with its {@link StockConcentration}
 * (The framework should prevent this)
 *
 * Agreeing Units:      NanoMolar, MicroMolar, or MilliMolar
 * OR                   UnitPerReaction with UnitsPerMicroLiter
 *
 */
public class UnitMismatchException extends Exception {
}
