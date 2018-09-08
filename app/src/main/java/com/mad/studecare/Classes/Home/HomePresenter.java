package com.mad.studecare.Classes.Home;

import com.mad.studecare.Models.Appointments.Appointments;
import com.mad.studecare.Models.Appointments.AppointmentsAdapter;

import java.util.ArrayList;

/**
 * Created by trevorlao on 22/8/18.
 */

public class HomePresenter implements HomeScreenContract.presenter {

    private HomeScreenContract.view mView;

    public HomePresenter(HomeScreenContract.view view) {
        this.mView = view;
    }

    @Override
    public void addAppointment() {
        mView.addAppointment();
    }

    @Override
    public void prepareMovieData(ArrayList appointmentsList, AppointmentsAdapter adapter) {
        Appointments appointments = new Appointments("Dr. Adrian Pham",
                "3:53pm 09/04/18",
                "Colonoscopy, checking of the lower bowel system for any inconsistencies. Bladder feels heavy at times",
                0);
        appointmentsList.add(appointments);

        appointments = new Appointments("Dr. Sarah Cheung",
                "6:30pm 12/06/19",
                "Regular checkup. I think i have the flu, but symptons seem to be a bit more severe. I am very worried",
                1);
        appointmentsList.add(appointments);

        appointments = new Appointments("Dr. Sarah Cheung",
                "9:30pm 12/06/19",
                "Regular checkup. I think i have the flu, but symptons seem to be a bit more severe. I am very worried",
                2);
        appointmentsList.add(appointments);

        appointments = new Appointments("Dr. Geoffrey Kostanidos",
                "6:30pm 18/06/19",
                "Lump in throat, worried about what it could mean",
                1);
        appointmentsList.add(appointments);

        adapter.notifyDataSetChanged();
    }
}
