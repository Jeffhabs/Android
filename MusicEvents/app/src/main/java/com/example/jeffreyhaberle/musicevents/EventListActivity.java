package com.example.jeffreyhaberle.musicevents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.widget.TextView;

/**
 * Created by jeffreyhaberle on 4/21/16.
 */
public class EventListActivity extends SingleFragmentActivity{

    @Override
    public Fragment createFragment() {
        return new EventListFragment();
    }
}
