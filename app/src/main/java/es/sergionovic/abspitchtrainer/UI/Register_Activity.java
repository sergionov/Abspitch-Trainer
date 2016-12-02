package es.sergionovic.abspitchtrainer.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import es.sergionovic.abspitchtrainer.R;
import es.sergionovic.abspitchtrainer.Util.Preferences;

public class Register_Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btNext;
    private static EditText etName, etAge;
    private static TextInputLayout tiName, tiAge;

    private static String NAME_EXTRA = "NAME_EXTRA", AGE_EXTRA = "AGE_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (Preferences.getUserCreated(this)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        } else {
            controls();

            events();
        }

    }

    private void events() {
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    Intent intent = new Intent(v.getContext(), RegisterPhoto_Activity.class);
                    intent.putExtra(NAME_EXTRA, etName.getText().toString());
                    intent.putExtra(AGE_EXTRA, etAge.getText().toString());
                    startActivity(intent);
                }

            }
        });
    }

    private boolean checkData() {
        boolean retur = false;

        String name, age;
        name = etName.getText().toString();
        age = etAge.getText().toString();

        if (!name.isEmpty() && !age.isEmpty()) {
            retur = true;
            tiAge.setErrorEnabled(false);
            tiName.setErrorEnabled(false);
        } else if (age.isEmpty() && !name.isEmpty()) {
            retur = false;
            tiAge.setErrorEnabled(true);
            tiName.setErrorEnabled(false);
            tiAge.setError("Introduce tu edad");
        } else if (!age.isEmpty() && name.isEmpty()) {
            retur = false;
            tiAge.setErrorEnabled(false);
            tiName.setErrorEnabled(true);
            tiName.setError("Introduce tu nombre");
        } else if (age.isEmpty() && name.isEmpty()) {
            retur = false;
            Snackbar.make(findViewById(R.id.register_main_content),
                    "Por favor, rellena los datos", Snackbar.LENGTH_SHORT)
                    .show(); // Dont forget to show!
            tiAge.setErrorEnabled(false);
            tiName.setErrorEnabled(false);
        }

        return retur;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    private void controls() {

        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        btNext = (Button) findViewById(R.id.btRegister_Next);
        etAge = (EditText) findViewById(R.id.etRegister_Age);
        etName = (EditText) findViewById(R.id.etRegister_Name);

        tiAge = (TextInputLayout) findViewById(R.id.textinput_age);
        tiName = (TextInputLayout) findViewById(R.id.textinput_name);

    }
}
