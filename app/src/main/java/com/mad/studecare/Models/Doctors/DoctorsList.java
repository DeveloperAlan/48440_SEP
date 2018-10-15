package com.mad.studecare.Models.Doctors;

import com.mad.studecare.Models.Appointments.Appointments;

import java.util.ArrayList;

/**
 * Singleton Class for transfering Doctor Samples. Remove when backend can do this.
 */

public class DoctorsList {
    private static DoctorsList singleton;
    private static ArrayList<Doctors> mDoctorsList;

    // Initialize singleton, if null, create a new instance.
    public static DoctorsList GetInstance() {
        if (singleton == null) {
            singleton = new DoctorsList();
            mDoctorsList = new ArrayList<>();
        } return singleton;
    }

    private DoctorsList() {
    }


    public void ClearList() { mDoctorsList.clear(); }

    public ArrayList<Doctors> getList() {
        return mDoctorsList;
    }

    public void SetList(ArrayList<Doctors> list) {
        mDoctorsList = list;
    }

    public void AddToList(Doctors doctor) { mDoctorsList.add(doctor); }
}

