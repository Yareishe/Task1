import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class GsonTest {
    public static void main(String[] args) {
        Object[] d = new Object[6];
        try(FileWriter writer = new FileWriter("file.txt", false))
        {

            String text = "nama age";
            writer.write(text);
            writer.append(" ");
            writer.append("alice 21");
            writer.append(" ");
            writer.append("ryan 30");
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        try(FileReader reader = new FileReader("file.txt"))
        {
            int c;
            String s = "";
            while((c=reader.read())!=-1){
                s += (char)c;
            }
            d = s.split(" ");
            String v = " ";
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        Person person = new Person((String) d[2], (String) d[3]);
        Person person2 = new Person((String) d[4], (String) d[5]);
        Person [] array = new Person[2];
        array[0] = person;
        array[1] = person2;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Object json = gson.toJson(array);
        System.out.println(json);

        try(FileWriter writer = new FileWriter("user.json", false))
        {

            Object text = json;
            writer.write((String) text);

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}

class Person {
    private String nam;
    private String age1;
    private String married;
    private String height;
    private String a;
    private String b;
    public Person(String name, String age) {
        this.nam = name;
        this.age1 = age;
        this.married = married;
        this.height = height;
        this. a = a;
        this.b = b;
    }

    public String getName() {
        return nam;
    }

    public String getAge() {
        return age1;
    }

    public String isMarried() {
        return married;
    }

    public String getHeight() {
        return height;
    }
    public String getA() {
        return a;
    }
    public String getB() {
        return b;
    }
}