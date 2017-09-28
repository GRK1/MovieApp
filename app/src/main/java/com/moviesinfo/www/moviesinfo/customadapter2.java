package com.moviesinfo.www.moviesinfo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HP on 21-08-2017.
 */

public class customadapter2 extends ArrayAdapter {
    public customadapter2(Context context, ArrayList<Moviess> movie) {
        super(context, 0, movie);

    }

    private Context context;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_main2, parent, false);
        //TextView language = (TextView) view.findViewById(R.id.language);
        //TextView adult = (TextView) view.findViewById(R.id.adult);
        //TextView releasedate = (TextView) view.findViewById(R.id.releasedate);
        //TextView movietitle = (TextView) view.findViewById(R.id.movietitle);
        TextView title = (TextView) view.findViewById(R.id.title);

        Moviess name = (Moviess) getItem(position);
        title.setText(String.valueOf(name.getTitle()));
        return view;
    }
}