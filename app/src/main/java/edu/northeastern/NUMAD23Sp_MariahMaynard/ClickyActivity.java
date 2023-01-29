package edu.northeastern.NUMAD23Sp_MariahMaynard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.northeastern.NUMAD23Sp_MariahMaynard.R.id;

public class ClickyActivity extends AppCompatActivity {
    TextView t_view;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky);

        t_view = findViewById(R.id.textView3);

    }

    public void btn_clicked(View v) {
        // get the button clicked
        Button btn = findViewById(v.getId());
        setT_view(btn);
    }


    public void setT_view(Button b) {
        // set the text of the text view
        String txt = "Pressed: " + b.getText();
        t_view.setText(txt);
    }




}