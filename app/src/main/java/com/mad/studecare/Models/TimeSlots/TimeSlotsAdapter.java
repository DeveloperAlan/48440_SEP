package com.mad.studecare.Models.TimeSlots;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.mad.studecare.R;
import com.mad.studecare.Utils.MenuItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by trevorlao on 5/9/18.
 */

public class TimeSlotsAdapter extends RecyclerView.Adapter<TimeSlotsAdapter.MyViewHolder> {

    private List<TimeSlots> mTimeSlotsList;
    private Context mContext;

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


    public TimeSlotsAdapter(List<TimeSlots> timeSlotsList, Context context) {
        this.mTimeSlotsList =  timeSlotsList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_timeslots, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        TimeSlots timeslot = mTimeSlotsList.get(position);
        holder.time.setText(timeslot.getTime());
        holder.date.setText(timeslot.getDate());
        holder.doctor.setText(timeslot.getDoctor());
        holder.qualifications.setText(timeslot.getQualifications());
    }

    @Override
    public int getItemCount() {
        return mTimeSlotsList.size();
    }
}
