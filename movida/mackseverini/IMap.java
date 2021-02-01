package movida.mackseverini;

public interface IMap<T> extends movida.mackseverini.IConfig<movida.commons.MapImplementation>{
  public boolean insert(T obj);

  public boolean delete(T obj);

  public boolean search(T obj);
}
