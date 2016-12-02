package es.sergionovic.abspitchtrainer.UI;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import es.sergionovic.abspitchtrainer.Model.ExerciseCard;
import es.sergionovic.abspitchtrainer.Model.TheoryCard;
import es.sergionovic.abspitchtrainer.R;
import es.sergionovic.abspitchtrainer.UI_Adapter.PitchCardAdapter;
import es.sergionovic.abspitchtrainer.UI_Adapter.TheoryCardAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TheoryFragment extends android.support.v4.app.Fragment {

    RecyclerView cardList;

    public TheoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_theory, container, false);

        controls(layout);

        initRecyclerView();

        return layout;
    }

    private void initRecyclerView(){

        cardList.setHasFixedSize(true);

        cardList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        cardList.setItemAnimator(new DefaultItemAnimator());

        TheoryCardAdapter pitchCardAdapter = new TheoryCardAdapter(getActivity(), getData(getActivity()));
        cardList.setAdapter(pitchCardAdapter);
    }

    private void controls(View layout) {
        cardList = (RecyclerView) layout.findViewById(R.id.theory_recycler);

    }

    public static List<TheoryCard> getData(Context context) {
        //load only static data inside a drawer
        List<TheoryCard> data = new ArrayList<>();

        String[] titles = context.getResources().getStringArray(R.array.tabs);

        String[] subtitles = context.getResources().getStringArray(R.array.theory_subtitles);

        String[] bodys = {context.getResources().getString(R.string.theory_pitch),
                context.getResources().getString(R.string.theory_interval),
                context.getResources().getString(R.string.theory_scales),
                context.getResources().getString(R.string.theory_chords),
                context.getResources().getString(R.string.theory_rythm)};

        for (int i = 0; i < titles.length; i++) {
            TheoryCard current = new TheoryCard();
            current.cardTitle = titles[i % titles.length];
            current.cardSubtitle = subtitles[i % subtitles.length];
            current.cardBody = bodys[i % bodys.length];
            current.cardButton =  context.getString(R.string.btQuery);
            data.add(current);

        }
        return data;
    }



}
