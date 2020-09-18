package movida.mackseverini;

import movida.mackseverini.Array;

public interface IAlg{
  public movida.commons.SortingAlgorithm type = null;
  public <T extends Comparable<T>> Array<T> sort(Array<T> array);
  public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> sort(IList<E> list);
  public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> keySort(IKeyList<E, T, K> list);
}
