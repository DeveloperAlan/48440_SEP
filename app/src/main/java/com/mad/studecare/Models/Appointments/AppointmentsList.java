package com.mad.studecare.Models.Appointments;

import java.util.ArrayList;

/**
 * Created by trevorlao on 18/9/18.
 */

public class AppointmentsList {
    private static AppointmentsList singleton;
    private ArrayList<Appointments> mAppointmentsList;

    // Initialize singleton, if null, create a new instance.
    public static void initInstance() {
        if (singleton == null) {
            singleton = new AppointmentsList();
        }
    }

    public static AppointmentsList getInstance() {
        return singleton;
    }

    private AppointmentsList() {
    }

    public ArrayList<Appointments> getList() {
        return mAppointmentsList;
    }

    public void setList(ArrayList<Appointments> list) {
        mAppointmentsList = list;
    }

    public void addToList(Appointments appointment) { mAppointmentsList.add(appointment); }
}
