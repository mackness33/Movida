package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node2;
import movida.mackseverini.List;
import movida.mackseverini.Array;
import movida.mackseverini.Set;
import movida.commons.Movie;
import movida.commons.Person;
import movida.mackseverini.IKeyList;

public class PriorityQueue<E extends Comparable<E>>{
  protected Array<E> binaryHeap;
  protected final int MAX_LENGTH = 100;
  protected int size;
  protected int height;

  @SuppressWarnings("unchecked")
  public PriorityQueue() {
    this.binaryHeap = new Array<E>(this.MAX_LENGTH);
    this.size = 0;
    this.height = 0;

    for (int i = 0; i < MAX_LENGTH; i++)
      this.binaryHeap.set(i, null);
  }

  public int getSize() { return this.size; }

  // reset of all the data structure
  // @Override
  public void reset (){
    this.size = 0;
    this.height = 0;

    for (int i = 0; i < this.binaryHeap.length; i++)
      this.binaryHeap.set(i, null);
  }

  // @Override
  // insert of a element. A lot similar to KeyHash.addHashKey(..)
  public boolean insert(E obj){
    if (obj == null)
      return false;


    this.binaryHeap.set(this.size, obj);
    this.size++;

    if (this.size > Math.pow(2, this.height+1) - 1)
      this.height++;

    this.moveUp(this.size-1);

    return true;
  }

  public boolean isEmpty(){ return (this.height == 0) ? true : false; }

  protected void moveUp(int pos){
    if (pos >= 0 && pos < this.size){
      if (this.binaryHeap.get(pos) == null)
        return;
    }
    else
      return;

    for (int i = ((pos+1)/2)-1; i >= 0; i=((pos+1)/2)-1){
      if (this.binaryHeap.get(pos).compareTo(this.binaryHeap.get(i)) < 0){
        E temp = this.binaryHeap.get(i);
        this.binaryHeap.set(i, this.binaryHeap.get(pos));
        this.binaryHeap.set(pos, temp);
        pos = i;
      }
      else break;
    }

    return;
  }

  protected void moveDown(int pos){
    if (pos >= 0 && pos < this.size){
      if (this.binaryHeap.get(pos) == null)
        return;
    }
    else return;

    for (int i = (pos*2)+1; i < this.size; i=(pos*2)+1){
      if (i+1 < this.size){
        if (this.binaryHeap.get(i).compareTo(this.binaryHeap.get(i+1)) < 0){
          if (this.compareAndSwap(pos, i))
            pos = i;
          else break;
        }
        else{
          if (this.compareAndSwap(pos, i+1))
            pos = i+1;
          else break;
        }
      }
      else{
        if (this.compareAndSwap(pos, i))
          pos = i;
        else break;
      }
    }

    return;
  }

  private boolean compareAndSwap(int pos, int pos2){
    if (this.binaryHeap.get(pos).compareTo(this.binaryHeap.get(pos2)) > 0){
      E temp = this.binaryHeap.get(pos2);
      this.binaryHeap.set(pos2, this.binaryHeap.get(pos));
      this.binaryHeap.set(pos, temp);
      return true;
    }

    return false;
  }

  // delete of a element. A lot similar to KeyHash.delHashKey(..)
  // @Override
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

    if (this.size < Math.pow(2, this.height) - 1)
      this.height--;

    this.moveDown(pos);

    return true;
  }

  protected int search(E obj, int pos){
    if (obj == null || pos < 1 || this.binaryHeap.get(pos-1) == null)
      return -1;

    if (obj.compareTo(this.binaryHeap.get(pos-1)) == 0)
      return pos-1;
    else if (obj.compareTo(this.binaryHeap.get(pos-1)) < 0)
      return -1;
    else
      return Math.max(this.search(obj, pos*2), this.search(obj, (pos*2)+1));
  }


  // print of the whole hash
  // FOR TEST USE ONLY
  public void print (){
    System.out.println("Size: " + this.size);
    E temp = null;

    for (int i = 0; i < this.size; i++)
      if ((temp = this.binaryHeap.get(i)) != null)
        System.out.println("POS => " + i + " VALUE => " + temp);
  }
}
