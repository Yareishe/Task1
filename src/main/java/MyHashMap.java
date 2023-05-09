public class MyHashMap<K, V> {
    public static void main(String[] args) {
        MyHashMap<Integer, String> deliveryCities = new MyHashMap<>();
        deliveryCities.clear();
        deliveryCities.put(0, "Cekago");
        deliveryCities.put(1, "Cekago1");
        deliveryCities.put(2, "Cekago2");
        deliveryCities.put(3, "Cekago3");
        deliveryCities.put(1, "Cekago4");
        deliveryCities.remove(0);
        deliveryCities.put(5, "Cekago5");
        System.out.println(deliveryCities.size());
        System.out.println("{" + deliveryCities + "}");
    }
    class Node {
        Node next;
        K key;
        V value;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public Node(K key) {
            this.key = key;
        }
        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            if (next != null) {
                return key + "=" + value + ',' + next;
            } else {
                return key + "=" + value;
            }
        }
    }

    Node root = new Node(null, null);

    public boolean put(K key, V value) {
        Node newNode = new Node(key, value);
        Node last = root;
        if(!containsKey(key)) {
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        } else {
            getNode(key).value = value;
        }
        return true;
    }

    public boolean remove(K key) {
        Node node = root;
        Node newNode = node.next;
        while (newNode != null) {
            if (newNode.key.equals(key)) {
                node.next = newNode.next;
                return true;
            }
            node = newNode;
            newNode = newNode.next;
        }
        return true;
    }

    void clear() {
        root = new Node(null);
    }

    int size() {
        Node node = root;
        int size = 0;
        while (node.next != null) {
                size++;
                node = node.next;
        }
        return size;
    }

    boolean containsKey(K key){
        Node node = root;
        boolean result = false;
        while (node.next != null) {
            node = node.next;
            if(node.key.equals(key)){
                result = true;
                break;
            }
        }
        return result;
    }

    Node getNode(K key){
        Node node = root;
        Node result = null;
        while (node.next != null) {
            node = node.next;
            if(node.key.equals(key)){
                result = node;
                break;
            }
        }
        return result;
    }

    V get(Object key) {
        Node node = root;
        V result = null;
        while (node.next != null) {
            node = node.next;
            if(node.key.equals(key)){
                result = node.value;
                break;
            }
        }
        return result;
    }
    @Override
    public String toString() {
        return "{" + root.next + "}";
    }
}