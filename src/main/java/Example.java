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
        getTasksForUser("1");
    }

    static class Todo {
        private int id;
        private int userId;
        private String title;
        private boolean completed;

        public int getId() {
            return id;
        }

        public int getUserId() {
            return userId;
        }

        public String getTitle() {
            return title;
        }

        public boolean getCompleted() {
            return completed;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        @Override
        public String toString() {
            return "Post{" +
                    "userId=" + userId +
                    ", id=" + id +
                    ", title=" + title +
                    ", completed=" + completed + '\'' +
                    '}';
        }
    }

    private static  Todo[]  getTasksForUser(String userId) throws IOException {
        URL url = new URL(TEST_URL +"/" +userId +"/todos?completed=false" );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String responseBody = in.lines().collect(Collectors.joining());
        Todo[] todos = gson.fromJson(responseBody, Todo[].class);
        if (responseCode != HttpURLConnection.HTTP_OK) {
            System.out.println("not work updateUser");
        }
        return todos;
    }
}