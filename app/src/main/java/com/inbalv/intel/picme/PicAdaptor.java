package com.inbalv.intel.picme;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.inbalv.intel.picme.model.PictureInfo;

import java.util.List;

/**
 * Created by intel on 29/09/2015.
 */
public class PicAdaptor extends ArrayAdapter<PictureInfo> {
    private Context context;

    private List<PictureInfo> pictureInfoList;
    public PicAdaptor(Context context, int resource,List<PictureInfo> objects) {
        super(context, resource,objects);
        this.context=context;
        this.pictureInfoList=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.item_pic,parent,false);
        PictureInfo pictureInfo=pictureInfoList.get(position);
        TextView tv= (TextView) view.findViewById(R.id.textView2);
        tv.setText(pictureInfo.getTitle());
        return view;
    }
}
