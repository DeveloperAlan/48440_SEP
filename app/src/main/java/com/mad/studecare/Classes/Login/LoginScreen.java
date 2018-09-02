package com.mad.studecare.Classes.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mad.studecare.Classes.Home.HomeScreen;
import com.mad.studecare.Classes.Register.RegisterScreen;
import com.mad.studecare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginScreen extends AppCompatActivity implements LoginScreenContract.view {

    @BindView(R.id.login_user)
    EditText username;
    @BindView(R.id.login_password)
    EditText password;
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
        presenter.authenticate(username.getText().toString(), password.getText().toString(), this);
    }

    @Override
    public void loginAuthenticated() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    @Override
    public void register(View v) {
        Intent intent = new Intent(this, RegisterScreen.class);
        startActivity(intent);
    }
}
