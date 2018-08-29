package com.mad.studecare.Classes.Loading;

import android.os.AsyncTask;

/**
 * Created by trevorlao on 15/8/18.
 */

public class LoadingAsyncTask extends AsyncTask<Void, Integer, String> {
    private LoadingScreenContract.presenter mPresenter;

    public LoadingAsyncTask(LoadingScreenContract.presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected String doInBackground(Void... voids) {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
                publishProgress(i);
            } catch (InterruptedException e) {

            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mPresenter.publishProgress(values);
    }

    @Override
    protected void onPostExecute(String s) {
        mPresenter.startApp();
    }
}
