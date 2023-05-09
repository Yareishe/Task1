import java.util.Arrays;

public class MyStack<T> {
    int filledCount = 0;
    Object[] array = new Object[11];

    public static void main(String[] args) {
        MyStack<String> deliveryCities = new MyStack<>();
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
        deliveryCities.remove(6);
        deliveryCities.pop();
        System.out.println(deliveryCities.peek());
        System.out.println(deliveryCities);
        deliveryCities.clear();
        System.out.println(deliveryCities.size());
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

    void remove(int index) {
        Object[] result = new Object[array.length - 1];
        System.arraycopy(array, 0, result, 0, index);
        System.arraycopy(array, index + 1, result, index, array.length - index - 2);
        array = result;
        filledCount--;
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

    T pop() {
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
        return "MyStack{" +
                "filledCount=" + filledCount +
                ", array=" + Arrays.toString(array) +
                '}';
    }
}
