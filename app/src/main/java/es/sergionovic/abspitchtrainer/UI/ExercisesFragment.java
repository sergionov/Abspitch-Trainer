package es.sergionovic.abspitchtrainer.UI;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import es.sergionovic.abspitchtrainer.R;
import es.sergionovic.abspitchtrainer.UI_Adapter.ViewPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExercisesFragment extends android.support.v4.app.Fragment {

    private final String STATE_POSITION = "pager_position";
    ViewPager viewPager;
    TabLayout tabLayout;


    public ExercisesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_exercises, container, false);

        if(savedInstanceState != null){
            viewPager.setCurrentItem(savedInstanceState.getInt(STATE_POSITION), true);
        }

        controls(layout);

        setRetainInstance(true);
        return layout;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(STATE_POSITION, viewPager.getCurrentItem());
    }

    private void controls(View layout) {

        viewPager = (ViewPager) layout.findViewById(R.id.tab_viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) layout.findViewById(R.id.tab_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new PitchFragment(), "Oido Absoluto");
        adapter.addFrag(new IntervalsFragment(), "Intervalos");
        adapter.addFrag(new ScalesFragment(), "Escalas");
        adapter.addFrag(new ChordsFragment(), "Acordes");
        adapter.addFrag(new RythmFragment(), "Ritmo");
        viewPager.setAdapter(adapter);
    }


}
