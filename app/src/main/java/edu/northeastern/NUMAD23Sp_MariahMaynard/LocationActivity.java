package edu.northeastern.NUMAD23Sp_MariahMaynard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {

    TextView latitude;
    TextView longitude;
    TextView distance;
    Handler textHandler;
    float distanceInMeters;
    Location startLocation = null;
    float totalDistance = 0;

    LocationManager manager;

    boolean starting = false;
    boolean reset = false;

    double startLat = 0;
    double startLong = 0;
    double currentLat = 0;
    double currentLong = 0;

    Location startLoc;
    Location atStart;
    Location atCurrent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        latitude = findViewById(R.id.textView6);
        longitude = findViewById(R.id.textView8);
        distance = findViewById(R.id.textView9);
        textHandler = new Handler();
        atStart = new Location("start");
        atCurrent = new Location("current");
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
                    System.out.println("HERE2");
                    starting = true;
                    startLat = location.getLatitude();
                    startLong = location.getLongitude();
                    atStart.setLatitude(startLat);
                    atStart.setLongitude(startLong);
                }

                currentLat = location.getLatitude();
                currentLong = location.getLongitude();
                atCurrent.setLatitude(currentLat);
                atCurrent.setLongitude(currentLong);

                float distanceInMeters = 0;
                if (startLocation != null) {
                    distanceInMeters = startLocation.distanceTo(location);
                }
                totalDistance += distanceInMeters * 0.000621371f;
                startLocation = location;
                System.out.println("METERSSSS DISTANCEEEEEE" + totalDistance);

            }
        };

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);

        View v = findViewById(android.R.id.content).getRootView();
        this.runLocation(v);


    }

    public void runLocation(View v){
        LocationActivity.runnableThread trackingThread = new LocationActivity.runnableThread();
        new Thread(trackingThread).start();
    }

    class runnableThread implements Runnable {
        boolean isReset = false;
        float dist;
        @SuppressLint({"SetTextI18n", "MissingPermission"})
        @Override
        public void run() {
            System.out.println("HERE1");

            while (true){ //start at 3 and increment forever by adding 2
                isReset = reset;
                System.out.println("Restinng2" + isReset);
                System.out.println("HERE3");
                textHandler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        if(reset) { //if the user clicked reset, set distance back to zero
                            System.out.println("cliedk resettttttttttttt");
                            distance.setText("RESET WAS CLICKED");
                            reset = false;
                        }

                        distance.setText("Distance: " + totalDistance);

                        latitude.setText(String.valueOf(currentLat));
                        longitude.setText(String.valueOf(currentLong));
                    }
                });
                try {
                    Thread.sleep(1000); //Makes the thread sleep or be inactive for 1 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }


    }



    public void resetLocation(View v) {
        AlertDialog.Builder popup = new AlertDialog.Builder(this);
        popup.setTitle("Resetting Location");
        popup.setMessage("This action will reset your total distance.");
        popup.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                totalDistance = 0;
                reset = true;
                startLocation = null;

            }
        });
        popup.show();

    }


}









