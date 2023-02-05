package edu.northeastern.NUMAD23Sp_MariahMaynard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class LinkActivity extends AppCompatActivity {
    RecyclerView linkRecyclerView;
    List<LinkCard> linksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        //Instantiate the arraylist
        linksList = new ArrayList<>();

        //Adding a new person object to the personList arrayList
        linksList.add(new LinkCard("Google", "www.google.com"));
        linksList.add(new LinkCard("Google", "www.google.com"));
        linksList.add(new LinkCard("Google", "www.google.com"));
        linksList.add(new LinkCard("Google", "www.google.com"));
        linksList.add(new LinkCard("Google", "www.google.com"));
        linksList.add(new LinkCard("Google", "www.google.com"));
        linksList.add(new LinkCard("Google", "www.google.com"));
        linksList.add(new LinkCard("Google", "www.google.com"));
        linksList.add(new LinkCard("Google", "www.google.com"));
        linksList.add(new LinkCard("Google", "www.google.com"));

        linkRecyclerView = findViewById(R.id.link_recycler_view);

        //This defines the way in which the RecyclerView is oriented
        linkRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Associates the adapter with the RecyclerView
        linkRecyclerView.setAdapter(new LinkAdapter(linksList, this));

    }
}

