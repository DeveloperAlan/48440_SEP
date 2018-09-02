package com.mad.studecare.Classes.Login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    private FirebaseAuth mAuth;

    LoginScreenContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        presenter = new LoginScreenPresenter(this);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            Intent intent = new Intent(LoginScreen.this, HomeScreen.class);
            startActivity(intent);
        }
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

    public void signInSucess(Context context){
        Intent intent = new Intent(context, HomeScreen.class);
        startActivity(intent);
    }

    public void signInFail(String message,Context context){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void emptyDetails(Context context){
        Toast.makeText(context, "Please make sure both fields have been entered", Toast.LENGTH_SHORT).show();
    }
}
