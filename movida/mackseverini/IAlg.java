package movida.mackseverini;

import movida.mackseverini.Array;

public interface IAlg{
  public <T extends Comparable<T>> Array<T> sort(Array<T> array);
  public <T extends Comparable<T>> IList<T> sort(IList<T> list);
  public <K extends Comparable<K>> IList<K> keySort(IKeyList<K> list);
}
