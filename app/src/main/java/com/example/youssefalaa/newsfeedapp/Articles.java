package com.example.youssefalaa.newsfeedapp;

import java.net.URL;

import static android.R.attr.author;
import static android.R.attr.description;

/**
 * Created by youssef alaa on 23/05/2017.
 */

public class Articles {
    private String sectionName;
    private String webTitle;
    private String webUrl;

    public Articles(String sectionName, String webTitle, String webUrl) {
        this.sectionName = sectionName;
        this.webTitle = webTitle;
        this.webUrl = webUrl;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
