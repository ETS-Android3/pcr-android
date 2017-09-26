package com.example.vishaalprasad.pcrapp.reactant_helpers.units;


import android.content.res.Resources;

import com.example.vishaalprasad.pcrapp.R;

public class MilliMolarUnit extends Unit {

    @Override
    public String getDisplayName(Resources resources) {
        return resources.getString(R.string.unit_millimolar_string);
    }
}
