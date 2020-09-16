package com.example.assigment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment.Modal.Booking;
import com.example.assigment.Modal.chat;
import com.example.assigment.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class bookingHistoryAdapter extends RecyclerView.Adapter<bookingHistoryAdapter.ViewHolder> {
    public static  final int Booking=0;
    public static  final int Booking1=1;
    private Context mContext;
    private List<Booking> myBooking;
    FirebaseUser fuser;

    public bookingHistoryAdapter(Context mContext, List<com.example.assigment.Modal.Booking> myBooking) {
        this.mContext = mContext;
        this.myBooking = myBooking;
    }

    @NonNull
    @Override
    public bookingHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == Booking){
            View view = LayoutInflater.from(mContext).inflate(R.layout.booking_history_layout, parent, false);
            return new bookingHistoryAdapter.ViewHolder(view);
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.booking_history_layout, parent, false);
            return new bookingHistoryAdapter.ViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking booking =myBooking.get(position);
        holder.txtBookingName.setText(booking.getBookingService());
        holder.txtBookingDate.setText(booking.getBookingDate());
        holder.txtBookingTime.setText(booking.getBookingTime());
        holder.txtAppointment.setText("Appointment type");
        holder.txtDate.setText("Date:");
        holder.txtTime.setText("Time:");
    }

    @Override
    public int getItemCount() {
        return myBooking.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtBookingName, txtBookingDate, txtBookingTime,txtDate,txtTime,txtAppointment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBookingName = (TextView) itemView.findViewById(R.id.bookingName);
            txtBookingDate = (TextView) itemView.findViewById(R.id.bookingDate);
            txtBookingTime = (TextView) itemView.findViewById(R.id.bookingTime);
            txtDate=(TextView)itemView.findViewById(R.id.txtDate);
            txtTime=(TextView)itemView.findViewById(R.id.txtTime);
            txtAppointment=(TextView)itemView.findViewById(R.id.txtAppointment_type);
        }
    }

    public int getItemViewType (int position){
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (myBooking.get(position).getBookingId().equals(fuser.getUid())){
            return Booking;
        }else{
            return Booking1;
        }
    }
}
