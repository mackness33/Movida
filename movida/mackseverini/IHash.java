package movida.mackseverini;

public interface IHash<T> extends movida.mackseverini.IMap<T> {
  public final movida.commons.MapImplementation type = movida.commons.MapImplementation.HashConcatenamento;

  public void reset ();

  public Array<T> toArray();
}
