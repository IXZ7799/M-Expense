package com.example.m_expense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    RecyclerView.Adapter myTripAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.detailsText);
        layoutManager = new LinearLayoutManager(this);

        try (DatabaseHelper db = new DatabaseHelper(this)) {
            ArrayList<Trip> details = db.getDetails();
            myTripAdapter = new TripAdapter(details, this);
            recyclerView.setAdapter(myTripAdapter);
            recyclerView.setLayoutManager(layoutManager);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}