package movida.mackseverini;

import movida.mackseverini.IList;
import movida.mackseverini.List;
import movida.mackseverini.KeyList;
import movida.mackseverini.Node2;
import movida.mackseverini.INode2;
import movida.mackseverini.Array;
import movida.mackseverini.Hash2;


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

    System.out.println("LENGTH: " + array.length);
    // cycle the array
    for (int i = 1; i < array.length; i++, j = 0, over = false){
      System.out.println("i: " + i);
      // checks
      for(INode2<T> iter = copy.getHead(); iter != null && !over; iter = iter.getNext(), j++){
        if(min_max_compare(array.get(i), iter.getValue(), isMin)){
        // if(array.get(i).compareTo(iter.getValue()) <= 0){
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


  // sort of an list
  // public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> sort(IList<E> list, boolean isMin) {
  //   if(list.getSize() <= 1)
  //     return list;
  //
  //   IList<E> copy = (list instanceof IKeyList) ? new KeyList<E, T, K>(0, ((IKeyNode<E, T>)list.getHead()).getKey(), list.getHead().getValue()) : new List<E>(list.getHead().getValue());
  //
  //   // check the type of list
  //   // if (list instanceof KeyList){
  //   //   copy = new KeyList<E, T, K>();
  //   //   ((KeyList<E, T, K>)copy).addTail(((KeyNode<E, T>)list.getHead()).getKey(), list.getHead().getValue());
  //   // }
  //   // else
  //   //   copy = new List<E>(list.getHead().getValue());
  //
  //   int j = 0, i = 1;
  //   boolean over = false;
  //
  //   System.out.println("SIZE: " + list.getSize());
  //   // for each node of the input
  //   for(INode2<E> iterIN = list.getHead().getNext(); iterIN != null; iterIN = iterIN.getNext(), i++, j = 0, over = false){
  //     // System.out.println("i: " + i);
  //     // System.out.println("INPUT VALUE: " + iterIN.getValue());
  //     // for each node of the copy
  //     for(INode2<E> iterCopy = copy.getHead(); iterCopy != null && !over; iterCopy = iterCopy.getNext(), j++){
  //       // System.out.println("COPY VALUE: " + iterCopy.getValue());
  //       // System.out.println("RESULT: " + iterIN.getValue().compareTo(iterCopy.getValue()));
  //       // if(iterIN.getValue().compareTo(iterCopy.getValue()) >= 0){
  //       if(min_max_compare(iterIN.getValue(), iterCopy.getValue(), isMin)){
  //         // different type of insert based of the type of the list
  //         if (list instanceof KeyList)
  //             ((KeyList<E, T, K>)copy).addAt(((IKeyNode<E, T>)iterIN).getKey(), iterIN.getValue(), j);
  //         else
  //           copy.addAt(iterIN.getValue(), j);
  //         over = true;
  //       }
  //     }
  //
  //     // if not added at at the end of the list
  //     if (!over){
  //       if (list instanceof KeyList)
  //           ((KeyList<E, T, K>)copy).addAt(((IKeyNode<E, T>)iterIN).getKey(), iterIN.getValue(), i);
  //       else
  //         copy.addAt(iterIN.getValue(), i);
  //     }
  //   }
  //
  //   return copy;
  // }

  public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> sort(IList<E> list, boolean isMin) { return this.sort(list, isMin, false); }

  public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IKeyList<E, T, K> keySort(IKeyList<E, T, K> list, boolean isMin) { return (IKeyList<E, T, K>)this.sort(list, isMin, true); }

  // public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> keySort(IKeyList<E, T, K> list, boolean isMin) {
  //   if(list.getSize() <= 1)
  //     return list;
  //
  //   System.out.println("CLASS LIST: " + list.getClass());
  //
  //   IKeyList<E, T, K> copy = new KeyList<E, T, K>();
  //   int j = 0, i = 1;
  //   boolean over = false;
  //
  //   copy.addTail(((IKeyNode<E, T>)list.getHead()).getKey(), list.getHead().getValue());
  //
  //   System.out.println("SIZE: " + list.getSize());
  //   for(IKeyNode<E, T> iterIN = (IKeyNode<E, T>)list.getHead().getNext(); iterIN != null; iterIN = (IKeyNode<E, T>)iterIN.getNext(), i++, j = 0, over = false){
  //     // System.out.println("i: " + i);
  //     // System.out.println("i: " + copy.getHead());
  //     for(IKeyNode<E, T> iterCopy = (IKeyNode<E, T>)copy.getHead(); iterCopy != null && !over; iterCopy = (IKeyNode<E, T>)iterCopy.getNext(), j++){
  //       // System.out.println("blip: " + j);
  //       // System.out.println("j: " + iterCopy.getNext());
  //       // System.out.println("ITERCOPY: " + iterCopy);
  //       if(min_max_compare(iterIN.getKey(), iterCopy.getKey(), isMin)){
  //         // System.out.println("SCARED");
  //         copy.addAt(iterIN.getKey(), iterIN.getValue(), j);
  //         over = true;
  //       }
  //       // System.out.println("shish: " + i);
  //     }
  //
  //     // System.out.println("qhat: " + iterIN);
  //     if (!over)
  //       copy.addAt(iterIN.getKey(), iterIN.getValue(), i);
  //   }
  //
  //   return copy;
  // }

  protected <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> sort(IList<E> list, boolean isMin, boolean isKey) {
    if(list.getSize() <= 1)
      return list;

    System.out.println("CLASS LIST: " + list.getClass());

    // check the type of list
    IList<E> copy = (list instanceof IKeyList) ? new KeyList<E, T, K>(null, ((IKeyNode<E, T>)list.getHead()).getKey(), list.getHead().getValue()) : new List<E>(list.getHead().getValue());
    int j = 0, i = 1;
    boolean over = false;

    System.out.println("SIZE: " + list.getSize());
    for(INode2<E> iterIN = list.getHead().getNext(); iterIN != null; iterIN = iterIN.getNext(), i++, j = 0, over = false){
      // System.out.println("i: " + i);
      // System.out.println("i: " + copy.getHead());
      for(INode2<E> iterCopy = copy.getHead(); iterCopy != null && !over; iterCopy = iterCopy.getNext(), j++){
        // System.out.println("blip: " + j);
        // System.out.println("j: " + iterCopy.getNext());
        // System.out.println("ITERCOPY: " + iterCopy);
        if((isKey) ? min_max_compare(((IKeyNode)iterIN).getKey(), ((IKeyNode)iterCopy).getKey(), isMin) : min_max_compare(iterIN.getValue(), iterCopy.getValue(), isMin)){
        // different type of insert based of the type of the list
        // if(min_max_compare(iterIN.getValue(), iterCopy.getValue(), isMin)){
          over = this.addAt(copy, iterIN, j);
        }
        // System.out.println("shish: " + i);
      }

      // System.out.println("qhat: " + iterIN);
      if (!over)
        this.addAt(copy, iterIN, i);
    }

    return copy;
  }

  protected <T extends Comparable<T>> boolean min_max_compare(T obj, T obj2, boolean isMin){ return (isMin) ? (obj.compareTo(obj2) < 0) : (obj.compareTo(obj2) > 0); }

  protected <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> void addTail(IList<E> list, INode2<E> nodeToAdd){
    if (list instanceof IKeyList)
      ((IKeyList<E, T, K>)list).addTail(((IKeyNode<E, T>)nodeToAdd).getKey(), nodeToAdd.getValue());

    list.addTail(nodeToAdd.getValue());
  }

  protected <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> boolean addAt(IList<E> list, INode2<E> nodeToAdd, int pos){
    if (list instanceof IKeyList)
      return ((IKeyList<E, T, K>)list).addAt(((IKeyNode<E, T>)nodeToAdd).getKey(), nodeToAdd.getValue(), pos);

    return list.addAt(nodeToAdd.getValue(), pos);
  }
}
