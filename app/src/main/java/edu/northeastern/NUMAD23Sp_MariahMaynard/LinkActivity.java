package edu.northeastern.NUMAD23Sp_MariahMaynard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LinkActivity extends AppCompatActivity {
    private RecyclerView linkRecyclerView;
    private LinkAdapter lAdapter;
    private ArrayList<LinkCard> linksList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton addLinkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        createRecyclerView();

        addLinkButton = findViewById(R.id.addLinkButton);
        addLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = 0;
                addItem(pos);
            }
        });

    }

    private void createRecyclerView() {

        layoutManager = new LinearLayoutManager(this);

        linkRecyclerView = findViewById(R.id.link_recycler_view);
        linkRecyclerView.setHasFixedSize(true);

        lAdapter = new LinkAdapter(linksList);

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //attributions bond to the item has been changed
                linksList.get(position).onItemClick(position);

                lAdapter.notifyItemChanged(position);
            }
        };


        lAdapter.setOnItemClickListener(itemClickListener);
        linkRecyclerView.setAdapter(lAdapter);
        linkRecyclerView.setLayoutManager(layoutManager);

    }

    private void addItem(int position) {
        linksList.add(position, new LinkCard("Youtube", "www.youtube.com", false));
        Toast.makeText(LinkActivity.this, "Add an item", Toast.LENGTH_SHORT).show();

        lAdapter.notifyItemInserted(position);
    }


/**
    //Adding a new person object to the personList arrayList
        linksList.add(new LinkCard("Google", "www.google.com"));
        linksList.add(new LinkCard("Google", "www.google.com"));
        linksList.add(new LinkCard("Google", "www.google.com"));


        linkRecyclerView = findViewById(R.id.link_recycler_view);

        //This defines the way in which the RecyclerView is oriented
        linkRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Associates the adapter with the RecyclerView
        linkRecyclerView.setAdapter(new LinkAdapter(linksList, this));

        addLinkButton = findViewById(R.id.addLinkButton);


    }


*/
}

