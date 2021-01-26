package movida.mackseverini;


import movida.mackseverini.KeyList;
import movida.mackseverini.IKeyList;

// FEATURE: can add a generics to let people choose with what type they want to save the vertex
public interface IVertex<E extends Comparable<E>, K extends Comparable<K>>{
  public IKeyList<Integer, K, Integer> getAdiacence ();
  public E getValue ();

  public void setValue (E v);

  public void addAdiacence (Integer v, K w);
  public boolean upsertAdiacence (Integer v, K w);
  public boolean delAdiacence (Integer v);

  public void print();
  public String toString();
  public int compareTo (IVertex<E, K> input);
}
