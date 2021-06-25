package com.parth.earthquakeapplearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();

    private Earthquake_adapter adapter;

    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView earthquakeListView = (ListView) findViewById(R.id.earthquakelist);

        adapter =new Earthquake_adapter(this,new ArrayList<Earthquake>());

        earthquakeListView.setAdapter(adapter);






        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /// getItem() is used to locate current clicked list item
                Earthquake currentearthqk=adapter.getItem(position);

                //convert the url to URI
                Uri urilink=Uri.parse(currentearthqk.getUrl());

                //creating intent
                Intent urlopener =new Intent(Intent.ACTION_VIEW,urilink);
                startActivity(urlopener);
            }
        });


        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);


    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {

        @Override
        protected List<Earthquake> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Earthquake> result = QueryUtils.fetchEarthquakeData(urls[0]);
            return result;

        }

        @Override
        protected void onPostExecute(List<Earthquake> data) {
            adapter.clear();

            if (data != null && !data.isEmpty()) {
                adapter.addAll(data);
            }
        }
    }



}