package edu.northeastern.NUMAD23Sp_MariahMaynard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class PrimeActivity extends AppCompatActivity {
    TextView prime;
    TextView current_number;
    private Handler textHandler = new Handler();
    boolean flag = false;
    boolean terminate = false;
    boolean reset = false;
    int INFINITY = Integer.MAX_VALUE;
    private int current_prime;
    private int current_count = 3;
    private boolean pressed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime);
        prime = findViewById(R.id.prime_number);
        current_number = findViewById(R.id.current_number);
        System.out.println("creating");

        if(savedInstanceState != null) {
            current_prime = savedInstanceState.getInt("prime");
            prime.setText(String.valueOf(current_prime));

            current_count = savedInstanceState.getInt("count");
            System.out.println("CURRENT COUNT" + current_count);
            current_number.setText(String.valueOf(current_count));
            View v = getWindow().getCurrentFocus();
            runFindPrimes(v);
        }
    }

    public void terminate(View v) {
        System.out.println("terminating");
        terminate = true;



    }


    public void runFindPrimes(View v) {
        //System.out.println("1");
        if(!reset) {
            System.out.println("1");

            runnableThread primeThread = new runnableThread();
            new Thread(primeThread).start();
            reset = true;
        }
        else{
            System.out.println("2");

            pressed = true;
            if(terminate) {
                runnableThread primeThread = new runnableThread();
                new Thread(primeThread).start();
                reset = true;
            }

            terminate = false;
            System.out.println("pressed" + pressed);
            System.out.println("NOT TERMINATED");
        }


    }


    class runnableThread implements Runnable {
        private volatile Boolean stop = false;
        private volatile Boolean restart = false;



        @SuppressLint("SetTextI18n")
        @Override
        public void run() {

            System.out.println("STOP" + stop);

            while (current_count <= INFINITY) { //start at 3 and increment forever by adding 2
                System.out.println(pressed);
                if(stop){
                    break;
                }
                int finalI = current_count;
                //current_count = finalI;
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
                        flag = false;
                        stop = terminate;
                        restart = pressed;
                        System.out.println("STOP" + stop);
                        System.out.println("RESTRAT" + restart);
                        if (restart) {
                            System.out.println("restart" + restart);
                            prime.setText("3");
                            current_number.setText("Number Being Checked: 3");
                            current_count = 3;
                            pressed = false;
                        }
                    }

                });
                try {
                    Thread.sleep(1000); //Makes the thread sleep or be inactive for 10 seconds

                    current_count += 2;


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            current_count += 2;
            stop = false;

        }





    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("prime", current_prime);
        outState.putInt("count", current_count);
    }

    @Override
    public void onBackPressed() {

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