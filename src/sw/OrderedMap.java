public interface OrderedMap<Key extends Comparable<Key>, Value> {
  int size();

  boolean isEmpty();

  boolean contains(Key key);

  Key floor(Key key);

  Key ceiling(Key key);

  Key max();

  Key min();

  void put(Key key, Value value);

  void deleteMin();

  void deleteMax();

  void delete(Key key);

  @Override
  String toString();
}


