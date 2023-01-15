package edu.northeastern.NUMAD23Sp_MariahMaynard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * Your activity is being created
         * when the activity is being created, it sets its own content view to be this layout
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void aboutToast(View view){
        Toast.makeText(getApplicationContext(), "Mariah Maynard \nmaynard.ma@northeastern.edu", Toast.LENGTH_LONG).show();

    }

}