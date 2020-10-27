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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter adapter;
    String def_term = "";
    String nTerm;
    String def_country = "ca";
    String nCountry = "ca";
    //api_Utelly api = new api_Utelly();
    String url;
    ListView listView;
    TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.listView);
        emptyView = findViewById(R.id.emptyView);

        //api_Utelly tel = new api_Utelly();
        new api_utelly_getrequest().execute();
        //System.out.println(data);

        //found an json adapter, gonna replace this one with that
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.months_array));

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setEmptyView(emptyView);
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
        ResponseBody res;

        @Override
        protected Void doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();
            if(nTerm != null || nCountry != null) {
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
                res = client.newCall(request).execute().body();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            //adapt json for listview
            try {
                System.out.println(res.string());
                //JSONObject s = res.string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //populate listview
            super.onPostExecute(aVoid);
        }
    }

    private class reponseObject{
        {"results":
            [{
                "id": "5d97da029a76a40056de1c59",
                "picture": "https://utellyassets9-1.imgix.net/api/Images/2b54cb3bdb54d7bb696801e52a789247/Redirect",
                "name": "Nella the Princess Knight",
                "locations":
                    [{
                        "icon": "https://utellyassets7.imgix.net/locations_icons/utelly/black_new/GooglePlayIVACA.png?w=92&auto=compress&app_version=8bc263d1-dd7b-40c0-98cd-f677eb14d81e_e12122020-10-27",
                        "display_name": "Google Play",
                        "name": "GooglePlayIVACA",
                        "id": "5d84d6ddd95dc7385f6a43eb",
                        "url": "https://play.google.com/store/tv/show?amp=&amp=&cdid=tvseason-L2LpMv5jWB9shr1ABBPEGg&gdid=tvepisode-UFsxdW-kaMw&gl=CA&hl=en&id=RmS_3uRtDJmG6knRcnyCOQ"}],
                "provider": "iva",
                "weight": 7733,
                "external_ids":
                    {
                        "iva_rating": null,
                        "imdb":
                            {"url": "https://www.imdb.com/title/tt6415656",
                            "id": "tt6415656"},
                        "tmdb":
                            {"url": "https://www.themoviedb.org/tv/70104",
                                    "id": "70104"},
                            "wiki_data":
                                {"url": "https://www.wikidata.org/wiki/Q28312035",
                                        "id": "Q28312035"},
                            "iva": {"id": "404751"},
                            "gracenote": null,
                            "rotten_tomatoes": null,
                            "facebook": null}},
            {
                "id": "5ed7b803ba30cfc1910f143d",
                "picture": "https://utellyassets9-1.imgix.net/api/Images/1c580fc0c52f42c2e50fe57ce8199177/Redirect",
                "name": "Lego Jurassic World: Legend of Isla Nublar",
                "locations":
                    [{
                        "icon": "https://utellyassets7.imgix.net/locations_icons/utelly/black_new/iTunesIVACA.png?w=92&auto=compress&app_version=8bc263d1-dd7b-40c0-98cd-f677eb14d81e_e12122020-10-27",
                        "display_name": "iTunes",
                        "name": "iTunesIVACA",
                        "id": "5d8415b32393e90053ac366c",
                        "url": "https://itunes.apple.com/ca/tv-season/under-the-volcano/id1500308383?i=1502262476"},
                    {
                        "icon": "https://utellyassets7.imgix.net/locations_icons/utelly/black_new/GooglePlayIVACA.png?w=92&auto=compress&app_version=8bc263d1-dd7b-40c0-98cd-f677eb14d81e_e12122020-10-27",
                            "display_name": "Google Play",
                            "name": "GooglePlayIVACA",
                            "id": "5d84d6ddd95dc7385f6a43eb",
                            "url": "https://play.google.com/store/tv/show?amp=&amp=&cdid=tvseason-JdO_Dum-Z9Am21K4gn9eaw&gdid=tvepisode-XRkYW_8asYU&gl=CA&hl=en&id=pjUC4n_Gb94361bxBgOwhw"}],
                "provider": "iva",
                "weight": 0,
                "external_ids":
                    {
                        "iva_rating": null,
                        "imdb":
                            {
                                "url": "https://www.imdb.com/title/tt10872880",
                                "id": "tt10872880"},
                        "tmdb": {"url": "https://www.themoviedb.org/tv/93895", "id": "93895"},
                        "wiki_data": {"url": "https://www.wikidata.org/wiki/Q76574623", "id": "Q76574623"},
                        "iva": {"id": "676719"}, "gracenote": null, "rotten_tomatoes": null, "facebook": null}}],
            "updated": "2020-10-27T05:19:14+0000",
            "term": "null",
            "status_code": 200,
            "variant": "ivafull"}
    }
}