package movida.mackseverini;

public interface IAbrNode<E extends Comparable<E>, T extends Comparable<T>>
{
  public void setKey(E key);

  public void setValue(T value);

  public E getKey();

  public T getValue();

  public void setLeftChild(IAbrNode<E, T> left);

  public IAbrNode<E, T> getLeftChild();

  public void setRightChild(IAbrNode<E, T> right);

  public IAbrNode<E, T> getRightChild();
}
