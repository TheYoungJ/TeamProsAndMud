package com.example.demosearch;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class api_Utelly  extends Activity {
    private final String def_term = "bad";
    private final String def_country = "ca";
    private String nTerm = "bad";
    private String nCountry = "ca";
    private String url;
    Gson gson;
    responseObject obj;

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
                obj = gson.fromJson(res, responseObject.class);
                System.out.println("OBJ INSIDE ---> "+obj);
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
            System.out.println("OBJ ON POST ---> "+obj);
            //testing for each part of the response, important parts
            for(responseObject.item item : obj.getResult()){
                System.out.println("Item -> "+ item.getName());
                for(responseObject.item.locations loc : item.getLocation()) {
                    System.out.println("service -> " + loc.getName());
                }
            }

            super.onPostExecute(aVoid);
        }


    }

    public responseObject invokeGet(String term, String country){
        if(term != null && country != null){
            nTerm = term;
            nCountry = country;
        }
        new api_utelly_getrequest().execute();
        return obj;
    }

    /*OkHttpClient client = new OkHttpClient();

    JSONObject getRequest(String term, String country) throws IOException, JSONException {
        String url = "https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term="+term+"&country="+country;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "dd37d15cf1msha20c25bf6ba08c7p1c3821jsn93385a10f1f6")
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        //Response response = client.newCall(request).execute();
        String jData = response.body().toString();
        return new JSONObject(jData);
    }

    public static void main(String[] args){
        String term = "The Office";
        String country = "ca";
        api_Utelly api_response = new api_Utelly();
        try{
            JSONObject get_r = api_response.getRequest(term, country);
            System.out.println(get_r);
        }
        catch(IOException | JSONException e){ System.out.println("ERROR: " + e.toString());}
    }*/
}