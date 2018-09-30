package com.mad.studecare.Models.Doctors;

import android.content.Context;
import android.graphics.Color;
import android.opengl.Visibility;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mad.studecare.Classes.Appointment.AppointmentScreenContract;
import com.mad.studecare.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by trevorlao on 15/9/18.
 */

public class DoctorsSlideAdapter extends RecyclerView.Adapter<DoctorsSlideAdapter.ViewHolder> {

    private Context mContext;
    private AppointmentScreenContract.presenter mPresenter;
    private ArrayList<Doctors> mDoctorsList = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.slider_picture)
        ImageView image;
        @Nullable
        @BindView(R.id.slider_name)
        TextView name;
        @Nullable
        @BindView(R.id.slider_rating)
        RatingBar rating;
        @Nullable
        @BindView(R.id.slider_specialties)
        TextView specialties;
        @Nullable
        @BindView(R.id.slider_checkbox)
        CheckBox selected;
        @Nullable
        @BindView(R.id.slider_rating_holder)
        RelativeLayout sliderHolder;

        public ViewHolder(View v) {
            super(v);
            // Bind Butterknife
            ButterKnife.bind(this, v);
        }
    }
    public DoctorsSlideAdapter(ArrayList<Doctors> doctors, Context context, AppointmentScreenContract.presenter presenter) {
        this.mDoctorsList = doctors;
        this.mContext = context;
        this.mPresenter = presenter;
    }

    @Override
    public DoctorsSlideAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_doctors, parent, false);

        return new DoctorsSlideAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DoctorsSlideAdapter.ViewHolder holder, int position) {
        // Getting specific doctor
        final Doctors doctor = mDoctorsList.get(position);

        holder.image.setImageResource(doctor.getPicture());
        holder.name.setText(doctor.getName());
        holder.rating.setRating(doctor.getRating());
        holder.specialties.setText(doctor.getSpecialties());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.selected.isChecked()) {
                    mPresenter.selectDoctor(doctor, false);
                    holder.selected.setChecked(false);
                    holder.image.setColorFilter(Color.argb(0,0,0,0));
//                    holder.sliderHolder.setVisibility(View.VISIBLE);
                } else {
                    mPresenter.selectDoctor(doctor, true);
                    holder.selected.setChecked(true);
                    holder.image.setColorFilter(Color.argb(50,50,205, 50));
//                    holder.sliderHolder.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDoctorsList.size();
    }
}
