package com.mad.studecare.Utils;

/**
 * Created by trevorlao on 29/8/18.
 */

public interface ICreateAppointmentInteractor {
    void createAppointment(OnCreateAppointmentFinishedListener listener, String name, String location, String date, String time);

    void retrieveGroupUid(String uid);
}
