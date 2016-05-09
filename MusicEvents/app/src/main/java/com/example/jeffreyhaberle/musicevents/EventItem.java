package com.example.jeffreyhaberle.musicevents;

import java.util.Random;
import java.util.UUID;

/**
 * Created by jeffreyhaberle on 4/21/16.
 */
public class EventItem {

    public UUID mID;
    public String mUrl;
    public String mName;
    public String mDate;
    public String mZip;

    public Integer Randomizer() {
        Random randomGenerator = new Random();
        int randNum = randomGenerator.nextInt(3);

        return randNum;
    }

    public EventItem() {
        this(UUID.randomUUID());
    }
    public EventItem(UUID id) {
        mID = id;
    }

    public String toString() {
        return mName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public UUID getID() {
        return mID;
    }

    public void setID(UUID ID) {
        mID = ID;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getZip() {
        return mZip;
    }

    public void setZip(String zip) {
        mZip = zip;
    }
}
