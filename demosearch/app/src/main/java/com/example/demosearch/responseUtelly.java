package com.example.demosearch;

import android.app.Activity;
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

public class responseUtelly extends Activity {
    private responseObject obj;
    private Gson gson;
    private GsonBuilder gbuilder;
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> pics = new ArrayList<String>();



    void doGetRequest(String term, String country) throws IOException {
        final String def_term = "bad";
        final String def_country = "ca";
        //private String nTerm;
        //private String nCountry;
        String url;

        OkHttpClient client = new OkHttpClient();
        if (term != null || term.length() != 0 || country != null || country.length() != 0) {
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
                //System.out.println("RESULT ----> "+res);
                gbuilder = new GsonBuilder();
                gson = gbuilder.excludeFieldsWithModifiers(Modifier.STATIC).create();
                obj = gson.fromJson(res, responseObject.class);
                for (responseObject.item item : obj.getResult()) {
                    names.add(item.getName());
                    pics.add(item.getPic());
                    //System.out.println(item.toString());
                }
            }
        });
    }

    public responseObject getObj(String term, String country) throws IOException, InterruptedException {
        doGetRequest(term, country);
        if(obj != null){
            return obj;
        }else{
            wait(3);
            return obj;
        }
    }
}