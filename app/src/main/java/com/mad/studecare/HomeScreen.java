package com.mad.studecare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreen extends AppCompatActivity {

    HomeScreenContract.presenter presenter;

    @BindView(R.id.home_appointments)
    RecyclerView mAppointments;
    @BindView(R.id.home_toolbar)
    Toolbar mToolbar;

    private AppointmentsAdapter mAppointmentsAdapter;
    private ArrayList<Appointments> mAppointmentsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        ButterKnife.bind(this);
        presenter = new HomePresenter();

        mAppointmentsAdapter = new AppointmentsAdapter(mAppointmentsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAppointments.setLayoutManager(mLayoutManager);
        mAppointments.setItemAnimator(new DefaultItemAnimator());
        mAppointments.setAdapter(mAppointmentsAdapter);

        presenter.prepareMovieData(mAppointmentsList, mAppointmentsAdapter);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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

}
