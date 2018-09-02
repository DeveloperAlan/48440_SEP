package com.mad.studecare.Models.Appointments;

/**
 * Created by trevorlao on 22/8/18.
 */

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

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.MyViewHolder> {

    private List<Appointments> mAppointmentsList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.title)
        TextView title;
        @Nullable
        @BindView(R.id.genre)
        TextView genre;
        @Nullable
        @BindView(R.id.year)
        TextView year;
        @Nullable
        @BindView(R.id.appointments_overflow)
        ImageView mOverflow;

        public TextView test;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            test = view.findViewById(R.id.genre);

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
        holder.title.setText(appointment.getTitle());
        holder.genre.setText(appointment.getGenre());
        holder.year.setText(appointment.getYear());
        holder.test.setText(appointment.getGenre());

        holder.mOverflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.mOverflow);
            }
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MenuItemClickListener());
        popup.show();
    }

    @Override
    public int getItemCount() {
        return mAppointmentsList.size();
    }
}
