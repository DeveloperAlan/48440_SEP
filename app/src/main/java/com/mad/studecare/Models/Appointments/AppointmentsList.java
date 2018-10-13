package com.mad.studecare.Models.Appointments;

import java.util.ArrayList;

/**
 * Created by trevorlao on 18/9/18.
 */

public class AppointmentsList {
    private static AppointmentsList singleton;
    private ArrayList<Appointments> mAppointmentsList;

    // Initialize singleton, if null, create a new instance.
    public static void InitInstance() {
        if (singleton == null) {
            singleton = new AppointmentsList();
        }
    }

    public static AppointmentsList GetInstance() {
        return singleton;
    }

    private AppointmentsList() {
    }

    public ArrayList<Appointments> GetList() {
        return mAppointmentsList;
    }

    public void SetList(ArrayList<Appointments> list) {
        mAppointmentsList = list;
    }

    public void RemoveItem(Appointments appointment) { mAppointmentsList.remove(appointment); }

    public void ClearList() { mAppointmentsList.clear(); }

    public void AddToList(Appointments appointment) { mAppointmentsList.add(appointment); }
}
