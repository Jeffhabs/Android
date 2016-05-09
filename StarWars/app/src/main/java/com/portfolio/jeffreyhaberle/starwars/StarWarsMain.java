package com.portfolio.jeffreyhaberle.starwars;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class StarWarsMain extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new StarWars_List_Frag();
    }
}
