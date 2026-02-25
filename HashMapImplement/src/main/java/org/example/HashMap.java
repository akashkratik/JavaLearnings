package org.example;

import java.util.*;

public class HashMap<K, V> {
    class Node {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int bucketSize;
    private int size;
    private LinkedList<Node>[] buckets;

    public HashMap() {
        bucketSize = 2;
        size = 0;
        buckets = new LinkedList[bucketSize];

        for(int i = 0; i < bucketSize; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private void rehash() {
        System.out.println("rehasing");
        System.out.println(size);
        for(LinkedList<Node> bucket: buckets) {
            for(Node node : bucket) {
                System.out.println(node.key + " : " +node.value);
            }
        }
        LinkedList<Node> oldBucket[] = buckets;
        bucketSize = bucketSize * 2;
        buckets = new LinkedList[bucketSize];
        for(int i = 0; i < bucketSize; i++) {
            buckets[i] = new LinkedList<>();
        }
        for(LinkedList<Node> segment: oldBucket) {
            for(Node node: segment) {
                putForRehash(node.key, node.value);
            }
        }

    }

    private int hashFunction(K key) {
        int bucketIndex = key.hashCode();
        //return (bucketIndex & 0x7fffffff) % bucketSize;
        return Math.abs(bucketIndex) % bucketSize;
    }

    public int searchInLL(K key, int bucketIndex) {
        LinkedList<Node> linkedList = buckets[bucketIndex];
        for(int i = 0; i < linkedList.size(); i++) {
            if(linkedList.get(i).key.equals(key)) {
                return i;
            }
        }
        return -1;
    }

    private void putForRehash(K key, V value) {
        int bucketIndex = hashFunction(key);
        buckets[bucketIndex].add(new Node(key, value));
    }

    public void put(K key, V value) {
        int bucketIndex = hashFunction(key);
        int dataIndex = searchInLL(key, bucketIndex);

        if(dataIndex == -1) {
            buckets[bucketIndex].add(new Node(key, value));
            size += 1;
        } else {
            Node node = buckets[bucketIndex].get(dataIndex);
            node.value = value;
        }

        //lambda = size/noOfbuckets
        // lambda <= K (k is constant)
        double lambda = (double) size/bucketSize;
        if(lambda > 2.0) {
            rehash();
        }
    }

    public V get(K key) {
        int bucketIndex = hashFunction(key);
        int dataIndex = searchInLL(key, bucketIndex);
        if(dataIndex == -1) {
            return null;
        } else {
            Node node = buckets[bucketIndex].get(dataIndex);
            return node.value;
        }
    }

    public boolean containsKey(K key) {
        int bucketIndex = hashFunction(key);
        int dataIndex = searchInLL(key, bucketIndex);
        return dataIndex != -1;
    }

    public V remove(K key) {
        int bucketIndex = hashFunction(key);
        int dataIndex = searchInLL(key, bucketIndex);
        if(dataIndex == -1) {
            return null;
        } else {
            Node node = buckets[bucketIndex].remove(dataIndex);
            size -= 1;
            return node.value;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }



}
