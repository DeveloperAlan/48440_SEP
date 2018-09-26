package com.mad.studecare.Classes.Appointment.Information;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.R;

import butterknife.ButterKnife;

public class AppointmentInformationScreen extends AppCompatActivity implements AppointmentInformationContract.view {

    private TimeSlots mTimeSlots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_information_screen);
        ButterKnife.bind(this);
    }

    public AppointmentInformationScreen(TimeSlots timeslot) {
        this.mTimeSlots = timeslot;
    }

    private void setLabels() {

    }

}
