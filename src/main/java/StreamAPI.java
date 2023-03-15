import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.*;
import java.util.stream.Stream;

class StreamAPI {
    public static void main(String[] args) {
        String[] name = {"g","b","a","k","l"};
        String[] odd = new StreamAPI().odd(name);
        System.out.println(1);
        System.out.println(Arrays.toString(odd));
        String[] ABC = new StreamAPI().ABC(odd);
        String[] number = {"1, 2, 0", "4, 5"};
        String numbers = new StreamAPI().numbers(number);
        System.out.println(22);
        System.out.println(Arrays.toString(ABC));
        System.out.println(333);
        System.out.println(numbers);
        Stream<Long> formula = new StreamAPI().formula(1L);



        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> stream2 = Stream.of(6, 7, 8, 9, 10, 11);
        System.out.println(55555);
        zip(stream1, stream2).forEach(System.out::println);

        }

    public String[] odd(String[] name){
        int J = 0;
        for (int i = 0; i < name.length; i++) {
            if(i % 2 == 0){
                J++;
            }
        }
        String[] names = new String[J];
        int a = 0;
        for (int i = 0; i < name.length; i++) {
            if(i % 2 == 0){
                names[a] = name[i];
                a++;
            }
        }
        return names;
    }
    public String[] ABC(String[] odd){
        Map<String,String> details =new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < odd.length; i++) {
            details.put(odd[i],odd[i]);
        }
        Set set = details.entrySet();
        Iterator d = set.iterator();
        int b = 0;
        while (d.hasNext()) {
            Map.Entry me = (Map.Entry)d.next();
            odd[b] = (String) me.getValue();
            b++;
        }
        
        return odd;
    }
    public String numbers(String[] odd){
        List<Integer> sorted = Arrays.stream(
                        Arrays.toString(odd)
                                .replace("[", "")
                                .replace("]", "")
                                .replace(" ", "")
                                .split(",")
                )
                .map(Integer::parseInt)
                .sorted()
                .toList();

        return sorted.toString().replace("[","").replace("]", "");
    }
    public static Stream<Long> formula(long seed) {
        long c = 11L;
        long m = (long) Math.pow(2, 48);
        long a = 25214903917L;

        return Stream.iterate(seed, x -> (a * x + c) % m);
    }
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();
        List<T> result = new ArrayList<>();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            result.add(iterator1.next());
            result.add(iterator2.next());
        }
        return result.stream();
    }
}