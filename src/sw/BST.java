/*
 * An implementation of binary search trees closely based on, and sometimes
 * copied from Sedgewick & Wayne algs4.
 *
 * CSCI 1102 Computer Science 2
 * */

import java.util.NoSuchElementException;

public class BST<K extends Comparable<K>, V> implements OrderedMap<K, V> {

  private Node root;

  private class Node {
    private K key;
    private V value;
    private Node left;
    private Node right;
    private int size;

    public Node(K key, V value, Node left, Node right) {
      this.key = key;
      this.value = value;
      this.left = left;
      this.right = right;
    }

    public Node(K key, V value, int size) {
      this.key = key;
      this.value = value;
      this.size = size;
      this.left = null;
      this.right = null;
    }
  }

  public int size() {
    return size(root);
  }

  private int size(Node a) {
    if (a == null) return 0;
    return a.size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public V get(K key) {
    if (key == null)
      throw new IllegalArgumentException("get: null key");
    return get(root, key);
  }

  private V get(Node a, K key) {
    if (a == null)
      throw new NoSuchElementException("get: key not found");
    int cmp = key.compareTo(a.key);
    if (cmp == 0) return a.value;
    if (cmp < 0) return get(a.left, key);
    return get(a.right, key);
  }

  public boolean contains(K key) {
    if (key == null)
      throw new IllegalArgumentException("contains: null key");
    return get(key) != null;
  }

  public void put(K key, V value) {
    if (key == null)
      throw new IllegalArgumentException("put: null key");
    if (value == null) {
      delete(key);
      return;
    }
    root = put(root, key, value);
  }

  private Node put(Node x, K key, V value) {
    if (x == null) return new Node(key, value, 1);
    int cmp = key.compareTo(x.key);
    if (cmp < 0) x.left = put(x.left, key, value);
    else if (cmp > 0) x.right = put(x.right, key, value);
    else x.value = value;
    x.size = 1 + size(x.left) + size(x.right);
    return x;
  }

  public K max() {
    if (isEmpty())
      throw new NoSuchElementException("max: empty map");
    return max(root).key;
  }

  private Node max(Node a) {
    if (a.right == null)
      return a;
    else
      return max(a.right);
  }

  public K min() {
    if (isEmpty())
      throw new NoSuchElementException("min: empty map");
    return min(root).key;
  }

  private Node min(Node a) {
    if (a.left == null)
      return a;
    else
      return min(a.left);
  }
  
  public K floor(K key) {
    if (key == null)
      throw new IllegalArgumentException("floor: null key");
    if (isEmpty())
      throw new NoSuchElementException("floor: empty map");
    Node x = floor(root, key);
    if (x == null)
      throw new NoSuchElementException("floor: key too small");
    else return x.key;
  }

  private Node floor(Node x, K key) {
    if (x == null) return null;
    int cmp = key.compareTo(x.key);
    if (cmp == 0) return x;                 // x.key same as key
    if (cmp < 0) return floor(x.left, key);
    Node t = floor(x.right, key);
    if (t != null) return t;
    else return x;                          // x.key small than key
  }

  public K ceiling(K key) {
    if (key == null)
      throw new IllegalArgumentException("ceiling: null key");
    if (isEmpty())
      throw new NoSuchElementException("ceiling: empty symbol table");
    Node x = ceiling(root, key);
    if (x == null)
      throw new NoSuchElementException("ceiling: key is too large");
    else return x.key;
  }

  private Node ceiling(Node x, K key) {
    if (x == null) return null;
    int cmp = key.compareTo(x.key);
    if (cmp == 0) return x;
    if (cmp < 0) {
      Node t = ceiling(x.left, key);
      if (t != null) return t;
      else return x;
    }
    return ceiling(x.right, key);
  }

  public void deleteMin() {
    if (isEmpty())
      throw new NoSuchElementException("deleteMin: underflow");
    root = deleteMin(root);
  }

  private Node deleteMin(Node x) {
    if (x.left == null) return x.right;
    x.left = deleteMin(x.left);
    x.size = size(x.left) + size(x.right) + 1;
    return x;
  }

  public void deleteMax() {
    if (isEmpty())
      throw new NoSuchElementException("deleteMax: underflow");
    root = deleteMax(root);
  }

  private Node deleteMax(Node x) {
    if (x.right == null)
      return x.left;
    x.right = deleteMax(x.right);
    x.size = size(x.left) + size(x.right) + 1;
    return x;
  }

  public void delete(K key) {
    if (key == null)
      throw new IllegalArgumentException("delete: null key");
    root = delete(root, key);
  }

  private Node delete(Node x, K key) {
    if (x == null) return null;

    int cmp = key.compareTo(x.key);
    if (cmp < 0) x.left = delete(x.left, key);
    else if (cmp > 0) x.right = delete(x.right, key);
    else {
      if (x.right == null) return x.left;
      if (x.left == null) return x.right;
      Node t = x;
      x = min(t.right);
      x.right = deleteMin(t.right);
      x.left = t.left;
    }
    x.size = size(x.left) + size(x.right) + 1;
    return x;
  }

  @Override
  public String toString() {
    return toString(root);
  }

  private String toString(Node node) {
    if (node == null)
      throw new NoSuchElementException("toString of empty BST");
    String kvString = toStringKV(node);
    if (node.left == null && node.right == null)
      return kvString;
    else if (node.left == null)
      return String.format("%s(%s)", kvString, toString(node.right));
    else if (node.right == null)
      return String.format("%s(%s)", kvString, toString(node.left));
    else {
      String left = toString(node.left),
              right = toString(node.right);
      return String.format("%s(%s, %s)", kvString, left, right);
    }
  }

  private String toStringKV(Node node) {
    return String.format("%s:%s", node.key.toString(), node.value.toString());
  }

  public static void main(String[] args) {

    OrderedMap<String, Integer> map = new BST<String, Integer>();
    map.put("Mary", 20);
    map.put("Alice", 30);
    map.put("Zena", 40);
    System.out.format("map is %s%n", map.toString());
  }
}
