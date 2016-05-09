package com.example.jeffreyhaberle.musicevents;

import android.app.usage.UsageEvents;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jeffreyhaberle on 4/21/16.
 */
public class EventListFragment extends Fragment {

    private static final String TAG = "EventListFragment";
    private static final String ARG_EVENT_ARTIST = "event_artist";


    private RecyclerView mEventRecyclerView;
    private List<EventItem> mEventItems = new ArrayList<>();
    private EventAdapter mEventAdapter;

    private EventItem mEventItem;

    public static String mEventZip;


    public static EventListFragment newInstance() {

        return new EventListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemsTask().execute();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_list, container, false);
        mEventRecyclerView= (RecyclerView) v
                .findViewById(R.id.fragment_event_list_recycler_view);
        mEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        //setupAdapter();
        return v;
    }

    private void updateUI() {
        EventSingleton eventSingleton = EventSingleton.get(getActivity());
        List<EventItem> events = eventSingleton.getEventItems();

        mEventAdapter = new EventAdapter(events);
        mEventRecyclerView.setAdapter(mEventAdapter);

    }

    private void setupAdapter() {
        if (isAdded()) {
            mEventRecyclerView.setAdapter(new EventAdapter(mEventItems));
        }
    }

    private class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private EventItem  mEventItem;

        private TextView mEventDateView;
        private RatingBar mRatingBar;
        private TextView mArtistTextView;

        public EventHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mArtistTextView = (TextView)
                    itemView.findViewById(R.id.artist_text_view);
            mEventDateView = (TextView)
                    itemView.findViewById(R.id.event_date_view);
            mRatingBar = (RatingBar)
                    itemView.findViewById(R.id.rating_bar);
        }

        public void onClick(View v) {
            /*
            Toast.makeText(getActivity(),
                    mEventItem.getName() + " artist clicked!", Toast.LENGTH_SHORT)
                    .show();
                    */

            //Intent intent = new Intent(getActivity(), EventDetail.class);
            //startActivity(intent);

            Intent intent = EventDetail.newIntent(getActivity(), mEventItem.getID());
            startActivity(intent);
        }

        public void bindEventItem(EventItem item) {
            mEventItem = item;

            mArtistTextView.setText(item.getName());
            mEventDateView.setText(item.getDate());
            mRatingBar.setNumStars(4);
        }
    }

    private class EventAdapter extends RecyclerView.Adapter<EventHolder> {

        private List<EventItem> mEventItems;

        public EventAdapter(List<EventItem> eventItems) {
            mEventItems = eventItems;
        }

        @Override
        public EventHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_event, viewGroup, false);
            return new EventHolder(view);
        }

        @Override
        public void onBindViewHolder(EventHolder eventHolder, int position) {
            EventItem eventItem = mEventItems.get(position);
            eventHolder.bindEventItem(eventItem);
        }

        @Override
        public int getItemCount() {
            return mEventItems.size();
        }
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, List<EventItem>> {
        @Override
        protected List<EventItem> doInBackground(Void...params) {
            return new EventFetchr().fetchItems();
        }

        @Override
        protected void onPostExecute(List<EventItem> items) {
            mEventItems = items;
            setupAdapter();
        }
    }

}
