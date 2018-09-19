package com.mad.studecare.Classes.Appointment;

import com.mad.studecare.Models.Doctors.Doctors;
import com.mad.studecare.Models.Doctors.DoctorsSlideAdapter;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import android.app.FragmentManager;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import android.widget.Button;


import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.Models.TimeSlots.TimeSlotsAdapter;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by trevorlao on 29/8/18.
 */

public interface AppointmentScreenContract {
    interface view {

        void setDateText(Button date, String fmtOut);

        void setDateDialog(DatePickerDialog dialog);

        void setTimeDialog(Calendar calendar);

        void setTimeText(String text);

        void showConfirmDialog(TimeSlots timeSlots);

    }

    interface presenter {

        void setDateButton(Button date);

        void setTimeButton(Button time);

        void setTimeText(int hour, int minute, int second);

        ArrayList<Doctors> doctorsList();

        void populateSample();

        void feedAdaptersList(TimeSlotsAdapter timeSlotsAdapter, DoctorsSlideAdapter doctorsSlideAdapter);

        void filterDoctor(Doctors doctor, boolean selected);

        void confirmTime(TimeSlots timeSlot);
    }
}
