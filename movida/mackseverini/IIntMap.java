package movida.mackseverini;

public interface IIntMap extends IMap<Integer>{
  @Override
  public boolean insert(Integer obj);

  @Override
  public boolean delete(Integer obj);

  @Override
  public Integer search(Integer obj);

  @Override
  public boolean update(Integer obj);
}
