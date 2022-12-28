package com.example.pizzarestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> names;
    ArrayList<String> details;
    LayoutInflater inflter;
    int images[];

    public CustomAdapter(Context applicationContext, ArrayList<String> names, ArrayList<String> details, int[] images) {
        this.context = context;
        this.names = names;
        this.details = details;
        this.images = images;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_list_menu, null);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView detail = (TextView) view.findViewById(R.id.detail);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        name.setText(names.get(i));
        detail.setText(details.get(i));
        image.setImageResource(images[i]);
        return view;
    }
}