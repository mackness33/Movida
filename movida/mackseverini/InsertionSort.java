package movida.mackseverini;

import movida.mackseverini.List;
import movida.mackseverini.KeyList;
import movida.mackseverini.INode2;
import movida.mackseverini.Array;


// Class to sort a list or array by the algorithm Insertion Sort
public class InsertionSort implements IAlg{
  public InsertionSort(){}

  @Override
  public final movida.commons.SortingAlgorithm getType(){ return movida.commons.SortingAlgorithm.InsertionSort; }

  // sort of an array
  public <T extends Comparable<T>> Array<T> sort(Array<T> array, boolean isMin) {
    if(array.length <= 1)
      return array;

    IList<T> copy = new List<T>(array.get(0));
    int j = 0;
    boolean over = false;

    // cycle the array
    for (int i = 1; i < array.length; i++, j = 0, over = false){
      // check
      for(INode2<T> iter = copy.getHead(); iter != null && !over; iter = iter.getNext(), j++){
        // compare the two elements
        if(min_max_compare(array.get(i), iter.getValue(), isMin)){
          copy.addAt(array.get(i), j);
          over = true;
        }
      }

      // if not added at at the end of the list
      if (!over)
        copy.addAt(array.get(i), i);
    }

    // return the list coverted to array
    return copy.toArray();
  }

  // sort of a generic list
  public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> sort(IList<E> list, boolean isMin) { return this.sort(list, isMin, false); }

  // sort of a generic keyList by key
  public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IKeyList<E, T, K> keySort(IKeyList<E, T, K> list, boolean isMin) { return (IKeyList<E, T, K>)this.sort(list, isMin, true); }

  // sort of a generic List. Can choose to sort by key or by value
  protected <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> sort(IList<E> list, boolean isMin, boolean isKey) {
    if(list.getSize() <= 1)
      return list;

    // check the type of list
    IList<E> copy = (list instanceof IKeyList) ? new KeyList<E, T, K>(null, ((IKeyNode<E, T>)list.getHead()).getKey(), list.getHead().getValue()) : new List<E>(list.getHead().getValue());
    int j = 0, i = 1;
    boolean over = false;

    for(INode2<E> iterIN = list.getHead().getNext(); iterIN != null; iterIN = iterIN.getNext(), i++, j = 0, over = false){
      // iter all the nodes in the copy list
      for(INode2<E> iterCopy = copy.getHead(); iterCopy != null && !over; iterCopy = iterCopy.getNext(), j++)
        // choose to compare the too node by key or by value if by decrescenting order or incrementing order
        if((isKey) ? min_max_compare(((IKeyNode)iterIN).getKey(), ((IKeyNode)iterCopy).getKey(), isMin) : min_max_compare(iterIN.getValue(), iterCopy.getValue(), isMin))
          over = this.addAt(copy, iterIN, j);

      // if the node to put in is bigger than all the nodes actually in the copy list, than add it at the end
      if (!over)
        this.addAt(copy, iterIN, i);
    }

    return copy;
  }

  // compare the two elements based on the order in input (isMin)
  protected <T extends Comparable<T>> boolean min_max_compare(T obj, T obj2, boolean isMin){ return (isMin) ? (obj.compareTo(obj2) < 0) : (obj.compareTo(obj2) > 0); }

  // addAt indipendent from the type of list in input
  protected <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> boolean addAt(IList<E> list, INode2<E> nodeToAdd, int pos){
    if (list instanceof IKeyList)
      return ((IKeyList<E, T, K>)list).addAt(((IKeyNode<E, T>)nodeToAdd).getKey(), nodeToAdd.getValue(), pos);

    return list.addAt(nodeToAdd.getValue(), pos);
  }
}
