package com.inbalv.intel.picme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TopicActivity extends AppCompatActivity {
    private static final String TAG = "Http Connection";

    private GridView gridView = null;

    private ArrayAdapter arrayAdapter = null;

    private String[] blogTitles;
    ImageView image=(ImageView) findViewById(R.id.topic1);
    ImageView[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        gridView = (GridView) findViewById(R.id.topicGrid);
        String imageTag="love";
        String apiKey ="7cac09213fb9d08d6efbc2aeb8a3f223";
        String secret="a5f141a334b64a27";
       // final String baseUrl="https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=7cac09213fb9d08d6efbc2aeb8a3f223&tags="+imageTag+"&per_page=9" ;
        final String baseUrl="https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=7cac09213fb9d08d6efbc2aeb8a3f223&tags=love&per_page=9&format=json";
        OkHttpClient client =new OkHttpClient();
        Request request = new Request.Builder().url(baseUrl).build();

        Call call =client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        Log.v(TAG,response.body().string( ));
                        InputStream in= (InputStream) new URL(response.body().toString()).getContent();
                        Bitmap bitmap= BitmapFactory.decodeStream(in);

                        in.close();
                       image.setImageBitmap(bitmap);


                    }
                }catch (IOException e){
                    Log.e(TAG,"Exception caught", e);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic, menu);
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
}
