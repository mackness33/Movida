package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node;
import movida.mackseverini.Array;

// BUG: Comparable cannot be used.
// SOLUTION: Comparable cannot be used.
public class Hash<T extends Comparable<T>> extends ComparableStatic implements movida.mackseverini.IHash<T> {
  private Array<Node<T>> dom;

  // constructor resides
  @SuppressWarnings("unchecked")
  public Hash() {
    this.dom = new Array<Node<T>> (50);
    for (int i = 0; i < this.dom.length; i++)
      this.dom.set(i, new Node<T>(i, null));
  }


  protected Integer hash (Integer input){
    return Math.abs(input);
  }

  @Override
  public boolean insert(T obj){
    return true;
  }

  // @Override
  public  boolean insert(Integer key, T obj){
    Node<T> node = new Node<T>(this.hash(new Integer(key)), obj);

    System.out.println("KEY: " + node.getKey());
    if (dom.get(node.getKey()).getValue() != null){
      Node<T> head = dom.get(node.getKey());

      if (head.getNext() != null)
        System.out.println("Head: " + head);
        System.out.println("Next: " + head.getNext());

      // shift to tail
      for (; head.getNext() != null; head = head.getNext()){
        System.out.println("Head: " + head);
        System.out.println("Next: " + head.getNext());
      }

      head.setNext(node);
    }
    else
      dom.set(node.getKey(), node);

    return true;
  }

  @Override
  public boolean delete(T obj){
    return true;
  }

  public boolean delete(Integer k, T obj){
    Integer key = this.hash(k);

    if (this.dom.get(key).getValue() != null){
      Node<T> head = dom.get(key);

      // if it's the head of the list
      if (this.compare(head.getValue(), obj) == 0){
        dom.set(key, (head.getNext() == null) ? new Node<T>(k, null) : head.getNext());
        head = null;          // handle by the garbage collector
        return true;
      }

      for (Node<T> searcher = head.getNext(); head.getNext() != null; head = head.getNext(), searcher = searcher.getNext()){
        System.out.println("Head: " + head + " val: " + head.getValue());
        System.out.println("Searcher: " + searcher + " val: " + searcher.getValue());

        if (this.compare(searcher.getValue(), obj) == 0){
          head.setNext(searcher.getNext());
          searcher = null;
          return true;
        }
      }
    }

    return false;
  }

  @Override
  public T search(T obj){
    return null;
  }

  public T search(Integer k, T obj){
    Integer key = this.hash(k);

    if (this.dom.get(key).getValue() != null){
      for (Node<T> head = dom.get(key); head != null; head = head.getNext()){
        System.out.println("Head: " + head + " val: " + head.getValue());
        System.out.println("Next: " + head.getNext());

        if (this.compare(head.getValue(), obj) == 0){
          return head.getValue();
        }
      }
    }

    return null;
  }

  @Override
  public boolean update(T obj){
    return true;
  }

  public void print (){
    System.out.println("Length: " + this.dom.length);
    for (int i = 0; i < this.dom.length; i++){
      // System.out.println("i: " + i);
      // System.out.println("Node: " + this.dom[i]);
      // System.out.println("KEY => " + this.dom[i].getKey());
      // System.out.println("VALUE => " + this.dom[i].getValue() );
      this.dom.get(i).print();
    }
  }
}
