package com.mad.studecare.Classes.Home;


import android.content.Context;
import android.widget.TextView;

import com.mad.studecare.Models.Appointments.Appointments;
import com.mad.studecare.Models.Appointments.AppointmentsAdapter;
import com.mad.studecare.Models.Doctors.DoctorsSlideAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by trevorlao on 22/8/18.
 */

public interface HomeScreenContract {
    interface view {

        void setDates(String day, String dayText, String month);

        void editAppointment(Appointments appointment);
    }

    interface presenter {

        void populateDate();

        void editAppointment(Appointments appointment);
    }
}
