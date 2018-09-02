package com.mad.studecare.Classes.Home;


import com.mad.studecare.Models.Appointments.AppointmentsAdapter;

import java.util.ArrayList;

/**
 * Created by trevorlao on 22/8/18.
 */

public interface HomeScreenContract {
    interface view {
        void addApointment();

    }

    interface presenter {
        void prepareMovieData(ArrayList appointmentsList, AppointmentsAdapter appointmentsAdapter);
        void addAppointment();

    }
}
