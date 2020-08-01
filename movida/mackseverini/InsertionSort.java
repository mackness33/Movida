package movida.mackseverini;

import movida.mackseverini.IList;
import movida.mackseverini.List;
import movida.mackseverini.Node2;
import movida.mackseverini.INode2;
import movida.mackseverini.Array;

public class InsertionSort implements IAlg{
  public InsertionSort(){}
  public <T extends Comparable<T>> Array<T> sort(Array<T> array) {
    if(array.length <= 1)
      return array;

    IList<T> copy = new List<T>(array.get(0));
    int j = 0;
    boolean over = false;

    System.out.println("LENGTH: " + array.length);
    for (int i = 1; i < array.length; i++, j = 0, over = false){
      System.out.println("i: " + i);
      for(INode2<T> iter = copy.getHead(); iter != null && !over; iter = iter.getNext(), j++){
        if(array.get(i).compareTo(iter.getValue()) <= 0){
          copy.addAt(array.get(i), j);
          over = true;
        }
      }

      if (!over)
        copy.addAt(array.get(i), i);

    }

    return copy.toArray();
  }
}
