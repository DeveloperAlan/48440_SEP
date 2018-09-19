package com.mad.studecare.Classes.Appointment;

import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mad.studecare.Classes.Home.HomeScreenContract;
import com.mad.studecare.Models.Doctors.Doctors;
import com.mad.studecare.Models.Doctors.DoctorsList;
import com.mad.studecare.Models.Doctors.DoctorsSlideAdapter;
import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.Models.TimeSlots.TimeSlotsAdapter;
import com.mad.studecare.Models.TimeSlots.TimeSlotsList;
import com.mad.studecare.R;
import com.mad.studecare.Utils.CreateAppointmentInteractor;
import com.mad.studecare.Utils.OnCreateAppointmentFinishedListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.lang.reflect.Array;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by trevorlao on 29/8/18.
 */

public class AppointmentScreenPresenter implements AppointmentScreenContract.presenter {

    private AppointmentScreenContract.view mView;
    private Context mContext;

    private TimeSlotsAdapter mTimeSlotsAdapter;
    private DoctorsSlideAdapter mDoctorsSlideAdapter;

    private Calendar mCalendar = Calendar.getInstance();
    private int mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
    private int mMonth = mCalendar.get(Calendar.MONTH);
    private int mYear = mCalendar.get(Calendar.YEAR);

    private ArrayList<TimeSlots> mTimeSlotsList = new ArrayList<>();
    private ArrayList<Doctors> mDoctorsList = new ArrayList<>();

    // Lists for the filter function
    private ArrayList<TimeSlots> mRemovedTimeSlotsTime = new ArrayList<>();
    private ArrayList<TimeSlots> mRemovedTimeSlotsDate = new ArrayList<>();
    private ArrayList<Doctors> mActiveDoctors = new ArrayList<>();

    public AppointmentScreenPresenter(AppointmentScreenContract.view view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    @Override
    public void feedAdaptersList(TimeSlotsAdapter timeSlotsAdapter,
                                 DoctorsSlideAdapter doctorsSlideAdapter) {
        this.mTimeSlotsAdapter = timeSlotsAdapter;
        this.mDoctorsSlideAdapter = doctorsSlideAdapter;
        this.mTimeSlotsList = TimeSlotsList.getInstance().getList();
    }

    /* Grabs list from Singleton instance. */
    @Override
    public ArrayList<Doctors> doctorsList() {
        ArrayList<Doctors> list = DoctorsList.getInstance().getList();
        // Passes it into local private variable
        this.mDoctorsList = list;
        return mDoctorsList;
    }

    @Override
    public void setDateButton(final Button dateButton) {
        // Date/Time picker
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        // Using SimpleDateFormat to format date, then change button text to it
                        try {
                            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                            Date date = fmt.parse(mContext.getResources().getString(R.string.time_concatenate_date,
                                    dayOfMonth,
                                    monthOfYear + 1,
                                    year));
                            SimpleDateFormat fmtOut = new SimpleDateFormat("EEE, dd/MM/yyyy", Locale.US);

                            // Filter Method for Date
                            filterDate(date);

                            mView.setDateText(dateButton, fmtOut.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, mYear, mMonth, mDay);
                mView.setDateDialog(dialog);
            }
        });
    }

    @Override
    public void setTimeButton(Button mTimeButton) {
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.setTimeDialog(mCalendar);
            }
        });
    }

    @Override
    public void setTimeText(int hour, int minute, int second) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("HH:mm", Locale.US);
            Date date = fmt.parse(mContext.getResources().getString(R.string.time_concatenate_time,
                    hour,
                    minute));
            SimpleDateFormat fmtOut = new SimpleDateFormat("h:mm a", Locale.US);

            // Filter method for Time
            filterTime(date);

            mView.setTimeText(fmtOut.format(date));

        } catch (final ParseException e) {
            e.printStackTrace();
        }
    }

    private void filterTime(Date time) {
        // Adding all the removed TimeSlots for a new filter
        mTimeSlotsList.addAll(mRemovedTimeSlotsTime);
        // Remove all removed TimeSlots
        mRemovedTimeSlotsTime.clear();

        Iterator<TimeSlots> i = mTimeSlotsList.iterator();
        while (i.hasNext()) {
            TimeSlots timeSlot = i.next();
            if (time.after(timeSlot.getDateTime())) {
                mRemovedTimeSlotsTime.add(timeSlot);
                i.remove();
            }
        }

        // Sort & Change
        sortChange();
    }

    private void filterDate(Date date) {
        // Adding all removed TimeSlots for a new filter
        mTimeSlotsList.addAll(mRemovedTimeSlotsDate);
        // Remove all removed Time Slots
        mRemovedTimeSlotsDate.clear();

        Iterator<TimeSlots> i = mTimeSlotsList.iterator();
        while (i.hasNext()) {
            TimeSlots timeSlot = i.next();
            if (date.after(timeSlot.getDateDate())) {
                mRemovedTimeSlotsDate.add(timeSlot);
                i.remove();
            }
        }

        // Sort & Change
        sortChange();
    }

    /* Each Doctor has a persistent list of which slots to remove & add back. */
    @Override
    public void filterDoctor(Doctors doctor, boolean status) {
        if (!status) {
            // Adding all removed TimeSlots for a new filter
            mTimeSlotsList.addAll(doctor.getRemovedSlots());
            // Remove all removed Time Slots
            doctor.getRemovedSlots().clear();


            Iterator<TimeSlots> i = mTimeSlotsList.iterator();
            while (i.hasNext()) {
                TimeSlots timeSlot = i.next();
                if (doctor != timeSlot.getDoctor()) {
                    doctor.getRemovedSlots().add(timeSlot);
                    i.remove();
                }
            }
        } else {
            // Adding all removed TimeSlots for a new filter
            mTimeSlotsList.addAll(doctor.getRemovedSlots());
            // Remove all removed Time Slots
            doctor.getRemovedSlots().clear();
        }

        // Sort & Change
        sortChange();
    }

//    @Override
//    public void setDoctorRemovableTimeSlots() {
//        // Populating the doctors list of removable TimeSlots based off loaded TimeSlots
//        for (TimeSlots timeSlot : mTimeSlotsList) {
//            for (int i = 0; i < mDoctorsList.size(); i++) {
//                if (timeSlot.getDoctor() == mDoctorsList.get(i)) {
//                    mDoctorsList.get(i).getRemovedSlots().add(timeSlot);
//                }
//            }
//        }
//    }

    /* Method to order the list before updating the adapter */
    private void sortChange(){

        mTimeSlotsAdapter.notifyDataSetChanged();
    }

    @Override
    public void confirmTime(TimeSlots timeSlot) {
        mView.showConfirmDialog(timeSlot);
    }

    /* Should migrate to JODA time instead of Java API DateTime. Right now all months will be (-1) */
    @Override
    public void populateSample() {

    }
}