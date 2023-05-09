import java.util.Arrays;

public class MyStack<T> {
    int filledCount = 0;
    Object[] array = new Object[11];

    public static void main(String[] args) {
        MyStack<String> deliveryCities = new MyStack<>();
        deliveryCities.push("Cekago0");
        deliveryCities.push("Cekago1");
        deliveryCities.push("Cekago2");
        deliveryCities.push("Cekago3");
        deliveryCities.push("Cekago4");
        deliveryCities.push("Cekago5");
        deliveryCities.push("Cekago6");
        deliveryCities.push("Cekago7");
        deliveryCities.push("Cekago8");
        deliveryCities.push("Cekago9");
        deliveryCities.remove(0);
        System.out.println(deliveryCities.pop());
        System.out.println(deliveryCities.peek());
        System.out.println(deliveryCities);
        deliveryCities.clear();
        System.out.println(deliveryCities.size());
    }

    void push(T value) {
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
        array = new Object[11];
        filledCount = 0;
    }
    int size() {
        return filledCount;
    }

    T peek() {
        return (T) array[array.length - 1];
    }

    T pop() {
        T first = (T) array[array.length - 2];
        Object[] result = new Object[array.length  - 2];
        System.arraycopy(array, 0, result, 0, array.length  - 2);
        System.arraycopy(array, array.length  - 2 + 1, result, array.length  - 2, array.length - (array.length  - 2) - 2);
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
