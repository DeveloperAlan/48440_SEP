package com.mad.studecare.Classes.Loading;

import android.content.Context;

import com.mad.studecare.Models.Doctors.DoctorsSlideAdapter;

import java.util.ArrayList;

/**
 * Created by trevorlao on 15/8/18.
 */

public interface LoadingScreenContract {
    interface view {

        void publishProgress(Integer... values);

        void startApp();
    }

    interface presenter {

        void startLoading(Context context);

        void publishProgress(Integer... values);

        void startApp();
    }
}
