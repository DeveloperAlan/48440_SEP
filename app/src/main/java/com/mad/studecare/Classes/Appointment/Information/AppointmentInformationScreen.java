package com.mad.studecare.Classes.Appointment.Information;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mad.studecare.Classes.Home.HomeScreen;
import com.mad.studecare.Models.API;
import com.mad.studecare.Models.Appointments.Appointments;
import com.mad.studecare.Models.Appointments.AppointmentsList;
import com.mad.studecare.Models.Constants;
import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.Models.TimeSlots.TimeSlotsList;
import com.mad.studecare.Models.Users;
import com.mad.studecare.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentInformationScreen extends AppCompatActivity implements AppointmentInformationContract.view {

    private Boolean mFromHome;
    private String IntDate;
    private String IntDoctor;
    private String IntTime;
    private String IntNotes;
    private String mTimeSlotId;
    private ArrayList<TimeSlots> mTimeSlotsList = TimeSlotsList.GetInstance().GetList();
    private ArrayList<Appointments> mAppointments = AppointmentsList.GetInstance().GetList();
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
        mTimeSlotId = getIntent().getStringExtra(Constants.TIMESLOT_ID);

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
            if (appointment.getTimeslot().equals(mTimeSlot.getId())) {
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
        Log.d("GETPIC", Integer.toString(mTimeSlot.getDoctor().getPicture()));
        if(mTimeSlot.getDoctor().getPicture() == 4) {
            mPicture.setImageResource(R.mipmap.doc1);
        } else if(mTimeSlot.getDoctor().getPicture() == 1) {
            mPicture.setImageResource(R.mipmap.doc4);
        } else if(mTimeSlot.getDoctor().getPicture() == 5) {
            mPicture.setImageResource(R.mipmap.doc5);
        } else if(mTimeSlot.getDoctor().getPicture() == 3) {
            mPicture.setImageResource(R.mipmap.doc2);
        } else if(mTimeSlot.getDoctor().getPicture() == 2) {
            mPicture.setImageResource(R.mipmap.doc3);
        }
        mName.setText(mTimeSlot.getDoctor().getName());
        mQualifications.setText(mTimeSlot.getDoctor().getQualifications());
        mSpecialties.setText(mTimeSlot.getDoctor().getSpecialties());
    }

    public void save(View v) {
        // Navigated from TimeSlots. Create a new Appointment.
        if (mFromHome) {
            mAppointment.setNotes(mNotesEdit.getText().toString());
            goToHome();
        }
        // Navigated from HOME. Do not create a new Appointment. Edit the homeNavigated appointment
        else {
            Log.d("APPSIZE", Integer.toString(AppointmentsList.GetInstance().GetList().size()));

            RequestQueue queue = Volley.newRequestQueue(this);
            final StringRequest request = new StringRequest(Request.Method.POST, API.BASE_URL_APPOINTMENTS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //This code is executed if the server responds, whether or not the response contains data.
                    //The String 'response' contains the server's response.
                    Log.d("POST", "SUCCESS: " + response);

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Appointments appointment = new Appointments(jsonObject.getString("id"), mTimeSlotId, mNotesEdit.getText().toString(), Users.getInstance().getUserId());
                        AppointmentsList.GetInstance().AddToList(appointment);


                        goToHome();

                    } catch (JSONException | NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //This code is executed if there is an error.
                    Log.d("POST", "ERROR: " + error.toString());
                }
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> appt = new HashMap<>();
                    appt.put("timeslotId", mTimeSlotId);
                    appt.put("notes", mNotesEdit.getText().toString());
                    appt.put("userId", Users.getInstance().getUserId());
                    appt.put("access_token", API.ACCESS_TOKEN);
                    return appt;
                }
            };
            queue.add(request);

        }
    }

    private void goToHome() {
        finish();
        Intent intent = new Intent(this, HomeScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    public void delete(View v) {
        /*
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest dr = new StringRequest(Request.Method.DELETE,  API.BASE_URL_APPOINTMENTS + mAppointment.getId(),
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        for (Appointments appointment : AppointmentsList.GetInstance().GetList()) {
                            if (appointment.getId().equals(mAppointment.getId())) {
                                Log.d("PRE", "SIZE BEFORE: " + AppointmentsList.GetInstance().GetList().size());
                                AppointmentsList.GetInstance().GetList().remove(appointment);
                                Log.d("AFTER", "SIZE AFTER: " + AppointmentsList.GetInstance().GetList().size());
                                goToHome();
                            }
                        }


                    }
                    }, new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        // error.

                    }
                    }) {
            protected Map<String, String> getParams() {
                Map<String, String> ID = new HashMap<>();
                ID.put("access_token", API.ACCESS_TOKEN);
                return ID;
            }
        };

        queue.add(dr);*/
    }

}
