package com.mad.studecare.Classes.Home;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mad.studecare.Classes.Appointment.AppointmentScreen;
import com.mad.studecare.Classes.Appointment.Information.AppointmentInformationScreen;
import com.mad.studecare.Models.Appointments.Appointments;
import com.mad.studecare.Models.Appointments.AppointmentsAdapter;
import com.mad.studecare.Models.Appointments.AppointmentsList;
import com.mad.studecare.Models.Constants;
import com.mad.studecare.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreen extends AppCompatActivity implements HomeScreenContract.view {

    HomeScreenContract.presenter presenter;

    @BindView(R.id.home_appointments)
    RecyclerView mAppointments;
    @BindView(R.id.home_add)
    FloatingActionButton mAddButton;
    @BindView(R.id.home_day)
    TextView dayTv;
    @BindView(R.id.home_day_text)
    TextView dayTextTv;
    @BindView(R.id.home_month)
    TextView monthTv;

    private ArrayList<Appointments> test = new ArrayList<>();
    private AppointmentsAdapter mAppointmentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        ButterKnife.bind(this);
        presenter = new HomePresenter(this);

        // Populate Date data
        presenter.populateDate();


        // Setting RecyclerView adapter
        mAppointmentsAdapter = new AppointmentsAdapter(test, this, presenter);
//        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mAppointments.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAppointments.setItemAnimator(new DefaultItemAnimator());
        mAppointments.setAdapter(mAppointmentsAdapter);
    }

    public void addAppointment(View v) {
        Intent myIntent = new Intent(HomeScreen.this, AppointmentScreen.class);
        HomeScreen.this.startActivityForResult(myIntent, 1);
    }


    @Override
    public void setDates(String day, String dayText, String month) {
        dayTv.setText(day);
        dayTextTv.setText(dayText);
        monthTv.setText(month);
    }

    @Override
    public void editAppointment(Appointments appointment) {
        Intent intent = new Intent(this, AppointmentInformationScreen.class);
        intent.putExtra(Constants.DOCTOR_NAME, appointment.getTimeslot().getDoctor().getName());
        intent.putExtra(Constants.TIMESLOT_TIME, appointment.getTimeslot().getTime());
        intent.putExtra(Constants.TIMESLOT_DATE, appointment.getTimeslot().getDate());
        intent.putExtra(Constants.FROM_HOME, true);
        intent.putExtra(Constants.APPOINTMENT_NOTE, appointment.getNotes());
        startActivity(intent);
    }
}
