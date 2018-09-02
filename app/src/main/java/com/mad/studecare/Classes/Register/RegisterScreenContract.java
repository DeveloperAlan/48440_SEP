package com.mad.studecare.Classes.Register;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

/**
 * Created by trevorlao on 29/8/18.
 */

public interface RegisterScreenContract {
    interface view {
        void register(View v);
    }

    interface presenter {
        void RegisterUser(String email, String password, Context context);
    }
}
