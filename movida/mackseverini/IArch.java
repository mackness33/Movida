package movida.mackseverini;

public interface IArch<E extends Comparable<E>, K extends Comparable<K>>{
  public K getWeight ();
  public E getFirstVertex ();
  public E getSecondVertex ();

  public void setWeight (K w);
  public void setFirstVertex (E v1);
  public void setSecondVertex (E v2);

  public void reset ();

  public void print();
}
