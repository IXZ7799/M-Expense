package com.example.m_expense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tripdetails";

    public static final String ID_COLUMN = "user_id";
    public static final String PICTURE_COLUMN = "picture_id";
    public static final String TRIPNAME_COLUMN = "trip_name";
    public static final String DESTINATION_COLUMN = "destination";
    public static final String TRIPDATE_COLUMN = "trip_date";
    public static final String RISKASSESSMENT_COLUMN = "risk_assessment";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String PEOPLEATTENDING_COLUMN = "people_attending";
    public static final String TRANSPORTATION_COLUMN = "transportation";

    private final SQLiteDatabase database;

    private static final String DATABASE_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s INT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s INT, " +
                    "%s TEXT, " +
                    "%s INT, " +
                    "%s TEXT) ",

            DATABASE_NAME, ID_COLUMN, PICTURE_COLUMN, TRIPNAME_COLUMN, DESTINATION_COLUMN, TRIPDATE_COLUMN, RISKASSESSMENT_COLUMN, DESCRIPTION_COLUMN, PEOPLEATTENDING_COLUMN, TRANSPORTATION_COLUMN);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);

        Log.v(this.getClass().getName(), DATABASE_NAME +
                " database upgrade to version " + newVersion + " - old data lost");
        onCreate(db);
    }

    public long insertDetails(Trip t){
        ContentValues rowValues = new ContentValues();

        rowValues.put(PICTURE_COLUMN, t.getPicture());
        rowValues.put(TRIPNAME_COLUMN, t.getTripName());
        rowValues.put(DESTINATION_COLUMN, t.getDestination());
        rowValues.put(TRIPDATE_COLUMN, t.getTripDate());
        rowValues.put(RISKASSESSMENT_COLUMN, t.getRiskAssessment());
        rowValues.put(DESCRIPTION_COLUMN, t.getDescription());
        rowValues.put(PEOPLEATTENDING_COLUMN, t.getPeopleAttending());
        rowValues.put(TRANSPORTATION_COLUMN, t.getTransportation());

        return database.insertOrThrow(DATABASE_NAME, null, rowValues);
    }

    public ArrayList<Trip> getDetails(){
        Cursor results = database.query("tripdetails", new String[] {"user_id", "picture_id", "trip_name", "destination", "trip_date", "risk_assessment","description", "people_attending", "transportation"},
                null, null, null, null, "trip_name");

        ArrayList<Trip> listTrips = new ArrayList<>();

        results.moveToFirst();
        while (!results.isAfterLast()) {
            int id = results.getInt(0);
            int picture = results.getInt(1);
            String trip_name = results.getString(2);
            String destination = results.getString(3);
            String trip_date = results.getString(4);
            Boolean risk_assessment = results.getInt(5) == 1;
            String description = results.getString(6);
            int people_attending = results.getInt(7);
            String transportation = results.getString(8);

            listTrips.add(new Trip(id, picture, trip_name, destination, trip_date, risk_assessment, description, people_attending, transportation));
            results.moveToNext();
        }

        results.close();
        database.close();
        return listTrips;
    }

    public void deleteAllTrips() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("tripdetails", null, null);
    }

    public int deleteTrip(Trip trip) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("tripdetails", "user_id=?", new String[]{String.valueOf(trip.getId())});
    }

    public int updateTrip(Trip trip) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues rowValues = new ContentValues();
        return db.update("tripdetails", rowValues, "user_id=?", new String[]{String.valueOf(trip.getId())});
    }

}