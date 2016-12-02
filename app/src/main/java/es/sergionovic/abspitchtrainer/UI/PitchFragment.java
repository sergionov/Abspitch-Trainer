package es.sergionovic.abspitchtrainer.UI;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.sergionovic.abspitchtrainer.Model.ExerciseCard;
import es.sergionovic.abspitchtrainer.R;
import es.sergionovic.abspitchtrainer.UI_Adapter.PitchCardAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio on 21/05/2015.
 */
public class PitchFragment extends android.support.v4.app.Fragment {

    RecyclerView cardList;
    int tab_no;

    public PitchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragmet_pitch, container, false);

        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().setSharedElementExitTransition(TransitionInflater.
                    from(getActivity()).inflateTransition(R.transition.shared_element_transition));
        }

        controls(layout);

        initRecyclerView();

        return layout;
    }


    private void initRecyclerView(){

        cardList.setHasFixedSize(true);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        final float density = getResources().getDisplayMetrics().density;
        float dpWidth = outMetrics.widthPixels / density;
        int columns = Math.round(dpWidth / 400);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), columns);
        cardList.setLayoutManager(mLayoutManager);

        cardList.setItemAnimator(new DefaultItemAnimator());

        PitchCardAdapter pitchCardAdapter = new PitchCardAdapter(getActivity(), getData(getActivity()));
        cardList.setAdapter(pitchCardAdapter);
    }

    private void controls(View layout) {
        cardList = (RecyclerView) layout.findViewById(R.id.pitch_recycler);

    }

    public static List<ExerciseCard> getData(Context context) {
        //load only static data inside a drawer
        List<ExerciseCard> data = new ArrayList<>();
        int[] images = {R.drawable.notas_oido_absoluto, R.drawable.canto_oido_absoluto};

        String[] subtitles = context.getResources().getStringArray(R.array.pitch_exercises_subtitles);

        String[] titles = context.getResources().getStringArray(R.array.pitch_exercises_titles);

        for (int i = 0; i < images.length && i < titles.length; i++) {
            ExerciseCard current = new ExerciseCard();
            current.cardTitle = titles[i % titles.length];
            current.cardImage = images[i % images.length];
            current.cardSubtitle = subtitles[i % subtitles.length];
            current.cardButton =  context.getResources().getString(R.string.btStart);
            data.add(current);

        }
        return data;
    }
}
