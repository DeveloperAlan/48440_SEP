package com.mad.studecare.Classes.Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mad.studecare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterScreen extends AppCompatActivity implements RegisterScreenContract.view {

    @BindView(R.id.register_email)
    EditText email;
    @BindView(R.id.register_password)
    EditText password;
    @BindView(R.id.register_name)
    EditText fullname;

    RegisterScreenContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        ButterKnife.bind(this);

        presenter = new RegisterScreenPresenter(this);
    }

    @Override
    public void register(View v) {
        if(presenter.RegisterUser(email.getText().toString(), password.getText().toString(), fullname.getText().toString(), this)){
            finish();
            Toast.makeText(this, "Registration successful! Please sign in.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void textFieldError(String error, String field){
        switch (field){
            case "email":
                email.setError(error);
                break;
            case "password":
                password.setError(error);
                break;
            case "fullname":
                fullname.setError(error);
                break;
        }
    }
}
