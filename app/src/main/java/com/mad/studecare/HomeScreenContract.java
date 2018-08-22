package com.mad.studecare;

import java.util.ArrayList;

/**
 * Created by trevorlao on 22/8/18.
 */

public interface HomeScreenContract {
    interface view {


    }

    interface presenter {
        void prepareMovieData(ArrayList appointmentsList, AppointmentsAdapter appointmentsAdapter);

    }
}
