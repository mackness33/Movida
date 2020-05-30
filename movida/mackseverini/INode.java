package movida.mackseverini;

public interface INode<T> {
  public int getKey ();
  public T getValue ();
  public INode<T> getNext ();

  // NECESSERARY?
  public void setKey (int k);
  public void setValue (T v);
  // public void setNext (INode<T> n);
}
