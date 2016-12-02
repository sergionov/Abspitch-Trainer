package es.sergionovic.abspitchtrainer.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.sergionovic.abspitchtrainer.R;


public class InfoFragment extends android.support.v4.app.Fragment {

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    public static RythmFragment newInstance() {
        RythmFragment fragment = new RythmFragment();
        return fragment;
    }

}
