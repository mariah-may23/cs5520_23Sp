package edu.northeastern.NUMAD23Sp_MariahMaynard;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LinkAdapter extends RecyclerView.Adapter<LinkHolder> {

    private final ArrayList<LinkCard> linksList;
    private ItemClickListener listener;

    public LinkAdapter(ArrayList<LinkCard> linksList) {
        this.linksList = linksList;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public LinkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.link_card, parent, false);
        return new LinkHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkHolder holder, int position) {
        LinkCard currentLink = linksList.get(position);
        // sets the name of the person to the name textview of the viewholder.
        holder.linkName.setText(currentLink.getLinkName());
        // sets the age of the person to the age textview of the viewholder.
        holder.linkUrl.setText(String.valueOf(currentLink.getLinkUrl()));

    }

    @Override
    public int getItemCount() {
        return linksList.size();
    }


}
