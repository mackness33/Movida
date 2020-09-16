package movida.mackseverini;

public interface IHash<T> extends movida.mackseverini.IMap<T> {

  public void reset ();

  public Array<T> toArray();
}
