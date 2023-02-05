package edu.northeastern.NUMAD23Sp_MariahMaynard;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinkHolder extends RecyclerView.ViewHolder{
    public TextView link;
    public TextView url;

    public LinkHolder(@NonNull View itemView) {
        super(itemView);
    }
}
