package com.example.vishaalprasad.pcrapp.reactant_helpers.units;

import android.content.res.Resources;

import com.example.vishaalprasad.pcrapp.R;

public class MicroMolarUnit extends Unit {

    @Override
    public static String getDisplayName(Resources resources) {
        return resources.getString(R.string.unit_micromolar_string);
    }
}
