package movida.mackseverini;

import movida.mackseverini.IMap;
import movida.mackseverini.IAbrNode;

public interface IABR<T extends Comparable<T>> extends IMap<T>
{
  // change based of what you implement in ABR
  public IAbrNode<T> getRoot();

  // change based of what you implement in ABR
  public void setRoot(IAbrNode<T> root);

  public boolean update(T update, T keyToFind);
}
