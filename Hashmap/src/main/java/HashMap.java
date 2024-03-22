public class HashMap <K extends Comparable<K>,V> implements Map<K,V> {
    private Node<K, V>[] table;
    private int size;

    HashMap() {
        this.table = new Node[4];
    }

    public void put(K key, V value) {
        int hash = key.hashCode();
        int bucketIndex = hash % table.length;
        Node<K, V> currentNode = table[bucketIndex];
        while (currentNode != null) {
            if (hash == currentNode.getHash()) {
                if (key.equals(currentNode.getKey())) {
                    currentNode.setVal(value);
                    return;
                }
            }
            currentNode = currentNode.getNext();
        }
        Node<K, V> newNode = new Node<>(hash, key, value);
        newNode.setNext(table[bucketIndex]);
        table[bucketIndex] = newNode;
        size++;
    }

    public boolean containsKey(K key) {
        int hash = key.hashCode();
        int bucketIndex = hash % table.length;
        Node<K, V> currentNode = table[bucketIndex];
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }
    public boolean containsValue(V value) {
        for(Node<K,V> bucket: table ) {
            Node<K,V> currentNode = bucket;
            while(currentNode != null) {
                if(currentNode.getVal().equals(value)) {
                    return true;
                }
                currentNode = currentNode.getNext();
            }
        }
        return false;
    }
    public V remove (K key) {
        int hash = key.hashCode();
        int bucketIndex = hash % table.length;
        Node<K,V> curNode = table[bucketIndex];
        V res;
        if (curNode.getKey().equals(key)) {
            res = curNode.getVal();
            table[bucketIndex] = table[bucketIndex].getNext();
            size--;
            return res;
        }
        while (curNode.getNext() != null) {
            if (curNode.getNext().getKey().equals(key)) {
                res = curNode.getNext().getVal();
                curNode.setNext(curNode.getNext().getNext());
                size--;
                return res;
            }
            curNode = curNode.getNext();
        }
        return null;
    }
    public void clear () {
        table = new Node[4];
        size = 0;
    }
    public V get (K key) {
        int hash = key.hashCode();
        int bucketIndex = hash % table.length;
        Node<K,V> curNode = table[bucketIndex];
        while (curNode != null) {
            if (curNode.getKey().equals(key)) {
                return curNode.getVal();
            }
            curNode = curNode.getNext();
        }
        return null;
    }
    public int size() {
        return size;
    }



}