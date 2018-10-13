package com.mad.studecare.Classes.Appointment;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.mad.studecare.Models.Doctors.Doctors;
import com.mad.studecare.Models.Doctors.DoctorsList;
import com.mad.studecare.Models.Doctors.DoctorsSlideAdapter;
import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.Models.TimeSlots.TimeSlotsAdapter;
import com.mad.studecare.Models.TimeSlots.TimeSlotsList;
import com.mad.studecare.R;
import com.mad.studecare.Utils.SortList;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
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
    private Date mTime = null;
    private Date mDate = null;

    private ArrayList<TimeSlots> mTimeSlotsList = new ArrayList<>();
    private ArrayList<Doctors> mDoctorsList = new ArrayList<>();

    // Lists for the filter function
    private ArrayList<TimeSlots> mRemovedTimeSlotsTime = new ArrayList<>();
    private ArrayList<TimeSlots> mRemovedTimeSlotsDate = new ArrayList<>();
    private ArrayList<TimeSlots> mRemovedDoctors = new ArrayList<>();
    private HashSet<Doctors> mChosenDoctors= new HashSet<>();
    private ArrayList<TimeSlots> mFilterList = new ArrayList<>();

    public AppointmentScreenPresenter(AppointmentScreenContract.view view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    @Override
    public void feedAdaptersList(TimeSlotsAdapter timeSlotsAdapter,
                                 DoctorsSlideAdapter doctorsSlideAdapter) {
        this.mTimeSlotsAdapter = timeSlotsAdapter;
        this.mDoctorsSlideAdapter = doctorsSlideAdapter;
        this.mFilterList= TimeSlotsList.GetInstance().GetList();
    }

    /* Grabs list from Singleton instance. */
    @Override
    public ArrayList<Doctors> doctorsList() {
        ArrayList<Doctors> list = DoctorsList.GetInstance().getList();
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
                            mDate = date;

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
            mTime = date;

            mView.setTimeText(fmtOut.format(date));

        } catch (final ParseException e) {
            e.printStackTrace();
        }
    }

    private void filterTime(Date time) {
        // Adding all the removed TimeSlots for a new filter
        mFilterList.addAll(mRemovedTimeSlotsTime);
        // Remove all removed TimeSlots
        mRemovedTimeSlotsTime.clear();

        for(TimeSlots slot : mFilterList) {
            if (time.after(slot.getDateTime())) {
                mRemovedTimeSlotsTime.add(slot);
            }
        }

        mFilterList.removeAll(mRemovedTimeSlotsTime);

        mView.updateList(mFilterList);
        // Sort & Change
        sortChange();
    }

    private void filterDate(Date date) {
        // Adding all the removed TimeSlots for a new filter
        mFilterList.addAll(mRemovedTimeSlotsDate);
        // Remove all removed TimeSlots
        mRemovedTimeSlotsDate.clear();

        for (TimeSlots slot : mFilterList) {
            if (date.after(slot.getDateDate())) {
                mRemovedTimeSlotsDate.add(slot);
            }
        }

        mFilterList.removeAll(mRemovedTimeSlotsDate);

        mView.updateList(mFilterList);

        // Sort & Change
        sortChange();
    }

    /* Each Doctor has a persistent list of which slots to remove & add back. */
    @Override
    public void filterDoctor() {

        ArrayList<TimeSlots> testList = new ArrayList<>();

        SortList sort = new SortList();

        // Adding all the removed TimeSlots for a new filter
        mFilterList.addAll(mRemovedDoctors);
        // Remove all removed TimeSlots
        mRemovedDoctors.clear();

        if(mChosenDoctors.isEmpty()) {
            mView.updateList(mFilterList);
        } else {
            for(TimeSlots slot : mFilterList) {
                for(Doctors d : mChosenDoctors) {
                    if(slot.getDoctor() == d) {
                        testList.add(slot);
                        mRemovedDoctors.add(slot);
                    }
                }
            }

            //Collections.sort(mFilterList, sort);

            mView.updateList(testList);
            // Sort & Change
        }
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

    @Override
    public void selectDoctor(Doctors doctor, boolean b) {
        if(!b) {
            if(mChosenDoctors.contains(doctor)) {
                mChosenDoctors.remove(doctor);
            }
        } else {
            mChosenDoctors.add(doctor);
        }
        filterDoctor();
    }

    /* Should migrate to JODA time instead of Java API DateTime. Right now all months will be (-1) */
    @Override
    public void populateSample() {

    }
}