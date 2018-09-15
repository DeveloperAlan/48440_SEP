package com.mad.studecare.Models.Doctors;

import java.util.ArrayList;

/**
 * Singleton Class for transfering Doctor Samples. Remove when backend can do this.
 */

public class DoctorsList {
    private static DoctorsList singleton;
    private ArrayList<Doctors> mDoctorsList;

    // Initialize singleton, if null, create a new instance.
    public static void initInstance() {
        if (singleton == null) {
            singleton = new DoctorsList();
        }
    }

    public static DoctorsList getInstance() {
        return singleton;
    }

    private DoctorsList() {
    }

    public ArrayList<Doctors> getList() {
        return mDoctorsList;
    }

    public void setList(ArrayList<Doctors> list) {
        mDoctorsList = list;
    }
}

