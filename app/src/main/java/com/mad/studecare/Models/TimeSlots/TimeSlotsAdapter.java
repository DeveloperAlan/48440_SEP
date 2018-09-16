package com.mad.studecare.Models.TimeSlots;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.mad.studecare.Classes.Appointment.AppointmentScreen;
import com.mad.studecare.Classes.Appointment.AppointmentScreenContract;
import com.mad.studecare.R;
import com.mad.studecare.Utils.MenuItemClickListener;

import java.sql.Time;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by trevorlao on 5/9/18.
 */

public class TimeSlotsAdapter extends RecyclerView.Adapter<TimeSlotsAdapter.MyViewHolder> {

    private List<TimeSlots> mTimeSlotsList;
    private Context mContext;
    private AppointmentScreenContract.presenter mPresenter;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.timeslots_card_time)
        TextView time;
        @Nullable
        @BindView(R.id.timeslots_card_date)
        TextView date;
        @Nullable
        @BindView(R.id.timeslots_card_doctor)
        TextView doctor;
        @Nullable
        @BindView(R.id.timeslots_card_qualifications)
        TextView qualifications;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public TimeSlotsAdapter(List<TimeSlots> timeSlotsList, Context context, AppointmentScreenContract.presenter presenter) {
        this.mTimeSlotsList =  timeSlotsList;
        this.mContext = context;
        this.mPresenter = presenter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_timeslots, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final TimeSlots timeslot = mTimeSlotsList.get(position);

        holder.time.setText(timeslot.getTime());
        holder.date.setText(timeslot.getDate());
        holder.doctor.setText(timeslot.getDoctor().getName());
        holder.qualifications.setText(timeslot.getDoctor().getSpecialties());
    }

    @Override
    public int getItemCount() {
        return mTimeSlotsList.size();
    }
}
