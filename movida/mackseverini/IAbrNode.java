package movida.mackseverini;

public interface IAbrNode<T extends Comparable<T>>
{
  public void setKey(T key);

  public T getKey();

  public void setLeftChild(IAbrNode<T> left);

  public IAbrNode<T> getLeftChild();

  public void setRightChild(IAbrNode<T> right);

  public IAbrNode<T> getRightChild();
}
