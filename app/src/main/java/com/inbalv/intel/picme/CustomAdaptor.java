package com.inbalv.intel.picme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ResourceBundle;

/**
 * Created by intel on 23/09/2015.
 */
public class CustomAdaptor extends ArrayAdapter<String> {

    public CustomAdaptor(Context context, String[] Topics) {
        super(context, R.layout.custom_row, Topics);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater topicInflator = LayoutInflater.from(getContext());
        View customView = topicInflator.inflate(R.layout.custom_row, parent, false);
        String singleTopic = getItem(position);
        TextView topicText = (TextView) customView.findViewById(R.id.topicText);
        ImageView topicImage = (ImageView) customView.findViewById(R.id.topicImage);
        topicText.setText(singleTopic);

        topicImage.setImageResource(R.drawable.food);
        return customView;
    }

}

