package com.mad.studecare.Models.Appointments;

import com.mad.studecare.Models.Doctors.Doctors;
import com.mad.studecare.Models.TimeSlots.TimeSlots;

/**
 * Created by trevorlao on 22/8/18.
 */

public class Appointments {

    private TimeSlots timeslot;

    public Appointments(TimeSlots timeslot) {
        this.timeslot = timeslot;
    }

    public TimeSlots getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlots timeslot) {
        this.timeslot = timeslot;
    }
}
