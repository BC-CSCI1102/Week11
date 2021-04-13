/*
 * Part of an implementation of a Mutable Binary Search Tree ADT.
 * + The API is in OrderedMap.java
 * + This file implements non-empty nodes (interior or leaf)
 * + The file Empty.java empty nodes
 *
 * CSCI 1102 Computer Science 2
 */
public class Node<K extends Comparable<K>, V> implements OrderedMap<K, V> {

  private K key;
  private V value;
  private int size;
  private OrderedMap<K, V> left, right;

  public Node(K key, V value) {
    this.key = key;
    this.value = value;
    this.left = new Empty<K, V>();
    this.right = this.left;
    this.size = 1;
  }

  public Node(K key, V value, int size, OrderedMap<K, V> left, OrderedMap<K, V> right) {
    this.key = key;
    this.value = value;
    this.size = size;
    this.left = left;
    this.right = right;
  }

  public int size() {
    return this.size;
  }

  private int size(OrderedMap<K, V> map) {
    return map.size();
  }

  public boolean isEmpty() {
    return false;
  }

  private String kvToString() {
    return String.format("%s:%s", key.toString(), value.toString());
  }

  @Override
  public String toString() {
    String kvs = kvToString();
    if (left.isEmpty() && right.isEmpty())
      return kvs;
    else if (left.isEmpty())
      return String.format("%s(--, %s)", kvs, right.toString());
    else if (right.isEmpty())
      return String.format("%s(%s, --)", kvs, left.toString());
    else
      return String.format("%s(%s, %s)", kvs, left.toString(), right.toString());
  }

  public V get(K key) {
    if (key == null)
      throw new IllegalArgumentException("get: null key");
    int cmp = key.compareTo(this.key);
    if (cmp == 0) return this.value;
    if (cmp < 0) return this.left.get(key);
    else
      return this.right.get(key);
  }

  public boolean contains(K key) {
    if (key == null)
      throw new IllegalArgumentException("contains: null key");
    return this.get(key) != null;
  }

  public K min() {
    if (this.left.isEmpty())
      return this.key;
    else
      return this.left.min();
  }

  public K max() {
    if (this.right.isEmpty())
      return this.key;
    else
      return this.right.max();
  }

  public K floor(K key) { // Write this
    return null;
  }

  public K ceiling(K key) { // Write this
    return null;
  }

  public OrderedMap<K, V> put(K key, V value) {
    if (key == null)
      throw new IllegalArgumentException("put: null key");
    int cmp = key.compareTo(this.key);
    if (cmp == 0) this.value = value;
    else if (cmp < 0) this.left = this.left.put(key, value);
    else
      this.right = this.right.put(key, value);
    this.size = 1 + this.left.size() + this.right.size();
    return this;
  }

  public OrderedMap<K, V> deleteMin() {
    if (this.left.isEmpty())
      return this.right;
    else {
      this.left = this.left.deleteMin();
      return this;
    }
  }

  public OrderedMap<K, V> deleteMax() {
    if (this.right.isEmpty())
      return this.left;
    else {
      this.right = this.right.deleteMax();
      return this;
    }
  }

  public OrderedMap<K, V> delete(K key) {
    if (key == null)
      throw new IllegalArgumentException("delete: null key");
    int cmp = key.compareTo(this.key);
    if (cmp < 0) this.left = this.left.delete(key);
    else if (cmp > 0) this.right = this.right.delete(key);
    else { // key same as this.key
      if (this.right.isEmpty()) return this.left;
      if (this.left.isEmpty()) return this.right;
      //
      // deleting full interior node  |
      //                             Key
      //                             /  \
      K k = this.right.min();
      V v = this.right.get(k);
      OrderedMap<K, V> newRight = this.right.deleteMin();
      return new Node<K, V>(k, v, this.size() - 1, this.left, newRight);
    }
    this.size = size(this.left) + size(this.right) + 1;
    return this;
  }

  public static void main(String[] args) {
    OrderedMap<String, Integer> map = new Node<String, Integer>("Mary", 10);
    map.put("Alice", 30);     //     M           S
    map.put("Vera", 60);      //    / \         / \
    map.put("Susan", 20);     //   A   V   =>  A   V
    map.put("Zena", 40);      //      / \         / \
    map.put("Ursula", 50);    //     S   Z       U   Z
    map = map.delete("Mary"); //      \
    //                                 U
    System.out.format("map is %s, size is %d.%n", map.toString(), map.size());
    map = map.delete("Zena");
    System.out.format("map is %s, size is %d.%n", map.toString(), map.size());
  }
}
