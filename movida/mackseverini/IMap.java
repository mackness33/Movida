package movida.mackseverini;

public interface IMap<T> {

  public boolean insert(T obj);

  public boolean delete(T obj);

  public T search(T obj);

  public boolean update(T obj);
}
