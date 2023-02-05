package edu.northeastern.NUMAD23Sp_MariahMaynard;

import android.widget.AdapterView;

public class LinkCard implements ItemClickListener{
    private String linkName;
    private String linkUrl;
    private boolean isChecked;

    public LinkCard(String linkName, String linkUrl,boolean isChecked) {
        this.linkName = linkName;
        this.linkUrl = linkUrl;
        this.isChecked = isChecked;
    }

    public String getLinkName() {
        return linkName;
    }
    public String getLinkUrl() {
        return linkUrl;
    }
    public boolean getStatus() {
        return isChecked;
    }


    @Override
    public void onItemClick(int position) {
        isChecked = !isChecked;
    }



}
