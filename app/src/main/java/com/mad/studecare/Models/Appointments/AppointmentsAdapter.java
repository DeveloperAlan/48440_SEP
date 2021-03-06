package com.mad.studecare.Models.Appointments;

/**
 * Created by trevorlao on 22/8/18.
 */

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.vipulasri.timelineview.TimelineView;
import com.mad.studecare.Classes.Home.HomePresenter;
import com.mad.studecare.Classes.Home.HomeScreen;
import com.mad.studecare.Classes.Home.HomeScreenContract;
import com.mad.studecare.Models.TimeSlots.TimeSlots;
import com.mad.studecare.Models.TimeSlots.TimeSlotsList;
import com.mad.studecare.R;
import com.mad.studecare.Utils.MenuItemClickListener;
import com.mad.studecare.Utils.SortList;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.MyViewHolder> {

    private List<Appointments> mAppointmentsList;
    private HomeScreenContract.presenter mPresenter;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.appointments_picture)
        ImageView picture;
        @Nullable
        @BindView(R.id.appointments_time)
        TextView time;
        @Nullable
        @BindView(R.id.appointments_date)
        TextView date;
        @Nullable
        @BindView(R.id.appointments_timeline)
        TimelineView timeline;
        @Nullable
        @BindView(R.id.appointments_doctor_name)
        TextView name;

        public MyViewHolder(View view, int viewType) {
            super(view);
            ButterKnife.bind(this, view);

            timeline.initLine(viewType);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    public AppointmentsAdapter(List<Appointments> appointmentsList,
                               Context context,
                               HomeScreenContract.presenter presenter) {
        this.mAppointmentsList =  appointmentsList;
        this.mContext = context;
        this.mPresenter = presenter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_appointments, null);

        return new MyViewHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Appointments appointment = mAppointmentsList.get(position);

        TimeSlots timeSlot = null;
        for(TimeSlots slot : TimeSlotsList.GetInstance().GetList()) {
            if (appointment.getTimeslot().equals(slot.getId())) {
                timeSlot = slot;
            }
        }
        if(timeSlot.getDoctor().getPicture() == 4) {
            holder.picture.setImageResource(R.mipmap.doc1);
        } else if(timeSlot.getDoctor().getPicture() == 1) {
            holder.picture.setImageResource(R.mipmap.doc4);
        } else if(timeSlot.getDoctor().getPicture() == 5) {
            holder.picture.setImageResource(R.mipmap.doc5);
        } else if(timeSlot.getDoctor().getPicture() == 3) {
            holder.picture.setImageResource(R.mipmap.doc2);
        } else {
            holder.picture.setImageResource(R.mipmap.doc3);
        }
        holder.time.setText(timeSlot.getTime());
        holder.date.setText(timeSlot.getDate());
        holder.name.setText(timeSlot.getDoctor().getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.editAppointment(appointment);
            }
        });


        YoYo.with(Techniques.FadeInUp).duration(700).playOn(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return mAppointmentsList.size();
    }
}
