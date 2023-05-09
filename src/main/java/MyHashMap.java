import java.util.Arrays;
public class MyHashMap {
    private static final int filledCount = 16;
    private Node[] array;
    private int size;
    public MyHashMap() {
        this.array = new Node[filledCount];
        this.size = 0;
    }
    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        System.out.println( map.size());
        System.out.println(map.get("key1"));
        map.remove("key2");
        System.out.println(map.size());
        map.clear();
        System.out.println(map.size());
    }
    private static class Node {
        Object key;
        Object value;
        Node next;

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
    public void put(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("Ключ не може бути null");
        }
        int hash = key.hashCode();
        int index = hash % array.length;
        for (Node node = array[index]; node != null; node = node.next) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        Node newNode = new Node(key, value);
        newNode.next = array[index];
        array[index] = newNode;
        size++;
        if (size > array.length * 0.75) {
            resize();
        }
    }
    public Object get(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("Ключ не може бути null");
        }
        int hash = key.hashCode();
        int index = hash % array.length;

        for (Node node = array[index]; node != null; node = node.next) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }

    public void remove(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("Ключ не може бути null");
        }
        int hash = key.hashCode();
        int index = hash % array.length;
        Node prev = null;
        Node node = array[index];
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    array[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return;
            }
            prev = node;
            node = node.next;
        }
    }
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }
    public int size() {
        return size;
    }
    private void resize() {
        Node[] oldTable = array;
        array = new Node[oldTable.length * 2];
        size = 0;

        for (Node node : oldTable) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }
}
