package com.github.farhanaliofficial.wget.handler;

import android.os.AsyncTask;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.Charset;
import android.view.View;
import com.github.farhanaliofficial.wget.activity.MainActivity;
import java.util.Map;
import java.util.HashMap;

public class WGet extends AsyncTask<Void,Void,String> {
    String url;
    public WGet(String murl){
        url = murl;
    }
    @Override
    protected String doInBackground(Void... params) {
        try{
            URL Url = new URL(url);
            HttpURLConnection con = (HttpURLConnection) Url.openConnection();
            con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent","WGet-Android/1.0 - Farhan Ali");
			con.setRequestProperty("Accept", "*/*");
            InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream(),  Charset.forName("UTF-8"));
            BufferedReader bufferedreader = new BufferedReader(inputStreamReader);
            StringBuilder response = new StringBuilder();
            int chars;

            while((chars = bufferedreader.read()) != -1){
                response.append((char) chars);
            }
            return response.toString();
        }catch(Exception e){
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        MainActivity.progressBar.setVisibility(View.GONE);
        MainActivity.response.setText(result);
        MainActivity.bottomLayout.setVisibility(View.VISIBLE);
    }
}
