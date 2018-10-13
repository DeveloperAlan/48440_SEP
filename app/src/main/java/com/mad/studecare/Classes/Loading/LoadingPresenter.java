package com.mad.studecare.Classes.Loading;

import android.content.Context;

import com.mad.studecare.Models.Appointments.Appointments;
import com.mad.studecare.Models.Appointments.AppointmentsList;
import com.mad.studecare.Models.Doctors.Doctors;
import com.mad.studecare.Models.Doctors.DoctorsList;
import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.Models.TimeSlots.TimeSlotsList;

import java.util.ArrayList;

/**
 * Created by trevorlao on 15/8/18.
 */

public class LoadingPresenter implements LoadingScreenContract.presenter {

    private LoadingScreenContract.view mView;

    public LoadingPresenter(LoadingScreenContract.view view) {
        this.mView = view;
    }

    public void startLoading(Context context) {
        new LoadingAsyncTask(this).execute();
    }

    public void publishProgress(Integer... values) {
        mView.publishProgress(values);
    }

    public void startApp() {
        mView.startApp();
    }
}
