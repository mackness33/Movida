package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node;
import movida.mackseverini.Array;
import movida.mackseverini.Set;
import movida.commons.Movie;
import movida.commons.Person;

// BUG: Comparable cannot be used.
// SOLUTION: Comparable cannot be used.
public class Hash<T extends Comparable<T>> extends ComparableStatic implements movida.mackseverini.IHash<T> {
  protected Array<ListNode<T>> dom;
  protected int MAX_LENGTH = 50;
  protected int size;

  // constructor resides
  @SuppressWarnings("unchecked")
  public Hash() {
    this.size = 0;
    this.dom = new Array<ListNode<T>> (MAX_LENGTH);
    for (int i = 0; i < this.dom.length; i++)
      this.dom.set(i, new ListNode<T>(i, null));

  }

  protected int getSize() { return this.size; }


  // @Override
  protected Integer hash (T input){

    if (input instanceof Integer)
      return Math.abs((Integer)input) % MAX_LENGTH;
    else if (input instanceof String)
      return Math.abs(((String)input).codePointAt(0)) % this.MAX_LENGTH;
    else if (input instanceof Movie)
      return Math.abs(((Movie)input).getTitle().codePointAt(0)) % this.MAX_LENGTH;
    else if (input instanceof Person)
      return Math.abs(((Person)input).getName().codePointAt(0)) % this.MAX_LENGTH;

    return input.hashCode() % this.MAX_LENGTH;
  }

  @Override
  public boolean insert(T obj){
    ListNode<T> node = new ListNode<T>(this.hash(obj), obj);

    System.out.println("KEY: " + node.getKey());
    if (dom.get(node.getKey()).getValue() != null){
      ListNode<T> head = dom.get(node.getKey());

      if (head.getNext() != null)
        // System.out.println("Head: " + head);
        // System.out.println("Next: " + head.getNext());

      // shift to tail
      for (; head.getNext() != null; head = head.getNext()){
        // System.out.println("Head: " + head);
        // System.out.println("Next: " + head.getNext());
      }

      head.setNext(node);
    }
    else
      dom.set(node.getKey(), node);

    size++;

    return true;
  }

  @Override
  public boolean delete(T obj){
    Integer key = this.hash(obj);

    if (this.dom.get(key).getValue() != null){
      ListNode<T> head = dom.get(key);

      // if it's the head of the list
      if (this.compare(head.getValue(), obj) == 0){
        dom.set(key, (head.getNext() == null) ? new ListNode<T>(key, null) : head.getNext());
        head = null;          // handle by the garbage collector
        size--;
        return true;
      }

      for (ListNode<T> searcher = head.getNext(); head.getNext() != null; head = head.getNext(), searcher = searcher.getNext()){
        // System.out.println("Head: " + head + " val: " + head.getValue());
        // System.out.println("Searcher: " + searcher + " val: " + searcher.getValue());

        if (this.compare(searcher.getValue(), obj) == 0){
          head.setNext(searcher.getNext());
          searcher = null;
          size--;
          return true;
        }
      }
    }

    return false;
  }

  @Override
  public boolean search(T obj){
    Integer key = this.hash(obj);

    if (this.dom.get(key).getValue() != null){
      for (ListNode<T> head = dom.get(key); head != null; head = head.getNext()){
        // System.out.println("Head: " + head + " val: " + head.getValue());
        // System.out.println("Next: " + head.getNext());

        if (this.compare(head.getValue(), obj) == 0){
          return true;
        }
      }
    }

    return false;
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

  protected class ListNode<T extends Comparable<T>> extends Node<T>{
    private ListNode<T> next;
    private ListNode<T> tail;

    public ListNode(){
      super();
      this.next = null;
      this.tail = null;
    }

    public ListNode(int k, T v){
      super(k, v);
      this.next = null;
      this.tail = null;
    }

    public ListNode(int k, T v, ListNode<T> n, ListNode<T> t){
      super(k, v);
      this.next = n;
      this.tail = t;
    }

    public ListNode<T> getNext () { return this.next; }
    public ListNode<T> getTail () { return this.tail; }

    public void setNext (ListNode<T> n) { this.next = n; }
    public void setTail (ListNode<T> t) { this.tail = t; }

    public void print(){
      super.print();

      if(this.next != null)
          this.next.print();
    }
  }
}
