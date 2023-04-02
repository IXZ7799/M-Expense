package com.example.m_expense;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enterTripDetailsBtn = findViewById(R.id.enterTripDetailsBtn);
        enterTripDetailsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

        Button tripDetailsBtn = findViewById(R.id.tripDetailsBtn);
        tripDetailsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            startActivity(intent);
        });

        Button deleteAllDetailsBtn = findViewById(R.id.deleteAllDetailsBtn);
        deleteAllDetailsBtn.setOnClickListener(v -> new AlertDialog.Builder(MainActivity.this)
                .setTitle("Delete all trips")
                .setMessage("Are you sure you want to delete all trips?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);
                    dbHelper.deleteAllTrips();
                    Toast.makeText(MainActivity.this, "All trips deleted", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show());
    }

    private void deleteTripFromDatabase(int tripId) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.deleteTrip(tripId);
    }
}