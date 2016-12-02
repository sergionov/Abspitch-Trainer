package es.sergionovic.abspitchtrainer.UI;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import es.sergionovic.abspitchtrainer.DB.DataBaseHandler;
import es.sergionovic.abspitchtrainer.Model.TextCard;
import es.sergionovic.abspitchtrainer.R;
import es.sergionovic.abspitchtrainer.UI_Adapter.TextAdapter;

import static es.sergionovic.abspitchtrainer.Util.Preferences.*;


public class AbspitchTrainer_Activity extends AppCompatActivity {

    RecyclerView cardList;
    FloatingActionButton floatingActionButton;
    ImageView imageView;
    private String COLOR_BACKGROUND = "colorBackground";
    private String COLOR_STATUSBAR = "colorStatus";
    private String TITLE_CARD = "cardTitle";
    private String IMAGE_CARD = "cardImage";

    static String title;
    static String pitchTitle;
    static String interval1Title;
    static String interval2Title;
    static int back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abspitch_trainer);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementEnterTransition(TransitionInflater.
                    from(this).inflateTransition(R.transition.shared_element_transition));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getIntent().getIntExtra(COLOR_STATUSBAR,
                    getResources().getColor(R.color.colorPrimary)));
        }

        pitchTitle = getString(R.string.PitcEx_1);
        interval1Title = getString(R.string.IntervalEx_1);
        interval2Title = getString(R.string.IntervalEx_2);

        controls();

        initRecyclerView();

        events();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_abspitch_trainer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }


    private void initRecyclerView() {
        cardList = (RecyclerView) findViewById(R.id.scrollableview);

        cardList.setHasFixedSize(true);
        cardList.setItemAnimator(new DefaultItemAnimator());

        final TextAdapter textAdapter = new TextAdapter(this, getData());
        cardList.setAdapter(textAdapter);
        cardList.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public List<TextCard> getData() {
        //load only static data inside a drawer
        List<TextCard> data = new ArrayList<>();
        String[] body = new String[0];
        title = getIntent().getStringExtra(TITLE_CARD);

        if (title.equals(getString(R.string.PitcEx_1)))
            body = new String[]{getResources().getString(R.string.abstrainer_pitch_body)};
        if (title.equals(getString(R.string.IntervalEx_1)))
            body = new String[]{getResources().getString(R.string.abstrainer_interval1_body)};
        if (title.equals(getString(R.string.IntervalEx_2)))
            body = new String[]{getResources().getString(R.string.abstrainer_interval2_body)};


        String[] titles = {getString(R.string.text_card_title)};

        for (int i = 0; i < titles.length; i++) {
            TextCard current = new TextCard();
            current.cardTitle = titles[i % titles.length];
            current.cardSubtitle = body[i % body.length];
            data.add(current);

        }
        return data;
    }


    public void controls() {

        imageView = (ImageView) findViewById(R.id.header);

        imageView.setImageResource(getIntent().getIntExtra(IMAGE_CARD, R.drawable.notas_oido_absoluto));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        if (collapsingToolbar != null) {
            collapsingToolbar.setTitle(getIntent().getStringExtra(TITLE_CARD));

            collapsingToolbar.setContentScrimColor(getIntent().getIntExtra(COLOR_BACKGROUND,
                    getResources().getColor(R.color.colorPrimary)));
            collapsingToolbar.setStatusBarScrimColor(getIntent().getIntExtra(COLOR_BACKGROUND,
                    getResources().getColor(R.color.colorPrimary)));

        }

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_start);
    }


    private void events() {

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoSeleccion dialogo = new DialogoSeleccion();
                dialogo.show(fragmentManager, "tagAlerta");

            }
        });

    }

    public static int getExecType() {

        int back = 0;

        if (title.equals(pitchTitle))
            back = 0;
        if (title.equals(interval1Title))
            back = 1;
        if (title.equals(interval2Title))
            back = 2;

        return back;
    }

    public static class DialogoSeleccion extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final String EXERCISE_LEVEL = "eLevel";
            final String[] items = getResources().getStringArray(R.array.levels);

            final AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity());

            builder.setTitle(getString(R.string.level_dialog_title))
                    //.setMessage("Si no has entrenado nunca el oido absoluto, recomendamos el nivel Facil.")
                    .setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            Log.i("Dialogos", "Opcion elegida: " + items[item]);
                            Intent intent;
                            switch (getExecType()) {
                                case 0:
                                    intent = new Intent(getActivity(), AbsPitch_Activity.class);
                                    intent.putExtra(EXERCISE_LEVEL, item);

                                    updateExercisesNumber(getActivity());

                                    DataBaseHandler.newExercise(getActivity(), String.valueOf(exercisesNumber(getActivity())),
                                            "Oido Absoluto", String.valueOf(item + 1), "0");
                                    startActivity(intent);
                                    break;
                                case 1:
                                    intent = new Intent(getActivity(), AbsIntervals_Activity.class);
                                    intent.putExtra(EXERCISE_LEVEL, item);

                                    updateExercisesNumber(getActivity());

                                    DataBaseHandler.newExercise(getActivity(), String.valueOf(exercisesNumber(getActivity())),
                                            "Intervalos 1", String.valueOf(item + 1), "1");
                                    startActivity(intent);
                                    break;
                                case 2:
                                    intent = new Intent(getActivity(), AbsIntervals2_Activity.class);
                                    intent.putExtra(EXERCISE_LEVEL, item);

                                    updateExercisesNumber(getActivity());

                                    DataBaseHandler.newExercise(getActivity(), String.valueOf(exercisesNumber(getActivity())),
                                            "Intervalos 2", String.valueOf(item + 1), "1");
                                    startActivity(intent);
                                    break;
                            }
                        }
                    })
                    .setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            return builder.create();
        }
    }


}
