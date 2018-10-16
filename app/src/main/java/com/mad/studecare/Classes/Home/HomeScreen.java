package com.mad.studecare.Classes.Home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mad.studecare.Classes.Appointment.AppointmentScreen;
import com.mad.studecare.Classes.Appointment.Information.AppointmentInformationScreen;
import com.mad.studecare.Classes.Login.LoginScreen;
import com.mad.studecare.Models.Appointments.Appointments;
import com.mad.studecare.Models.Appointments.AppointmentsAdapter;
import com.mad.studecare.Models.Appointments.AppointmentsList;
import com.mad.studecare.Models.Constants;
import com.mad.studecare.Models.Doctors.DoctorsList;
import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.Models.TimeSlots.TimeSlotsList;
import com.mad.studecare.Models.Users;
import com.mad.studecare.R;
import com.mad.studecare.Utils.SortAppt;

import java.util.ArrayList;
import java.util.Collections;

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
    @BindView(R.id.user_name)
    TextView nameTv;

    private ArrayList<Appointments> test = new ArrayList<>();
    private AppointmentsAdapter mAppointmentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        ButterKnife.bind(this);
        presenter = new HomePresenter(this);

        // Populate Date data
        test = AppointmentsList.GetInstance().GetList();
        presenter.populateDate();
        SortAppt sort = new SortAppt();
        Collections.sort(test, sort);


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
        nameTv.setText("Welcome, " + Users.getInstance().getName() + "!");
    }

    @Override
    public void editAppointment(Appointments appointment) {
        Intent intent = new Intent(this, AppointmentInformationScreen.class);
        TimeSlots timeSlot = null;
        for(TimeSlots slot : TimeSlotsList.GetInstance().GetList()) {
            if (appointment.getTimeslot().equals(slot.getId())) {
                timeSlot = slot;
            }
        }
        intent.putExtra(Constants.DOCTOR_NAME, timeSlot.getDoctor().getName());
        intent.putExtra(Constants.TIMESLOT_TIME, timeSlot.getTime());
        intent.putExtra(Constants.TIMESLOT_DATE, timeSlot.getDate());
        intent.putExtra(Constants.FROM_HOME, true);
        intent.putExtra(Constants.APPOINTMENT_NOTE, appointment.getNotes());
        startActivity(intent);
    }

    public void logout(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to logout?")
                .setMessage("")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        logoutNow();
                        finish();
                    }
                }) .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .create()
                .show();
    }

    private void logoutNow() {
        Users.getInstance().setEmail("");
        Users.getInstance().setName("");
        Users.getInstance().setUserId("");
        Users.getInstance().setAccessToken("");

        AppointmentsList.GetInstance().ClearList();
        TimeSlotsList.GetInstance().ClearList();
        DoctorsList.GetInstance().ClearList();

        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
        finish();
    }
}
