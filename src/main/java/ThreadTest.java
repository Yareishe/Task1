import java.time.Duration;
import java.time.Instant;

class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread messageThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    System.out.println(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        messageThread.start();

        Instant startTime = Instant.now();
        Thread timerThread = new Thread(() -> {
            while (true) {
                Instant now = Instant.now();
                Duration elapsed = Duration.between(startTime, now);
                System.out.println( elapsed.getSeconds());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timerThread.start();
    }
}