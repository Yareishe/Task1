public class MyLinkedList<F> {

    public static void main(String[] args) {
        MyLinkedList<String> deliveryCities  = new MyLinkedList<>();
        deliveryCities.clear();
        deliveryCities.add("Cekago");
        deliveryCities.add("Cekago1");
        deliveryCities.add("Cekago2");
        deliveryCities.add("Cekago3");
        deliveryCities.add("Cekago4");
        deliveryCities.remove(0);
        deliveryCities.add("Cekago5");
        System.out.println(deliveryCities.size());
        System.out.println(deliveryCities.get(5));
    }
    class Node {
        Node next;
        Node prev;
        F data;
        public Node(F data) {
            this.data = data;
        }

        public Node clone(Node node) {
            return new Node(node.getData());
        }

        public F getData() {
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

    public boolean add(F value) {
        Node newNode = new Node(value);
        Node last = root;
        while (last.next != null) {
            last = last.next;
        }
        last.next = newNode;
        if (size() > 1) {
            Node node = new Node(null);
            last.next.prev = node.clone(last);
        }
        return true;
    }

    public boolean remove(int index) {
        Node node = root;
        int i = 0;
        while (node.next != null) {
            if(index == i && size() > 2) {
                if(node.next.prev == null){
                    Node nodeClone = new Node(null);
                    node.next.prev = nodeClone.clone(root);
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
    F get(int index) {
        Node node = root;
        int count = 0;
        while (node.next != null) {
            if(index > size()-1){
                node = null;
            }
            else if (index == 0) {
                node = root.next;
                break;
            }
            else if (index + 1 == count) {
                break;
            }

            count++;
            node = node.next;
        }
        return node.getData();
    }
}