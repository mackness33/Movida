package movida.mackseverini;

public interface IMap<T> {
  public movida.commons.MapImplementation type = null;
  
  public boolean insert(T obj);

  public boolean delete(T obj);

  public boolean search(T obj);
}
