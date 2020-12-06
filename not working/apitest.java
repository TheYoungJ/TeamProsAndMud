import com.squareup.okhttp3.OkHttpClient;
import com.squareup.okhttp3.Request;
import com.squareup.okhttp3.Response;
import java.io.IOException;

public class apitest{
	public static Response get_request() throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
			.url("https://unogs-unogs-v1.p.rapidapi.com/aaapi.cgi")
			.get()
			.addHeader("x-rapidapi-host", "unogs-unogs-v1.p.rapidapi.com")
			.addHeader("x-rapidapi-key", "dd37d15cf1msha20c25bf6ba08c7p1c3821jsn93385a10f1f6")
			.build();

		Response response = client.newCall(request).execute();
		return response;
	}

	public static void main(String[] args){
		try{
			Response get_r = get_request();
			System.out.println(get_r);
		}
		catch(IOException e){ System.out.println("ERROR: " + e.toString());}

	}
}