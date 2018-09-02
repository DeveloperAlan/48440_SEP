package com.mad.studecare.Classes.Login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    FirebaseAuth mAuth;

    LoginScreen loginScreen;

    public LoginScreenPresenter(LoginScreenContract.view view) {
        this.mView = view;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void authenticate(String email, String password, final Context context) {
        this.mUsername = email;
        this.mPassword = password;

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        loginScreen.signInSucess(context);
                    } else {
                        loginScreen.signInFail(task.getException().getMessage(), context);
                    }
                }
            });
        } else {
            loginScreen.emptyDetails(context);
        }
    }
}
