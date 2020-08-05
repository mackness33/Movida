package movida.mackseverini;

import movida.mackseverini.IList;
import movida.mackseverini.List;
import movida.mackseverini.KeyList;
import movida.mackseverini.Node2;
import movida.mackseverini.INode2;
import movida.mackseverini.Array;
import movida.mackseverini.Hash2;

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

  public <T extends Comparable<T>> IList<T> sort(IList<T> list) {
    if(list.getSize() <= 1)
      return list;

    System.out.println("CLASS LIST: " + list.getClass());
    IList<T> copy;
    if (list instanceof KeyList){
      // return this.keySort(list);
      System.out.println("HERE i AM: ");
      copy = new KeyList<T>();
      ((KeyList<T>)copy).addTail(((KeyNode<T>)list.getHead()).getKey(), list.getHead().getValue());
    }
    else
      copy = new List<T>(list.getHead().getValue());

    int j = 0, i = 1;
    boolean over = false;

    System.out.println("SIZE: " + list.getSize());
    for(INode2<T> iterIN = list.getHead().getNext(); iterIN != null; iterIN = iterIN.getNext(), i++, j = 0, over = false){
      // System.out.println("i: " + i);
      // System.out.println("INPUT VALUE: " + iterIN.getValue());
      for(INode2<T> iterCopy = copy.getHead(); iterCopy != null && !over; iterCopy = iterCopy.getNext(), j++){
        // System.out.println("COPY VALUE: " + iterCopy.getValue());
        // System.out.println("RESULT: " + iterIN.getValue().compareTo(iterCopy.getValue()));
        if(iterIN.getValue().compareTo(iterCopy.getValue()) <= 0){
          if (list instanceof KeyList)
              ((KeyList<T>)copy).addBlue(((IKeyNode<T>)iterIN).getKey(), iterIN.getValue(), j);
          else
            copy.addAt(iterIN.getValue(), i);
          over = true;
        }
      }

      if (!over){
        if (list instanceof KeyList)
            ((KeyList<T>)copy).addBlue(((IKeyNode<T>)iterIN).getKey(), iterIN.getValue(), i);
        else
          copy.addAt(iterIN.getValue(), i);
      }
    }

    return copy;
  }

  private <K extends Comparable<K>> IList<K> keySort(IList<K> list) {
    if(list.getSize() <= 1)
      return list;

    System.out.println("CLASS LIST: " + list.getClass());

    KeyList<K> copy = new KeyList<K>();
    int j = 0, i = 1;
    boolean over = false;

    copy.addTail(((KeyNode<IList<K>>)list.getHead()).getKey(), list.getHead().getValue());

    System.out.println("SIZE: " + list.getSize());
    for(KeyNode<K> iterIN = (KeyNode<K>)list.getHead().getNext(); iterIN != null; iterIN = (KeyNode<K>)iterIN.getNext(), i++, j = 0, over = false){
      System.out.println("i: " + i);
      System.out.println("i: " + copy.getHead());
      for(KeyNode<K> iterCopy = (KeyNode<K>)copy.getHead(); iterCopy != null && !over; iterCopy = (KeyNode<K>)iterCopy.getNext(), j++){
        System.out.println("blip: " + j);
        System.out.println("j: " + iterCopy.getNext());
        System.out.println("ITERCOPY: " + iterCopy);
        if(iterIN.getKey().compareTo(iterCopy.getKey()) >= 0){
          System.out.println("SCARED");
          copy.addBlue(iterIN.getKey(), iterIN.getValue(), j);
          over = true;
        }
        System.out.println("shish: " + i);
      }

      System.out.println("qhat: " + iterIN);
      if (!over)
        copy.addBlue(iterIN.getKey(), iterIN.getValue(), i);
    }

    return copy;
  }
}
