import java.util.Arrays;
import java.util.*;
import java.util.stream.Stream;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;
class StreamAPI {
    public static void main(String[] args) {
        String[] name = {"g","b","a","k","l"};
        String[] arr = new StreamAPI().arr(name).toArray(new String[0]);
        String[] number = {"91, 2, 0", "12, 4, 5"};
        String numbers = new StreamAPI().numbers(number);
        System.out.println(Arrays.toString(arr));
        System.out.println(numbers);
        formula(1234L).limit(10).forEach(System.out::println);
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> stream2 = Stream.of(6, 7, 8, 9, 10, 11, 12, 13, 14);
        zip(stream1, stream2).forEach(System.out::println);
        }
    public List<String> arr (String[] odd){
        List<String> names = Arrays.asList(odd);
        List<String> arrevers = names.stream()
                .filter(n -> names.indexOf(n) % 2 == 0)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        return arrevers;
    }
    public String numbers(String[] odd){
        String result = Arrays.stream(odd)
                .flatMap(s -> Arrays.stream(s.split(", ")))
                .map(Integer::parseInt)
                .distinct()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println(result);
        return result;
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
            if (!iterator1.hasNext() || !iterator2.hasNext()) {
                break;
            }
        }
        return result.stream();
    }
}