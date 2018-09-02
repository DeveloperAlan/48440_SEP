package com.mad.studecare.Classes.Login;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mad.studecare.Models.API;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by trevorlao on 22/8/18.
 */

public class LoginScreenPresenter implements LoginScreenContract.presenter {
    private LoginScreenContract.view mView;
    String mUsername;
    String mPassword;

    public LoginScreenPresenter(LoginScreenContract.view view) {
        this.mView = view;
    }

    @Override
    public void authenticate(String email, String password, Context context) {
        this.mUsername = email;
        this.mPassword = password;

    }
}
