package com.mad.studecare.Classes.Appointment.Information;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mad.studecare.R;

public class AppointmentInformationScreen extends AppCompatActivity implements AppointmentInformationContract.view {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_information_screen);
    }

}
