package com.lin.timeline.example;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by owp on 2017/8/18.
 */

public class Trace<T> {

    public boolean isHead;

    public String name;
    @TraceType
    public String type;
    public T value;



    public final static String TEXT_TYPE = "text";
    public final static String MARKER_TYPE = "marker";
    public final static String TIME_TYPE = "time";
    public final static String IMG_TYPE = "img";
    public final static String BUTTON_TYPE = "button";
    public final static String RATING_TYPE = "rating";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            TEXT_TYPE,
            MARKER_TYPE,
            TIME_TYPE,
            IMG_TYPE,
            BUTTON_TYPE,
            RATING_TYPE
    })
    public @interface TraceType { }

}
