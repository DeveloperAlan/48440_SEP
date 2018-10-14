package com.mad.studecare.Models.TimeSlots;

import com.mad.studecare.Models.Doctors.Doctors;
import com.mad.studecare.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by trevorlao on 5/9/18.
 */

public class TimeSlots {

    private String id;
    private Doctors doctor;
    private String time;
    private String date;

    public TimeSlots(String id, Doctors doctor, String time, String date) {
        this.id = id;
        this.doctor = doctor;
        this.time = time;
        this.date = date;
    }

    public Doctors getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctors doctor) {
        this.doctor = doctor;
    }

    public String getTime() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("HH:mm", Locale.US);
            Date date = fmt.parse(time);
            SimpleDateFormat fmtOut = new SimpleDateFormat("h:mm a", Locale.US);

            return fmtOut.format(date);

        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            Date value = fmt.parse(date);
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

            return fmtOut.format(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /* Takes the String date format and spits out a passable date */
    public Date getDateDate() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            Date value = fmt.parse(date);

            return value;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* Takes the String time format and spits out a passable date */
    public Date getDateTime() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("hh:mm", Locale.US);
            Date value = fmt.parse(time);

            return value;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
