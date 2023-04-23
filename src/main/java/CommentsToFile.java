import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommentsToFile {

    private static final String TEST_URL =
            "https://jsonplaceholder.typicode.com/users/10/posts";

    public static void main(String[] args) throws IOException {
        String userId = "1";
        int maxId = -1;
        String responseBody = "";
        responseBody = CommentsByUserPost(MaxID(userId,maxId),responseBody);
        saveJSON(userId,MaxID(userId,maxId), responseBody);
    }

    static class Comment {
        private int postId;
        private int id;
        private String name;
        private String email;
        private String body;

        public int getPostId() {
            return postId;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getBody() {
            return body;
        }

        public void setPostId(int postId) {
            this.postId = postId;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setBody(String body) {
            this.body = body;
        }

        @Override
        public String toString() {
            return "Comment{" +
                    "postId=" + postId +
                    ", id=" + id +
                    ", name=" + name +
                    ", email=" + email +
                    ", body='" + body + '\'' +
                    '}';
        }
    }

    private static int MaxID(String userId,int maxId) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        URL url = new URL("https://jsonplaceholder.typicode.com/users/" + userId + "/posts");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String responseBody = in.lines().collect(Collectors.joining());
            Comment[] posts = gson.fromJson(responseBody, Comment[].class);
            for (Comment post : posts) {
                if (post.id > maxId) {
                    maxId = post.id;
                }
            }
            return maxId;
        } else {
            System.out.println("GET request not worked");
            return maxId;
        }
    }
    private static String CommentsByUserPost(int maxId, String responseBody) throws IOException {
        URL url1 = new URL("https://jsonplaceholder.typicode.com/posts/" + maxId + "/comments");
        HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
        connection1.setRequestMethod("GET");
        int responseCode1 = connection1.getResponseCode();
        if (responseCode1 == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection1.getInputStream()));
            responseBody = in.lines().collect(Collectors.joining());
            return responseBody;
        } else {
            return responseBody;
        }
    }
    private static void saveJSON(String userId ,int maxId,String responseBody) throws IOException {
        File file = new File("user-" + userId + "-post-" + maxId + "-comments.json");
        try(FileWriter writer = new FileWriter(file, false))
        {
            Object text = responseBody;
            writer.write((String) text);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}