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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mad.studecare.Classes.Appointment.AppointmentScreen;
import com.mad.studecare.Models.Appointments.Appointments;
import com.mad.studecare.Models.Appointments.AppointmentsAdapter;
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
    FirebaseAuth firebaseAuth;

    private AppointmentsAdapter mAppointmentsAdapter;
    private ArrayList<Appointments> mAppointmentsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        ButterKnife.bind(this);
        presenter = new HomePresenter(this);

        firebaseAuth = FirebaseAuth.getInstance();

        mAppointmentsAdapter = new AppointmentsAdapter(mAppointmentsList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAppointments.setLayoutManager(mLayoutManager);
        mAppointments.setItemAnimator(new DefaultItemAnimator());
        mAppointments.setAdapter(mAppointmentsAdapter);

        presenter.prepareMovieData(mAppointmentsList, mAppointmentsAdapter);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addAppointment();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            firebaseAuth.signOut();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addApointment() {
        Intent myIntent = new Intent(HomeScreen.this, AppointmentScreen.class);
        HomeScreen.this.startActivity(myIntent);
    }
}
