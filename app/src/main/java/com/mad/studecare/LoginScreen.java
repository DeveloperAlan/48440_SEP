package com.mad.studecare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginScreen extends AppCompatActivity implements LoginScreenContract.view {

    @BindView(R.id.login_login)
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        ButterKnife.bind(this);
    }

    @Override
    public void login(View v)
    {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
        finish();
    }
}
