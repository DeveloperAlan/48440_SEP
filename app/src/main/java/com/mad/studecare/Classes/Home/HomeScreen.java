package com.mad.studecare.Classes.Home;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mad.studecare.Classes.Appointment.AppointmentScreen;
import com.mad.studecare.Models.Appointments.Appointments;
import com.mad.studecare.Models.Appointments.AppointmentsAdapter;
import com.mad.studecare.Models.Doctors.Doctors;
import com.mad.studecare.Models.Doctors.DoctorsSlideAdapter;
import com.mad.studecare.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreen extends AppCompatActivity implements HomeScreenContract.view {

    HomeScreenContract.presenter presenter;

    @BindView(R.id.home_appointments)
    RecyclerView mAppointments;
    @BindView(R.id.home_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.home_add)
    FloatingActionButton mAddButton;

    private AppointmentsAdapter mAppointmentsAdapter;
    private ArrayList<Appointments> mAppointmentsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        ButterKnife.bind(this);
        presenter = new HomePresenter(this);

        // Setting RecyclerView adapter
        mAppointmentsAdapter = new AppointmentsAdapter(mAppointmentsList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAppointments.setLayoutManager(mLayoutManager);
        mAppointments.setItemAnimator(new DefaultItemAnimator());
        mAppointments.setAdapter(mAppointmentsAdapter);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addAppointment();
            }
        });

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Welcome, PLACEHOLDER");

        presenter.prepareMovieData(mAppointmentsList, mAppointmentsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_settings: {
                // do your stuff
                break;
            }
            // case blocks for other MenuItems (if any)
        }
        return false;
    }

    @Override
    public void addAppointment() {
        Intent myIntent = new Intent(HomeScreen.this, AppointmentScreen.class);
        HomeScreen.this.startActivity(myIntent);
    }
}
