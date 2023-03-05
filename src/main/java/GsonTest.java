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
                // process the line.
                values.add(line);
            }
            System.out.println(values);
        }
        String[] titles = values.get(0).split(" ");
        String[] values1 = values.get(1).split(" ");
        String[] values2 = values.get(2).split(" ");
        String result = "[\n" +
                "    {\n" +
                "        \"" + titles[0] + "\": \"" + values1[0] + "\",\n" +
                "        \"" + titles[1] + "\": " + values1[1] + "\n" +
                "    },\n" +
                "    {\n" +
                "        \"" + titles[0] + "\": \"" + values2[0] + "\",\n" +
                "        \"" + titles[1] + "\": " + values2[1] + "\n" +
                "    }\n" +
                "]";
        try (FileWriter writer = new FileWriter("user.json", false)) {
            writer.write(result);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
