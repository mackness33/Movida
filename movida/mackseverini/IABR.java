package movida.mackseverini;

import movida.mackseverini.IMap;
import movida.mackseverini.IAbrNode;

public interface IABR<T extends Comparable<T>> extends IMap<T>
{
  public boolean update(T update, T keyToFind);
}
