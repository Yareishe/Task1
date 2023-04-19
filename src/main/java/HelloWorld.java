import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.*;

public class HelloWorld {
    private static final String TEST_URL = "https://jsonplaceholder.typicode.com/users";
    public static void main(String[] args) throws IOException {
        getUsers();
        createUser();
        updateUser("10");
        deleteUser("10");
        getUserById("8");
        getUserByUsername("Bret");
        saveCommentsByUserPost("1");
        createGet("1");
    }

    static class User {
        private Address address;
        private Company company;
        private Address.Geo geo;
        private int id;
        private String name;
        private String phone;
        private String website;
        private String email;
        private String username;

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Company getCompany() {
            return company;
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Address.Geo getGeo() {
            return geo;
        }

        public void setGeo(Address.Geo geo) {
            this.geo = geo;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "address=" + address +
                    ", company=" + company +
                    ", geo=" + geo +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", website='" + website + '\'' +
                    ", email='" + email + '\'' +
                    ", username='" + username + '\'' +
                    '}';
        }

        static class Address {
            private String street;
            private String suite;
            private String city;
            private String zipcode;

            public void setStreet(String street) {
                this.street = street;
            }

            public void setSuite(String suite) {
                this.suite = suite;
            }

            public void setZipcode(String zipcode) {
                this.zipcode = zipcode;
            }

            public void setCity(String city) {
                this.city = city;
            }

            @Override
            public String toString() {
                return "Address{" +
                        "street='" + street + '\'' +
                        ", suite='" + suite + '\'' +
                        ", city='" + city + '\'' +
                        ", zipcode='" + zipcode + '\'' +
                        '}';
            }

            static class Geo {
                private String lat;
                private String lng;

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public void setLng(String lng) {
                    this.lng = lng;
                }
            }
        }

        static class Company {
            private String name;
            private String catchPhrase;
            private String bs;

            public void setName(String name) {
                this.name = name;
            }

            public void setCatchPhrase(String catchPhrase) {
                this.catchPhrase = catchPhrase;
            }

            public void setBs(String bs) {
                this.bs = bs;
            }

            @Override
            public String toString() {
                return "Company{" +
                        "name='" + name + '\'' +
                        ", catchPhrase='" + catchPhrase + '\'' +
                        ", bs='" + bs + '\'' +
                        '}';
            }
        }
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

    static class Post {
        private int id;
        private int userId;
        private String title;
        private String body;

        public int getId() {
            return id;
        }

        public int getUserId() {
            return userId;
        }

        public String getTitle() {
            return title;
        }

        public String getBody() {
            return body;
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

        public void setBody(String body) {
            this.body = body;
        }

        @Override
        public String toString() {
            return "Post{" +
                    "userId=" + userId +
                    ", id=" + id +
                    ", title=" + title +
                    ", body='" + body + '\'' +
                    '}';
        }
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
    private static void getUsers() throws IOException {
        URL url = new URL(TEST_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(connection.getResponseMessage());
            String responseBody = in.lines().collect(Collectors.joining());
            List<User> users = gson.fromJson(responseBody, ArrayList.class);
            System.out.println(users);
        } else {
            System.out.println("GET request not worked");
        }
    }

    private static void createUser() throws IOException {
        URL url = new URL(TEST_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User user = new User();

        user.setName("Vasya");
        user.setId(11);
        user.setUsername("M.S");
        user.setEmail("AAAAA@.email");
        user.setPhone("1234567890");
        user.setWebsite("ffff.net");

        User.Address address = new User.Address();
        address.setStreet("xz");
        address.setSuite("Suite 444");
        address.setCity("Kyev");
        address.setZipcode("6666-7777");

        User.Address.Geo geo = new User.Address.Geo();
        geo.setLat("34.657544");
        geo.setLng("2882182");

        User.Company company = new User.Company();
        company.setName("ttt");
        company.setCatchPhrase("fgdfg - gfdffdsf - dgfdd");
        company.setBs("refre - gfdf");

        String userAsString = gson.toJson(user);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = userAsString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        int responseCode = connection.getResponseCode();
        System.out.println("POST response code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String responseBody = in.lines().collect(Collectors.joining());
            User users = gson.fromJson(responseBody, User.class);
            System.out.println(users);

        } else {
            System.out.println("POST request not worked");
        }
    }

    private static void updateUser(String userId) throws IOException {
        URL url = new URL(TEST_URL + "/" + userId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User user = new User();

        user.setName("Vasya");
        user.setId(11);
        user.setUsername("M.S");
        user.setEmail("AAAAA@.email");
        user.setPhone("1234567890");
        user.setWebsite("ffff.net");

        User.Address address = new User.Address();
        address.setStreet("xz");
        address.setSuite("Suite 444");
        address.setCity("Kyev");
        address.setZipcode("6666-7777");

        User.Address.Geo geo = new User.Address.Geo();
        geo.setLat("34.657544");
        geo.setLng("2882182");

        User.Company company = new User.Company();
        company.setName("ttt");
        company.setCatchPhrase("fgdfg - gfdffdsf - dgfdd");
        company.setBs("refre - gfdf");

        String userAsString = gson.toJson(user);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = userAsString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        int responseCode = connection.getResponseCode();
        System.out.println("PUT response code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String responseBody = in.lines().collect(Collectors.joining());
            User users = gson.fromJson(responseBody, User.class);
            System.out.println(users);

        } else {
            System.out.println("PUT request not worked");
        }
    }

    private static void deleteUser(String userId) throws IOException {
        URL url = new URL(TEST_URL + "/" + userId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User user = new User();

        user.setName("Vasya");
        user.setId(11);
        user.setUsername("M.S");
        user.setEmail("AAAAA@.email");
        user.setPhone("1234567890");
        user.setWebsite("ffff.net");

        User.Address address = new User.Address();
        address.setStreet("xz");
        address.setSuite("Suite 444");
        address.setCity("Kyev");
        address.setZipcode("6666-7777");

        User.Address.Geo geo = new User.Address.Geo();
        geo.setLat("34.657544");
        geo.setLng("2882182");

        User.Company company = new User.Company();
        company.setName("ttt");
        company.setCatchPhrase("fgdfg - gfdffdsf - dgfdd");
        company.setBs("refre - gfdf");

        String userAsString = gson.toJson(user);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = userAsString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        int responseCode = connection.getResponseCode();
        System.out.println("DELETE response code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String responseBody = in.lines().collect(Collectors.joining());
            User users = gson.fromJson(responseBody, User.class);
            System.out.println(users);

        } else {
            System.out.println("DELETE request not worked");
        }
    }

    public static void getUserById(String userId) throws IOException {
        URL url = new URL(TEST_URL + "/" + userId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(connection.getResponseMessage());
            String responseBody = in.lines().collect(Collectors.joining());
            User users = gson.fromJson(responseBody, User.class);
            System.out.println(users);
        } else {
            System.out.println("GET request not worked");
        }
    }

    public static void getUserByUsername(String userName) throws IOException {
        URL url = new URL(TEST_URL + "?username=" + userName);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(connection.getResponseMessage());
            String responseBody = in.lines().collect(Collectors.joining());
            List<User> users = gson.fromJson(responseBody, ArrayList.class);
            System.out.println(users);
        } else {
            System.out.println("GET request not worked");
        }
    }

    private static void saveCommentsByUserPost(String userId) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        int maxId = -1;
        /////////////////1
        URL url = new URL("https://jsonplaceholder.typicode.com/users/" + userId + "/posts");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("GET1 response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String responseBody = in.lines().collect(Collectors.joining());
            Post[] posts = gson.fromJson(responseBody, Post[].class);
            System.out.println(Arrays.toString(posts));
            for (Post post : posts) {
                if (post.id > maxId) {
                    maxId = post.id;
                }
            }
        } else {
            System.out.println("GET1 request not worked");
        }

        ///////////////2
        URL url1 = new URL("https://jsonplaceholder.typicode.com/posts/" + maxId + "/comments");
        HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
        connection1.setRequestMethod("GET");
        int responseCode1 = connection1.getResponseCode();
        System.out.println("GET2 response code: " + responseCode1);
        List<Comment> comments = null;
            String responseBody = "";
        if (responseCode1 == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection1.getInputStream()));
            responseBody = in.lines().collect(Collectors.joining());
            comments = gson.fromJson(responseBody, ArrayList.class);
            System.out.println(comments);
        } else {
            System.out.println("GET2 request not worked");
        }
        ////////////////3 save to file
            File file = new File("user-" + userId + "-post-" + maxId+ "-comments.json");

            try(FileWriter writer = new FileWriter(file, false))
            {

                Object text = responseBody;
                writer.write((String) text);
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
    }

    private static void createGet(String userId) throws IOException {
        URL url = new URL(TEST_URL +"/"+ userId + "/todos");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(connection.getResponseMessage());
            String responseBody = in.lines().collect(Collectors.joining());
            Todo[] todos = gson.fromJson(responseBody, Todo[].class);
            for (Todo todo : todos) {
                if (!todo.completed){
                    System.out.println(todo);
                }
            }

        } else {
            System.out.println("GET request not worked");
        }
    }
}

