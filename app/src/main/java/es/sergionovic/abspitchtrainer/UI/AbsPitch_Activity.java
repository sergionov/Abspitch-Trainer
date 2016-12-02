package es.sergionovic.abspitchtrainer.UI;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import es.sergionovic.abspitchtrainer.DB.DataBaseHandler;
import es.sergionovic.abspitchtrainer.DB.DataBaseHelper;
import es.sergionovic.abspitchtrainer.Model.User;
import es.sergionovic.abspitchtrainer.R;

import static es.sergionovic.abspitchtrainer.Util.Preferences.getUser;
import static es.sergionovic.abspitchtrainer.Util.Preferences.statisticNumber;
import static es.sergionovic.abspitchtrainer.Util.Preferences.updateStatisticNumber;


public class AbsPitch_Activity extends AppCompatActivity {

    DataBaseHelper DB;
    User user;
    static int exec_level;
    static int exec_level_save;
    static final String SAVE_LEVEL = "save_level";
    static final String EXERCISE_LEVEL = "eLevel";
    static final String PADDING_CHANGED = "padding";
    static Bundle State;

    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";

    private int note, random = -1, chances = 0, success = 0;

    private boolean first = true;
    private boolean nextNote = true;
    private static boolean successed = false;
    private boolean failed = false;
    private boolean repeat = false;
    private static boolean paddingchanged = false;
    private static String dialogNote;

    private Button btNext;
    private Button btRepeat;
    private TextView tvChances;
    private TextView tvSuccessDef;
    private TextView tvSuccessNote;
    private TextView tvFailedDef;
    private TextView tvFailedNote;
    private TextView tvNoteDef;

    static ImageButton[] notes = new ImageButton[60];

    private LinearLayout WhiteOctave2, WhiteOctave3, WhiteOctave4, WhiteOctave5, WhiteOctave6,
            BlackOctave2, BlackOctave3, BlackOctave4, BlackOctave5, BlackOctave6;
    private RelativeLayout BlackLayout;
    private LinearLayout WhiteLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abspitch);

        exec_level = getIntent().getIntExtra(EXERCISE_LEVEL, 0);

        saveToPreferences(this, KEY_USER_LEARNED_DRAWER, true + "");

        controls();

        generateUI();

        events();

    }

    private void generateUI() {
        switch (exec_level) {
            case 0:
                WhiteOctave2.setVisibility(View.GONE);
                WhiteOctave3.setVisibility(View.GONE);
                WhiteOctave4.setVisibility(View.VISIBLE);
                WhiteOctave5.setVisibility(View.GONE);
                WhiteOctave6.setVisibility(View.GONE);
                BlackOctave2.setVisibility(View.GONE);
                BlackOctave3.setVisibility(View.GONE);
                BlackOctave4.setVisibility(View.VISIBLE);
                BlackOctave5.setVisibility(View.GONE);
                BlackOctave6.setVisibility(View.GONE);

                BlackLayout.setPadding(-82, 0, 0, 0);
                break;
            case 1:
                WhiteOctave2.setVisibility(View.GONE);
                WhiteOctave3.setVisibility(View.VISIBLE);
                WhiteOctave4.setVisibility(View.VISIBLE);
                WhiteOctave5.setVisibility(View.GONE);
                WhiteOctave6.setVisibility(View.GONE);
                BlackOctave2.setVisibility(View.GONE);
                BlackOctave3.setVisibility(View.VISIBLE);
                BlackOctave4.setVisibility(View.VISIBLE);
                BlackOctave5.setVisibility(View.GONE);
                BlackOctave6.setVisibility(View.GONE);

                BlackLayout.setPadding(-82, 0, 0, 0);
                break;
            case 2:
                WhiteOctave2.setVisibility(View.GONE);
                WhiteOctave3.setVisibility(View.VISIBLE);
                WhiteOctave4.setVisibility(View.VISIBLE);
                WhiteOctave5.setVisibility(View.VISIBLE);
                WhiteOctave6.setVisibility(View.GONE);
                BlackOctave2.setVisibility(View.GONE);
                BlackOctave3.setVisibility(View.VISIBLE);
                BlackOctave4.setVisibility(View.VISIBLE);
                BlackOctave5.setVisibility(View.VISIBLE);
                BlackOctave6.setVisibility(View.GONE);

                BlackLayout.setPadding(-82, 0, 0, 0);
                break;
            case 3:
                WhiteOctave2.setVisibility(View.VISIBLE);
                WhiteOctave3.setVisibility(View.VISIBLE);
                WhiteOctave4.setVisibility(View.VISIBLE);
                WhiteOctave5.setVisibility(View.VISIBLE);
                WhiteOctave6.setVisibility(View.VISIBLE);
                BlackOctave2.setVisibility(View.VISIBLE);
                BlackOctave3.setVisibility(View.VISIBLE);
                BlackOctave4.setVisibility(View.VISIBLE);
                BlackOctave5.setVisibility(View.VISIBLE);
                BlackOctave6.setVisibility(View.VISIBLE);
                WhiteLayout.setPadding(0, 0, 0, 0);
                break;
            default:
                WhiteOctave2.setVisibility(View.VISIBLE);
                WhiteOctave3.setVisibility(View.VISIBLE);
                WhiteOctave4.setVisibility(View.VISIBLE);
                WhiteOctave5.setVisibility(View.VISIBLE);
                WhiteOctave6.setVisibility(View.VISIBLE);
                BlackOctave2.setVisibility(View.VISIBLE);
                BlackOctave3.setVisibility(View.VISIBLE);
                BlackOctave4.setVisibility(View.VISIBLE);
                BlackOctave5.setVisibility(View.VISIBLE);
                BlackOctave6.setVisibility(View.VISIBLE);
                WhiteLayout.setPadding(0, 0, 0, 0);
                break;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        exec_level = savedInstanceState.getInt(SAVE_LEVEL);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SAVE_LEVEL, getIntent().getIntExtra(EXERCISE_LEVEL, 0));
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        controls();
        generateUI();
        events();
    }

    private void events() {
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //onRestart();
                initializeKeyboard();
                initializeVariables();
                random = generateRandom();
                pressNote(random);
                updateUI();

            }
        });
        btRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeat = true;
                pressNote(random);
            }
        });

    }

    private void updateUI() {
        tvSuccessNote.setVisibility(View.INVISIBLE);
        tvSuccessDef.setVisibility(View.INVISIBLE);
        tvFailedDef.setVisibility(View.INVISIBLE);
        tvFailedNote.setVisibility(View.INVISIBLE);
        btRepeat.setEnabled(true);
        btRepeat.setBackgroundColor(getResources().getColor(R.color.white));
        btRepeat.setTextColor(getResources().getColor(R.color.colorPrimary));
        btRepeat.setAlpha(100);
        btNext.setText(getResources().getString(R.string.btNext));
        tvNoteDef.setText("");
    }

    private void initializeVariables() {
        chances = 3;
        success = 0;
        first = false;
        nextNote = true;
        successed = false;
        failed = false;
    }

    private void initializeKeyboard() {


        for (int i = 0; i < notes.length; i++) {
            ImageButton btNote = new ImageButton(this);
            notes[i] = btNote;
        }

        for (int i = 0; i < notes.length; i++) {
            String button = "btNote";
            int j = i + 1;
            note = getResources().getIdentifier(button + j,
                    "id", getPackageName());

            notes[i] = (ImageButton) findViewById(note);
            final int finalI = i;
            notes[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pressNote(finalI);
                }
            });
        }
    }

    private int generateRandom() {
        int tempRandom;
        Random rand = new Random();
        switch (exec_level) {
            case 0:
                tempRandom = rand.nextInt((35 - 23) + 1) + 23;
                break;
            case 1:
                tempRandom = rand.nextInt((35 - 11) + 1) + 11;
                break;
            case 2:
                tempRandom = rand.nextInt((47 - 11) + 1) + 11;
                break;
            case 3:
                tempRandom = (int) (Math.random() * 60);
                break;
            default:
                tempRandom = (int) (Math.random() * 60);
                break;
        }

        return tempRandom;
    }

    private void controls() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btRepeat = (Button) findViewById(R.id.btRepeat);
        btRepeat.setEnabled(false);
        btRepeat.setBackgroundColor(getResources().getColor(R.color.colorHighlight));
        btRepeat.setTextColor(getResources().getColor(R.color.colorPrimaryText));
        btRepeat.setAlpha(88);
        btNext = (Button) findViewById(R.id.btNext);
        tvChances = (TextView) findViewById(R.id.chancesNumber);
        tvFailedDef = (TextView) findViewById(R.id.failedDef);
        tvFailedNote = (TextView) findViewById(R.id.failedNote);
        tvSuccessDef = (TextView) findViewById(R.id.successDef);
        tvSuccessNote = (TextView) findViewById(R.id.successNote);
        tvNoteDef = (TextView) findViewById(R.id.noteDef);
        WhiteOctave2 = (LinearLayout) findViewById(R.id.wb_gr_1);
        WhiteOctave3 = (LinearLayout) findViewById(R.id.wb_gr_2);
        WhiteOctave4 = (LinearLayout) findViewById(R.id.wb_gr_3);
        WhiteOctave5 = (LinearLayout) findViewById(R.id.wb_gr_4);
        WhiteOctave6 = (LinearLayout) findViewById(R.id.wb_gr_5);
        BlackOctave2 = (LinearLayout) findViewById(R.id.bb_gr_1);
        BlackOctave3 = (LinearLayout) findViewById(R.id.bb_gr_2);
        BlackOctave4 = (LinearLayout) findViewById(R.id.bb_gr_3);
        BlackOctave5 = (LinearLayout) findViewById(R.id.bb_gr_4);
        BlackOctave6 = (LinearLayout) findViewById(R.id.bb_gr_5);

        BlackLayout = (RelativeLayout) findViewById(R.id.bb_layout);
        WhiteLayout = (LinearLayout) findViewById(R.id.wb_layout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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

    private void pressNote(int btId) {

        String[] notesList = getResources().getStringArray(R.array.notesList);
        String[] notesNames = getResources().getStringArray(R.array.notesNames);
        int note = getResources().getIdentifier(notesList[btId],
                "raw", getPackageName());

        if (chances > 0 && success != 2 && !first) {

            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), note);

            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.start();
        }

        if (successed || failed || repeat) {

        } else {

            if (!nextNote) {
                tvNoteDef.setText(notesNames[btId]);
                dialogNote = notesNames[random];
                if (btId == random && !successed) {
                    chances = chances - 1;
                    success = success + 1;
                } else if (btId != random && !failed) {
                    chances = chances - 1;
                }
            }


            nextNote = false;


            if (chances <= 0)
                tvChances.setText("0");
            else
                tvChances.setText(String.valueOf(chances));

            if (success == 2) {
                successed = true;
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogSucces dialogo = new DialogSucces();
                dialogo.show(fragmentManager, "tagSuccess");

                user = getUser(this);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                System.out.println(dateFormat.format(date));

                int no = statisticNumber(this) + 1;
                if (no == 0)
                    no = statisticNumber(this) + 1;

                DataBaseHandler.newStatistic(this, String.valueOf(no), user.getUser_id(), "0", dateFormat.format(date), true, "");
                updateStatisticNumber(this);
                //SQLiteDatabase db = DB.getWritableDatabase();


            } else if (chances == 0) {
                successed = false;
                failed = true;
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogSucces dialogo = new DialogSucces();
                dialogo.show(fragmentManager, "tagFail");
                user = getUser(this);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                System.out.println(dateFormat.format(date));

                int no = statisticNumber(this) + 1;
                if (no == 0)
                    no = statisticNumber(this) + 1;

                DataBaseHandler.newStatistic(this, String.valueOf(no), user.getUser_id(), "0", dateFormat.format(date), false, "");
                updateStatisticNumber(this);

            }
        }

        repeat = false;
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();

    }

    public static class DialogSucces extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {


            AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity());
            String result;

            if (!successed)
                result = getString(R.string.failTitle);
            else
                result = getString(R.string.successTitle);

            builder.setTitle(result)
                    .setMessage("La nota era: " + dialogNote)
                    .setNegativeButton("Continuar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            return builder.create();
        }
    }

}
