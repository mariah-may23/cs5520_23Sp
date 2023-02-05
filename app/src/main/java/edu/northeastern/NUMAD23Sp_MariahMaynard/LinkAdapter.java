package edu.northeastern.NUMAD23Sp_MariahMaynard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LinkAdapter extends RecyclerView.Adapter<LinkHolder> {

    private final List<LinkCard> links;
    private final Context context;

    public LinkAdapter(List<LinkCard> links, Context context) {
        this.links = links;
        this.context = context;
    }

    @NonNull
    @Override
    public LinkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinkHolder(LayoutInflater.from(context).inflate(R.layout.link_card, null));

    }

    @Override
    public void onBindViewHolder(@NonNull LinkHolder holder, int position) {
        // sets the name of the person to the name textview of the viewholder.
        holder.linkName.setText(links.get(position).getLinkName());
        // sets the age of the person to the age textview of the viewholder.
        holder.linkUrl.setText(String.valueOf(links.get(position).getLinkUrl()));

        // set a click event on the whole itemView (every element of the recyclerview).
        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(context, links.get(position).getLinkName(), Toast.LENGTH_SHORT).show();
        });


    }

    @Override
    public int getItemCount() {
        return links.size();
    }
}
