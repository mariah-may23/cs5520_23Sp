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
    int INFINITY = Integer.MAX_VALUE;
    private int current_prime;
    private int current_count = 3;


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
     

        runnableThread primeThread = new runnableThread();
        new Thread(primeThread).start();

    }

    class runnableThread implements Runnable {
        private volatile Boolean stop = false;

        @Override
        public void run() {


            System.out.println("STOP" + stop);
            for (int i = current_count; i <= INFINITY; i += 2) { //start at 3 and increment forever by adding 2
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
                     //   System.out.println("next");

                        if (!flag) { // after checking, if the fag hasn't changed, we know the number is prime
                            String last_prime = String.valueOf(finalI);
                            prime.setText(last_prime);
                            current_prime = finalI;
                        }
                     //   System.out.println("AFTER");
                        flag = false;
                        stop = terminate;
                        System.out.println(stop);

                    }
                });
                try {
                    Thread.sleep(1000); //Makes the thread sleep or be inactive for 10 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
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