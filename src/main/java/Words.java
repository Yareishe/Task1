import java.io.*;
import java.util.*;

public class Words {

    public static void main(String[] args) {


        try(FileReader reader = new FileReader("file1.txt"))
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
            TreeMap<Integer,String> map = new TreeMap<>(Collections.reverseOrder());
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
                            map.put(b, a[i]);
                        }
                    }
                }

            }

            Set<Map.Entry<Integer,String>> entries = map.entrySet();
            for (Map.Entry<Integer,String> entry : entries) {
                System.out.println(entry.getValue() + " " + entry.getKey());
            }

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }
}