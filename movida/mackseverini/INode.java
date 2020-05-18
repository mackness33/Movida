package movida.mackseverini;

public interface INode<T> {
  public int getKey ();
  public T getValue ();

  // NECESSERARY?
  public void setKey (int k);
  public void setValue (T v);
}
