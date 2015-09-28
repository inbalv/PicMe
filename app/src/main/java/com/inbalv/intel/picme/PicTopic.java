package com.inbalv.intel.picme;

import android.app.ListActivity;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.AsyncTask;
import android.widget.Toast;

import com.inbalv.intel.picme.model.PictureInfo;
import com.inbalv.intel.picme.parsers.PicJSONParser;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class PicTopic extends ListActivity {

    ProgressBar pb;
    List<MyTask> tasks;
    List<PictureInfo> pictureList;
    final String baseUrl = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=7cac09213fb9d08d6efbc2aeb8a3f223&tags=love&per_page=9&format=json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_topic);


       //final String baseUrl = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=7cac09213fb9d08d6efbc2aeb8a3f223&tags=love&per_page=9&format=json";


        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);
        tasks = new ArrayList<>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pic_topic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        //noinspection SimplifiableIfStatement
        if (item.getItemId()== R.id.action_settings) {
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
    class MyTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
      //      updateDisplay("starting task");
            if (tasks.size() == 0) {
                Log.i("PreExceute", "On pre Exceute......");
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected String doInBackground(String... params) {
           String content= HttpManager.getData(params[0]);
            return content;
        }
        @Override
        protected void onProgressUpdate(String... values) {
   //         updateDisplay(values[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            pictureList= PicJSONParser.parseFeed(result);
            updateDisplay();
            tasks.remove(this);
            if (tasks.size() == 0) {
                Log.i("PostExceute", result);
                pb.setVisibility(View.INVISIBLE );
            }


        }


    }



    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,uri);
    }

    protected void updateDisplay() {
    PicAdaptor adapter=new PicAdaptor(this,R.layout.item_pic, pictureList);
        setListAdapter(adapter);

            }
        };

