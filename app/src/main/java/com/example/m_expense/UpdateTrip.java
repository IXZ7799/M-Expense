package com.example.m_expense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdateTrip extends AppCompatActivity {
    int id;
    public EditText nameTrip, destination, dateTrip, description, peopleAttending, transportation;
    public CheckBox riskAssessment;
    public ImageView imageView2;
    int[] images = {R.drawable.earth_africa, R.drawable.earth_americas, R.drawable.earth_asia, R.drawable.earth_europe, R.drawable.earth_oceania};
    int currentImage = R.drawable.default_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_trip);

        Trip t = (Trip) getIntent().getExtras().getSerializable("tripdetails");

        nameTrip = findViewById(R.id.nameTripInput);
        destination = findViewById(R.id.destinationInput);
        dateTrip = findViewById(R.id.dateTripInput);
        riskAssessment = findViewById(R.id.riskAssessmentInput);
        description = findViewById(R.id.descriptionInput);
        peopleAttending = findViewById(R.id.peopleAttendingInput);
        transportation = findViewById(R.id.transportationInput);
        imageView2 = findViewById(R.id.imageView2);

        id = t.getId();
        nameTrip.setText(t.getTripName());
        destination.setText(t.getDestination());
        dateTrip.setText(t.getTripDate());
        riskAssessment.setChecked(t.getRiskAssessment());
        description.setText(t.getDescription());
        peopleAttending.setText(String.valueOf(t.getPeopleAttending()));
        transportation.setText(t.getTransportation());
        imageView2.setImageResource(t.getPicture());

        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(UpdateTrip.this, DetailsActivity.class);
            startActivity(intent);
        });
    }
    public void changeImage(View view) {
        int randomIndex = (int) (Math.random() * images.length);
        ImageView imageView = findViewById(R.id.imageView2);
        currentImage = images[randomIndex];
        imageView.setImageResource(currentImage);
    }

    public void updateDetails(View v){
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

        Trip t = new Trip(id, currentImage, nametrip, destination1, datetrip, riskassessment, description1, Integer.parseInt(String.valueOf(peopleattending)), transportation1);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        int result = dbHelper.updateTrip(t);

        if(result > 0){
            Toast.makeText(this, "Updated " + result, Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this, "Failed " + result, Toast.LENGTH_SHORT).show();
        }
    }
}
