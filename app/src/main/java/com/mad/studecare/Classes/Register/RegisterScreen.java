package com.mad.studecare.Classes.Register;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.mad.studecare.Classes.Home.HomeScreen;
import com.mad.studecare.Classes.Login.LoginScreen;
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
        presenter.RegisterUser(email.getText().toString(), password.getText().toString(), getApplicationContext());
        finish();
    }

    public void signInSucess(String message, Context context){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(RegisterScreen.this, HomeScreen.class); //this, RegisterScreen.this, getApplicationContext, context
        //startActivity(intent);
    }

    public void signInFail(String message,Context context){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void emptyDetails(Context context){
        Toast.makeText(context, "Please make sure both fields have been entered", Toast.LENGTH_SHORT).show();
    }
}
