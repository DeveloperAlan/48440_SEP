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

    private List<TimeSlots> mAppointmentsList;
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
        @BindView(R.id.timeSlots_overflow)
        ImageView mOverflow;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public TimeSlotsAdapter(List<TimeSlots> appointmentsList, Context context) {
        this.mAppointmentsList =  appointmentsList;
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
        TimeSlots appointment = mAppointmentsList.get(position);
        holder.title.setText(appointment.getTitle());
        holder.genre.setText(appointment.getGenre());
        holder.year.setText(appointment.getYear());

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
