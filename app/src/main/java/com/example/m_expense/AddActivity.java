package com.example.m_expense;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    int[] images = {R.drawable.earth_africa, R.drawable.earth_americas, R.drawable.earth_asia, R.drawable.earth_europe, R.drawable.earth_oceania};
    int currentImage = R.drawable.default_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {
            Intent backIntent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(backIntent);
        });
        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(v -> saveDetails());
        Button selectBtn = findViewById(R.id.selectBtn);
        selectBtn.setOnClickListener(v -> changeImage());
    }

    private void changeImage() {
        int randomIndex = (int) (Math.random() * images.length);
        ImageView imageView = findViewById(R.id.imageView);
        currentImage = images[randomIndex];
        imageView.setImageResource(currentImage);
    }
    private void saveDetails() {
        try (DatabaseHelper dbHelper = new DatabaseHelper(this)){

            EditText nameTrip = findViewById(R.id.nameTripInput);
            EditText destination = findViewById(R.id.destinationInput);
            EditText dateTrip = findViewById(R.id.dateTripInput);
            CheckBox riskAssessment = findViewById(R.id.riskAssessmentInput);
            EditText description = findViewById(R.id.descriptionInput);
            EditText peopleAttending = findViewById(R.id.peopleAttendingInput);
            EditText transportation = findViewById(R.id.transportationInput);

            String nametrip = nameTrip.getText().toString();
            String destination1 = destination.getText().toString();
            String datetrip = dateTrip.getText().toString();
            Boolean riskassessment = riskAssessment.isChecked();
            String description1 = description.getText().toString();
            int peopleattending = 0;
            String peopleattending1 = peopleAttending.getText().toString();
            if (!peopleattending1.isEmpty()) {
                peopleattending = Integer.parseInt(peopleattending1);
            }
            String transportation1 = transportation.getText().toString();

            if (nametrip.isEmpty() || destination1.isEmpty() || datetrip.isEmpty()) {
                Toast.makeText(AddActivity.this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Do you want to save this trip?");
                int finalPeopleattending = peopleattending;
                builder.setPositiveButton("Yes", (dialog, which) -> {
                    try (DatabaseHelper db = new DatabaseHelper(this)) {
                        Trip t = new Trip(currentImage, nametrip, destination1, datetrip, riskassessment, description1, finalPeopleattending, transportation1);
                        long tripId = db.insertDetails(t);
                        Toast.makeText(this, "Trip has been created with id: " + tripId, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, DetailsActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
}