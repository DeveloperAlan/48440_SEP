package com.mad.studecare.Models.Appointments;

import com.mad.studecare.Models.Doctors.Doctors;
import com.mad.studecare.Models.TimeSlots.TimeSlots;

/**
 * Created by trevorlao on 22/8/18.
 */

public class Appointments {

    private String timeslot;
    //private String id;
    private String notes;
    private String userId;

    public Appointments(String timeslot, String notes, String userId) {
        this.timeslot= timeslot;
        this.notes = notes;
        this.userId = userId;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getNotes() {
        return notes;
    }

    public String getUserId() {
        return userId;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
