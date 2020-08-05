package movida.mackseverini;

public interface IKeyNode<E extends Comparable<E>> extends INode2<E> {
  public Integer getKey();

  public void setKey (Integer k);
}
