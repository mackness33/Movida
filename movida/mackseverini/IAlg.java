package movida.mackseverini;

import movida.mackseverini.Array;

public interface IAlg{
  public <T extends Comparable<T>> Array<T> sort(Array<T> array);
}
