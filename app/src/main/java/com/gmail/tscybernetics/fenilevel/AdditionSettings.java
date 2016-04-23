package com.gmail.tscybernetics.fenilevel;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class AdditionSettings extends PreferenceActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new AdditionalSettingsFragment())
                .commit();
    }

}
