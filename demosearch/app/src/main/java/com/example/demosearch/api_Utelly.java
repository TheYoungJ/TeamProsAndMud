package com.example.demosearch;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class api_Utelly{

    OkHttpClient client = new OkHttpClient();

    JSONObject getRequest(String term, String country) throws IOException, JSONException {
        String url = "https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term="+term+"&country="+country;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "dd37d15cf1msha20c25bf6ba08c7p1c3821jsn93385a10f1f6")
                .build();

        Response response = client.newCall(request).execute();
        String jdata = response.body().toString();
        return new JSONObject(jdata);
    }

    /*public static void main(String[] args){
        String term = "The Office";
        String country = "ca";
        api_Utelly api_response = new api_Utelly();
        try{
            Response get_r = api_response.getRequest(term, country);
            System.out.println(get_r.toString());
        }
        catch(IOException e){ System.out.println("ERROR: " + e.toString());}
    }*/
}