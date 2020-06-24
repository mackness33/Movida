package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node;
import movida.mackseverini.Array;
import movida.mackseverini.Set;
import movida.mackseverini.Hash;

import movida.commons.Movie;
import movida.commons.Person;

// BUG: Comparable cannot be used.
// SOLUTION: Comparable cannot be used.
public class MovieHash<Movie extends Comparable<Movie>> extends Hash<Movie> {
  // protected Set<String> casts;
  // protected Set<String> directors;
  // protected Set<Integer> dates;
  // protected Set<Integer> rates;

  // constructor residesHash<Movie>
  @SuppressWarnings("unchecked")
  public MovieHash() {
    super();

    // this.casts = new Set<String> ();
    // this.directors = new Set<String> ();
    // this.dates = new Set<Integer> ();
    // this.rates = new Set<Integer> ();
    // for (int i = 0; i < this.sets.length; i++)
    //   this.sets.set(i, new Set<String>(i, null));
  }

  protected Integer hash (movida.commons.Movie in){
    String dir = in.getDirector().getName();

    return Math.abs(dir.codePointAt(0)) % this.MAX_LENGTH;
  }

  @Override
  public  boolean insert(Movie obj){
    ListNode<Movie> node = new ListNode<Movie>(this.hash(obj), obj);

    System.out.println("KEY: " + node.getKey());
    if (dom.get(node.getKey()).getValue() != null){
      ListNode<Movie> head = dom.get(node.getKey());

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

    this.size++;
    return true;
  }


  public boolean addKey(String k){
    //Create new Set with key set
    // Iterate in the array of Elements orderBy the key
    // Add the Set to the Array
    // Cost O(n)
    // keys.set(this.)
    return true;
  }

  @Override
  public boolean delete(Movie obj){
    Integer key = this.hash(obj);

    if (this.dom.get(key).getValue() != null){
      ListNode<Movie> head = dom.get(key);

      // if it's the head of the list
      if (this.compare(head.getValue(), obj) == 0){
        dom.set(key, (head.getNext() == null) ? new ListNode<Movie>(key, null) : head.getNext());
        head = null;          // handle by the garbage collector
        return true;
      }

      for (ListNode<Movie> searcher = head.getNext(); head.getNext() != null; head = head.getNext(), searcher = searcher.getNext()){
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
  public boolean search(Movie obj){
    return this.search(obj.getTitle());
  }

  @Override
  public boolean update(Movie obj){
    Integer key = this.hash(obj);

    if (this.dom.get(key).getValue() != null){
      for (ListNode<Movie> head = dom.get(key); head != null; head = head.getNext()){
        System.out.println("Head: " + head + " val: " + head.getValue());
        System.out.println("Next: " + head.getNext());

        if (this.compare(head.getValue(), obj) == 0){
          head.setValue(obj);
          return true;
        }
      }
    }

    return false;
  }


  public boolean upsert(Movie obj){
    Integer key = this.hash(obj);

    if (this.dom.get(key).getValue() != null){
      for (ListNode<Movie> head = dom.get(key); head != null; head = head.getNext()){
        System.out.println("Head: " + head + " val: " + head.getValue());
        System.out.println("Next: " + head.getNext());

        if (this.compare(head.getValue(), obj) == 0){
          head.setValue(obj);
          return true;
        }
      }
    }

    this.insert(obj);

    return false;
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
