package es.sergionovic.abspitchtrainer.UI;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.sergionovic.abspitchtrainer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScalesFragment extends android.support.v4.app.Fragment {


    public ScalesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scales, container, false);
    }

    public static ScalesFragment newInstance() {
        ScalesFragment fragment = new ScalesFragment();
        return fragment;
    }
    
}
