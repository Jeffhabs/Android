package com.portfolio.jeffreyhaberle.starwars;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffreyhaberle on 5/4/16.
 */
public class StarWars_List_Frag extends Fragment {

    private RecyclerView mRecyclerViewList;
    private List<StarWarsItem> mStarWarsItems = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_starwars_list, container, false);
        mRecyclerViewList = (RecyclerView) v
                .findViewById(R.id.fragment_starwars_list_recycler_view);
        mRecyclerViewList.setLayoutManager(new LinearLayoutManager(getActivity()));

        setUpAdapter();

        return v;
    }

    private void setUpAdapter() {
        if(isAdded()) {
            mRecyclerViewList.setAdapter(new SWAdapter(mStarWarsItems));
        }
    }

    private class SWHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private StarWarsItem mStarWarsItem;
        private TextView mName;
        private TextView mGender;
        private TextView mBday;

        public SWHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mName = (TextView)
                    itemView.findViewById(R.id.name_text_view);
            mGender = (TextView)
                    itemView.findViewById(R.id.gender_text_view);
            mBday = (TextView)
                    itemView.findViewById(R.id.bday_text_view);
        }


        public void onClick(View v) {
            //intents

            Intent intent = DetailActivity.newIntent(getActivity(), mStarWarsItem.getName(),
                    mStarWarsItem.getHair_color(), mStarWarsItem.getSkin_color(), mStarWarsItem.getEye_color());
            startActivity(intent);

        }

        public void bindSWItem(StarWarsItem item) {
            mStarWarsItem = item;

            mName.setText(item.getName());
            mGender.setText(item.getGender());
            mBday.setText(item.getBday());
        }
    }

    private class SWAdapter extends RecyclerView.Adapter<SWHolder> {

        private List<StarWarsItem> mStarWarsItems;

        public SWAdapter(List<StarWarsItem> starWarsItems) {
            mStarWarsItems = starWarsItems;
        }

        @Override
        public SWHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_starwars, viewGroup, false);
            return new SWHolder(view);
        }

        @Override
        public void onBindViewHolder(SWHolder swHolder, int position) {
            StarWarsItem starWarsItem = mStarWarsItems.get(position);
            swHolder.bindSWItem(starWarsItem);
        }

        @Override
        public int getItemCount() {
            return mStarWarsItems.size();
        }
    }

    private class FetchItemTask extends AsyncTask<Void, Void, List<StarWarsItem>> {
        @Override
        protected List<StarWarsItem> doInBackground(Void...params) {
            return new API_Fetch().fetchItems();
        }

        @Override
        protected void onPostExecute(List<StarWarsItem> items) {
            mStarWarsItems = items;
            setUpAdapter();
        }
    }


    //fetch items


}
