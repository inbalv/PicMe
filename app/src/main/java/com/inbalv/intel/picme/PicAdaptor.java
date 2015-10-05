package com.inbalv.intel.picme;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inbalv.intel.picme.model.PictureInfo;

import java.util.List;

/**
 * Created by intel on 29/09/2015.
 */
public class PicAdaptor extends ArrayAdapter<PictureInfo> {
    private Context context;

    private List<PictureInfo> pictureInfoList;

    public PicAdaptor(Context context, int resource, List<PictureInfo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.pictureInfoList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_pic, parent, false);
        PictureInfo pictureInfo = pictureInfoList.get(position);



        TextView tv = (TextView) view.findViewById(R.id.textView2);
        tv.setText(pictureInfo.getTitle());
        if (pictureInfo.getBitmap()!=null) {
            ImageView iv = (ImageView) view.findViewById(R.id.imageView2);
            iv.setImageBitmap(pictureInfo.getBitmap());
        } else{
            PicAndView container=new PicAndView();
            container.pictureInfo=pictureInfo;
            container.view=view;
            ImageLoader loader=new ImageLoader();
            loader.execute(container);
            }

        return view;
    }

class PicAndView{
    public PictureInfo pictureInfo;
    public View view;
    public Bitmap bitmap;
}

    private class ImageLoader extends AsyncTask<PicAndView, Void ,PicAndView>{


        @Override
        protected PicAndView doInBackground(PicAndView... params) {
            PicAndView container=params[0];
            PictureInfo pictureInfo=container.pictureInfo;
            BitBuilder bitmapBulider=new BitBuilder();
            Bitmap bitmap=(bitmapBulider.getBitmapFromURL(pictureInfo.myImageURL()));
            pictureInfo.setBitmap(bitmap);
            container.bitmap=pictureInfo.getBitmap();
            return container;
        }

        @Override
        protected void onPostExecute(PicAndView picAndView) {
            ImageView iv = (ImageView) picAndView.view.findViewById(R.id.imageView2);
            iv.setImageBitmap(picAndView.bitmap);
            picAndView.pictureInfo.setBitmap(picAndView.bitmap);
        }
    }
}