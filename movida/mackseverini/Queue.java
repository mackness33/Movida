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
  protected int size;

  @SuppressWarnings("unchecked")
  public Queue() {
    this.elements = new List<E>();
    this.size = 0;
  }

  public Queue(E el) {
    this.elements = new List<E>(el);
    this.size = 1;
  }

  public int getSize() { return this.size; }

  // reset of all the data structure
  // @Override
  public void reset (){
    this.size = 0;
    this.elements.reset();
  }

  // @Override
  // insert of a element. A lot similar to KeyHash.addHashKey(..)
  public boolean enqueue(E obj){ return this.elements.addTail(obj); }

  public boolean isEmpty(){ return (this.elements.getHead() == null)}

  public E dequeue(){
    E el_to_retrive = this.elements.getHead();

    if (el_to_retrive != null)
      this.elements.delHead();

    return el_to_retrive;
  }

  public E top(){ return this.elements.getHead(); }

  // print of the whole hash
  // FOR TEST USE ONLY
  public void print (){
    System.out.println("Size: " + this.size);

    int i = this.size;
    for (INode2<E> iter = this.elements.getHead(); iter != null; iter = (Node2<E>)iter.getHead(), i--)
      System.out.print("POS => " + i + "  " + iter.getValue());
  }


  @Override
  // trasform the list into an array object
  public Array<E> toArray(){ return this.elements.toArray(); }
}
