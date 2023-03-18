package edu.northeastern.NUMAD23Sp_MariahMaynard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {

    TextView latitude;
    TextView longitude;
    TextView distance;


    LocationManager manager;

    boolean starting = false;

    double startLat = 0;
    double startLong = 0;
    double currentLat = 0;
    double currentLong = 0;
    double totalDistance = 0;
    Location startLoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        latitude = findViewById(R.id.textView6);
        longitude = findViewById(R.id.textView8);
        distance = findViewById(R.id.textView9);
       // startLoc = new Location("startLocation");

        // instance to interact with location
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //ask permission
        if (ActivityCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            AlertDialog.Builder popup = new AlertDialog.Builder(this);
            popup.setTitle("Please Allow US to Track Your Location");
            popup.setMessage("This activity shows your current latitude and longitude. To work, it needs you to grant location access");
            popup.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(LocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                }
            });
            popup.show();
        } else {
            AlertDialog.Builder popup = new AlertDialog.Builder(this);
            popup.setTitle("Currently Tracking Your Location");
            popup.setMessage("You have given this activity permission to show your current latitude and longitude.");
            popup.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(LocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
                }
            });
        }

        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                if(!starting) {
                    starting = true;

                    //startLoc.setLatitude(location.getLatitude());
                    //startLoc.setLongitude(location.getLongitude());
                    startLat = location.getLatitude();
                    startLong = location.getLongitude();
                }

                    latitude.setText(String.valueOf(location.getLatitude()));
                    longitude.setText(String.valueOf(location.getLongitude()));
                    setTotalDistance(location);

            }
        };

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);

    }


    public void resetLocation(View v) {
        AlertDialog.Builder popup = new AlertDialog.Builder(this);
        popup.setTitle("Resetting Location");
        popup.setMessage("This action will reset your total distance.");
        popup.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                totalDistance = 0;
                String dText = R.string.total_distance + "0";
                distance.setText(dText);



            }
        });
        popup.show();

    }

    public void setTotalDistance(Location location){
        Location locationA = new Location("point A");

        locationA.setLatitude(startLat);
        locationA.setLongitude(startLong);

        Location locationB = new Location("point B");

        locationB.setLatitude(location.getLatitude());
        locationB.setLongitude(location.getLongitude());

        float distance = locationA.distanceTo(locationB);
        System.out.println(distance);


    }
}









