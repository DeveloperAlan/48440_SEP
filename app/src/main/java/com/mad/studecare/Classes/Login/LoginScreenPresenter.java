package com.mad.studecare.Classes.Login;

import android.view.View;

/**
 * Created by trevorlao on 22/8/18.
 */

public class LoginScreenPresenter implements LoginScreenContract.presenter {
    private LoginScreenContract.view mView;

    public LoginScreenPresenter(LoginScreenContract.view view) {
        this.mView = view;
    }

}
