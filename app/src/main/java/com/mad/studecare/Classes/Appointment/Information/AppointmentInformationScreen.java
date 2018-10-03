package com.mad.studecare.Classes.Appointment.Information;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mad.studecare.Classes.Appointment.AppointmentScreen;
import com.mad.studecare.Models.Appointments.Appointments;
import com.mad.studecare.Models.Appointments.AppointmentsAdapter;
import com.mad.studecare.Models.Appointments.AppointmentsList;
import com.mad.studecare.Models.Constants;
import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.Models.TimeSlots.TimeSlotsList;
import com.mad.studecare.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentInformationScreen extends AppCompatActivity implements AppointmentInformationContract.view {

    private TimeSlots mTimeSlots;
    private Appointments mHomeNavigated;
    private AppointmentsAdapter mAppointmentsAdapter;
    private ArrayList<TimeSlots> mTimeSlotsList = TimeSlotsList.getInstance().getList();

    @BindView(R.id.information_day)
    TextView mDay;
    @BindView(R.id.information_month)
    TextView mMonth;
    @BindView(R.id.information_day_text)
    TextView mDayText;
    @BindView(R.id.information_time)
    TextView mTime;
    @BindView(R.id.information_picture)
    ImageView mPicture;
    @BindView(R.id.information_doctor_name)
    TextView mName;
    @BindView(R.id.information_doctor_qualifications)
    TextView mQualifications;
    @BindView(R.id.information_doctor_specialties)
    TextView mSpecialties;
    @BindView(R.id.information_notes_edit)
    EditText mNotesEdit;
    @BindView(R.id.information_notes_read)
    TextView mNotesRead;
    @BindView(R.id.information_save)
    Button mSave;
    @BindView(R.id.information_edit)
    Button mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_information_screen);
        ButterKnife.bind(this);

        String doctorName = getIntent().getStringExtra(Constants.DOCTOR_NAME);
        String time = getIntent().getStringExtra(Constants.TIMESLOT_TIME);
        String date = getIntent().getStringExtra(Constants.TIMESLOT_DATE);

        // Grab the Timeslot with intent details.
        for(TimeSlots timeSlot : mTimeSlotsList) {
            if(timeSlot.getDoctor().getName().equals(doctorName) && timeSlot.getTime().equals(time) && timeSlot.getDate().equals(date)) {
                mTimeSlots = timeSlot;
            }
        }

        setVisibilities();
        setLabels();
    }

    public AppointmentInformationScreen(TimeSlots timeslot, Appointments homeNavigated, AppointmentsAdapter adapter) {
        this.mTimeSlots = timeslot;
        this.mHomeNavigated = homeNavigated;
        this.mAppointmentsAdapter = adapter;
    }

    public AppointmentInformationScreen() {

    }

    private void setLabels() {
        Date calendar = mTimeSlots.getDateTime();
        SimpleDateFormat day = new SimpleDateFormat("dd", Locale.US);
        SimpleDateFormat day_text = new SimpleDateFormat("EEEE", Locale.US);
        SimpleDateFormat month = new SimpleDateFormat("MMMM", Locale.US);

        mDay.setText(day.format(calendar));
        mMonth.setText(month.format(calendar));
        mDayText.setText(day_text.format(calendar));
        mTime.setText(mTimeSlots.getTime());
        mPicture.setImageResource(mTimeSlots.getDoctor().getPicture());
        mName.setText(mTimeSlots.getDoctor().getName());
        mQualifications.setText(mTimeSlots.getDoctor().getQualifications());
        mSpecialties.setText(mTimeSlots.getDoctor().getSpecialties());
    }

    private void setVisibilities() {
        // Navigated from TimeSlots.
        if (mHomeNavigated == null) {
            TimeSlotsVisibility();
        }
        // Navigated from HOME.
        else {
            HomeVisibility();
        }
    }

    private void TimeSlotsVisibility() {
        mEdit.setVisibility(View.INVISIBLE);
        mSave.setVisibility(View.VISIBLE);

        mNotesRead.setVisibility(View.INVISIBLE);
        mNotesEdit.setVisibility(View.VISIBLE);
    }

    private void HomeVisibility() {
        mEdit.setVisibility(View.VISIBLE);
        mSave.setVisibility(View.INVISIBLE);

        mNotesRead.setVisibility(View.VISIBLE);
        mNotesEdit.setVisibility(View.INVISIBLE);
    }

    public void save(View v) {
        // Navigated from TimeSlots. Create a new Appointment.
        if (mHomeNavigated == null) {
            Appointments newAppointments = new Appointments(mTimeSlots, mEdit.getText().toString());
            AppointmentsList.getInstance().addToList(newAppointments);
            finish();
        }
        // Navigated from HOME. Do not create a new Appointment. Edit the homeNavigated appointment
        else {

        }

    }

    public void edit(View v) {
        TimeSlotsVisibility();
    }
}
