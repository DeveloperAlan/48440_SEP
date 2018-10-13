package com.mad.studecare.Classes.Login;

import android.content.Context;
import android.view.View;

/**
 * Created by trevorlao on 22/8/18.
 */

public interface LoginScreenContract {
    interface view {
        void login(View v);

        void loginAuthenticated();

        void register(View v);

        void showProgress();

        void hideProgress();

    }

    interface presenter {
        void authenticate(String username, String password, Context context);
    }
}
