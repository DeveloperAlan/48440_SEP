package com.mad.studecare.Models.Doctors;

import android.content.Context;
import android.media.Rating;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mad.studecare.Models.Doctors.Doctors;
import com.mad.studecare.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by trevorlao on 15/9/18.
 */

public class DoctorsSlideAdapter extends RecyclerView.Adapter<DoctorsSlideAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Doctors> mDoctorsList = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.slider_picture)
        ImageView image;
        @Nullable
        @BindView(R.id.slider_name)
        TextView name;
        @Nullable
        @BindView(R.id.slider_qualifications)
        TextView qualifications;
        @Nullable
        @BindView(R.id.slider_specialties)
        TextView specialties;
        @Nullable
        @BindView(R.id.slider_rating)
        RatingBar rating;

        public ViewHolder(View v) {
            super(v);
            // Bind Butterknife
            ButterKnife.bind(this, v);
        }
    }
    public DoctorsSlideAdapter(ArrayList<Doctors> doctors, Context context) {
        this.mDoctorsList = doctors;
        this.mContext = context;
    }

    @Override
    public DoctorsSlideAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_doctors_screen, parent, false);

        return new DoctorsSlideAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DoctorsSlideAdapter.ViewHolder holder, int position) {
        // Getting specific doctor
        Doctors doctor = mDoctorsList.get(position);

        holder.image.setImageResource(doctor.getPicture());
        holder.name.setText(doctor.getName());
        holder.qualifications.setText(doctor.getQualifications());
        holder.specialties.setText(doctor.getSpecialties());
        holder.rating.setRating(doctor.getRating());
    }

    @Override
    public int getItemCount() {
        return mDoctorsList.size();
    }
}
