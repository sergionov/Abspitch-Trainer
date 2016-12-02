package es.sergionovic.abspitchtrainer.UI;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import es.sergionovic.abspitchtrainer.R;

public class Theory_Activity extends AppCompatActivity {

    private String SUBTITLE_CARD = "cardSubtitle";
    private String BODY_CARD = "cardBody";
    private String TITLE_CARD = "cardTitle";

    Toolbar toolbar;
    TextView tvTitle, tvSubtitle, tvBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory);

        controls();

    }

    private void controls() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        if(collapsingToolbar != null){
            collapsingToolbar.setTitle(getIntent().getStringExtra(TITLE_CARD));

            collapsingToolbar.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
            collapsingToolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorPrimary));

        }

        tvTitle = (TextView) findViewById(R.id.theory_title);
        tvSubtitle = (TextView) findViewById(R.id.theory_subtitle);
        tvBody = (TextView) findViewById(R.id.theory_body);

        tvTitle.setText(getIntent().getStringExtra(TITLE_CARD));
        tvSubtitle.setText(getIntent().getStringExtra(SUBTITLE_CARD));
        tvBody.setText(getIntent().getStringExtra(BODY_CARD));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

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
}
