package com.mad.studecare;

import android.view.View;

/**
 * Created by trevorlao on 22/8/18.
 */

public interface LoginScreenContract {
    interface view {
        void login(View v);

    }

    interface presenter {
        void login(View v);

    }
}
