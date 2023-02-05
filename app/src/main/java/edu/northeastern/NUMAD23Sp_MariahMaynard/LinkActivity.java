package edu.northeastern.NUMAD23Sp_MariahMaynard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import com.google.android.material.snackbar.Snackbar;

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
                showLinkDialog(v);

            }

        });

        ItemTouchHelper deleteTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(LinkActivity.this, "Delete an item", Toast.LENGTH_SHORT).show();
                int position = viewHolder.getLayoutPosition();
                linksList.remove(position);
                lAdapter.notifyItemRemoved(position);
            }
        });
        ItemTouchHelper editTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Toast.makeText(LinkActivity.this, "Edit an item", Toast.LENGTH_SHORT).show();
                int position = viewHolder.getLayoutPosition();
                LinkCard card = linksList.get(position);
                showLinkDialogEdit(linkRecyclerView,card,position);


            }
        });

        deleteTouchHelper.attachToRecyclerView(linkRecyclerView);
        editTouchHelper.attachToRecyclerView(linkRecyclerView);
    }







    private void showLinkDialog(View v){
        final Dialog dialog = new Dialog(LinkActivity.this);
        // disable default title
      //  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
                if(name.length()==0 || url.length() == 0){
                    dialog.dismiss();
                    Snackbar.make(LinkActivity.this,v,"Link not created! Please enter valid criteria.",Snackbar.LENGTH_LONG).show();

                }     else{
                    addItem(name,url);
                    dialog.dismiss();
                    Snackbar.make(LinkActivity.this,v,"Link successfully created!",Snackbar.LENGTH_LONG).show();
                }



            }
        });
        dialog.show();

    }

    private void showLinkDialogEdit(View v,LinkCard card, int pos){
        final Dialog dialog = new Dialog(LinkActivity.this);
        // disable default title
        //  dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
                if(name.length()==0 || url.length() == 0){
                    dialog.dismiss();
                    Snackbar.make(LinkActivity.this,v,"Link not edited! Please enter valid criteria.",Snackbar.LENGTH_LONG).show();

                }     else{
                    card.setLinkName(name);
                    card.setLinkUrl(url);
                    lAdapter.notifyItemChanged(pos);

                    dialog.dismiss();
                    Snackbar.make(LinkActivity.this,v,"Link successfully edited!",Snackbar.LENGTH_LONG).show();
                }



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

                lAdapter.notifyItemChanged(position);

                String link = linksList.get(position).getLinkUrl();
                if (!link.startsWith("http://") && !link.startsWith("https://")){
                    link = "http://" + link;}

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(browserIntent);

            }
        };
        lAdapter.setOnItemClickListener(itemClickListener);
        linkRecyclerView.setAdapter(lAdapter);
        linkRecyclerView.setLayoutManager(layoutManager);

    }

    private void addItem(String name, String url) {

        linksList.add(0, new LinkCard(name, url));

        lAdapter.notifyItemInserted(0);
    }


}

