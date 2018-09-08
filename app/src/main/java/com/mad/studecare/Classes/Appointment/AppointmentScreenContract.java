package com.mad.studecare.Classes.Appointment;

import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.Models.TimeSlots.TimeSlotsAdapter;

import java.util.ArrayList;

/**
 * Created by trevorlao on 29/8/18.
 */

public interface AppointmentScreenContract {
    interface view {
        void createNewSchedule();

        void scheduleSuccessfullyCreated();

        void emptyDetails();

        void error(String error);

    }

    interface presenter {
        void createSchedule(String name, String location, String date, String time);

        void onEmptyDetails();

        void onSuccess();

        void onError(String error);

        void passGroupUid(String uid);

        void populateSample(ArrayList timeslotList, TimeSlotsAdapter appointmentsAdapter);

    }
}
