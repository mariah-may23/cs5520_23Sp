package edu.northeastern.NUMAD23Sp_MariahMaynard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PrimeActivity extends AppCompatActivity {
    TextView prime;
    TextView current_number;
    private Handler textHandler;
    boolean flag = false;
    boolean terminate = false;
    boolean reset = false;
    int INFINITY = Integer.MAX_VALUE;
    private int current_prime;
    private int current_count = 3;
    private boolean pressed = false;
    private boolean terminate_reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime);
        prime = findViewById(R.id.prime_number);
        current_number = findViewById(R.id.current_number);
        textHandler = new Handler();


        if(savedInstanceState != null) {
            // reset the prime number that was there
            // start counting at the same number left on
            current_prime = savedInstanceState.getInt("prime");
            prime.setText(String.valueOf(current_prime));

            current_count = savedInstanceState.getInt("count");

            current_number.setText(String.valueOf(current_count));
            View v = getWindow().getCurrentFocus();
            runFindPrimes(v);
            terminate =  savedInstanceState.getBoolean("terminate");

        }
    }

    public void terminate(View v) {
        terminate = true;
    }

    public void runFindPrimes(View v) {
        // check if the count was already running
        if(!reset) {
            runnableThread primeThread = new runnableThread();

            new Thread(primeThread).start();
            reset = true;
        }
        // if the count was already running
        else{
            pressed = true;
            if(terminate) {
                runnableThread primeThread = new runnableThread();
                new Thread(primeThread).start();
                reset = true;
            }
            terminate = false;

        }
    }

    class runnableThread implements Runnable {
        private volatile Boolean stop = false;
        private volatile Boolean restart = false;



        @SuppressLint("SetTextI18n")
        @Override
        public void run() {


            while (current_count <= INFINITY) { //start at 3 and increment forever by adding 2
                if(stop){
                    terminate = true;
                    break;
                }
                int finalI = current_count;

                textHandler.post(new Runnable() {

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {

                        current_number.setText("Number Being Checked: " + finalI); // set the current number being checked

                        for (int j = 2; j < finalI; j++) {
                            if (finalI % j == 0) { // the number should only be divisible by one and itself
                                flag = true;
                                break;
                            }
                        }

                        if (!flag) { // after checking, if the fag hasn't changed, we know the number is prime
                            String last_prime = String.valueOf(finalI);
                            prime.setText(last_prime);
                            current_prime = finalI;
                        }
                        // check for any changes as a result of the button being pushed
                        flag = false;
                        stop = terminate;
                        restart = pressed;

                        if (restart) { // if terminated was pushed, then find primes was pushed
                            prime.setText("3");
                            current_number.setText("Number Being Checked: 3");
                            current_count = 3;
                            pressed = false;
                        }
                    }

                });

                try {
                    Thread.sleep(1000); //Makes the thread sleep or be inactive for 10 seconds
                    if(!stop) {
                        current_count += 2;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if(!stop) {
                stop = false;
            }

        }


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("prime", current_prime);
        outState.putInt("count", current_count);
        outState.putBoolean("terminate", terminate);
    }

    @Override
    public void onBackPressed() {
        // alert the user that they are about to leave the current count
        AlertDialog.Builder alert = new AlertDialog.Builder(PrimeActivity.this);
        alert.setTitle("Exiting Prime Directive!");
        alert.setMessage("Going back will terminate the search. Are you sure you want to go back?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });

        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();

    }
}