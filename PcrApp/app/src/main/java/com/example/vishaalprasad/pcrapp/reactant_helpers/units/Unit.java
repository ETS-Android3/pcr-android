package com.example.vishaalprasad.pcrapp.reactant_helpers.units;

import android.content.res.Resources;

/**
 * Unit to be used with any Reactant or StockConcentration
 */
public abstract class Unit {

    enum Units {NANO_MOLAR, MICRO_MOLAR, MILLI_MOLAR, PER_X}

    public static String getDisplayName(Resources resources) {
        return null;
    }

}
