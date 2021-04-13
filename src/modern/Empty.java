/*
 * Part of an implementation of a Mutable Binary Search Tree ADT.
 * + The API is in OrderedMap.java
 * + This file Node.java implements non-empty nodes (interior or leaf)
 * + This file implements empty nodes
 *
 * CSCI 1102 Computer Science 2
 */

import java.util.NoSuchElementException;

public class Empty<K extends Comparable<K>, V> implements OrderedMap<K, V> {

  public int size() {
    return 0;
  }

  public boolean isEmpty() {
    return true;
  }

  public V get(K key) {
    throw new NoSuchElementException("get: key not found");
  }

  @Override
  public String toString() {
    throw new NoSuchElementException("toString: an empty map");
  }

  public boolean contains(K key) {
    return false;
  }

  public K floor(K key) {
    throw new NoSuchElementException("floor: empty map");
  }

  public K ceiling(K key) {
    throw new NoSuchElementException("ceiling: empty map");
  }

  public K min() {
    throw new NoSuchElementException("min: empty map");
  }

  public K max() {
    throw new NoSuchElementException("max: empty map");
  }

  public OrderedMap<K, V> put(K key, V value) {
    return new Node<K, V>(key, value, 1, this, this);
  }

  public OrderedMap<K, V> deleteMin() {
    throw new NoSuchElementException("deleteMin: empty map");
  }

  public OrderedMap<K, V> deleteMax() {
    throw new NoSuchElementException("deleteMax: empty map");
  }

  public OrderedMap<K, V> delete(K key) {
    throw new NoSuchElementException("delete: key not found");
  }
}
