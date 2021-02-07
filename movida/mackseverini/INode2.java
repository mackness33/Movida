package movida.mackseverini;

// interface that describe the basic operation of a Node
public interface INode2<E extends Comparable<E>> {
  public INode2<E> getNext ();
  public E getValue ();

  public void setNext (INode2<E> v);
  public void setValue (E v);
}
