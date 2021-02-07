package movida.mackseverini;

// interface that describe the basic operation of an arch
public interface IArch<E extends Comparable<E>, K extends Comparable<K>> extends Comparable<IArch<E, K>>{
  public K getWeight ();
  public E getFirstVertex ();
  public E getSecondVertex ();

  public void setWeight (K w);
  public void setFirstVertex (E v1);
  public void setSecondVertex (E v2);

  public void reset ();   // reset the arch back to the original state

  public int compareTo (IArch<E, K> input);   // compare to an IArch in input

}
