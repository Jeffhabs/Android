package com.example.jeffreyhaberle.musicevents;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jeffreyhaberle on 4/26/16.
 */
public class EventSingleton {
    private static EventSingleton sEventSingleton;

    private List<EventItem> mEventItems;

    public static EventSingleton get(Context context) {
        if(sEventSingleton == null) {
            sEventSingleton = new EventSingleton(context);
        }
        return sEventSingleton;
    }

    private EventSingleton(Context context) {
        mEventItems = new ArrayList<>();

        /*
        for (int i = 0; i < 100; i++) {
            EventItem eventItem = new EventItem();
            eventItem.setName("Artist #" + i );
            mEventItems.add(eventItem);

        }
        */
    }

    public List<EventItem> getEventItems() {
        return mEventItems;
    }

    public EventItem getEvent(UUID id) {
        for (EventItem eventItem : mEventItems) {
            if (eventItem.getID().equals(id)) {
                return eventItem;
            }
        }
        return null;
    }
}
