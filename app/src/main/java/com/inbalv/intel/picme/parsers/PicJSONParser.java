package com.inbalv.intel.picme.parsers;

import android.util.Log;

import com.inbalv.intel.picme.model.PictureInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intel on 28/09/2015.
 */
public class PicJSONParser {

public static List<PictureInfo> parseFeed(String content){
    List<PictureInfo> pictureList=new ArrayList<>();

    try{
        //JSONObject  picInfo = new JSONObject(content);
       // Log.i("PicJSONParser-picInfo", String.valueOf(picInfo.names()));

        //JSONObject picInfo2 = new JSONObject(picInfo.get("photos").toString());
        //Log.i("PicJSONParser-picInfo2", String.valueOf(picInfo2.names()));

        //JSONArray ar = new JSONArray(picInfo2.getJSONArray("photo").toString());
        //.i("PicJSONParser-ar", String.valueOf(ar.length()));
       // Log.i("PicJSONParser-ar", String.valueOf(ar.toString()));
        int IB=content.indexOf("[");
        int IE=content.indexOf("]");
        String editContent=content.substring(IB,IE+1);
        Log.i("PicJSONParser-edit",editContent);
        JSONArray ar = new JSONArray(editContent);
        for (int i=0; i<ar.length();i++) {
        JSONObject obj = ar.getJSONObject(i);
            PictureInfo pictureInfo = new PictureInfo();
            pictureInfo.setId(obj.getString("id"));
            Log.i("PicJSONParser-id", pictureInfo.getId());

            pictureInfo.setOwner(obj.getString("owner"));
        pictureInfo.setSecret(obj.getString("secret"));
        pictureInfo.setServer(obj.getString("server"));
            pictureInfo.setFarm(obj.getString("farm"));
        pictureInfo.setTitle(obj.getString("title"));
        pictureInfo.setIspublic(obj.getString("ispublic"));
        pictureInfo.setIsfriend(obj.getString("isfriend"));
        pictureInfo.setIsfamily(obj.getString("isfamily"));
       String imageURL=pictureInfo.getImageURL();
            pictureInfo.setImageURL(imageURL);
        pictureList.add(pictureInfo);
    }
        return  pictureList;
    } catch (JSONException e){
        e.printStackTrace();
        Log.i("PicJSON","2");
        return null;
    }

}
}

