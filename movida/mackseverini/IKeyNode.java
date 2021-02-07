package movida.mackseverini;

// interface that describe the basic operation of Node with keys
public interface IKeyNode<E extends Comparable<E>, K extends Comparable<K>> extends INode2<E> {
  public K getKey();

  public void setKey (K k);
}
