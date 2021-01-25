package movida.mackseverini;

public interface IKeyMap<E, T> extends IMap<T> {
  public boolean insert(E key, T value);

  public boolean deleteByKey(E keyToDelete);

  public boolean searchByKey(E keyToFind);
}
