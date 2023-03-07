public class MyLinkedList {

    public static void main(String[] args) {
        MyLinkedList deliveryCities  = new MyLinkedList();
        deliveryCities.clear();
        deliveryCities.add("Cekago");
        deliveryCities.add("Cekago1");
        deliveryCities.add("Cekago2");
        deliveryCities.add("Cekago3");
        deliveryCities.add("Cekago4");
        System.out.println(deliveryCities);
        deliveryCities.remove(0);
        deliveryCities.add("Cekago5");
        System.out.println(deliveryCities.size());
        System.out.println(deliveryCities.get(1));
    }
    static class Node {
        Node next;
        Node prev;
        Object data;
        public Node(Object data) {
            this.data = data;
        }

        public static Node clone(Node node) {//needed for delete looping
            return new Node(node.getData());
        }

        public Object getData() {
            return data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "next=" + next +
                    ", prev=" + prev +
                    ", data=" + data +
                    '}';
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
        if (size() > 1) {
            last.next.prev = Node.clone(last);
        }
        return true;
    }

    public boolean remove(int index) {
        Node node = root;
        int i = 0;
        while (node.next != null) {
            if(index == i && size() > 2){
                if(node.next.prev == null){
                    node.next.prev = Node.clone(root);
                }
                node.next.next.prev = node.next.prev;
                node.next = node.next.next;
                break;
            }
            i++;
            node = node.next;
        }
        return true;
    }
    void clear(){
        root = new Node(null);
    }
    int size(){
        Node node = root;
        int size = 0;
        while (node.next != null) {
            size++;
            node = node.next;
        }
        return size;
    }
    Object get(int index){
        Node node = root;
        Node result = null;
        int size = 0;
        while (node.next != null) {
            if(index == size){
                result = node;
            }
            size++;
            node = node.next;
        }
        return result;
    }
}