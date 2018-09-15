package com.mad.studecare.Classes.Home;


import android.content.Context;

import com.mad.studecare.Models.Appointments.AppointmentsAdapter;
import com.mad.studecare.Models.Doctors.DoctorsSlideAdapter;

import java.util.ArrayList;

/**
 * Created by trevorlao on 22/8/18.
 */

public interface HomeScreenContract {
    interface view {
        void addAppointment();

    }

    interface presenter {
        void prepareMovieData(ArrayList appointmentsList, AppointmentsAdapter appointmentsAdapter);

        void addAppointment();

    }
}
