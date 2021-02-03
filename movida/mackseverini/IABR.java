package movida.mackseverini;

import movida.mackseverini.IMap;
import movida.mackseverini.Array;
import movida.mackseverini.IAbrNode;

public interface IABR<E extends Comparable<E>, T extends Comparable<T>> extends IKeyMap<E, T>
{
  public IAbrNode<E, T> getRoot();
  public Integer getSize();
  public Array<E> getAll(T valueToFind);
  // public Array<E> getNumMax(Integer num, Stack movies);
  public boolean update(E keyToUpdate, T valueToUpdate, T valueToFind);
  public void setRoot(IAbrNode<E,T> root);
}
