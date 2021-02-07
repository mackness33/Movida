package movida.mackseverini;

import movida.commons.Movie;
import movida.commons.Person;

import movida.mackseverini.List;
import movida.mackseverini.Array;

public class Queue<E extends Comparable<E>>{
  protected IList<E> elements;

  public Queue() {
    this.elements = new List<E>();
  }

  public Queue(E el) {
    this.elements = new List<E>(el);
  }

  public int getSize() { return this.elements.getSize(); }

  // reset of all the data structure
  public void reset (){ this.elements.reset(); }

  // insert of a element
  public boolean enqueue(E obj){ return this.addTail(obj); }

  // let the addTail of list return a boolean
  private boolean addTail(E obj){
    if (obj == null)
      return false;

    this.elements.addTail(obj);
    return true;
  }

  public boolean isEmpty(){ return (this.elements.getHead() == null); }

  // delete the head of the list and return the value of the deleted node
  public E dequeue(){
    INode2<E> el_to_retrive = this.elements.getHead();

    if (el_to_retrive != null)
      this.elements.delHead();

    return el_to_retrive.getValue();
  }

  // return the value of the head
  public E top(){ return this.elements.getHead().getValue(); }


  // trasform the list into an array object
  public Array<E> toArray(){ return this.elements.toArray(); }
}
