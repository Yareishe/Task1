import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Words {

    public static void main(String[] args) {

        try(FileWriter writer = new FileWriter("file.txt", false))
        {

            String text = "the day is sunny the the";
            writer.write(text);
            writer.append("\n");
            writer.append("the sunny is is");
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        try(FileReader reader = new FileReader("file.txt"))
        {
            // читаем посимвольно
            int c;
            String s = "";
            while((c=reader.read())!=-1){
                if ((char)c == '\n') {
                    s += ' ';
                }
                else {
                    s += (char) c;
                }
            }

            String[] a = s.split(" ");
            HashMap<String,Integer> map = new HashMap<>();
            for (int i = 0;i < a.length;i++){
                int b = 0;
                for (int j = 0; j < a.length; j++) {
                    if (a[i].equals(a[j])){
                        b++;
                    }
                }

                for (int l = 0; l < a.length; l++) {
                    if (a[i].equals(a[l])){
                        if(a[i] != null) {
                            map.put(a[i], b);
                        }
                    }
                }

            }
            Set<Map.Entry<String, Integer>> entries = map.entrySet();
            for (Map.Entry<String, Integer> entry : entries) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }
}