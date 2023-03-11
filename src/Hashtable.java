import java.util.LinkedList;
import java.util.Iterator;

public class Hashtable<V> {
    private static double LOAD_FACTOR = 0.75;
    private static double LOAD_FACTOR_SMALL = 0.25;
    private static int SIZE_OF_TABLE = 8;
    private int size = 0;
    private int capacity = SIZE_OF_TABLE;
    private LinkedList<Node<V>>[] table = new LinkedList[SIZE_OF_TABLE];

    public Hashtable() {}

    public void insert(String key, V value) {
        int index = hashKey(key);
        LinkedList<Node<V>> list = table[index];
        if (list == null) {
            list = new LinkedList<>();
            list.add(new Node<>(key, value));
            table[index] = list;
        }
        else {
            for (Node<V> entry : list) {
                if (entry.key.equals(key)) {
                    System.out.println("There is already this key in hash table with value: " + entry.value);
                    return;
                }
            }
            list.add(new Node<>(key, value));
        }
        size++;
        System.out.println("Successfully inserted!");
        if (size > capacity * LOAD_FACTOR) {
            resize(true);
        }
    }

    public void delete(String key) {
        int index = hashKey(key);
        LinkedList<Node<V>> list = table[index];
        if (list != null) {
            Iterator<Node<V>> iterator = list.iterator();
            while (iterator.hasNext()) {
                Node<V> entry = iterator.next();
                if (entry.key.equals(key)) {
                    iterator.remove();
                    size--;
                    System.out.println("Successfully found and deleted key! Value=" + entry.value);
                    if(size < capacity * LOAD_FACTOR_SMALL && SIZE_OF_TABLE >= capacity / 2) resize(false);
                    return;
                }
            }
        }
        System.out.println("There is no such key in hash table!");
    }

    public V search(String key) {
        int index = hashKey(key);
        LinkedList<Node<V>> list = table[index];
        if (list != null) {
            for (Node<V> entry : list) {
                if (entry.key.equals(key)) {
                    System.out.println("I found this key in hashtable! Value: " + entry.value);
                    return entry.value;
                }
            }
        }
        System.out.println("There is no such key in table!");
        return null;
    }

    private int hashKey(String key) {
        final long FNV_offset_basis = 0xcbf29ce484222325L;
        final long FNV_prime = 1099511628211L;
        long hash = FNV_offset_basis;
        for (int i = 0; i < key.length(); i++) {
            hash = hash * 1099511628211L;
            hash ^= key.charAt(i);
        }
        return Math.abs((int) hash % capacity);
    }

    private void resize(boolean sizing) {
        if(sizing) capacity *= 2;
        else capacity /= 2;
        LinkedList<Node<V>>[] newTable = new LinkedList[capacity];
        for (LinkedList<Node<V>> list : table) {
            if (list != null) {
                for (Node<V> entry : list) {
                    int index = hashKey(entry.key);
                    LinkedList<Node<V>> newList = newTable[index];
                    if (newList == null) {
                        newList = new LinkedList<>();
                        newTable[index] = newList;
                    }
                    newList.add(entry);
                }
            }
        }
        table = newTable;
    }

    private static class Node<V> {

        public final String key;
        public V value;

        public Node(String key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
