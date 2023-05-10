import java.util.Arrays;

public class MyHashMap<K, V> {
    private Node<K, V>[] table;
    private int size;

    public static void main(String[] args) {
        MyHashMap<Object,Object> map = new MyHashMap<>();
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
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap() {
        table = new Node[16];
    }

    public void put(K key, V value) {
        int hash = key.hashCode() % table.length;
        Node<K, V> node = table[hash];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.next;
        }
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = table[hash];
        table[hash] = newNode;
        size++;
        if ((double) size / table.length > 0.75) {
            resize();
        }
    }

    public V get(K key) {
        int hash = key.hashCode() % table.length;
        Node<K, V> node = table[hash];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public void remove(K key) {
        int hash = key.hashCode() % table.length;
        Node<K, V> node = table[hash];
        Node<K, V> prevNode = null;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prevNode == null) {
                    table[hash] = node.next;
                } else {
                    prevNode.next = node.next;
                }
                size--;
                return;
            }
            prevNode = node;
            node = node.next;
        }
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    private void resize() {
        Node<K, V>[] newTable = new Node[table.length * 2];
        for (Node<K, V> node : table) {
            while (node != null) {
                Node<K, V> nextNode = node.next;
                int hash = node.key.hashCode() % newTable.length;
                node.next = newTable[hash];
                newTable[hash] = node;
                node = nextNode;
            }
        }
        table = newTable;
    }
}