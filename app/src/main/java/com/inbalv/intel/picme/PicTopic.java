package com.inbalv.intel.picme;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.inbalv.intel.picme.model.PictureInfo;
import com.inbalv.intel.picme.parsers.PicJSONParser;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PicTopic extends AppCompatActivity {

    ProgressBar pb;
    List<MyTask> tasks;
    List<PictureInfo> pictureList;
    ListView myList;
    public String imageTag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_topic);

        Bundle selTag = getIntent().getExtras();
        imageTag = selTag.getString("selectedTag");


       //final String baseUrl = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=7cac09213fb9d08d6efbc2aeb8a3f223&tags=love&per_page=9&format=json";


        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);
        tasks = new ArrayList<>();
        myList=(ListView) findViewById(R.id.myList);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pic_topic, menu);
        return super.onCreateOptionsMenu(menu)
       ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        final String baseUrl = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=7cac09213fb9d08d6efbc2aeb8a3f223&tags="+imageTag+"&per_page=12&format=json";
        //noinspection SimplifiableIfStatement
        int id = item.getItemId();
        if (id== R.id.action_set) {
            if (isOnline()) {
                requestData(baseUrl);

            }
            else {
                Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
            }


        }

        return false;
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo!=null && netInfo.isConnectedOrConnecting()){
            return true;
        }
        else {
            return false;
        }


    }
    class MyTask extends AsyncTask<String, String, List<PictureInfo>> {
        @Override
        protected void onPreExecute() {
      //      updateDisplay("starting task");
            if (tasks.isEmpty()) {
                Log.i("PreExceute", "On pre Exceute......");
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected List<PictureInfo> doInBackground(String... params) {
            Log.i("doInBack-params",  params[0]);
           String content= HttpManager.getData(params[0]);
            Log.i("doInBack=content", content);
            pictureList= PicJSONParser.parseFeed(content);

            for (PictureInfo pictureInfo:pictureList){
                BitBuilder bitmapBulider=new BitBuilder();
                Bitmap bitmap=(bitmapBulider.getBitmapFromURL(pictureInfo.myImageURL()));
                pictureInfo.setBitmap(bitmap);
            }
            return pictureList;
        }
        @Override
        protected void onProgressUpdate(String... values) {
   //         updateDisplay(values[0]);
        }
        @Override
        protected void onPostExecute(List<PictureInfo> result) {
          //  Log.i("PostExceute", result);

            if (result!=null) {
                updateDisplay();
                tasks.remove(this);
                if (tasks.isEmpty()) {

                    pb.setVisibility(View.INVISIBLE);

                }

            }else{
                Log.i("PostExceute", "picturelist is null");
            }

        }


    }



    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,uri);
    }

    protected void updateDisplay() {
    PicAdaptor adapter=new PicAdaptor(this,R.layout.item_pic, pictureList);
        myList.setAdapter(adapter);

            }
        };

