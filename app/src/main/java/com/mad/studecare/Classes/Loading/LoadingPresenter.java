package com.mad.studecare.Classes.Loading;

import android.content.Context;

import com.mad.studecare.Models.Appointments.Appointments;
import com.mad.studecare.Models.Appointments.AppointmentsList;
import com.mad.studecare.Models.Doctors.Doctors;
import com.mad.studecare.Models.Doctors.DoctorsList;
import com.mad.studecare.Models.Doctors.DoctorsSlideAdapter;
import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.Models.TimeSlots.TimeSlotsList;
import com.mad.studecare.R;

import java.util.ArrayList;

/**
 * Created by trevorlao on 15/8/18.
 */

public class LoadingPresenter implements LoadingScreenContract.presenter {

    private LoadingScreenContract.view mView;
    private ArrayList<Doctors> mDoctorsList = new ArrayList<>();
    private ArrayList<TimeSlots> mTimeSlotsList = new ArrayList<>();
    private ArrayList<Appointments> mAppointmentsList = new ArrayList<>();

    public LoadingPresenter(LoadingScreenContract.view view) {
        this.mView = view;
    }

    public void startLoading(Context context) {
        // Load Singleton
        DoctorsList.initInstance();
        TimeSlotsList.initInstance();
        AppointmentsList.initInstance();

        // Prep sample data
        prepareDoctorsData();
        prepareTimeSlotsData();
        prepareAppointmentsData();

        new LoadingAsyncTask(this).execute();
    }

    public void publishProgress(Integer... values) {
        mView.publishProgress(values);
    }

    public void startApp() {
        DoctorsList.getInstance().setList(mDoctorsList);
        TimeSlotsList.getInstance().setList(mTimeSlotsList);
        AppointmentsList.getInstance().setList(mAppointmentsList);
        mView.startApp();
    }

    private void prepareDoctorsData() {
        Doctors doctor = new Doctors("Dr. Sarah Cheung",
                "Immunologist",
                "MD(Res), DM",
                R.mipmap.doc4,
                (float) 4.8,
                new ArrayList<TimeSlots>());
        mDoctorsList.add(doctor);

        doctor = new Doctors("Dr. Faraj Mohammed",
                "Dermatologist",
                "DMSc, DMedSc",
                R.mipmap.doc1,
                (float) 4.5,
                new ArrayList<TimeSlots>());
        mDoctorsList.add(doctor);

        doctor = new Doctors("Dr. Simon Nigel",
                "Urologist",
                "DS, PhD",
                R.mipmap.doc2,
                (float) 3.7,
                new ArrayList<TimeSlots>());
        mDoctorsList.add(doctor);

        doctor = new Doctors("Dr. Richard Seddejer",
                "Gynaecologist",
                "MM, MMed",
                R.mipmap.doc3,
                (float) 4.2,
                new ArrayList<TimeSlots>());
        mDoctorsList.add(doctor);

        doctor = new Doctors("Dr. Michelle Matthews",
                "Pediatrician",
                "MSc",
                R.mipmap.doc5,
                (float) 4,
                new ArrayList<TimeSlots>());
        mDoctorsList.add(doctor);
    }

    private void prepareTimeSlotsData() {
        TimeSlots timeslot = new TimeSlots(mDoctorsList.get(1),
                "12:30",
                "12/09/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(2),
                "14:30",
                "13/09/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(2),
                "16:30",
                "14/09/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(4),
                "18:30",
                "15/09/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(1),
                "20:30",
                "16/09/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(1),
                "22:30",
                "17/09/2018");
        mTimeSlotsList.add(timeslot);

        //5 new time slots for doctor3
        timeslot = new TimeSlots(mDoctorsList.get(2),
                "14:30",
                "10/10/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(2),
                "17:00",
                "15/10/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(2),
                "8:30",
                "17/10/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(2),
                "10:30",
                "19/10/2018");
        mTimeSlotsList.add(timeslot);

        //5 new time slots for doctor2
        timeslot = new TimeSlots(mDoctorsList.get(1),
                "21:30",
                "22/09/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(1),
                "9:30",
                "24/09/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(1),
                "10:00",
                "01/10/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(1),
                "11:30",
                "3/10/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(1),
                "12:30",
                "7/10/2018");
        mTimeSlotsList.add(timeslot);

        //5 new time slots for doctor1
        timeslot = new TimeSlots(mDoctorsList.get(0),
                "12:30",
                "10/10/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(0),
                "12:30",
                "15/10/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(0),
                "16:30",
                "17/10/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(0),
                "18:30",
                "19/10/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(0),
                "20:00",
                "02/11/2018");
        mTimeSlotsList.add(timeslot);

        //5 new time slots for doctor4
        timeslot = new TimeSlots(mDoctorsList.get(3),
                "21:30",
                "20/09/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(3),
                "9:30",
                "21/09/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(3),
                "10:00",
                "03/10/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(3),
                "11:30",
                "10/10/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(3),
                "12:30",
                "14/10/2018");
        mTimeSlotsList.add(timeslot);

        //5 new time slots for doctor5
        timeslot = new TimeSlots(mDoctorsList.get(4),
                "9:00",
                "20/09/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(4),
                "10:30",
                "21/09/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(4),
                "12:00",
                "03/10/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(4),
                "15:30",
                "10/10/2018");
        mTimeSlotsList.add(timeslot);

        timeslot = new TimeSlots(mDoctorsList.get(4),
                "21:00",
                "14/10/2018");
        mTimeSlotsList.add(timeslot);
    }

    private void prepareAppointmentsData() {
        Appointments appointments = new Appointments(
                mTimeSlotsList.get(2),  "Testy McTestFace");
        mAppointmentsList.add(appointments);

        appointments = new Appointments(
                mTimeSlotsList.get(1), "");
        mAppointmentsList.add(appointments);

        appointments = new Appointments(mTimeSlotsList.get(3), "");
        mAppointmentsList.add(appointments);

        appointments = new Appointments(mTimeSlotsList.get(4), "");
        mAppointmentsList.add(appointments);

        appointments = new Appointments(mTimeSlotsList.get(5), "");
        mAppointmentsList.add(appointments);

        appointments = new Appointments(mTimeSlotsList.get(0), "");
        mAppointmentsList.add(appointments);
    }
}
