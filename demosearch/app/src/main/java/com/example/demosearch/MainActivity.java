package com.example.demosearch;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    ImageAdapter adapter;
    String def_term = "bad";
    String nTerm = def_term;
    String def_country = "ca";
    String nCountry = "ca";
    GridView gridView;
    responseObject obj;
    private Gson gson;
    private GsonBuilder gbuilder;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> pics = new ArrayList<String>();
    //api_Utelly tel = new api_Utelly();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridView = findViewById(R.id.gridView);
        //emptyView = findViewById(R.id.emptyView);

        //call the invoke process to  get the response from the api
        //tel.invokeGet(def_term, def_country);
        //obj = tel.sendResponse();
        //while(obj == null){
        //    obj = tel.sendResponse();
        //}
        gbuilder = new GsonBuilder();
        gson = gbuilder.excludeFieldsWithModifiers(Modifier.STATIC).create();

        try {
            doGetRequest(def_term, def_country);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //new api_utelly_getrequest().execute();

        //get names from obj

        adapter = new ImageAdapter(this, names, pics);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        ((TextView) v.findViewById(R.id.grid_item_label))
                                .getText(), Toast.LENGTH_SHORT).show();

            }
        });
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
                try {
                    doGetRequest(query, def_country);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //new api_utelly_getrequest().execute();
                //obj = tel.sendResponse();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //nTerm = newText;
                try {
                    doGetRequest(newText, def_country);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //new api_utelly_getrequest().execute();
                //tel.invokeGet(newText, def_country);
                //adapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    void doGetRequest(String term, String country) throws IOException{
        final String def_term = "bad";
        final String def_country = "ca";
        //private String nTerm;
        //private String nCountry;
        String url;

        OkHttpClient client = new OkHttpClient();
        if (nTerm != null || nTerm.length() != 0 || nCountry != null || nCountry.length() != 0) {
            url = "https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term=" + term + "&country=" + country;
        } else {
            url = "https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term=" + def_term + "&country=" + def_country;
        }

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "dd37d15cf1msha20c25bf6ba08c7p1c3821jsn93385a10f1f6")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
                    public void onFailure(final Call call, IOException e) {
                        // Error
                        System.out.println("ERROR ---> ");
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // For the example, you can show an error dialog or a toast
                                // on the main UI thread
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        String res = response.body().string();
                        //System.out.println("RESULT ----> "+res);
                        obj = gson.fromJson(res, responseObject.class);
                        for (responseObject.item item : obj.getResult()) {
                            names.add(item.getName());
                            pics.add(item.getPic());
                            //System.out.println(item.toString());
                        }

                        //System.out.print("Names --> "+names);
                        System.out.println("Pics -->" +pics);
                        // Do something with the response
                    }
                });
    }

    /*private class api_utelly_getrequest extends AsyncTask<Void, Void, Void> {
        private String res;

        //private responseObject obj;
        //private final String def_term = "bad";
        //private final String def_country = "ca";
        //private String nTerm;
        //private String nCountry;
        private String url;

        @Override
        protected Void doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            if (nTerm != null || nTerm.length() != 0 || nCountry != null || nCountry.length() != 0) {
                url = "https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term=" + nTerm + "&country=" + nCountry;
            } else {
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
                System.out.println("RESULT ---> " + res);
                obj = gson.fromJson(res, responseObject.class);
                System.out.println("OBJ inside ---> " + obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //adapt json for gridview
            //create obj for the results using the responseobject class
            obj = gson.fromJson(res, responseObject.class);
            //getResponse(obj);
            System.out.println("OBJ ON POST ---> " + obj);
            //testing for each part of the response, important parts
            for (responseObject.item item : obj.getResult()) {
                System.out.println("Item -> " + item.getName());
                for (responseObject.item.locations loc : item.getLocation()) {
                    System.out.println("service -> " + loc.getName());
                }
            }

            super.onPostExecute(aVoid);
        }
    }*/
}