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
import android.widget.Toast;

public class LocationActivity extends AppCompatActivity {

    TextView latitude;
    TextView longitude;
    TextView distance;
    Handler textHandler;
    float distanceInMeters;
    Location startLocation = null;
    float totalDistance = 0;
    LocationListener listener;
    LocationListener listener1;

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
    LocationActivity.runnableThread trackingThread;


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
        if (ActivityCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //totalDistance = 0;
            System.out.println("here");
            ActivityCompat.requestPermissions(LocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        } else {
            totalDistance = 0;
            reset = true;
            startLocation = null;
            listener1 = new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    if (!starting) {
                        //System.out.println("HERE2");
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
                    //System.out.println("METERSSSS DISTANCEEEEEE" + totalDistance);

                }
            };

            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener1);

            View v = findViewById(android.R.id.content).getRootView();
            runLocation(v);
        }
    }

//        if (ActivityCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
////            if(listener != null){
////            manager.removeUpdates(listener);}
//            trackingThread
//            if(listener != null) {
//                manager.removeUpdates(listener);
//            }
//            if(listener1!=null) {
//                manager.removeUpdates(listener1);
//            }
//            AlertDialog.Builder popup1 = new AlertDialog.Builder(this);
//            popup1.setTitle("Currently Tracking Your Location");
//            popup1.setMessage("You have given this activity permission to show your current latitude and longitude.");
//            popup1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
//                @SuppressLint("MissingPermission")
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    ActivityCompat.requestPermissions(LocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
//                    listener1 = new LocationListener() {
//                        @Override
//                        public void onLocationChanged(@NonNull Location location) {
//                            if(!starting) {
//                                System.out.println("HERE2");
//                                starting = true;
//                                startLat = location.getLatitude();
//                                startLong = location.getLongitude();
//                                atStart.setLatitude(startLat);
//                                atStart.setLongitude(startLong);
//                            }
//
//                            currentLat = location.getLatitude();
//                            currentLong = location.getLongitude();
//                            atCurrent.setLatitude(currentLat);
//                            atCurrent.setLongitude(currentLong);
//
//                            float distanceInMeters = 0;
//                            if (startLocation != null) {
//                                totalDistance = 0;
//                                distanceInMeters = startLocation.distanceTo(location);
//                            }
//                            totalDistance += distanceInMeters * 0.000621371f;
//                            startLocation = location;
//                            System.out.println("METERSSSS DISTANCEEEEEE" + totalDistance);
//
//                        }
//                    };
//
//                    manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener1);
//
//                    View v = findViewById(android.R.id.content).getRootView();
//                    runLocation(v);
//                }
//            });
//            popup1.show();
//        }
//
//



    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                totalDistance = 0;
                reset = true;
                startLocation = null;
                // Permission was granted, proceed with using location services
                System.out.println("**************************");
                listener = new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        if(!starting) {
                            //System.out.println("HERE2");
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
                        //System.out.println("METERSSSS DISTANCEEEEEE" + totalDistance);

                    }
                };

                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);

                View v = findViewById(android.R.id.content).getRootView();
                runLocation(v);
            }


        } else {
                // Permission was denied, show a message or disable location functionality
                //Toast.makeText(this, "Location permission was not granted", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder popup1 = new AlertDialog.Builder(this);
            popup1.setTitle("Need Your Location");
            popup1.setMessage("You have not given this activity permission to show your current latitude and longitude. Please go to settings to allow access.");
            }
        }



    public void runLocation(View v){
        trackingThread = new LocationActivity.runnableThread();
        new Thread(trackingThread).start();
    }

    class runnableThread implements Runnable {
        boolean isReset = false;
        boolean interrupt = false;
        float dist;
        @SuppressLint({"SetTextI18n", "MissingPermission"})
        @Override
        public void run() {
           // System.out.println("HERE1");

            while (!interrupt){ //start at 3 and increment forever by adding 2
                isReset = reset;
               // System.out.println("Restinng2" + isReset);
                //System.out.println("HERE3");
                textHandler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        if(reset) { //if the user clicked reset, set distance back to zero
                           // System.out.println("cliedk resettttttttttttt");
                            distance.setText("RESET WAS CLICKED");
                            reset = false;
                        }

                        distance.setText(String.valueOf(totalDistance));

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
                //trackingThread.interrupt = true;

            }
        });
        popup.show();

    }

//    public float calculateDistance(){
////        atStart.setLongitude(-71.0892);
////        atStart.setLatitude(42.3398);
////        atCurrent.setLongitude(-71.0817);
////        atCurrent.setLatitude(42.3471);
//        System.out.println("START LAT"+startLat);
//        System.out.println("start long"+startLong);
//        System.out.println("cur lat"+ currentLat);
//        System.out.println("cur long"+currentLong);
//
//        float distMeters = atStart.distanceTo(atCurrent);
//       // System.out.println("DISTANCEEEEEEEEEEEEEEEEEEEEEEE" + distMeters*0.000621371f);
//        return distMeters * 0.000621371f;
//
//
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Interrupt the thread to stop its execution
        trackingThread.interrupt = true;
        if(listener != null) {
            manager.removeUpdates(listener);
        }
        if(listener1 !=null) {
            manager.removeUpdates(listener1);
        }
        starting = false;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure?");
        builder.setMessage("Your distance will be lost if you go back. Proceed?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Call super method to exit activity
                LocationActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton("Go Back", null);
        builder.show();
    }

}









