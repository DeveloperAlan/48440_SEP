package com.mad.studecare.Models.Appointments;

import com.mad.studecare.Models.Doctors.Doctors;
import com.mad.studecare.Models.TimeSlots.TimeSlots;

/**
 * Created by trevorlao on 22/8/18.
 */

public class Appointments {

    private TimeSlots timeslot;
    private String notes;

    public Appointments(TimeSlots timeslot, String notes) {
        this.timeslot = timeslot;
        this.notes = notes;
    }

    public TimeSlots getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlots timeslot) {
        this.timeslot = timeslot;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
