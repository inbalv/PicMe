package com.inbalv.intel.picme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.TooManyListenersException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] Topics={"Home","Love","Food","Nature","Sea","Safari"};
        final Intent topicInt = new Intent(this, PicTopic.class);
      // ListAdapter adapter=new CustomAdaptor(this,Topics);
       ListAdapter adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Topics);
        ListView topicList=(ListView) findViewById(R.id.topicList);
        topicList.setAdapter(adapter);
        topicList.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedTopic = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, selectedTopic, Toast.LENGTH_SHORT).show();
                        startActivity(topicInt);

                    }

                }

        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
