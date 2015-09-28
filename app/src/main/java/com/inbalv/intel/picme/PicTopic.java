package com.inbalv.intel.picme;

import android.content.AsyncTaskLoader;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.os.AsyncTask;

import java.net.URL;

import javax.xml.transform.Result;

public class PicTopic extends AppCompatActivity {
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_topic);
        output=(TextView) findViewById(R.id.textView);
        output.setMovementMethod(new ScrollingMovementMethod());
        final String baseUrl="https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=7cac09213fb9d08d6efbc2aeb8a3f223&tags=love&per_page=9&format=json";
        //URL url=new URL(baseUrl);

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class TestAsync extends AsyncTask<String, String, String>
    {
        @Override
        protected void onPreExecute (){
            Log.d("PreExceute", "On pre Exceute......");

        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        }

        @Override
        protected void onPostExecute(String result) {

        }
    }

}
