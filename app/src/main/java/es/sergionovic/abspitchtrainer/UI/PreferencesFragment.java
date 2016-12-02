package es.sergionovic.abspitchtrainer.UI;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import es.sergionovic.abspitchtrainer.R;

/**
 * Created by Sergio on 08/07/2015.
 */
public class PreferencesFragment extends PreferenceFragment {

    public PreferencesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
