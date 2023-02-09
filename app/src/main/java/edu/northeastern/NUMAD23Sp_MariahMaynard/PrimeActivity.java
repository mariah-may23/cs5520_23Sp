package edu.northeastern.NUMAD23Sp_MariahMaynard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
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
            runnableThread primeThread = new runnableThread();
            new Thread(primeThread).start();
        }
    }

    public void terminate(View v) {
        System.out.println("terminating");
        terminate = true;
      


    }


    public void runFindPrimes(View v) {
        if(!pressed) {

            runnableThread primeThread = new runnableThread();
            new Thread(primeThread).start();
            pressed = true;
        }
        else{
            pressed = true;
        }


    }



    class runnableThread implements Runnable {
        private volatile Boolean stop = false;
        private volatile Boolean restart = false;



        @SuppressLint("SetTextI18n")
        @Override
        public void run() {

            System.out.println("STOP" + stop);
            int i = 3;
            while (i <= INFINITY) { //start at 3 and increment forever by adding 2
                if(stop){
                    break;
                }

                int finalI = i;
                current_count = finalI;
                textHandler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                       // System.out.println("BEFORE");

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
                        //System.out.println(stop);
                        restart = pressed;
                        if (restart) {
                            prime.setText("3");
                            current_number.setText("Number Being Checked: 3");
                        }
                    }

                });
                try {
                    Thread.sleep(700); //Makes the thread sleep or be inactive for 10 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(restart) {
                    i = 3;
                    pressed = false;
                    restart = false;
                }
                else {
                    i += 2;
                }

            }

        }





    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("prime", current_prime);
        outState.putInt("count", current_count);
    }


}