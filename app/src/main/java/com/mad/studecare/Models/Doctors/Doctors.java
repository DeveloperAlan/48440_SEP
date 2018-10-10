package com.mad.studecare.Models.Doctors;

import android.media.Image;
import android.widget.ImageView;

import com.mad.studecare.Models.TimeSlots.TimeSlots;

import java.util.ArrayList;

/**
 * Created by trevorlao on 15/9/18.
 */

public class Doctors {
    private String name;
    private String specialties;
    private String qualifications;
    private int picture;
    private float rating;

    public Doctors(String name, String specialties, String qualifications, int picture, float rating, ArrayList<TimeSlots> removedSlots) {
        this.name = name;
        this.specialties = specialties;
        this.qualifications = qualifications;
        this.picture = picture;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialties() {
        return specialties;
    }

    public void setSpecialties(String specialties) {
        this.specialties = specialties;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
//    public boolean isChecked() {
//        return checked;
//    }
//
//    public void setChecked(boolean checked) {
//        this.checked = checked;
//    }
}
