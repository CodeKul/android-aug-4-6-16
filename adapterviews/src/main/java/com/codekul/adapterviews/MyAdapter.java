package com.codekul.adapterviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aniruddha on 14/9/16.
 */
public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MyItem> dataSet;
    private LayoutInflater inflater;

    public MyAdapter(Context context, ArrayList<MyItem> dataSet) {
        this.context = context;
        this.dataSet = dataSet;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dataSet.get(position).getItemId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = null;
        if(convertView == null)
            itemView =  inflater.inflate(R.layout.my_item,null,false);
        else itemView = convertView;

        ((ImageView)itemView.findViewById(R.id.imageView))
                .setImageResource(dataSet.get(position).getImageId());

        ((TextView)itemView.findViewById(R.id.textView))
                .setText(dataSet.get(position).getText());

        return itemView;
    }
}
