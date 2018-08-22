package com.mad.studecare;

/**
 * Created by trevorlao on 15/8/18.
 */

public interface LoadingScreenContract {
    interface view {

        void publishProgress(Integer... values);
        void startApp();

    }

    interface presenter {

        void startLoading();
        void publishProgress(Integer... values);
        void startApp();
    }
}
