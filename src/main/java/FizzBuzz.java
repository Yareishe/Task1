import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzz {
    public static void main(String[] args) {
        int n = 150;
        AtomicInteger i = new AtomicInteger();
        FizzBuzz fizzBuzz = new FizzBuzz();
            Thread threadA = new Thread(() -> {
                while (i.get() <= n) {
                    if (i.get() % 3 == 0 && i.get() % 5 != 0) {
                        System.out.print("fizz ");
                        i.getAndIncrement();
                    }
                        else if (i.get() % 5 == 0 && i.get() % 3 != 0) {
                            System.out.print("fizz ");
                            i.getAndIncrement();
                        }
                     else {
                        System.out.println(i.get());
                    }
                }
            });
    }
}