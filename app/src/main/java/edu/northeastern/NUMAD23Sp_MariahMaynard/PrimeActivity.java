package edu.northeastern.NUMAD23Sp_MariahMaynard;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class PrimeActivity extends AppCompatActivity {
    TextView prime;
    TextView current_number;
    private Handler textHandler = new Handler();
    boolean flag = false;
    int INFINITY = Integer.MAX_VALUE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime);
        prime = findViewById(R.id.prime_number);
        current_number = findViewById(R.id.current_number);
        System.out.println("creating");

    }

    public void here(View v) {
        System.out.println("clicked");
    }

    public void runFindPrimes(View v) {
        System.out.println("HERE");
        runnableThread primeThread = new runnableThread();
        new Thread(primeThread).start();
        System.out.println("HERE");
    }

    class runnableThread implements Runnable {

        @Override
        public void run() {
            while (!flag) {
                for (int i = 3; i <= INFINITY; i += 2) { //start at 3 and increment forever by adding 2
                    int finalI = i;
                    textHandler.post(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            System.out.println("BEFORE");
                            current_number.setText("Number Being Checked: " + finalI); // set the current number being checked
                            for (int j = 2; j < finalI; j++) {
                                if (finalI % j == 0) { // the number should only be divisible by one and itself
                                    flag = true;
                                    break;
                                }
                            }
                            System.out.println("next");

                            if (!flag) { // after checking, if the fag hasn't changed, we know the number is prime
                                String last_prime = String.valueOf(finalI);
                                prime.setText(last_prime);
                            }
                            System.out.println("AFTER");
                            flag = false;

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


    }
}