package com.example.jeffreyhaberle.musicevents;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jeffreyhaberle on 4/25/16.
 */
public class MusicEventsFragment extends Fragment {

    private static final String ARG_EVENT_ZIP = "zip_code";

    private EventItem mEventItem;
    private EditText mZipText;
    public static String mZipCode; //global passed to EventFetchr.fetchItem(zipCode)
    private Button mEnterZipButton;


    public static MusicEventsFragment newInstance() {
        return new MusicEventsFragment();
        /*
        Bundle args = new Bundle();
        args.putString(ARG_EVENT_ZIP, zipCode);

        MusicEventsFragment musicEventsFragment = new MusicEventsFragment();
        musicEventsFragment.setArguments(args);
        return musicEventsFragment;
        */
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        String zipcode = (String) getActivity().getIntent()
                .getStringExtra(MusicEventsActivity.ZIP_CODE);
                */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);

        mZipText = (EditText) v.findViewById(R.id.enter_zip);
        mEnterZipButton = (Button) v.findViewById(R.id.enter_zip_button);
        mEnterZipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mZipText.getText().toString().length() != 0) {
                    mZipCode = String.valueOf(mZipText.getText());
                }
                Intent i = new Intent(getActivity(), EventListActivity.class);
                startActivity(i);
            }
        });
        return v;
    }

}
