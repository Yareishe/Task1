import java.util.Arrays;

public class MyQueue<T> {
    int filledCount = 0;
    Object[] array = new Object[11];

    public static void main(String[] args) {
        MyQueue<String> deliveryCities = new MyQueue<>();
        deliveryCities.add("Cekago0");
        deliveryCities.add("Cekago1");
        deliveryCities.add("Cekago2");
        deliveryCities.add("Cekago3");
        deliveryCities.add("Cekago4");
        deliveryCities.add("Cekago5");
        deliveryCities.add("Cekago6");
        deliveryCities.add("Cekago7");
        deliveryCities.add("Cekago8");
        deliveryCities.add("Cekago9");
        System.out.println(deliveryCities.poll());
        System.out.println(deliveryCities);
        System.out.println(deliveryCities.peek());
        deliveryCities.clear();

    }

    void add(T value) {
        if (array.length - 1 == filledCount) {
            Object[] array2 = new Object[array.length + 10];
            System.arraycopy(array, 0, array2, 0, array.length);
            array = array2;
        }
        array[filledCount] = value;
        filledCount++;
    }

    void clear() {
        array = new Object[filledCount];
    }

    int size() {
        return filledCount;
    }

   T peek() {
        return (T) array[0];
    }

    T poll() {
        T first = (T) array[0];
        Object[] result = new Object[array.length - 2];
        System.arraycopy(array, 0, result, 0, 0);
        System.arraycopy(array, 0 + 1, result, 0, array.length - 0 - 2);
        array = result;
        filledCount--;
        return first;
    }
    @Override
    public String toString() {
        return "MyQueue{" +
                "filledCount=" + filledCount +
                ", array=" + Arrays.toString(array) +
                '}';
    }
}