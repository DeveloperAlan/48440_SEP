package com.mad.studecare.Classes.Appointment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mad.studecare.Classes.Home.HomeScreenContract;
import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.Models.TimeSlots.TimeSlotsAdapter;
import com.mad.studecare.R;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentScreen extends AppCompatActivity implements AppointmentScreenContract.view, TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.appointments_timeslots)
    RecyclerView mTimeSlots;
    @BindView(R.id.appointments_date)
    Button mDateButton;
    @BindView(R.id.appointments_time)
    Button mTimeButton;

    private ProgressDialog mProgressDialog;
    private ArrayList<TimeSlots> mTimeSlotsList = new ArrayList<>();

    AppointmentScreenContract.presenter presenter;
    TimeSlotsAdapter mTimeSlotsAdapter;

    final Calendar calendar = Calendar.getInstance();
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
    final int month = calendar.get(Calendar.MONTH);
    final int year = calendar.get(Calendar.YEAR);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_screen);
        ButterKnife.bind(this);

        presenter = new AppointmentScreenPresenter(this, getApplicationContext());
        mProgressDialog = new ProgressDialog(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // RecyclerView
        mTimeSlotsAdapter = new TimeSlotsAdapter(mTimeSlotsList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mTimeSlots.setLayoutManager(mLayoutManager);
        mTimeSlots.setItemAnimator(new DefaultItemAnimator());
        mTimeSlots.setAdapter(mTimeSlotsAdapter);

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final com.wdullaer.materialdatetimepicker.date.DatePickerDialog dialog = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        // Using SimpleDateFormat to format date, then change button text to it
                        try {
                            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                            Date date = fmt.parse(getResources().getString(R.string.time_concatenate_date,
                                    dayOfMonth,
                                    monthOfYear,
                                    year));
                            SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

                            mDateButton.setText(fmtOut.format(date));
                        } catch (final ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, year, month, day);
                dialog.show(getFragmentManager(), getString(R.string.DatePickerDialog));
            }
        });

        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(AppointmentScreen.this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show(getFragmentManager(), getString(R.string.TimePicker));
            }
        });

        presenter.populateSample(mTimeSlotsList, mTimeSlotsAdapter);
    }

    @Override
    public void scheduleSuccessfullyCreated() {
        mProgressDialog.dismiss();
//        Intent intent = new Intent(AppointmentScreen.this, GroupActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra(getString(R.string.uid), mGroupUid);
//        intent.putExtra(getString(R.string.name), mGroupName);
//        startActivity(intent);
    }

    @Override
    public void emptyDetails() {
        mProgressDialog.dismiss();
//        Toast.makeText(getApplicationContext(), getString(R.string.emptyFields_prompt), Toast.LENGTH_LONG).show();
    }

    @Override
    public void error(String error) {
        mProgressDialog.dismiss();
//        Toast.makeText(getApplicationContext(), getString(R.string.error_prompt) + error, Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        // Using SimpleDateFormat to format time, then change button text to it
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("hh:mm", Locale.US);
            Date date = fmt.parse(getResources().getString(R.string.time_concatenate_time,
                    hourOfDay,
                    minute));
            SimpleDateFormat fmtOut = new SimpleDateFormat("K:mm a", Locale.US);

            mTimeButton.setText(fmtOut.format(date));
        } catch (final ParseException e) {
            e.printStackTrace();
        }
    }
}

