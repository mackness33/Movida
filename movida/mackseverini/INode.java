package movida.mackseverini;

public interface INode<T> {
  public Integer getKey ();
  public T getValue ();

  // NECESSERARY?
  public void setKey (Integer k);
  public void setValue (T v);
}
