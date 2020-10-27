package com.example.demosearch;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class api_Utelly  {

    OkHttpClient client = new OkHttpClient();

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
    /*
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