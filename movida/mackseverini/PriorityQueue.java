package movida.mackseverini;

import movida.commons.Movie;
import movida.commons.Person;

import movida.mackseverini.Array;

public class PriorityQueue<E extends Comparable<E>, K extends Comparable<K>>{
  protected Array<Pair<E, K>> binaryHeap;
  protected final int MAX_LENGTH = 100;
  protected int size;
  protected int height;
  protected boolean isMin;

  public PriorityQueue(boolean min) {
    this.binaryHeap = new Array<Pair<E, K>>(this.MAX_LENGTH);
    this.size = 0;
    this.height = 0;
    this.isMin = min;

    for (int i = 0; i < MAX_LENGTH; i++)
      this.binaryHeap.set(i, null);
  }

  public PriorityQueue() {
    this.binaryHeap = new Array<Pair<E, K>>(this.MAX_LENGTH);
    this.size = 0;
    this.height = 0;
    this.isMin = true;

    for (int i = 0; i < MAX_LENGTH; i++)
      this.binaryHeap.set(i, null);
  }

  public int getSize() { return this.size; }

  // @Override
  // reset of all the data structure
  public void reset (){
    this.size = 0;
    this.height = 0;

    for (int i = 0; i < this.binaryHeap.length; i++)
      this.binaryHeap.set(i, null);
  }

  // @Override
  // insert of a element. A lot similar to KeyHash.addHashKey(..)
  public boolean insert(E obj, K key){
    if (obj == null || key == null)
      return false;

    // add a new node at the end of the binaryheap
    this.binaryHeap.set(this.size, new Pair<E, K>(obj, key));
    this.size++;

    // increase the height if needed
    if (this.size > Math.pow(2, this.height) - 1)
      this.height++;

    // move up the new node added
    this.moveUp(this.size-1);

    return true;
  }

  public boolean isEmpty(){ return (this.height == 0) ? true : false; }

  // move up a node to its right position
  protected void moveUp(int pos){
    // if position is right and the has a node inside than continue
    if (pos >= 0 && pos < this.size){
      if (this.binaryHeap.get(pos) == null)
        return;
    }
    else
      return;

    // iterate till the node is in the right position
    for (int i = ((pos+1)/2)-1; i >= 0; i=((pos+1)/2)-1){
      // compare the input node with the iter one
      if (min_max_compare(this.binaryHeap.get(pos), this.binaryHeap.get(i))){
        // swap the two node
        Pair<E, K> temp = this.binaryHeap.get(i);
        this.binaryHeap.set(i, this.binaryHeap.get(pos));
        this.binaryHeap.set(pos, temp);
        pos = i;
      }
      else break;
    }

    return;
  }

  // move up a node to its right position
  protected void moveDown(int pos){
    if (pos >= 0 && pos < this.size){
      if (this.binaryHeap.get(pos) == null)
        return;
    }
    else return;

    // iterate till the node is in the right position
    for (int i = (pos*2)+1; i < this.size; i = (pos*2)+1){
      // if i+1 doesn't exceed the size of the binaryHeap
      if (i+1 < this.size){
        // check (and swap) the bigger (lesser) of the nodes in i and i+1
        if (min_max_compare(this.binaryHeap.get(i), this.binaryHeap.get(i+1))){
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
      // else just check (and swap) the node in i
      else{
        if (this.compareAndSwap(pos, i))
          pos = i;
        else break;
      }
    }

    return;
  }

  // compare and swap the node in the first position in input with the second based of which one is bigger (lesser)
  private boolean compareAndSwap(int pos, int pos2){
    if (min_max_compareEqual(this.binaryHeap.get(pos2), this.binaryHeap.get(pos))){
      Pair<E, K> temp = this.binaryHeap.get(pos2);
      this.binaryHeap.set(pos2, this.binaryHeap.get(pos));
      this.binaryHeap.set(pos, temp);
      return true;
    }

    return false;
  }

  // delete of a element. A lot similar to KeyHash.delHashKey(..)
  public boolean delete(E obj){
    if (obj == null)
      return false;

    int pos = -1;

    // get and check the position of the object in input
    if ((pos = this.search(obj, 1)) < 0)
      return false;

    // swap with the last node of the binaryHeap
    Pair<E, K> temp = this.binaryHeap.get(this.size-1);
    this.binaryHeap.set(pos, temp);
    this.binaryHeap.set(this.size-1, null);

    this.size--;

    // decrease the height if needed
    if (this.size < Math.pow(2, this.height) - 1)
      this.height--;

    // move down the new node in the position of the deleted one
    this.moveDown(pos);

    return true;
  }

  // recursive search of the obj in input. It return the position of the obj if found else -1
  protected int search(E obj, int pos){
    // checks on the input
    if (obj == null || pos < 1 || this.binaryHeap.get(pos-1) == null || pos > this.size)
      return -1;

    // check the el with the position in input
    if (obj.compareTo(this.binaryHeap.get(pos-1).getValue()) == 0)
      return pos-1;
    // if the object exceed the value on the position, return not found
    else  if (min_max_compare(obj, this.binaryHeap.get(pos-1).getValue()))
      return -1;
    // else get the bigger results of the recursive call
    return Math.max(this.search(obj, pos*2), this.search(obj, (pos*2)+1));
  }

  // check if the object is present
  public boolean check(E obj){
    if (obj == null)
      return false;

    // check the binaryheap as a normal array
    for (int i = 0; i < this.size; i++)
      if (obj.compareTo(this.binaryHeap.get(i).getValue()) == 0)
        return true;

    return false;
  }

  // recursive search of the key of the input object
  public K getKey(E obj, int pos){
    if (obj == null || pos < 1 || this.binaryHeap.get(pos-1) == null || pos > this.size)
      return null;


    // check the el with the position in input
    if (obj.compareTo(this.binaryHeap.get(pos-1).getValue()) == 0)
      return this.binaryHeap.get(pos-1).getKey();
    // if the object exceed the value on the position, return not found
    else  if (min_max_compare(obj, this.binaryHeap.get(pos-1).getValue()))
      return null;
    // else get the bigger results of the recursive call and check
    else{
      K first = this.getKey(obj, pos*2);
      K second = this.getKey(obj, (pos*2)+1);
      int p = Math.max((first != null) ? pos*2 : -1 , (second != null) ? (pos*2 + 1) : -1);
      return (p < 0) ? null : this.binaryHeap.get(p).getKey();
    }
  }

  // return the value at the top
  public E find() { return this.binaryHeap.get(0).getValue();}

  // delete the value at the top
  public boolean delete() { return this.delete(this.binaryHeap.get(0).getValue());}

  // increase the key of the object in input
  public boolean increaseKey(E obj, K key){
    if (obj == null || key == null)
      return false;

    int pos = -1;

    // check if the object is present
    if ((pos = this.search(obj, 1)) < 0)
      return false;

    // check the key of the node found
    if (min_max_compareEqual(key, this.binaryHeap.get(pos).getKey()))
      return false;

    // set the new object
    this.binaryHeap.set(pos, new Pair<E, K>(obj, key));

    // move based on the order decided
    if (isMin)
      this.moveUp(pos);
    else
      this.moveDown(pos);

    return true;
  }

  // decrease the key of the object in input
  public boolean decreaseKey(E obj, K key){
    if (obj == null || key == null)
      return false;

    int pos = -1;

    // check if the object is present
    if ((pos = this.search(obj, 1)) < 0)
      return false;

    // check the key of the node found
    if (min_max_compare(this.binaryHeap.get(pos).getKey(), key))
      return false;

    // set the new object
    this.binaryHeap.set(pos, new Pair<E, K>(obj, key));

    // move based on the order decided
    if (isMin)
      this.moveUp(pos);
    else
      this.moveDown(pos);

    return true;
  }


  // compare the two elements based on the order in input (isMin)
  protected <T extends Comparable<T>> boolean min_max_compare(T obj, T obj2){ return (isMin) ? (obj.compareTo(obj2) < 0) : (obj.compareTo(obj2) > 0); }

  // compare the two elements based on the order in input (isMin)
  protected <T extends Comparable<T>> boolean min_max_compareEqual(T obj, T obj2){ return (isMin) ? (obj.compareTo(obj2) <= 0) : (obj.compareTo(obj2) >= 0); }


  // class for pair value and key
  protected class Pair <E extends Comparable<E>, K extends Comparable<K>> implements Comparable<Pair<E, K>>{
    protected E value;
		protected K key;

    public Pair(){
      this.value = null;
      this.key = null;
    }

    public Pair(E v, K k){
      this.value = v;
      this.key = k;
    }

    public E getValue() { return this.value; }
    public K getKey() { return this.key; }

    public void setValue (E v) { this.value = v; }
    public void setKey (K k) { this.key = k; }

    public int compareTo (Pair<E, K> input) {
      return this.key.compareTo(input.getKey());
    }

  }
}
