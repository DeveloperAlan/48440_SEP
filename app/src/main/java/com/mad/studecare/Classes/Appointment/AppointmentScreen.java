package com.mad.studecare.Classes.Appointment;

import android.app.*;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.mad.studecare.Models.Doctors.DoctorsSlideAdapter;
import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.Models.TimeSlots.TimeSlotsAdapter;
import com.mad.studecare.Models.TimeSlots.TimeSlotsList;
import com.mad.studecare.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentScreen extends AppCompatActivity implements AppointmentScreenContract.view, TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.appointments_timeslots)
    RecyclerView mTimeSlots;
    @BindView(R.id.appointments_doctors)
    RecyclerView mDoctors;
    @BindView(R.id.appointments_date)
    Button mDateButton;
    @BindView(R.id.appointments_time)
    Button mTimeButton;

    private ProgressDialog mProgressDialog;
    private DoctorsSlideAdapter mDoctorsSlideAdapter;

    AppointmentScreenContract.presenter presenter;
    TimeSlotsAdapter mTimeSlotsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_screen);
        ButterKnife.bind(this);

        presenter = new AppointmentScreenPresenter(this, getApplicationContext());
        mProgressDialog = new ProgressDialog(this);

        // RecyclerView
        mTimeSlotsAdapter = new TimeSlotsAdapter(TimeSlotsList.getInstance().getList(), this, presenter);
        RecyclerView.LayoutManager timeslotsLayoutManager = new LinearLayoutManager(getApplicationContext());
        mTimeSlots.setLayoutManager(timeslotsLayoutManager);
        mTimeSlots.setItemAnimator(new DefaultItemAnimator());
        mTimeSlots.setAdapter(mTimeSlotsAdapter);

        // ViewPager
        mDoctorsSlideAdapter = new DoctorsSlideAdapter(presenter.doctorsList(), this, presenter);
        mDoctorsSlideAdapter.notifyDataSetChanged();
        LinearLayoutManager doctorsLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mDoctors.setLayoutManager(doctorsLayoutManager);
        mDoctors.setAdapter(mDoctorsSlideAdapter);

        presenter.setDateButton(mDateButton);
        presenter.setTimeButton(mTimeButton);
        presenter.feedAdaptersList(mTimeSlotsAdapter, mDoctorsSlideAdapter);
    }

    @Override
    public void setDateText(Button date, String fmtOut) {
        date.setText(fmtOut);
    }

    @Override
    public void setDateDialog(DatePickerDialog dialog) {
        dialog.show(getFragmentManager(), getString(R.string.DatePickerDialog));
    }

    @Override
    public void setTimeDialog(Calendar calendar) {
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                AppointmentScreen.this,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false);
        timePickerDialog.show(getFragmentManager(), getString(R.string.TimePicker));
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        // Using SimpleDateFormat to format time, then change button text to it
        presenter.setTimeText(hourOfDay, minute, second);
    }

    @Override
    public void setTimeText(String text) {
        mTimeButton.setText(text);
    }

    @Override
    public void showConfirmDialog(TimeSlots timeSlots) {
        DialogPlus dialog = DialogPlus.newDialog(this)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {

                    }
                })
                .setExpanded(true)
                .create();
        dialog.show();
    }
}

