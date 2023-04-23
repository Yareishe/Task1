import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class Example {
    private static final String TEST_URL = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) throws IOException {
        getasksforuser("1");
    }


    private static  Gson  getasksforuser(String userId) throws IOException {
        URL url = new URL(TEST_URL +"/" +userId +"/todos?completed=false" );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            System.out.println("not work updateUser");
        }
        return gson;
    }
}