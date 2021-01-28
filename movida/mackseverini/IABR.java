package movida.mackseverini;

import movida.mackseverini.IMap;
import movida.mackseverini.IAbrNode;

public interface IABR<E extends Comparable<E>, T extends Comparable<T>> extends IKeyMap<E, T>
{
  public boolean update(E keyToUpdate, T valueToUpdate, T valueToFind);
}
