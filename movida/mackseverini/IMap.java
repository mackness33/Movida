package movida.mackseverini;

// interface that describe the basic operation of a generic Map
public interface IMap<T>{
  public boolean insert(T obj);

  public boolean delete(T obj);

  public boolean search(T obj);
}
