package com.mad.studecare.Classes.Appointment;

import android.content.Context;

import com.mad.studecare.Classes.Home.HomeScreenContract;
import com.mad.studecare.Utils.CreateAppointmentInteractor;
import com.mad.studecare.Utils.OnCreateAppointmentFinishedListener;

/**
 * Created by trevorlao on 29/8/18.
 */

public class AppointmentScreenPresenter implements AppointmentScreenContract.presenter, OnCreateAppointmentFinishedListener {

    private AppointmentScreenContract.view mView;
    private CreateAppointmentInteractor mInteractor;

    public AppointmentScreenPresenter(AppointmentScreenContract.view view, Context context) {
        this.mView = view;
        mInteractor = new CreateAppointmentInteractor(context);
    }

    @Override
    public void createSchedule(String name, String location, String date, String time) {
        mInteractor.createAppointment(this, name, location, date, time);
    }

    @Override
    public void passGroupUid(String uid) {
        mInteractor.retrieveGroupUid(uid);
    }

    @Override
    public void onEmptyDetails() {
        mView.emptyDetails();
    }

    @Override
    public void onSuccess() {
        mView.scheduleSuccessfullyCreated();
    }

    @Override
    public void onError(String error) {
        mView.error(error);
    }
}