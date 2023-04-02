package com.example.m_expense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    RecyclerView.Adapter myTripAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchView searchView;

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
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try (DatabaseHelper db = new DatabaseHelper(this)) {
            ArrayList<Trip> details = db.getDetails();
            myTripAdapter = new TripAdapter(details, this);
            recyclerView.setAdapter(myTripAdapter);
            recyclerView.setLayoutManager(layoutManager);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<Trip> details = new ArrayList<>();
        myTripAdapter = new TripAdapter(details, this);
        recyclerView.setAdapter(myTripAdapter);
        return true;
    }
}