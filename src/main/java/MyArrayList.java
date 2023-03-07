import java.util.Arrays;

public class MyArrayList {
    int filledCount = 0 ;
    Object [] array = new Object[11];

    public static void main(String[] args) {
        MyArrayList deliveryCities = new MyArrayList();
        deliveryCities.add("Cekago");
        deliveryCities.remove(9);
        deliveryCities.clear();
        deliveryCities.size();
        deliveryCities.get(9);
    }

    boolean add(Object value) {

        if (array.length - 1 == filledCount) {
            Object[] array2 = new Object[array.length+10];
            System.arraycopy(array,0,array2,0, array.length );
            array = array2;
        }

            array[filledCount]=value;

        filledCount++;


        System.out.println(Arrays.toString(array));
     return true;
    }
    boolean remove(int index){
        array[index] = null;
        System.out.println((Arrays.toString(array)));
        return true;
    }
    void clear(){
        array = new Object[array.length];
        System.out.println((Arrays.toString(array)));
    }
    int size(){
        System.out.println(array.length);
        return array.length;
    }
    Object get(int index){
        System.out.println(array[index]);
        return array[index];
    }
}
