package com.moviesinfo.www.moviesinfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        DownloadData task = new DownloadData();
        task.execute();
    }
    class DownloadData extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                ArrayList<Moviess> movie = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject mov = jsonArray.getJSONObject(i);
                   // int vote_count = mov.getInt("vote_count");
                    String title = mov.getString("title");
                   movie.add(new Moviess(title));
                }
                customadapter2 adapter = new customadapter2(Main2Activity.this, movie);
                TextView title=(TextView)findViewById(R.id.title);
                title.setText(title+"");
            }

            catch (Exception e) {
                Log.i("MyApp", e.getMessage());
            }
        }

        @Override
        protected String doInBackground(URL... params) {
            try {


                URL url = new URL("https://api.themoviedb.org/3/movie/upcoming?api_key=22a6cdb758da70ff320970b3ab4f2880");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(20000);
                connection.setReadTimeout(20000);



                InputStream stream = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(stream);
                BufferedReader breader = new BufferedReader(reader);

                StringBuilder output = new StringBuilder();
                String line = breader.readLine();
                while (line != null) {
                    output.append(line);
                    line = breader.readLine();
                }
                return output.toString();
            } catch (Exception e) {
                Log.i("MyApp", e.getMessage());
                return null;
            }
        }
    }
}
