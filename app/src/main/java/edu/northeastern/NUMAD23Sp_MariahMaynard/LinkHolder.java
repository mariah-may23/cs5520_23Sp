package edu.northeastern.NUMAD23Sp_MariahMaynard;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinkHolder extends RecyclerView.ViewHolder{
    public TextView linkName;
    public TextView linkUrl;

    public LinkHolder(@NonNull View itemView, final ItemClickListener listener) {
        super(itemView);
        this.linkName = itemView.findViewById(R.id.linkName);
        this.linkUrl = itemView.findViewById(R.id.linkUrl);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        listener.onItemClick(position);
                    }
                }
            }
        });
    }


}
