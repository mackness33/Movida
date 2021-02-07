package movida.mackseverini;

import movida.mackseverini.IKeyList;

// interface that describe the basic operation of a Vertex
public interface IVertex<E extends Comparable<E>, K extends Comparable<K>> extends Comparable<IVertex<E,K>>{
  public IKeyList<Integer, K, Integer> getAdiacence ();
  public E getValue ();

  public void setValue (E v);

  public void addAdiacence (Integer v, K w);    // add adiacence
  public boolean upsertAdiacence (Integer v, K w);  // upsert adiacence
  public boolean updAdiacence (Integer v, K w);   // update adiacence
  public boolean delAdiacence (Integer v);  // delete adiacence

  public String toString();
  public int compareTo (IVertex<E, K> input);
}
