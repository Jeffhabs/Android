package com.portfolio.jeffreyhaberle.starwars;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jeffreyhaberle on 5/4/16.
 */
public class DetailFragment extends Fragment {

    private TextView mName;
    private TextView mHairColor;
    private TextView mSkinColor;
    private TextView mEyeColor;


    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup context,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sw_detail, context, false);

        mName = (TextView) v.findViewById(R.id.sw_name);
        mHairColor = (TextView) v.findViewById(R.id.sw_hair_color);
        mSkinColor = (TextView) v.findViewById(R.id.sw_skin_color);
        mEyeColor = (TextView) v.findViewById(R.id.sw_eye_color);

        String name = getActivity().getIntent().getStringExtra("name");
        mName.setText(name);
        String hairColor = getActivity().getIntent().getStringExtra("haircolor");
        mHairColor.setText(hairColor);
        String skinColor = getActivity().getIntent().getStringExtra(("skincolor"));
        mSkinColor.setText(skinColor);
        String eyecolor = getActivity().getIntent().getStringExtra(("eyecolor"));
        mEyeColor.setText(eyecolor);

        return v;

    }

}
