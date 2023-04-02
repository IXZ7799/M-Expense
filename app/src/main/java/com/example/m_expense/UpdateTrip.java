package com.example.m_expense;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class UpdateTrip extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Trip t = (Trip) getIntent().getExtras().getSerializable("TRIP");
    }
}