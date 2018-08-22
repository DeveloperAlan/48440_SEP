package com.mad.studecare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.glomadrian.grav.GravView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingScreen extends AppCompatActivity implements LoadingScreenContract.view {

    LoadingScreenContract.presenter presenter;

    @BindView(R.id.loading_progress)
    TextView mLoadingText;
    @BindView(R.id.loading_grav)
    GravView mGravView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);
        ButterKnife.bind(this);

        presenter = new LoadingPresenter(this);

        presenter.startLoading();
    }

    @Override
    public void publishProgress(Integer... values) {
         mLoadingText.setText(getResources().getString(R.string.loading_format,
               values[0],
               getResources().getString(R.string.loading_percent)));
    }

    @Override
    public void startApp() {
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
        finish();
    }
}
