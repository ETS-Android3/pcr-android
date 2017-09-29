package com.example.vishaalprasad.pcrapp.reactant_helpers;

import android.content.res.Resources;
import android.util.Log;

import com.example.vishaalprasad.pcrapp.R;

/**
 * Helps differentiate which type of unit
 */
public class UnitHelper {

    private static final String TAG = "UnitHelper";


    /**
     * Implementation of Unit will only be enumerators
     */
    interface Unit {
        String getDisplayName(Resources res);
    }

    /**
     * Group of units only to be used with concentration reactants
     */
    enum ConcentrationUnit implements Unit {
        NANO_MOLAR, MICRO_MOLAR, MILLI_MOLAR, PER_X;

        public String getDisplayName(Resources res) {
            switch (this) {
                case NANO_MOLAR:
                    return res.getString(R.string.unit_nanomolar_string);
                case MICRO_MOLAR:
                    return res.getString(R.string.unit_nanomolar_string);
                case MILLI_MOLAR:
                    return res.getString(R.string.unit_millimolar_string);
                case PER_X:
                    return res.getString(R.string.unit_per_x_string);
                default:
                    Log.e(TAG, "Unexpected Unit Type [" + this.getClass().getSimpleName() + "]");
                    return "";
            }
        }
    }

    /**
     * Group of units only to be used with volume reactants
     */
    enum VolumeUnit implements Unit {
        MICRO_LITER, MILLI_LITER;

        @Override
        public String getDisplayName(Resources res) {
            switch (this) {
                case MICRO_LITER:
                    return res.getString(R.string.unit_microliter_string);
                case MILLI_LITER:
                    return res.getString(R.string.unit_milliliter_string);
                default:
                    Log.e(TAG, "Unexpected Unit Type [" + this.getClass().getSimpleName() + "]");
                    return "";
            }
        }
    }
}
