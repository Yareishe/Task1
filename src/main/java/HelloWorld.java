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


    private static List<User> getUsers() throws IOException {
        URL url = new URL(TEST_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(connection.getResponseMessage());
        String responseBody = in.lines().collect(Collectors.joining());
        List<User> users = gson.fromJson(responseBody, ArrayList.class);
        if (responseCode != HttpURLConnection.HTTP_OK) {
            System.out.println("npt work getUsers");
        }
        return users;
    }


    private static User createUser() throws IOException {
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
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String responseBody = in.lines().collect(Collectors.joining());
            User users = gson.fromJson(responseBody, User.class);
        if (responseCode != HttpURLConnection.HTTP_CREATED) {
            System.out.println("not work createUser");
        }
        return users ;
    }

    private static User updateUser(String userId) throws IOException {
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
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
        String responseBody = in.lines().collect(Collectors.joining());
        User users = gson.fromJson(responseBody, User.class);
        if (responseCode != HttpURLConnection.HTTP_OK) {
            System.out.println("not work updateUser");
        }
        return users ;
    }

    private static void deleteUser(String userId) throws IOException {
        URL url = new URL(TEST_URL + "/" + userId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            System.out.println("not work deleteUser");
        }
    }

    public static User getUserById(String userId) throws IOException {
        URL url = new URL(TEST_URL + "/" + userId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(connection.getResponseMessage());
        String responseBody = in.lines().collect(Collectors.joining());
        User users = gson.fromJson(responseBody, User.class);
        if (responseCode != HttpURLConnection.HTTP_OK) {
            System.out.println("not work getUserById");
        }
        return users ;
    }

    public static List<User> getUserByUsername(String userName) throws IOException {
        URL url = new URL(TEST_URL + "?username=" + userName);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(connection.getResponseMessage());
        String responseBody = in.lines().collect(Collectors.joining());
        List<User> users = gson.fromJson(responseBody, ArrayList.class);
        if (responseCode != HttpURLConnection.HTTP_OK) {
            System.out.println("not work getUserByUsername");
        }
        return users ;
    }

}