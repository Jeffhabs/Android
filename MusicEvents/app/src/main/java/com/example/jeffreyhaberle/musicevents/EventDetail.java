package com.example.jeffreyhaberle.musicevents;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.util.UUID;

/**
 * Created by jeffreyhaberle on 4/26/16.
 */
public class EventDetail extends SingleFragmentActivity {

    public static final String EXTRA_EVENT_ID = "com.example.jeffreyhaberle.musicevents.event_id";

    @Override
    public Fragment createFragment() {
        return EventDetailFragment.newInstance();
    }

    public static Intent newIntent(Context packageContext, UUID eventItem) {
        Intent intent = new Intent(packageContext, EventDetail.class);
        intent.putExtra(EXTRA_EVENT_ID, eventItem);
        return intent;
    }
}
