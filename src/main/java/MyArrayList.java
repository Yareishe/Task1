import java.util.Arrays;

public class MyArrayList<T> {
    int filledCount = 0;
    Object[] array = new Object[11];

    public static void main(String[] args) {
        MyArrayList<String> deliveryCities = new MyArrayList<>();
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
        System.out.println(deliveryCities);
        deliveryCities.clear();
        System.out.println(deliveryCities.size());
        deliveryCities.get(8);
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
        Object[] result = new Object[array.length - 2];
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
    T get(int index) {
        return (T) array[index];
    }
    @Override
    public String toString() {
        return "MyArrayList{" +
                "filledCount=" + filledCount +
                ", array=" + Arrays.toString(array) +
                '}';
    }
}