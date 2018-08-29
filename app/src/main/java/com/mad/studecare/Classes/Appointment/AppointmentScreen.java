package com.mad.studecare.Classes.Appointment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mad.studecare.Classes.Home.HomeScreenContract;
import com.mad.studecare.R;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentScreen extends AppCompatActivity implements AppointmentScreenContract.view, TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.create_group_create_button)
    Button mCreateButton;
    @BindView(R.id.schedule_name_edit_text)
    EditText mNameText;
    @BindView(R.id.schedule_location_edit_text)
    EditText mLocationText;
    @BindView(R.id.schedule_date_button)
    Button mDateButton;
    @BindView(R.id.schedule_time_button)
    Button mTimeButton;
    @BindView(R.id.schedule_date_textView)
    TextView mDateTextView;
    @BindView(R.id.schedule_time_textView)
    TextView mTimeTextView;

    private String mDate;
    private String mTime;
    private ProgressDialog mProgressDialog;
    private String mGroupUid;
    private String mGroupName;

    AppointmentScreenContract.presenter presenter;

    private static final String TAG = "CreateScheduleActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_screen);
        ButterKnife.bind(this);

        presenter = new AppointmentScreenPresenter(this, getApplicationContext());
        mProgressDialog = new ProgressDialog(this);

        presenter.passGroupUid(mGroupUid);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final com.wdullaer.materialdatetimepicker.date.DatePickerDialog dialog = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        mDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year; //+1 because java cal starts from 0
                        mDateTextView.setText(mDate);
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

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewSchedule();
            }
        });
    }

    @Override
    public void createNewSchedule() {
        String location = mLocationText.getText().toString().trim();
        String name = mNameText.getText().toString().trim();

        mProgressDialog.setMessage("Placeholdering...");
        mProgressDialog.show();

        presenter.createSchedule(name, location, mDate, mTime);
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
        mTime = hourOfDay + ":" + minute;
        mTimeTextView.setText(mTime);
    }
}

