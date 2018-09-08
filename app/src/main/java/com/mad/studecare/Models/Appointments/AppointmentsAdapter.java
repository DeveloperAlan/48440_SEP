package com.mad.studecare.Models.Appointments;

/**
 * Created by trevorlao on 22/8/18.
 */

import android.content.Context;
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

import com.mad.studecare.R;
import com.mad.studecare.Utils.MenuItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.MyViewHolder> {

    private List<Appointments> mAppointmentsList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.appointments_card_doctor)
        TextView doctor;
        @Nullable
        @BindView(R.id.appointments_card_time_date)
        TextView timeDate;
        @Nullable
        @BindView(R.id.appointments_card_description)
        TextView description;
        @Nullable
        @BindView(R.id.appointments_card_colour)
        ImageView status;
        @Nullable
        @BindView(R.id.appointments_card_edit)
        ImageView edit_button;
        @BindView(R.id.appointments_card_cancel)
        ImageView cancel_button;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }


    public AppointmentsAdapter(List<Appointments> appointmentsList, Context context) {
        this.mAppointmentsList =  appointmentsList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_appointments, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Appointments appointment = mAppointmentsList.get(position);
        holder.doctor.setText(appointment.getDoctor());
        holder.timeDate.setText(appointment.getTimeDate());
        holder.description.setText(appointment.getDescription());

        switch (appointment.getStatus()) {
            // Pending
            case 0: holder.status.setBackgroundColor(mContext.getResources().getColor(R.color.appointment_status_pending));
            break;
            // Confirmed
            case 1: holder.status.setBackgroundColor(mContext.getResources().getColor(R.color.appointment_status_confirmed));
            break;
            // Cancelled
            case 2: holder.status.setBackgroundColor(mContext.getResources().getColor(R.color.appointment_status_cancelled));
            break;
        }

        // Edit Button
        holder.edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        // Cancel Button
        holder.cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    // Use for popup menus from button clicks
//    private void showPopupMenu(View view) {
//        PopupMenu popup = new PopupMenu(mContext, view);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.menu_album, popup.getMenu());
//        popup.setOnMenuItemClickListener(new MenuItemClickListener());
//        popup.show();
//    }

    @Override
    public int getItemCount() {
        return mAppointmentsList.size();
    }
}
