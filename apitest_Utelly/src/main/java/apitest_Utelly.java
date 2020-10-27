package apitest_Utelly;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class apitest_Utelly{

	public static Response getRequest(String term, String country){
		
		OkHttpClient client = new OkHttpClient();

		String url = "https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term="+term+"&country="+country

		Request request = new Request.Builder()
			.url(url)
			.get()
			.addHeader("x-rapidapi-host", "utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com")
			.addHeader("x-rapidapi-key", "dd37d15cf1msha20c25bf6ba08c7p1c3821jsn93385a10f1f6")
			.build();

		Response response = client.newCall(request).execute();
		return response;
	}
	
	public static void main(String[] args){
		String term = "The Office"
		String country = "ca"
		
		try{
			Response get_r = getRequest(term, country);
			System.out.println(get_r);
		}
		catch(IOException e){ System.out.println("ERROR: " + e.toString());}

	}
}