package com.mad.studecare;

/**
 * Created by trevorlao on 15/8/18.
 */

public class LoadingPresenter implements LoadingScreenContract.presenter {

    private LoadingScreenContract.view mView;

    public LoadingPresenter(LoadingScreenContract.view view) {
        this.mView = view;
    }

    public void startLoading() {
        new LoadingAsyncTask(this).execute();
    }

    public void publishProgress(Integer... values) {
        mView.publishProgress(values);
    }

    public void startApp() {
        mView.startApp();
    }
}
