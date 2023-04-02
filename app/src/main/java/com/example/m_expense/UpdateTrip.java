package com.example.m_expense;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class UpdateTrip extends AppCompatActivity {
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_trip);

        Trip t = (Trip) getIntent().getExtras().getSerializable("tripdetails");

        EditText nameTrip = findViewById(R.id.nameTripInput);
        EditText destination = findViewById(R.id.destinationInput);
        EditText dateTrip = findViewById(R.id.dateTripInput);
        CheckBox riskAssessment = findViewById(R.id.riskAssessmentInput);
        EditText description = findViewById(R.id.descriptionInput);
        EditText peopleAttending = findViewById(R.id.peopleAttendingInput);
        EditText transportation = findViewById(R.id.transportationInput);

        id = t.getId();
        nameTrip.setText(t.getTripName());
        destination.setText(t.getDestination());
        dateTrip.setText(t.getTripDate());
        riskAssessment.setChecked(t.getRiskAssessment());
        description.setText(t.getDescription());
        peopleAttending.setText(String.valueOf(t.getPeopleAttending()));
        transportation.setText(t.getTransportation());
    }

    public void update(View view){

    }
}