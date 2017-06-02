package com.example.youssefalaa.newsfeedapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    public ListView newsList;
    private ArticlesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsList = (ListView) findViewById(R.id.activity_main);
        getSupportLoaderManager().initLoader(1, null, this).forceLoad();
//        new NewsTask().execute();
    }


    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new ArticlesLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {

        final List<Articles> list;
        list = parseList(s);
        adapter = new ArticlesAdapter(MainActivity.this, list);
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isConnected(MainActivity.this)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getWebUrl()));
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "There is no internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
        newsList.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        // TODO: Loader reset, so we can clear out our existing data.
    }


    private List<Articles> parseList(String s) {
        List<Articles> list = new ArrayList<>();
        try {
            JSONObject rootObject = new JSONObject(s);
            JSONObject subObject = rootObject.getJSONObject("response");
            JSONArray results = subObject.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject arrayObject = results.getJSONObject(i);
                String sectionName = arrayObject.getString("sectionName");
                String webTitle = arrayObject.getString("webTitle");
                String webUrl = arrayObject.getString("webUrl");

                list.add(new Articles(sectionName, webTitle, webUrl));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
