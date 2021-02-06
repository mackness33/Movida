package movida.mackseverini;

import movida.mackseverini.Node2;
import movida.mackseverini.INode2;
import movida.mackseverini.IKeyNode;
import movida.mackseverini.KeyNode;
import movida.mackseverini.Array;


// Class used for list with keys. It extends the List class by adding methods for keys
public class KeyList<E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> extends movida.mackseverini.List<E> implements movida.mackseverini.IKeyList<E, T, K>{
  protected K key;

  public KeyList (){
    this.head = null;
    this.tail = null;
    this.size = 0;
    this.key = null;
  }

  public KeyList (K k, T el_key, E el){
    this.head = new KeyNode<E, T>(el_key, el);
    this.tail = this.head;
    this.size = 1;
    this.key = k;
  }

  public KeyList (K k){
    this.head = null;
    this.tail = null;
    this.size = 0;
    this.key = k;
  }

  public KeyList (KeyList<E, T, K> shallow){
    this.head = (KeyNode<E, T>)shallow.getHead();
    this.tail = (KeyNode<E, T>)shallow.getTail();
    this.size = shallow.getSize();
    this.key = shallow.getKey();
  }

  @Override
  public void print(){ System.out.println("KeyList: KEY => " + this.key + " HEAD => " + this.head); }

  public void printAll(){
    System.out.println("KeyList: KEY => " + this.key + " HEAD => " + this.head);
    if (this.head != null && this.head instanceof KeyNode)
      ((KeyNode<E, T>)this.head).printAll();
  }

  @Override
  public K getKey() { return this.key; }

  @Override
  public void setKey (K k) { this.key = k; }

  // Get the element by having a key as the input
  @Override
  public E getByKey (T k){
    if (this.size <= 0 || k == null)
      return null;

    // iterate all the list
    for (KeyNode<E, T> iter = (KeyNode<E, T>)this.head; iter != null; iter = (KeyNode<E, T>)iter.getNext())
      if (iter.getKey() == k)
        return iter.getValue();

    return null;
  }

  // Delete a element by having a key as the input
  @Override
  public boolean delByKey (T k){
    if (this.size <= 0 || k == null)
      return false;

    // check wheter the key is the first or last element
    if (k == ((KeyNode<E, T>)this.head).getKey()){
      this.delHead();
      return true;
    }
    else if (k == ((KeyNode<E, T>)this.tail).getKey()){
      this.delTail();
      return true;
    }

    // check to see if there's more then one element
    if((KeyNode<E, T>)this.head.getNext() == null)
      return false;

    int i = 0;

    // iterate all the elements of the list
    for (KeyNode<E, T> prev = (KeyNode<E, T>)this.head, iter = (KeyNode<E, T>)this.head.getNext(); iter.getNext() != null; iter = (KeyNode<E, T>)iter.getNext(), prev = (KeyNode<E, T>)prev.getNext()){
      // if the key match, delete the node
      if (iter.getKey() == k){
        prev.setNext(iter.getNext());
        iter = null;
        this.size--;
        return true;
      }
    }

    return false;
  }

  @Override
  // get the key by having the element as the input
  public T searchKey (E el){
    if (this.size <= 0 || el == null)
      return null;

    // compare the element with the first and last node of the list
    if (el.compareTo(this.head.getValue()) == 0)
      return ((KeyNode<E, T>)this.head).getKey();
    else if (el.compareTo(this.tail.getValue()) == 0)
      return ((KeyNode<E, T>)this.tail).getKey();


    // check to see if there's more then one element
    if((KeyNode<E, T>)this.head.getNext() == null)
      return null;

    int i = 1;
    // iterate all the list to compare the element
    for (KeyNode<E, T> iter = (KeyNode<E, T>)this.head.getNext(); iter.getNext() != null && i < this.size; iter = (KeyNode<E, T>)iter.getNext(), i++)
      if (el.compareTo(iter.getValue()) == 0)
        return ((KeyNode<E, T>)iter).getKey();

    return null;
  }

  @Override
  // get the key by having the element as the input
  public boolean updElKey (E el, T k){
    if (this.size <= 0 || el == null)
      return false;

    // compare the element with the first and last node of the list
    if (el.compareTo(this.head.getValue()) == 0){
      ((KeyNode<E, T>)this.head).setKey(k);
      return true;
    }
    else if (el.compareTo(this.tail.getValue()) == 0){
      ((KeyNode<E, T>)this.tail).setKey(k);
      return true;
    }

    // check to see if there's more then one element
    if((KeyNode<E, T>)this.head.getNext() == null)
      return false;

    int i = 1;
    // iterate all the list to compare the element
    for (KeyNode<E, T> iter = (KeyNode<E, T>)this.head.getNext(); iter.getNext() != null && i < this.size; iter = (KeyNode<E, T>)iter.getNext(), i++){
      if (el.compareTo(iter.getValue()) == 0){
        ((KeyNode<E, T>)iter).setKey(k);
        return true;
      }
    }

    return false;
  }

  @Override
  // add element and key as the last node of the list
  public void addTail (T k, E el){
    if (k == null || el == null)
      return;

    if (this.size <= 0 ){
      this.head = new KeyNode<E, T>(k, el);
      this.tail = this.head;
      this.size = 1;
      return;
    }

    KeyNode<E, T> temp = new KeyNode<E, T>(k, el);
    this.tail.setNext(temp);
    this.tail = temp;
    this.size++;
  }

  @Override
  // add element and key as the first node of the list
  public void addHead (T k, E el){
    if (k == null || el == null)
      return;

    if (this.size <= 0){
      this.head = new KeyNode<E, T>(k, el);
      this.tail = this.head;
      this.size = 1;
      return;
    }

    KeyNode<E, T> temp = new KeyNode<E, T>(k, el);
    temp.setNext(this.head);
    this.head = temp;
    this.size++;
  }

  @Override
  // add element and key at a specified position of the list
  public boolean addAt (T k, E el, Integer pos){
    if (k == null || el == null || pos == null)
      return false;

    // check if the position is valid
    if (pos <= 0 || pos >= size){
      if (pos == 0){
        this.addHead(k, el);
        return true;
      }
      else if (pos == size){
        this.addTail(k, el);
        return true;
      }

      return false;
    }

    int i = 1;
    KeyNode<E, T> iter = null;
    // iterate all the list to get to the position
    for (iter = (KeyNode<E, T>)this.head; iter.getNext() != null && i < pos; iter = (KeyNode<E, T>)iter.getNext(), i++);

    // if right, add to the list
    if (i == pos){
      KeyNode<E, T> temp = new KeyNode<E, T>(k, el);
      temp.setNext((KeyNode<E, T>)iter.getNext());
      iter.setNext(temp);
      this.size++;
    }

    return true;
  }

  // Update an element by having its key as the input
  public void updByKey (E el, T key){
    if (key == null || el == null)
      return;

    if (this.size <= 0)
      return;

    // compare the element with the first and last node of the list
    if (key == ((KeyNode<E, T>)this.head).getKey())
      this.head.setValue(el);
    else if (key == ((KeyNode<E, T>)this.tail).getKey())
      this.tail.setValue(el);

    // check to see if there's more then one element
    if((KeyNode<E, T>)this.head.getNext() == null)
      return;


    // iterate all the list to compare the key
    for (IKeyNode<E, T> iter = (IKeyNode<E, T>)this.head.getNext(); iter.getNext() != null; iter = (IKeyNode<E, T>)iter.getNext())
      if (iter.getKey() == key)
        iter.setValue(el);
  }

  @Override
  // how to compare two differents list
  public boolean swap (IKeyNode<E, K> first, IKeyNode<E, K> second){
    if (first == null || second == null || first == second)
      return false;

    Integer i = this.search(first.getValue()), j = this.search(second.getValue());

    if (i == null || j == null || i == -1 || j == -1)
      return false;

    E temp = first.getValue();
    first.setValue(second.getValue());
    second.setValue(temp);

    K key_temp = first.getKey();
    first.setKey(second.getKey());
    second.setKey(key_temp);

    return true;
  }
}
