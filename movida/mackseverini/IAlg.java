package movida.mackseverini;

import movida.mackseverini.Array;

public interface IAlg extends movida.mackseverini.IConfig<movida.commons.SortingAlgorithm>{
  public <T extends Comparable<T>> Array<T> sort(Array<T> array, boolean isMin);
  public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> sort(IList<E> list, boolean isMin);
  public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> keySort(IKeyList<E, T, K> list, boolean isMin);
}
