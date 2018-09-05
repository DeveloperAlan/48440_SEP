package com.mad.studecare.Classes.Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mad.studecare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterScreen extends AppCompatActivity implements RegisterScreenContract.view {

    @BindView(R.id.register_email)
    EditText email;
    @BindView(R.id.register_password)
    EditText password;

    RegisterScreenContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        ButterKnife.bind(this);

        presenter = new RegisterScreenPresenter();
    }

    @Override
    public void register(View v) {
        presenter.RegisterUser(email.getText().toString(), password.getText().toString(), this);
        finish();
    }
}
