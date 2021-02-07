package movida.mackseverini;

// interface of basic operation of an hash
public interface IHash<T> extends movida.mackseverini.IMap<T> {
  public int getSize();

  public int getLength();

  public void sort(IAlg algorithm, boolean decrescent);

  public void reset ();

  public Array<T> toArray();
}
