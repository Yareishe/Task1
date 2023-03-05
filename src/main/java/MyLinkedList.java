public class MyLinkedList {

    public static void main(String[] args) {
        MyLinkedList deliveryCities  = new MyLinkedList();
        deliveryCities.add("Cekago");
        deliveryCities.add("Cekago1");
        deliveryCities.remove(0);
        deliveryCities.add("Cekago2");
        deliveryCities.clear();
        deliveryCities.size();
        deliveryCities.get(9);
    }
    class Node<E> {
        Node<E> next;
        Node<E> prev;
        Object data;
        public Node(Object data) {
            this.data = data;
        }
    }
    Node root = new Node(null);
    public boolean add(Object value) {
        Node newNode = new Node(value);
        Node last = root;
        while (last.next != null) {
            last = last.next;
        }
        last.next = newNode;

        return true;
    }

    public boolean remove(int index) {

        return true;
        }
    void clear(){
    }
    int size(){
        return 7;
    }
    Object get(int index){
        return 6;
    }
}

