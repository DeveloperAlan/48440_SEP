package com.mad.studecare.Classes.Home;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mad.studecare.Classes.Appointment.AppointmentScreen;
import com.mad.studecare.Models.Appointments.Appointments;
import com.mad.studecare.Models.Appointments.AppointmentsAdapter;
import com.mad.studecare.Models.Appointments.AppointmentsList;
import com.mad.studecare.Models.Doctors.Doctors;
import com.mad.studecare.Models.Doctors.DoctorsSlideAdapter;
import com.mad.studecare.R;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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
        mAppointmentsAdapter = new AppointmentsAdapter(AppointmentsList.getInstance().getList(), this);
//        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mAppointments.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAppointments.setItemAnimator(new DefaultItemAnimator());
        mAppointments.setAdapter(mAppointmentsAdapter);
    }


    public void addAppointment(View v) {
        Intent myIntent = new Intent(HomeScreen.this, AppointmentScreen.class);
        HomeScreen.this.startActivity(myIntent);
    }

    @Override
    public void setDates(String day, String dayText, String month) {
        dayTv.setText(day);
        dayTextTv.setText(dayText);
        monthTv.setText(month);
    }
}
