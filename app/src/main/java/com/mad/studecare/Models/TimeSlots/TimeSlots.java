package com.mad.studecare.Models.TimeSlots;

/**
 * Created by trevorlao on 5/9/18.
 */

public class TimeSlots {

    private String time, date, doctor, qualifications, specialties;

    public TimeSlots(String time, String date, String doctor, String qualifications, String specialties) {
        this.time = time;
        this.date = date;
        this.doctor = doctor;
        this.qualifications = qualifications;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }
}
