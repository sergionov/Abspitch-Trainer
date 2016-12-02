package es.sergionovic.abspitchtrainer.UI;


import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
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

import es.sergionovic.abspitchtrainer.DB.DataBaseHandler;
import es.sergionovic.abspitchtrainer.Model.ExerciseCard;
import es.sergionovic.abspitchtrainer.Model.ProfileCard;
import es.sergionovic.abspitchtrainer.Model.User;
import es.sergionovic.abspitchtrainer.R;
import es.sergionovic.abspitchtrainer.UI_Adapter.PitchCardAdapter;
import es.sergionovic.abspitchtrainer.UI_Adapter.ProfileCardAdapter;
import es.sergionovic.abspitchtrainer.Util.Preferences;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends android.support.v4.app.Fragment {

    RecyclerView cardList;

    static User user;
    static int photo;
    View layout;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        user = getUser();

        controls(layout);

        initRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.fragment_profile, container, false);

        user = getUser();

        controls(layout);

        initRecyclerView();

        // Inflate the layout for this fragment
        return layout;
    }

    private void initRecyclerView() {

        cardList.setHasFixedSize(true);

        cardList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        cardList.setItemAnimator(new DefaultItemAnimator());

        ProfileCardAdapter profileCardAdapter = new ProfileCardAdapter(getActivity(), getData(getActivity()));
        cardList.setAdapter(profileCardAdapter);
    }

    private void controls(View layout) {
        cardList = (RecyclerView) layout.findViewById(R.id.profile_recycler);

    }

    public static List<ProfileCard> getData(Context context) {
        //load only static data inside a drawer
        List<ProfileCard> data = new ArrayList<>();

        ProfileCard current = new ProfileCard();
        current.cardTitle = user.getName();
        current.cardImage = user.getPhoto();
        current.cardSubtitle = String.valueOf(user.getAge());
        current.cardButton = context.getResources().getString(R.string.btEdit);
        data.add(current);

        return data;
    }

    public User getUser() {
        return DataBaseHandler.getUser(getActivity(), String.valueOf(Preferences.userNumber(getActivity())));
    }

}
