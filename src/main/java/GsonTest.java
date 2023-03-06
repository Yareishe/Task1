import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class GsonTest {
    public static void main(String[] args) throws IOException {
        ArrayList<String> values = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                values.add(line);
            }
            System.out.println(values);
        }
        String[] titles = values.get(0).split(" ");
        int size = values.size();
        StringBuilder result = new StringBuilder("[\n");
        for (int i = 1; i < size; i++) {
             if(size == 2) {
                String[] values1 = values.get(1).split(" ");
                result.append("    {\n" + "        \"")
                        .append(titles[0])
                        .append("\": \"")
                        .append(values1[0])
                        .append("\",\n")
                        .append("        \"")
                        .append(titles[1])
                        .append("\": ")
                        .append(values1[1])
                        .append("\n")
                        .append("    }\n");
            }
            else {
                String[] values1 = values.get(i).split(" ");
               result.append("    {\n" + "        \"")
                       .append(titles[0])
                       .append("\": \"")
                       .append(values1[0])
                       .append("\",\n")
                       .append("        \"")
                       .append(titles[1])
                       .append("\": ")
                       .append(values1[1])
                       .append("\n")
                       .append("    },\n");
            }
        }
        result.append("]");
        result = new StringBuilder(result.toString().replace(",\n]", "\n]"));
        try (FileWriter writer = new FileWriter("user.json", false)) {
            writer.write(result.toString());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
