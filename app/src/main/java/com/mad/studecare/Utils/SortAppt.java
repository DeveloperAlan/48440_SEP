package com.mad.studecare.Utils;

import com.mad.studecare.Models.Appointments.Appointments;
import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.Models.TimeSlots.TimeSlotsList;

import java.util.Comparator;

/**
 * Created by Jacky on 17/10/2018.
 */

public class SortAppt implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Appointments appt1 = (Appointments) o1;
        Appointments appt2 = (Appointments) o2;
        TimeSlots timeSlot1 = null;
        TimeSlots timeSlot2 = null;
        for(TimeSlots slot : TimeSlotsList.GetInstance().GetList()) {
            if (appt1.getTimeslot().equals(slot.getId())) {
                timeSlot1 = slot;
            }
        }
        for(TimeSlots slot : TimeSlotsList.GetInstance().GetList()) {
            if (appt2.getTimeslot().equals(slot.getId())) {
                timeSlot2 = slot;
            }
        }
        int result;
        if (timeSlot1.getDate().equals(timeSlot2.getDate())) {
            if(timeSlot1.getTime().split(":")[0].equals(timeSlot2.getTime().split(":")[0])){
                result = -(timeSlot1.getTime().split(":")[1].compareTo(timeSlot2.getTime().split(":")[1]));
            }else {
                result = -(timeSlot1.getTime().split(":")[0].compareTo(timeSlot2.getTime().split(":")[0]));
            }
        } else {
            if (timeSlot1.getDate().split("/")[2].equals(timeSlot2.getDate().split("/")[2])) {
                if (timeSlot1.getDate().split("/")[1].equals(timeSlot2.getDate().split("/")[1])) {
                    result = timeSlot1.getDate().split("/")[0].compareTo(timeSlot2.getDate().split("/")[0]);
                } else {
                    result = timeSlot1.getDate().split("/")[1].compareTo(timeSlot2.getDate().split("/")[1]);
                }
            } else {
                result = timeSlot1.getDate().split("/")[2].compareTo(timeSlot2.getDate().split("/")[2]);
            }
        }
        return result;
    }
}
