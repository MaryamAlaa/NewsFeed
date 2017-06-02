package com.example.youssefalaa.newsfeedapp;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;


/**
 * Created by youssef alaa on 23/05/2017.
 */

public class ArticlesAdapter extends BaseAdapter {
    private Context mContext;
    private List<Articles> list;

    public ArticlesAdapter(Context mContext, List<Articles> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Articles currentArticles = (Articles) getItem(position);
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_news_feed, parent, false);
        }
        TextView sectionName =(TextView) view.findViewById(R.id.author);
        TextView webTitle =(TextView) view.findViewById(R.id.title);
        TextView webUrl =(TextView) view.findViewById(R.id.webUrl);

        sectionName.setText(currentArticles.getSectionName());
        webTitle.setText(currentArticles.getWebTitle());
        webUrl.setText(currentArticles.getWebUrl());

        return view;
    }

}
