package es.sergionovic.abspitchtrainer.UI;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import es.sergionovic.abspitchtrainer.DB.DataBaseHandler;
import es.sergionovic.abspitchtrainer.DB.DataBaseHelper;
import es.sergionovic.abspitchtrainer.R;
import es.sergionovic.abspitchtrainer.Util.Preferences;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    TextView textView;

    int exitCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDB();

        controls();

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            ProfileFragment profileFragment = new ProfileFragment();
            fragmentTransaction.add(R.id.content_frame, profileFragment)
                    .commit();
        }

    }

    private void createDB() {
        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        DataBaseHelper DB = new DataBaseHelper(this, "AbsPitchDB", null, 1);
        SharedPreferences preferences = getSharedPreferences("AbsPitchPref", Context.MODE_PRIVATE);

        Preferences.setUser(getApplication());

        if (!preferences.getBoolean("create_DB", false)) {
            DataBaseHandler.initDB(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("create_DB", true);
            editor.apply();
        }

    }

    private void controls() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);

        else {
            exitCounter += 1;
            if (exitCounter == 1)
                Snackbar.make(findViewById(R.id.content_frame),
                        getString(R.string.exit_message), Snackbar.LENGTH_SHORT)
                        .show(); // Dont forget to show!

            if (exitCounter == 2) {
                finish();
                exitCounter = 0;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        Fragment fragment;
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        int verion = Build.VERSION.SDK_INT;
                        if (verion >= Build.VERSION_CODES.LOLLIPOP)
                            toolbar.setElevation(4);
                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_profile:
                                menuItem.setChecked(true);
                                setTitle(menuItem.getTitle());
                                fragment = new ProfileFragment();
                                fragmentTransaction.replace(R.id.content_frame, fragment)
                                        .commit();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                exitCounter = 0;
                                return true;
                            case R.id.item_navigation_drawer_exercises:
                                menuItem.setChecked(true);

                                if (verion >= Build.VERSION_CODES.LOLLIPOP)
                                    toolbar.setElevation(0);
                                fragment = new ExercisesFragment();
                                fragmentTransaction.replace(R.id.content_frame, fragment)
                                        .commit();
                                setTitle(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                exitCounter = 0;
                                return true;
                            case R.id.item_navigation_drawer_sent_marks:
                                menuItem.setChecked(true);
                                fragment = new MarksFragment();
                                fragmentTransaction.replace(R.id.content_frame, fragment)
                                        .commit();
                                setTitle(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                exitCounter = 0;
                                return true;
                            case R.id.item_navigation_drawer_theory:
                                menuItem.setChecked(true);
                                fragment = new TheoryFragment();
                                fragmentTransaction.replace(R.id.content_frame, fragment)
                                        .commit();
                                setTitle(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                exitCounter = 0;
                                return true;
                            case R.id.item_navigation_drawer_settings:
                                menuItem.setChecked(true);
                                Toast.makeText(MainActivity.this, "Launching " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                                startActivity(intent);
                                exitCounter = 0;
                                return true;
                            case R.id.item_navigation_drawer_help:
                                menuItem.setChecked(true);
                                fragment = new InfoFragment();
                                fragmentTransaction.replace(R.id.content_frame, fragment)
                                        .commit();
                                setTitle(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                exitCounter = 0;
                                return true;
                        }

                        return true;
                    }
                });
    }
}