package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node2;
import movida.mackseverini.List;
import movida.mackseverini.Array;
import movida.mackseverini.Set;
import movida.commons.Movie;
import movida.commons.Person;
import movida.mackseverini.IKeyList;

public class PriorityQueue<E extends Comparable<E>> implements movida.mackseverini.IKeyList<E, K, T>{
  protected Array<E> binaryheap;
  protected final int MAX_LENGTH = 100;
  protected int size;
  protected int height;

  @SuppressWarnings("unchecked")
  public PriorityQueue() {
    this.binaryHeap = new Array<E>(MAX_LENGTH);
    this.size = 0;
    this.height = 0;

    for (int i = 0; i < MAX_LENGTH; i++)
      this.binaryHeap.set(i, null);
  }

  public int getSize() { return this.size; }

  // reset of all the data structure
  @Override
  public void reset (){
    this.size = 0;
    this.height = 0;

    for (int i = 0; i < this.binaryheap.length; i++)
      this.binaryheap.set(i, null);
  }

  @Override
  // insert of a element. A lot similar to KeyHash.addHashKey(..)
  public boolean insert(E obj){
    if (obj == null)
      return false;


    this.binaryHeap.set(i, this.size);
    this.size++;

    if (this.size > (2 ** (this.height + 1) - 1))
      this.height++;


    this.moveUp(this.size);

    return true;
  }

  public boolean isEmpty(){ return (this.height == 0) ? true : false; }

  protected void moveUp(int pos){
    if (pos >= 0 && pos < this.size)
      if (this.binaryHeap.get(pos) == null)
        return false;
    else
      return false;

    for (int i = pos/2; i >= 0; i/=2){
      if (this.binaryHeap.get(i).compareTo(is.binaryHeap.get(pos)) > 0){
        E temp = this.binaryHeap.get(i);
        this.binaryHeap.set(i, this.binaryHeap.get(pos));
        this.binaryHeap.set(pos, temp);
      }
      else
        break;
    }

    return true;
  }

  protected void moveDown(int pos){
    if (pos >= 0 && pos < this.size)
      if (this.binaryHeap.get(pos) == null)
        return false;
    else
      return false;

    for (int i = pos*2; i < this.size; i*=2){
      if (this.binaryHeap.get(i).compareTo(is.binaryHeap.get(pos)) > 0){
        E temp = this.binaryHeap.get(i);
        this.binaryHeap.set(i, this.binaryHeap.get(pos));
        this.binaryHeap.set(pos, temp);
      }
      else if (i+1 < this.size){
        if (this.binaryHeap.get(i+1).compareTo(is.binaryHeap.get(pos)) > 0){
          E temp = this.binaryHeap.get(i);
          this.binaryHeap.set(i, this.binaryHeap.get(pos));
          this.binaryHeap.set(pos, temp);
        }
      }
      else
        break;
    }

    return true;
  }

  // delete of a element. A lot similar to KeyHash.delHashKey(..)
  @Override
  public boolean delete(E obj){
    if (obj == null)
      return false;

    int pos = -1;

    if ((pos = this.search(obj, 1)) < 0)
      return false;

    E temp = this.binaryHeap.get(this.size-1);
    this.binaryHeap.set(pos, temp);
    this.binaryHeap.set(this.size-1, null);

    this.size--;

    if (this.size < (2 ** (this.height)) - 1)
      this.height--;

    this.moveDown(pos);

    return true;
  }

  protected int search(E obj, int pos){
    if (obj == null || pos < 1)
      return -1;

    if (obj.compareTo(this.binaryHeap.get(pos-1)) == 0)
      return pos-1;
    else if (obj.compareTo(this.binaryHeap.get(pos-1)) < 0)
      return -1
    else
      return Math.max(this.search(obj, pos*2), this.search(obj, (pos*2)+1))
  }


  // print of the whole hash
  // FOR TEST USE ONLY
  public void print (){
    System.out.println("Length: " + this.dom.length);
    E temp = null;

    for (int i = 0; i < this.dom.length; i++)
      if ((temp = this.dom.get(i)) != null)
        System.out.println("VALUE => " + this.dom.get(i));

    this.major.print();
  }
}
