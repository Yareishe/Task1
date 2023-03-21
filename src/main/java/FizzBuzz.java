public class FizzBuzz {

    private int n;
    private int current = 1;
    private Object lock = new Object();

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz() throws InterruptedException {
        synchronized (lock) {
            while (current <= n) {
                if (current % 3 == 0 && current % 5 != 0) {
                    System.out.print("fizz ");
                    current++;
                    lock.notifyAll();
                } else {
                    lock.wait();
                }
            }
        }
    }

    public void buzz() throws InterruptedException {
        synchronized (lock) {
            while (current <= n) {
                if (current % 5 == 0 && current % 3 != 0) {
                    System.out.print("buzz ");
                    current++;
                    lock.notifyAll();
                } else {
                    lock.wait();
                }
            }
        }
    }

    public void fizzbuzz() throws InterruptedException {
        synchronized (lock) {
            while (current <= n) {
                if (current % 3 == 0 && current % 5 == 0) {
                    System.out.print("fizzbuzz ");
                    current++;
                    lock.notifyAll();
                } else {
                    lock.wait();
                }
            }
        }
    }

    public void number() throws InterruptedException {
        synchronized (lock) {
            while (current <= n) {
                if (current % 3 != 0 && current % 5 != 0) {
                    System.out.print(current + ", ");
                    current++;
                    lock.notifyAll();
                } else {
                    lock.wait();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 15;
        FizzBuzz fizzBuzz = new FizzBuzz(n);

        Thread threadA = new Thread(() -> {
            try {
                fizzBuzz.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadD = new Thread(() -> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        threadA.join();
        threadB.join();
        threadC.join();
        threadD.join();
    }
}