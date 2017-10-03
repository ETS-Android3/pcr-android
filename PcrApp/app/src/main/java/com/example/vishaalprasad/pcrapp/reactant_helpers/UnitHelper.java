package com.example.vishaalprasad.pcrapp.reactant_helpers;

import android.content.res.Resources;
import android.util.Log;

import com.example.vishaalprasad.pcrapp.R;

import java.io.Serializable;

/**
 * Helps differentiate which type of unit
 */
public class UnitHelper {

    private static final String TAG = "UnitHelper";


    /**
     * Implementation of Unit will only be enumerators
     */
    public interface Unit {
        String getDisplayName(Resources res);
    }

    /**
     * Group of units only to be used with concentration reactants
     */
    public enum ConcentrationUnit implements Unit {
        NANO_MOLAR, MICRO_MOLAR, MILLI_MOLAR, PER_X;

        public String getDisplayName(Resources res) {
            switch (this) {
                case NANO_MOLAR:
                    return res.getString(R.string.unit_nanomolar_string);
                case MICRO_MOLAR:
                    return res.getString(R.string.unit_micromolar_string);
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
    public enum VolumeUnit implements Unit {
        MICRO_LITER;

        @Override
        public String getDisplayName(Resources res) {
            switch (this) {
                case MICRO_LITER:
                    return res.getString(R.string.unit_microliter_string);
                default:
                    Log.e(TAG, "Unexpected Unit Type [" + this.getClass().getSimpleName() + "]");
                    return "";
            }
        }
    }
}
