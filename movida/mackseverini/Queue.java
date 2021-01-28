package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node2;
import movida.mackseverini.List;
import movida.mackseverini.Array;
import movida.mackseverini.Set;
import movida.commons.Movie;
import movida.commons.Person;
import movida.mackseverini.IKeyList;

public class Queue<E extends Comparable<E>>{
  protected IList<E> elements;

  @SuppressWarnings("unchecked")
  public Queue() {
    this.elements = new List<E>();
  }

  public Queue(E el) {
    this.elements = new List<E>(el);
  }

  public int getSize() { return this.elements.getSize(); }

  // reset of all the data structure
  // @Override
  public void reset (){
    this.elements.reset();
  }

  // @Override
  // insert of a element. A lot similar to KeyHash.addHashKey(..)
  public boolean enqueue(E obj){ return this.addTail(obj); }

  private boolean addTail(E obj){
    this.elements.addTail(obj);
    return true;
  }

  public boolean isEmpty(){ return (this.elements.getHead() == null); }

  public E dequeue(){
    E el_to_retrive = this.elements.getHead().getValue();

    if (el_to_retrive != null)
      this.elements.delHead();

    return el_to_retrive;
  }

  public E top(){ return this.elements.getHead().getValue(); }

  // print of the whole hash
  // FOR TEST USE ONLY
  public void print (){
    System.out.println("Size: " + this.elements.getSize());

    int i = this.elements.getSize();
    for (INode2<E> iter = this.elements.getHead(); iter != null; iter = (Node2<E>)iter.getNext(), i--)
      System.out.print("POS => " + i + "  " + iter.getValue() + "\n\r");
  }


  // @Override
  // trasform the list into an array object
  public Array<E> toArray(){ return this.elements.toArray(); }
}
