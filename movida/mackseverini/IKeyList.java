package movida.mackseverini;

// interfacec that describe the basic operation of List with keys
public interface IKeyList<E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> extends IList<E>{
  public K getKey();  // get the key of the list

  public void setKey (K k); // set the key of the list

  public E getByKey (T k);    // get the element by the key in input

  public boolean delByKey (T k);    // delete an element by the key in input

  public T searchKey (E el);    // get the key by an element in input

  public boolean updElKey (E el, T k);  // update the key of an element

  public void addTail (T k, E el);

  public void addHead (T k, E el);

  public boolean addAt (T k, E el, Integer pos);

  public boolean swap (IKeyNode<E, K> first, IKeyNode<E, K> second);  // swap two nodes
}
