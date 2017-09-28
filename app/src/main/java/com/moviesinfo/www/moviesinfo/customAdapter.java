package com.moviesinfo.www.moviesinfo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.media.MediaBrowserCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.support.v7.widget.AppCompatDrawableManager.get;

/**
 * Created by HP on 03-08-2017.
 */

public class customAdapter extends ArrayAdapter {
    public customAdapter(Context context, ArrayList<Movies> movie) {
        super(context, 0, movie);

    }
    private Context context;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom, parent, false);
        //TextView language = (TextView) view.findViewById(R.id.language);
        //TextView adult = (TextView) view.findViewById(R.id.adult);
        //TextView releasedate = (TextView) view.findViewById(R.id.releasedate);
        //TextView movietitle = (TextView) view.findViewById(R.id.movietitle);
        TextView vote_count=(TextView)view.findViewById(R.id.vote);
        ImageView imageview=(ImageView)view.findViewById(R.id.image);


        Movies name = (Movies) getItem(position);
        vote_count.setText(String.valueOf(name.getVote_count()));
       // language.setText(String.valueOf(name.getLanguage()));
        //adult.setText(String.valueOf(name.getAdult()));
        //releasedate.setText(String.valueOf(name.getReleasedate()));
        //movietitle.setText(String.valueOf(name.getMovieTitle()));
        try
        {
            ImageDownload imagetask = new ImageDownload(imageview);
            imagetask.execute(new URL("https://image.tmdb.org/t/p/w500/" +name.getUrl()));
        }
        catch(Exception e)
        {

        }

        return view;
    }
    class ImageDownload extends AsyncTask<URL,Void,Bitmap>
    {
        ImageView imageView=null;

        public ImageDownload(ImageView imageView)
        {
            this.imageView=imageView;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {

            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(URL... params)
        {

            Bitmap bitmap=null;

            try {

                HttpURLConnection connection = (HttpURLConnection) params[0].openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(20000);
                connection.setReadTimeout(20000);



                InputStream stream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(stream);
            }
            catch (Exception e)
            {
                Log.i("MyApp","Error : " + e.getMessage());
            }

            return bitmap;
        }
    }
}
