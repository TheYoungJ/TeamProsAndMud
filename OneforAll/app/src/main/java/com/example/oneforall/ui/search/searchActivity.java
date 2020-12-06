package com.example.oneforall.ui.search;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import com.example.oneforall.R;
import com.example.oneforall.ui.search.ImageAdapter;
import com.example.oneforall.ui.search.responseObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class searchActivity extends AppCompatActivity {
    ImageAdapter adapter;
    String def_term = "";
    String nTerm = def_term;
    String def_country = "ca";
    String nCountry = "ca";
    GridView gridView;
    responseObject obj;
    private Gson gson;
    private GsonBuilder gbuilder;
    ArrayList<ArrayList<String>> urls = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> provider_name = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> objAdapter = new ArrayList<ArrayList<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridView = findViewById(R.id.gridView);

        gbuilder = new GsonBuilder();
        gson = gbuilder.excludeFieldsWithModifiers(Modifier.STATIC).create();

        try {
            doGetRequest(def_term, def_country);
        } catch (IOException e) {
            e.printStackTrace();
        }

        adapter = new ImageAdapter(this, objAdapter);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {
                /*Toast.makeText(
                        getApplicationContext(),
                        ((TextView) v.findViewById(R.id.grid_item_label))
                                .getText(), Toast.LENGTH_SHORT).show();*/

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(searchActivity.this);
                //alertDialogBuilder.setMessage("Open this content in: ");
                List<String> plhol = provider_name.get(position);
                alertDialogBuilder.setSingleChoiceItems(plhol.toArray(new String[provider_name.get(position).size()]), -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Toast.makeText(getApplicationContext(),
                                "clicked", Toast.LENGTH_SHORT).show();
                        runURL(position, item);
                        dialog.dismiss();// dismiss the alertbox after chose option
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create(); //Read Update
                alertDialog.show();  //<-- See This!
            }
        });
    }

    public void runURL(int position, int item){
        Intent openurl = new Intent(Intent.ACTION_VIEW, Uri.parse(urls.get(position).get(item)));
        startActivity(openurl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem mSearch = menu.findItem(R.id.appSearchBar);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                nTerm = query;
                try {
                    doGetRequest(query, def_country);
                    adapter.updateView(objAdapter);
                    if(objAdapter != null || objAdapter.size() != 0) {
                        adapter.getFilter().filter(query);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //nTerm = newText;
                try {
                    doGetRequest(newText, def_country);
                    adapter.updateView(objAdapter);
                    if(objAdapter != null || objAdapter.size() != 0) {
                        adapter.getFilter().filter(newText);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    void doGetRequest(String term, String country) throws IOException{
        final String def_term = "";
        final String def_country = "ca";
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
                        System.out.println("ERROR IN run on UI thread");
                        // For the example, you can show an error dialog or a toast
                        // on the main UI thread
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String res = response.body().string();
                System.out.println("RESULT ----> "+res);
                obj = gson.fromJson(res, responseObject.class);
                for (responseObject.item item : obj.getResult()) {
                    //get names and pics urls for each result item
                    ArrayList<String> l = new ArrayList<>();
                    l.add(0, item.getName());
                    l.add(1, item.getPic());
                    objAdapter.add(l);
                    //update image adapter
                    adapter.updateView(objAdapter);

                    //get urls of providers and their names
                    ArrayList<String> it = new ArrayList<>();
                    ArrayList<String> p = new ArrayList<>();
                    for(responseObject.item.locations lo : item.getLocation()){
                        it.add(lo.getUrl());
                        p.add(lo.getDisplay_name());
                    }
                    urls.add(it);
                    provider_name.add(p);
                }
                /*System.out.println("URLS ----> \n");
                for(ArrayList<String> s : urls){
                    System.out.println("ITEM --->\n");
                    for(String url : s){
                        System.out.println("URL ---> "+url+"\n");
                    }
                }*/
            }
        });
    }
}