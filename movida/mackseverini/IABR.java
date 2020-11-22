package movida.mackseverini;

import movida.mackseverini.IMap;
import movida.mackseverini.IAbrNode;

public interface IABR<T extends Comparable<T>> extends IMap<T>, IAbrNode<T>
{
  public IAbrNode<T> getRoot();

  public void setRoot(IAbrNode<T> root);

  public boolean update(T update, T keyToFind);
}
