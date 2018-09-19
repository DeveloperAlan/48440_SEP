package com.mad.studecare.Models.TimeSlots;

import java.util.ArrayList;

/**
 * Created by trevorlao on 18/9/18.
 */

public class TimeSlotsList {
    private static TimeSlotsList singleton;
    private ArrayList<TimeSlots> mTimeSlotsList;

    // Initialize singleton, if null, create a new instance.
    public static void initInstance() {
        if (singleton == null) {
            singleton = new TimeSlotsList();
        }
    }

    public static TimeSlotsList getInstance() {
        return singleton;
    }

    private TimeSlotsList() {
    }

    public ArrayList<TimeSlots> getList() {
        return mTimeSlotsList;
    }

    public void setList(ArrayList<TimeSlots> list) {
        mTimeSlotsList = list;
    }
}
