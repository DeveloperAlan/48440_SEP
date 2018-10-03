package com.mad.studecare.Classes.Home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mad.studecare.Classes.Appointment.Information.AppointmentInformationScreen;
import com.mad.studecare.Models.Appointments.Appointments;
import com.mad.studecare.Models.Appointments.AppointmentsAdapter;
import com.mad.studecare.Models.Constants;
import com.mad.studecare.Models.Doctors.Doctors;
import com.mad.studecare.Models.Doctors.DoctorsSlideAdapter;
import com.mad.studecare.R;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by trevorlao on 22/8/18.
 */

public class HomePresenter implements HomeScreenContract.presenter {

    private HomeScreenContract.view mView;

    public HomePresenter(HomeScreenContract.view view) {
        this.mView = view;
    }

    @Override
    public void populateDate() {
        Date calendar = Calendar.getInstance().getTime();
        SimpleDateFormat day = new SimpleDateFormat("dd", Locale.US);
        SimpleDateFormat day_text = new SimpleDateFormat("EEEE", Locale.US);
        SimpleDateFormat month = new SimpleDateFormat("MMMM", Locale.US);

        mView.setDates(day.format(calendar), day_text.format(calendar), month.format(calendar));
    }

    @Override
    public void editAppointment(Appointments appointment) {
        mView.editAppointment(appointment);
    }
}
