/*
 * Part of an implementation of a Mutable Binary Search Tree ADT.
 * + This file contains the API.
 * + The file Node.java implements non-empty nodes (interior or leaf)
 * + The file Empty.java empty nodes
 *
 * CSCI 1102 Computer Science 2
 */
public interface OrderedMap<Key extends Comparable<Key>, Value> {
  boolean isEmpty();

  int size();

  Value get(Key key);

  @Override
  String toString();

  boolean contains(Key key);

  Key ceiling(Key key);

  Key floor(Key key);

  Key max();

  Key min();

  OrderedMap<Key, Value> put(Key key, Value value);

  OrderedMap<Key, Value> deleteMin();

  OrderedMap<Key, Value> deleteMax();

  OrderedMap<Key, Value> delete(Key key);
}


