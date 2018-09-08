package com.mad.studecare.Models.Appointments;

/**
 * Created by trevorlao on 22/8/18.
 */

public class Appointments {

    private String doctor;
    private String timeDate;
    private String description;
    private int status;

    public Appointments(String doctor, String timeDate, String description, int status) {
        this.doctor = doctor;
        this.timeDate = timeDate;
        this.description = description;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
