package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node;
import movida.mackseverini.Array;

public class IntHash implements movida.mackseverini.IHash<Integer> {
  private Array<Node<Integer>> dom;

  // constructor resides
  @SuppressWarnings("unchecked")
  public IntHash() {
    this.dom = new Array<Node<Integer>> (50);
    this.init_app();
  }

  public void init_app() {
    for (int i = 0; i < this.dom.length; i++)
      this.dom.set(i, new Node<Integer>(i, -1));
  }

  protected Integer hash (Integer input){
    return Math.abs(input);
  }

  @Override
  public boolean insert(Integer obj){
    return true;
  }

  // @Override
  public boolean insert(Integer key, Integer obj){
    Node<Integer> node = new Node<Integer>(this.hash(new Integer(key)), obj);

    System.out.println("KEY: " + node.getKey());
    if (dom.get(node.getKey()).getValue() != -1){
      Node<Integer> head = dom.get(node.getKey());

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
  public boolean delete(Integer obj){
    return true;
  }

  public boolean delete(Integer k, Integer obj){
    Integer key = this.hash(k);

    if (this.dom.get(key).getValue() != -1){
      Node<Integer> head = dom.get(key);

      // if it's the head of the list
      if (head.getValue() == obj){
        dom.set(key, (head.getNext() == null) ? new Node<Integer>(k, -1) : head.getNext());
        head = null;          // handle by the garbage collector
        return true;
      }

      for (Node<Integer> searcher = head.getNext(); head.getNext() != null; head = head.getNext(), searcher = searcher.getNext()){
        System.out.println("Head: " + head + " val: " + head.getValue());
        System.out.println("Searcher: " + searcher + " val: " + searcher.getValue());

        if (searcher.getValue() == obj){
          head.setNext(searcher.getNext());
          return true;
        }
      }
    }

    return false;
  }

  @Override
  public Integer search(Integer obj){
    return -1;
  }

  public Integer search(Integer k, Integer obj){
    Integer key = this.hash(k);

    if (this.dom.get(key).getValue() != -1){
      for (Node<Integer> head = dom.get(key); head != null; head = head.getNext()){
        System.out.println("Head: " + head + " val: " + head.getValue());
        System.out.println("Next: " + head.getNext());

        if (head.getValue() == obj){
          return head.getValue();
        }
      }
    }

    return -1;
  }

  @Override
  public boolean update(Integer obj){
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
