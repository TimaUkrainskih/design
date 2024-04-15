package ru.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int index = indexFor(hash(Objects.hashCode(key)));
        if (count >= capacity * LOAD_FACTOR) {
            expand();
            index = indexFor(hash(Objects.hashCode(key)));
        }
        if (table[index] != null) {
            return false;
        }
        table[index] = new MapEntry<>(key, value);
        modCount++;
        count++;
        return true;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                int newIndex = indexFor(hash(Objects.hashCode(entry.key)));
                newTable[newIndex] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(Objects.hashCode(key)));
        MapEntry<K, V> entry = table[index];
        if (entry == null || !Objects.equals(entry.key, key)) {
            return null;
        }
        return entry.value;
    }

    @Override
    public boolean remove(K key) {
        MapEntry<K, V> deleteEntry = table[indexFor(hash(Objects.hashCode(key)))];
        if (deleteEntry != null) {
            table[indexFor(hash(Objects.hashCode(key)))] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int countElements = 0;

            int currentIndex = 0;

            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return countElements < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[currentIndex] == null) {
                    currentIndex++;
                }
                countElements++;
                return table[currentIndex++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;

        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}