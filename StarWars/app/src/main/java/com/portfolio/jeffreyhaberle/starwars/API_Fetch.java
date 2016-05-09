package com.portfolio.jeffreyhaberle.starwars;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffreyhaberle on 5/4/16.
 */
public class API_Fetch {

    private static final String TAG = "StarWarsFetchr";

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

    public List<StarWarsItem> fetchItems() {

        List<StarWarsItem> starWarsItems = new ArrayList<>();
        try {
            String url = Uri.parse("http://swapi.co/api/people/?format=json")
                    .buildUpon()
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Recieved JSON: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(starWarsItems, jsonBody);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON items", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        }

        return starWarsItems;
    }

    private void parseItems(List<StarWarsItem> items, JSONObject jsonBody)
        throws IOException, JSONException {

        JSONArray resultsArray = jsonBody.getJSONArray("results");

        for(int i = 0; i < resultsArray.length(); i++) {
            JSONObject resultObject = resultsArray.getJSONObject(i);

            StarWarsItem starWarsItem = new StarWarsItem();
            starWarsItem.setName(resultObject.getString("name"));
            starWarsItem.setGender(resultObject.getString("gender"));
            starWarsItem.setBday(resultObject.getString("birth_year"));
            starWarsItem.setHeight(resultObject.getString("height"));
            starWarsItem.setHair_color(resultObject.getString("hair_color"));
            starWarsItem.setSkin_color(resultObject.getString("skin_color"));
            starWarsItem.setEye_color(resultObject.getString("eye_color"));

            items.add(starWarsItem);
            System.out.println(items.get(i).getName());
        }

    }

}
