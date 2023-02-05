package edu.northeastern.NUMAD23Sp_MariahMaynard;

import android.widget.AdapterView;

public class LinkCard{
    private String linkName;
    private String linkUrl;




    public LinkCard(String linkName, String linkUrl) {
        this.linkName = linkName;
        this.linkUrl = linkUrl;

    }

    public String getLinkName() {
        return linkName;
    }
    public String getLinkUrl() {
        return linkUrl;
    }


    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }





}
