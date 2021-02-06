package movida.mackseverini;

import movida.mackseverini.INode;

public interface IKeyList<E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> extends IList<E>{
  public void print();

  public void printAll();

  public K getKey();

  public void setKey (K k);

  public E getByKey (T k);

  public boolean delByKey (T k);

  public T searchKey (E el);

  public boolean updElKey (E el, T k);

  public void addTail (T k, E el);

  public void addHead (T k, E el);

  public boolean addAt (T k, E el, Integer pos);

  public boolean swap (IKeyNode<E, K> first, IKeyNode<E, K> second);
}
