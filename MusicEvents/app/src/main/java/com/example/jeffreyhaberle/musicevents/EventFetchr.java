package com.example.jeffreyhaberle.musicevents;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffreyhaberle on 4/21/16.
 */
public class EventFetchr {

    private static final String TAG = "EventFetchr";
    private static final String API_KEY = "59btb7tn9wfd4khqcwkpag66";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                    ": with " +
                    urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0 ) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<EventItem> fetchItems() {


       List<EventItem> eventItems = new ArrayList<>();
        try {
            String url = Uri.parse("http://api.jambase.com/events")
                    .buildUpon()
                    .appendQueryParameter("zipcode", MusicEventsFragment.mZipCode)
                            .appendQueryParameter("page", "0")
                            .appendQueryParameter("api_key", API_KEY)
                            .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Recieved JSON: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(eventItems, jsonBody);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON items", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }

        return eventItems;
    }

    private void parseItems(List<EventItem> items, JSONObject jsonBody)
        throws IOException, JSONException {

        JSONArray eventJsonArray = jsonBody.getJSONArray("Events");
        Integer counter = 0;

        for (int i = 0; i < eventJsonArray.length(); i++ ) {
            JSONObject eventObject = eventJsonArray.getJSONObject(i);
            JSONArray artistJsonArray = eventObject.getJSONArray("Artists");
            counter++;

            for (int k = 0; k < artistJsonArray.length(); k++) {
                JSONObject numOfArtistsObject = artistJsonArray.getJSONObject(k);

                EventItem eventItem = new EventItem();
                eventItem.setDate(eventObject.getString("Date"));
                eventItem.setName(numOfArtistsObject.getString("Name"));
                items.add(eventItem);
            }
            //items.add(eventItem);
            //System.out.println(items.get(i).getName() + counter);
        }
    }
}
