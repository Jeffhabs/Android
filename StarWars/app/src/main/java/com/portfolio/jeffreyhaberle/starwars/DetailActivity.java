package com.portfolio.jeffreyhaberle.starwars;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by jeffreyhaberle on 5/4/16.
 */
public class DetailActivity extends SingleFragmentActivity {



    @Override
    public Fragment createFragment() {
        return DetailFragment.newInstance();
    }

    public static Intent newIntent(Context packageContext, String name, String hair_color, String skin_color,
                                   String eye_color) {
        Intent intent = new Intent(packageContext, DetailActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("haircolor", hair_color);
        intent.putExtra("skincolor", skin_color);
        intent.putExtra("eyecolor", eye_color);
        return intent;
    }
}

