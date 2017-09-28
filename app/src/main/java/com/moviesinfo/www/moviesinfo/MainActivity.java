package com.moviesinfo.www.moviesinfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadData task = new DownloadData();
        task.execute();


        gridview = (GridView) findViewById(R.id.gridview);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

    }

   /* class DownloadData extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            try {
              JSONObject jsonObject=new JSONObject(s);
              JSONArray jsonArray=jsonObject.getJSONArray("results");
                ArrayList<Movies> movie=new ArrayList<>();
                Log.i("MyApp","hello");
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject mov=jsonArray.getJSONObject(i);
                   String title=mov.getString("title");
                    String original_language=mov.getString("original_language");
                    String adult=mov.getString("adult");
                    String release_date=mov.getString("release_date");
                    String url=mov.getString("poster_path");
                  movie.add(new Movies(title,adult,release_date,original_language,url));
                }
                customAdapter adapter=new customAdapter(MainActivity.this,movie);
                GridView gridview=(GridView)findViewById(R.id.gridview);
                gridview.setAdapter(adapter);
                }


             catch (Exception e) {
                Log.i("MyApp", e.getMessage());
            }
        }

        @Override
        protected String doInBackground(URL... params) {
            try {

                Log.i("MyApp","Entering");
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
    }*/

   class DownloadData extends AsyncTask<URL, Void, String> {
       @Override
       protected void onPostExecute(String s) {
           try {
               JSONObject jsonObject = new JSONObject(s);
               JSONArray jsonArray = jsonObject.getJSONArray("results");
               ArrayList<Movies> movie = new ArrayList<>();

               for (int i = 0; i < jsonArray.length(); i++) {
                   JSONObject mov = jsonArray.getJSONObject(i);
                   int vote_count = mov.getInt("vote_count");
                   String url = mov.getString("poster_path");
                   movie.add(new Movies(url, vote_count));
               }
               customAdapter adapter = new customAdapter(MainActivity.this, movie);

                gridview = (GridView) findViewById(R.id.gridview);
                gridview.setAdapter(adapter);

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
