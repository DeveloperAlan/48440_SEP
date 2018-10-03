package com.mad.studecare.Classes.Appointment;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.mad.studecare.Classes.Appointment.Information.AppointmentInformationScreen;
import com.mad.studecare.Classes.Home.HomeScreen;
import com.mad.studecare.Models.Doctors.DoctorsSlideAdapter;
import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.Models.TimeSlots.TimeSlotsAdapter;
import com.mad.studecare.Models.TimeSlots.TimeSlotsList;
import com.mad.studecare.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.Serializable;
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
    AppointmentScreenContract.presenter presenter;
    TimeSlotsAdapter mTimeSlotsAdapter;
    private ProgressDialog mProgressDialog;
    private DoctorsSlideAdapter mDoctorsSlideAdapter;
    private ArrayList<TimeSlots> mTimeList = new ArrayList<>();
    public static final String TIMESLOT_TIME = "time";
    public static final String TIMESLOT_DATE = "date";
    public static final String DOCTOR_NAME = "doctor";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_screen);
        ButterKnife.bind(this);

        presenter = new AppointmentScreenPresenter(this, getApplicationContext());
        mProgressDialog = new ProgressDialog(this);

        // RecyclerView
        mTimeSlotsAdapter = new TimeSlotsAdapter(mTimeList, this, presenter);
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
        System.out.println(timeSlots.getTime() + " " + timeSlots.getDate());
        final TimeSlots timeSlot = timeSlots;

        //AppointmentDialog appointmentDialog = new AppointmentDialog();
        //appointmentDialog.show(getFragmentManager(),"appointment dialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Appointment")
                .setMessage("")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startConfirmationScreen(timeSlot);
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

    public void startConfirmationScreen(TimeSlots timeSlot) {
        Intent intent = new Intent(this, AppointmentInformationScreen.class);
        intent.putExtra(DOCTOR_NAME, timeSlot.getDoctor().getName());
        intent.putExtra(TIMESLOT_TIME, timeSlot.getTime());
        intent.putExtra(TIMESLOT_DATE, timeSlot.getDate());
        startActivity(new Intent(this, AppointmentInformationScreen.class));
    }

    @Override
    public void updateList(ArrayList<TimeSlots> testList) {
        mTimeList.clear();
        for(TimeSlots slot : testList) {
            mTimeList.add(slot);
        }
    }
}

