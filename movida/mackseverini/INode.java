package movida.mackseverini;

public interface INode<E extends Comparable<E>> {
  public INode<E> getNext ();
  public Integer getKey ();
  public E getValue ();

  public void setNext (INode<E> v);
  public void setKey (Integer k);
  public void setValue (E v);
}
