package labs.l10_hash_tables_sets_maps.lab;

import java.util.*;
import java.util.stream.Collectors;

public class HashTable<TKey, TValue> implements Iterable<KeyValue<TKey, TValue>> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double loadFactor = 0.75;
    private int size;
    private int capacity;
    private List<KeyValue<TKey, TValue>>[] chainedElements;

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.chainedElements = new LinkedList[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void add(TKey key, TValue value) {
        growIfNecessary();
        int index =  Math.abs(key.hashCode()) % this.chainedElements.length;
        if(null == chainedElements[index]) {
            this.chainedElements[index] = new LinkedList<>();
        }
        for (KeyValue<TKey, TValue> item : this.chainedElements[index]) {
            if(item.getKey().equals(key)) {
                throw new IllegalArgumentException();
            }
        }
        this.chainedElements[index].add(new KeyValue<>(key, value));
        this.size++;
    }

    public int size() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int capacity() {
        return this.capacity;
    }

    public boolean addOrReplace(TKey key, TValue value) {
        growIfNecessary();
        int index =  key.hashCode() % this.capacity;
        if(chainedElements[index] == null) {
            this.chainedElements[index] = new LinkedList<>();
        }
        for (KeyValue<TKey, TValue> item : this.chainedElements[index]) {
            if(item.getKey().equals(key)) {
                item.setValue(value);
                return true;
            }
        }
        this.chainedElements[index].add(new KeyValue<>(key, value));
        this.size++;
        return false;
    }

    public TValue get(TKey key) {
        KeyValue<TKey, TValue> kvp = this.find(key);
        if(kvp == null) {
            throw new IllegalArgumentException();
        }
        return kvp.getValue();
    }

    public boolean tryGetValue(TKey key, TValue value) {
        KeyValue<TKey, TValue> kvp = this.find(key);
        if(kvp == null) {
            return false;
        }
        value = kvp.getValue();
        return true;
    }

    public KeyValue<TKey, TValue> find(TKey key) {
        int index = Math.abs(key.hashCode() % this.chainedElements.length);
        if(this.chainedElements[index] != null) {
            for (KeyValue<TKey, TValue> item : this.chainedElements[index]) {
                if(item.getKey().equals(key)) {
                    return item;
                }
            }
        }
        return null;
    }

    public boolean containsKey(TKey key) {
        return this.find(key) != null;
    }

    public boolean remove(TKey key) {
        int index = Math.abs(key.hashCode()) % this.chainedElements.length;
        if (this.chainedElements[index] != null) {
            List<KeyValue<TKey, TValue>> crntList = this.chainedElements[index];
            for (KeyValue<TKey, TValue> keyValuePair : crntList) {
                if (keyValuePair.getKey().equals(key)) {
                    crntList.remove(keyValuePair);
                    this.size--;
                    return true;
                }
            }
        }
        return false;
    }

    public void clear() {
        this.chainedElements = new LinkedList[DEFAULT_CAPACITY];
    }

    public Iterable<TKey> keys() {
        List<TKey> keys = new ArrayList<>();
        Arrays.stream(this.chainedElements)
                .forEach(x -> x.forEach(kvp -> keys.add(kvp.getKey())));
        return keys;
    }

    public Iterable<TValue> values() {
        List<TValue> values = new ArrayList<>();
        List<List<KeyValue<TKey, TValue>>> existingValues = Arrays.stream(this.chainedElements).filter(Objects::nonNull)
                .collect(Collectors.toList());
        for (List<KeyValue<TKey, TValue>> list : existingValues) {
            for (KeyValue<TKey, TValue> kvp : list) {
                values.add(kvp.getValue());
            }
        }
        return values;
    }

    @Override
    public Iterator<KeyValue<TKey, TValue>> iterator() {
        List<KeyValue<TKey, TValue>> pairs = new LinkedList<>();
        List<List<KeyValue<TKey, TValue>>> existingValues =
                Arrays.stream(this.chainedElements)
                        .filter(Objects::nonNull).collect(Collectors.toList());
        for (List<KeyValue<TKey, TValue>> list : existingValues) {
            pairs.addAll(list);
        }
        return pairs.iterator();
    }

    private void growIfNecessary() {
        int fillFactor = (this.size + 1) / this.capacity;
        if(fillFactor > loadFactor) {
            grow();
        }
    }

    private void grow() {
        HashTable<TKey, TValue> newTable = new HashTable<>(this.capacity * 2);
        this.capacity *= 2;
        List<List<KeyValue<TKey, TValue>>> existingValues =
                Arrays.stream(this.chainedElements)
                        .filter(Objects::nonNull).collect(Collectors.toList());
        for (List<KeyValue<TKey, TValue>> element : existingValues) {
            for (KeyValue<TKey, TValue> kvp : element) {
                newTable.add(kvp.getKey(), kvp.getValue());
            }
        }
        this.chainedElements = newTable.chainedElements;
        this.setSize(newTable.size);
    }
}
