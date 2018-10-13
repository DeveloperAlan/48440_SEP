package com.mad.studecare.Models.TimeSlots;

import java.util.ArrayList;

/**
 * Created by trevorlao on 18/9/18.
 */

public class TimeSlotsList {
    private static TimeSlotsList singleton;
    private ArrayList<TimeSlots> mTimeSlotsList;

    // Initialize singleton, if null, create a new instance.
    public static void InitInstance() {
        if (singleton == null) {
            singleton = new TimeSlotsList();
        }
    }

    public static TimeSlotsList GetInstance() {
        return singleton;
    }

    private TimeSlotsList() {
    }

    public ArrayList<TimeSlots> GetList() {
        return mTimeSlotsList;
    }


    public void ClearList() { mTimeSlotsList.clear(); }

    public void SetList(ArrayList<TimeSlots> list) {
        mTimeSlotsList = list;
    }
}
