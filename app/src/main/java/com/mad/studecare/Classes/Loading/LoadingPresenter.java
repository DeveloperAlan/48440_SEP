package com.mad.studecare.Classes.Loading;

import android.content.Context;

import com.mad.studecare.Models.Doctors.Doctors;
import com.mad.studecare.Models.Doctors.DoctorsList;
import com.mad.studecare.Models.Doctors.DoctorsSlideAdapter;
import com.mad.studecare.R;

import java.util.ArrayList;

/**
 * Created by trevorlao on 15/8/18.
 */

public class LoadingPresenter implements LoadingScreenContract.presenter {

    private LoadingScreenContract.view mView;
    private DoctorsSlideAdapter mDoctorsSlideAdapter;
    private ArrayList<Doctors> mDoctorsList = new ArrayList<>();

    public LoadingPresenter(LoadingScreenContract.view view) {
        this.mView = view;
    }

    public void startLoading(Context context) {
        // Load Singleton
        DoctorsList.initInstance();

        prepareDoctorsData();

        new LoadingAsyncTask(this).execute();
    }

    public void publishProgress(Integer... values) {
        mView.publishProgress(values);
    }

    public void startApp() {
        DoctorsList.getInstance().setList(mDoctorsList);
        mView.startApp();
    }

    public void prepareDoctorsData() {
        Doctors doctor = new Doctors("Dr. Sarah Cheung",
                "Allergist/Immunologist",
                "MD(Res), DM",
                R.mipmap.doc4,
                (float) 4.8);
        mDoctorsList.add(doctor);

        doctor = new Doctors("Dr. Faraj Mohammed",
                "Dermatologist",
                "DMSc, DMedSc",
                R.mipmap.doc1,
                (float) 4.5);
        mDoctorsList.add(doctor);

        doctor = new Doctors("Dr. Simon Nigel",
                "Urologist",
                "DS, PhD",
                R.mipmap.doc2,
                (float) 3.7);
        mDoctorsList.add(doctor);

        doctor = new Doctors("Dr. Richard Seddejer",
                "Gynaecologist",
                "MM, MMed",
                R.mipmap.doc3,
                (float) 4.2);
        mDoctorsList.add(doctor);

        doctor = new Doctors("Dr. Michelle Matthews",
                "Pediatrician",
                "MSc",
                R.mipmap.doc5,
                (float) 4);
        mDoctorsList.add(doctor);
    }
}
