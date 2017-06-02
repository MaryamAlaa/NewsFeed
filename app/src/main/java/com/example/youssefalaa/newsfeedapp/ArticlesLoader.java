package com.example.youssefalaa.newsfeedapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by youssef alaa on 02/06/2017.
 */

public class ArticlesLoader extends android.support.v4.content.AsyncTaskLoader<String>{

    public ArticlesLoader(Context context) {
        super(context);
        // TODO: Finish implementing this constructor
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String reviewJsonStr = null;

        try {
            String NEWS_URL = "https://content.guardianapis.com/search?api-key=e6ddde03-eeee-4a40-9af2-f95842c28d2e";
            Uri uri = Uri.parse(NEWS_URL).buildUpon().build();
            URL url = new URL(uri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            reviewJsonStr = buffer.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
        }
        try {
            return reviewJsonStr;
        } catch (Exception e) {
        }
        return null;
    }
}
