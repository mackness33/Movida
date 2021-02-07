package movida.mackseverini;

import movida.mackseverini.Array;

// Interface used to describe the sort that must be implemented in a sorting algorithm
public interface IAlg extends movida.mackseverini.IConfig<movida.commons.SortingAlgorithm>{
  public <T extends Comparable<T>> Array<T> sort(Array<T> array, boolean isMin);    // sort of an Array
  public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> sort(IList<E> list, boolean isMin); // sort of a List
  public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> keySort(IKeyList<E, T, K> list, boolean isMin); // sort of a list by key
}
