package com.mad.studecare;

/**
 * Created by trevorlao on 22/8/18.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.MyViewHolder> {

    private List<Appointments> mAppointmentsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title =  view.findViewById(R.id.title);
            genre = view.findViewById(R.id.genre);
            year =  view.findViewById(R.id.year);
        }
    }


    public AppointmentsAdapter(List<Appointments> appointmentsList) {
        this.mAppointmentsList =  appointmentsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_appointments, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Appointments appointment = mAppointmentsList.get(position);
        holder.title.setText(appointment.getTitle());
        holder.genre.setText(appointment.getGenre());
        holder.year.setText(appointment.getYear());
    }

    @Override
    public int getItemCount() {
        return mAppointmentsList.size();
    }
}
