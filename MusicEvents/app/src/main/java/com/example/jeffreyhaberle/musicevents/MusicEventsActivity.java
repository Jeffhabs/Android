package com.example.jeffreyhaberle.musicevents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MusicEventsActivity extends SingleFragmentActivity {


    /*
    public static final String ZIP_CODE = "com.example.jeffreyhaberle.musicevents.zip_code";

    public static Intent newIntent(Context packageContext, String zipCode) {
        Intent i = new Intent(packageContext, MusicEventsActivity.class);
        i.putExtra(ZIP_CODE, zipCode);
        return i;
    }
    */

    @Override
    public Fragment createFragment() {
        /*
        String eventZip = (String) getIntent()
                .getStringExtra(ZIP_CODE);
        return MusicEventsFragment.newInstance(eventZip);
        */
        return MusicEventsFragment.newInstance();
    }

}
