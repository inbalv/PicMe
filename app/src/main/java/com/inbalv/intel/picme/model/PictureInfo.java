package com.inbalv.intel.picme.model;

import android.graphics.Bitmap;

/**
 * Created by intel on 28/09/2015.
 */
public class PictureInfo {
    private String id;
    private String owner;
    private String secret;
    private String server;
    private String farm;
    private String title;
    private String ispublic;
    private String isfriend;
    private String isfamily;
    public  String imageURL;
    public Bitmap bitmap;



    public String getIspublic() {
        return ispublic;
    }

    public void setIspublic(String ispublic) {
        this.ispublic = ispublic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(String isfriend) {
        this.isfriend = isfriend;
    }

    public String getIsfamily() {
        return isfamily;
    }

    public void setIsfamily(String isfamily) {
        this.isfamily = isfamily;
    }

    public String getImageURL() {
        imageURL = ("https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+"_q.jpg");
        return imageURL; }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public String myImageURL() {

        return imageURL; }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
