package com.mad.studecare.Classes.Home;

import com.mad.studecare.Models.Appointments.Appointments;
import com.mad.studecare.Models.Appointments.AppointmentsAdapter;

import java.util.ArrayList;

/**
 * Created by trevorlao on 22/8/18.
 */

public class HomePresenter implements HomeScreenContract.presenter {

    private HomeScreenContract.view mView;

    public HomePresenter(HomeScreenContract.view view) {
        this.mView = view;
    }

    @Override
    public void addAppointment() {
        mView.addApointment();
    }

    @Override
    public void prepareMovieData(ArrayList appointmentsList, AppointmentsAdapter adapter) {
        Appointments appointments = new Appointments("Mad Max: Fury Road", "Action & Adventure", "2015");
        appointmentsList.add(appointments);

        appointments = new Appointments("Inside Out", "Animation, Kids & Family", "2015");
        appointmentsList.add(appointments);

        appointments = new Appointments("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        appointmentsList.add(appointments);

        appointments = new Appointments("Shaun the Sheep", "Animation", "2015");
        appointmentsList.add(appointments);

        appointments = new Appointments("The Martian", "Science Fiction & Fantasy", "2015");
        appointmentsList.add(appointments);

        appointments = new Appointments("Mission: Impossible Rogue Nation", "Action", "2015");
        appointmentsList.add(appointments);

        appointments = new Appointments("Up", "Animation", "2009");
        appointmentsList.add(appointments);

        appointments = new Appointments("Star Trek", "Science Fiction", "2009");
        appointmentsList.add(appointments);

        appointments = new Appointments("The LEGO Appointments", "Animation", "2014");
        appointmentsList.add(appointments);

        adapter.notifyDataSetChanged();
    }
}
