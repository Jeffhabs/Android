package com.example.jeffreyhaberle.musicevents;

import android.app.usage.UsageEvents;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by jeffreyhaberle on 4/26/16.
 */
public class EventDetailFragment extends Fragment {


    private EventItem mEventItem;
    private TextView mArtist;
    private TextView mDate;

    public static EventDetailFragment newInstance() {
        return new EventDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        UUID eventID = (UUID) getActivity().getIntent()
                .getSerializableExtra(EventDetail.EXTRA_EVENT_ID);
        mEventItem = EventSingleton.get(getActivity()).getEvent(eventID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup context,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_detail, context, false);

        mArtist = (TextView) v.findViewById(R.id.event_artist);
        //mArtist.setText(mEventItem.getName());

//
//        mDate = (TextView) v.findViewById(R.id.event_date_view);
//        mDate.setText(mEventItem.getDate());

        return v;
    }
}
