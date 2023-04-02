package com.example.m_expense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder> {
    private final ArrayList<Trip> trips;
    private final Context context;

    public TripAdapter(ArrayList<Trip> trips, Context context) {
        this.trips = trips;
        this.context = context;
    }

    public static class TripViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNameTripInput, tvDestination, tvDateTrip, tvRiskAssessment, tvDescription, tvPeopleAttending, tvTransportation;
        public ImageView imageView1;
        public ImageButton deleteBtn;

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameTripInput = itemView.findViewById(R.id.tvNameTripInput);
            tvDestination = itemView.findViewById(R.id.tvDestination);
            tvDateTrip = itemView.findViewById(R.id.tvDateTripInput);
            tvRiskAssessment = itemView.findViewById(R.id.tvRiskAssessmentInput);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPeopleAttending = itemView.findViewById(R.id.tvPeopleAttending);
            tvTransportation = itemView.findViewById(R.id.tvTransportation);
            imageView1 = itemView.findViewById(R.id.imageView1);
        }
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trip, parent, false);
        return new TripViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        holder.tvNameTripInput.setText(trips.get(position).getTripName());
        holder.tvDestination.setText(trips.get(position).getDestination());
        holder.tvDateTrip.setText(trips.get(position).getTripDate());
        holder.tvRiskAssessment.setText(trips.get(position).getRiskAssessment() ? "Yes" : "No");
        holder.tvDescription.setText(trips.get(position).getDescription());
        holder.tvPeopleAttending.setText(String.valueOf(trips.get(position).getPeopleAttending()));
        holder.tvTransportation.setText(trips.get(position).getTransportation());
        holder.imageView1.setImageResource(trips.get(position).getPicture());

        holder.tvNameTripInput.setFocusable(false);
        holder.tvDestination.setFocusable(false);
        holder.tvDateTrip.setFocusable(false);
        holder.tvRiskAssessment.setFocusable(false);
        holder.tvDescription.setFocusable(false);
        holder.tvPeopleAttending.setFocusable(false);
        holder.tvTransportation.setFocusable(false);

        holder.deleteBtn.setOnClickListener(v -> {
            DatabaseHelper db = new DatabaseHelper(context);
            int result = db.deleteTrip(trips.get(position));

            if(result > 0){
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

}
