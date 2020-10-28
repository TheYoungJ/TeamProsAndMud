package com.example.demosearch;

import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {
    ImageAdapter adapter;
    String def_term = "bad";
    String nTerm = "bad";
    String def_country = "ca";
    String nCountry = "ca";
    //api_Utelly api = new api_Utelly();
    String url;
    GridView gridView;
    responseObject obj;
    private Gson gson;
    private GsonBuilder gbuilder;
    api_Utelly tel = new api_Utelly();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridView = findViewById(R.id.gridView);
        //emptyView = findViewById(R.id.emptyView);

        gbuilder = new GsonBuilder();
        gson = gbuilder.excludeFieldsWithModifiers(Modifier.STATIC).create();

        //call the invoke process to  get the response from the api
        obj = tel.invokeGet(def_term, def_country);

        //get names from obj
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> pics = new ArrayList<String>();
        System.out.println("OBJ RESULTS ----> "+obj);
        for(responseObject.item item : obj.getResult()){
            names.add(item.getName());
            pics.add(item.getPic());
        }

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
        /*
         gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });
         */
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
                obj = tel.invokeGet(query, def_country);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                nTerm = newText;
                obj = tel.invokeGet(newText, def_country);
                //adapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


}