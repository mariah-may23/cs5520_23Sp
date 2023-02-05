package edu.northeastern.NUMAD23Sp_MariahMaynard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

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
                showLinkDialog();
            }
        });


    }

    private void showLinkDialog(){
        final Dialog dialog = new Dialog(LinkActivity.this);
        // disable default title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true); // can cancel dialog
        dialog.setContentView(R.layout.link_dialog_box); // layout created to get link

        //INITIALIZE VIEWS
        final EditText name_et = dialog.findViewById(R.id.linkName_et);
        final EditText url_et = dialog.findViewById(R.id.linkUrl_et);
        Button submit = dialog.findViewById(R.id.submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = name_et.getText().toString();
                String url = url_et.getText().toString();
                addItem(name,url);
                dialog.dismiss();
            }
        });
        dialog.show();

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

    private void addItem(String name, String url) {
        linksList.add(0, new LinkCard(name, url, false));
        Toast.makeText(LinkActivity.this, "Add an item", Toast.LENGTH_SHORT).show();

        lAdapter.notifyItemInserted(0);
    }

    private void goToWeb(View v) {
        TextView txt = findViewById(v.getId());
        txt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse((String) txt.getText()));
                startActivity(intent);
            }

        });
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

