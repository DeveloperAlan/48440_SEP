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

    private Boolean mFromHome;
    private String IntDate;
    private String IntDoctor;
    private String IntTime;
    private String IntNotes;
    private ArrayList<TimeSlots> mTimeSlotsList = TimeSlotsList.getInstance().getList();
    private ArrayList<Appointments> mAppointments = AppointmentsList.getInstance().getList();
    private TimeSlots mTimeSlot;
    private Appointments mAppointment;

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
    @BindView(R.id.information_save)
    Button mSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_information_screen);
        ButterKnife.bind(this);

        IntDoctor = getIntent().getStringExtra(Constants.DOCTOR_NAME);
        IntTime = getIntent().getStringExtra(Constants.TIMESLOT_TIME);
        IntDate = getIntent().getStringExtra(Constants.TIMESLOT_DATE);
        IntNotes = getIntent().getStringExtra(Constants.APPOINTMENT_NOTE);
        mFromHome = getIntent().getExtras().getBoolean(Constants.FROM_HOME);

        // Grab the Timeslot with intent details.
        for (TimeSlots timeSlot : mTimeSlotsList) {
            if(timeSlot.getDoctor().getName().equals(IntDoctor)
                    && timeSlot.getTime().equals(IntTime)
                    && timeSlot.getDate().equals(IntDate)) {
                mTimeSlot = timeSlot;
            }
        }

        // Grab the appointment, if there is one.
        for (Appointments appointment : mAppointments) {
            if (appointment.getTimeslot().equals(mTimeSlot)) {
                mAppointment = appointment;
            }
        }

        setLabels();
    }

    public AppointmentInformationScreen() {
    }

    private void setLabels() {
        Date calendar = mTimeSlot.getDateTime();
        SimpleDateFormat day = new SimpleDateFormat("dd", Locale.US);
        SimpleDateFormat day_text = new SimpleDateFormat("EEEE", Locale.US);
        SimpleDateFormat month = new SimpleDateFormat("MMMM", Locale.US);

        mNotesEdit.setText(IntNotes);
        mDay.setText(day.format(calendar));
        mMonth.setText(month.format(calendar));
        mDayText.setText(day_text.format(calendar));
        mTime.setText(mTimeSlot.getTime());
        mPicture.setImageResource(mTimeSlot.getDoctor().getPicture());
        mName.setText(mTimeSlot.getDoctor().getName());
        mQualifications.setText(mTimeSlot.getDoctor().getQualifications());
        mSpecialties.setText(mTimeSlot.getDoctor().getSpecialties());
    }

    public void save(View v) {
        // Navigated from TimeSlots. Create a new Appointment.
        if (mFromHome) {
            mAppointment.setNotes(mNotesEdit.getText().toString());
            finish();
        }
        // Navigated from HOME. Do not create a new Appointment. Edit the homeNavigated appointment
        else {
            Appointments newAppointments = new Appointments(mTimeSlot, mNotesEdit.getText().toString());
            AppointmentsList.getInstance().addToList(newAppointments);
            finish();
        }
    }

    public void delete(View v) {

    }
}
