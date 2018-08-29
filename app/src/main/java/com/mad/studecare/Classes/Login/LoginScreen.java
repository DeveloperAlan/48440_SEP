package com.mad.studecare.Classes.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mad.studecare.Classes.Home.HomeScreen;
import com.mad.studecare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginScreen extends AppCompatActivity implements LoginScreenContract.view {

    @BindView(R.id.login_login)
    Button loginButton;
    @BindView(R.id.login_register)
    Button registerButton;

    LoginScreenContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        presenter = new LoginScreenPresenter(this);
        ButterKnife.bind(this);
    }

    @Override
    public void login(View v)
    {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void register(View v) {

    }
}
