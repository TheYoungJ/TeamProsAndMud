package com.example.demosearch;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.os.Message;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter adapter;
    String def_term = "bad";
    String nTerm = "bad";
    String def_country = "ca";
    String nCountry = "ca";
    //api_Utelly api = new api_Utelly();
    String url;
    ListView listView;
    GridView gridView;
    TextView emptyView;
    responseObject obj;
    private Gson gson;
    private GsonBuilder gbuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridView = findViewById(R.id.gridview);
        //listView = findViewById(R.id.listView);
        emptyView = findViewById(R.id.emptyView);

        gbuilder = new GsonBuilder();
        gson = gbuilder.excludeFieldsWithModifiers(Modifier.STATIC).create();

        //api_Utelly tel = new api_Utelly();
        new api_utelly_getrequest().execute();

        //found an json adapter, gonna replace this one with that
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.months_array));

        gridView.setAdapter(new ImageAdapter(this));
        /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem mSearch = menu.findItem(R.id.appSearchBar);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                nTerm = query;
                new api_utelly_getrequest().execute();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                nTerm = newText;
                new api_utelly_getrequest().execute();
                adapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private class api_utelly_getrequest extends AsyncTask<Void, Void, Void> {
        String res;

        @Override
        protected Void doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            if(nTerm != null || nTerm.length() != 0 || nCountry != null || nCountry.length() != 0) {
                url = "https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term=" + nTerm + "&country=" + nCountry;
            }else {
                url = "https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term=" + def_term + "&country=" + def_country;
            }

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("x-rapidapi-host", "utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "dd37d15cf1msha20c25bf6ba08c7p1c3821jsn93385a10f1f6")
                    .build();

            try {
                res = client.newCall(request).execute().body().string();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            //adapt json for gridview
            //create obj for the results using the responseobject class
            obj = gson.fromJson(res, responseObject.class);
            //testing for each part of the response, important parts
            for(responseObject.item item : obj.getResult()){
                System.out.println("Item -> "+ item.getName());
                for(responseObject.item.locations loc : item.getLocation()) {
                    System.out.println("service -> " + loc.getName());
                }
            }

            //populate gridview

            super.onPostExecute(aVoid);
        }
    }
}