package com.example.jeffreyhaberle.draganddraw;

import android.graphics.PointF;

/**
 * Created by jeffreyhaberle on 4/26/16.
 */
public class Box {
    private PointF mOrigin;
    private PointF mCurrent;
    public Box(PointF origin) {
        mOrigin = origin;
        mCurrent = origin;
    }
    public PointF getCurrent() {
        return mCurrent;
    }
    public void setCurrent(PointF current) {
        mCurrent = current;
    }
    public PointF getOrigin() {
        return mOrigin;
    }
}
