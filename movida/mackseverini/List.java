package movida.mackseverini;

import movida.mackseverini.Node2;
import movida.mackseverini.INode2;
import movida.mackseverini.Array;

// class that implements basic operation of a list
public class List<E extends Comparable<E>> implements movida.mackseverini.IList<E>{
  protected INode2<E> head;
  protected INode2<E> tail;
  protected Integer size;

  public List (){
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  public List (E el){
    this.head = new Node2<E>(el);
    this.tail = this.head;
    this.size = 1;
  }

  public List (IList<E> shallow){
    this.head = (Node2<E>)shallow.getHead();
    this.tail = (Node2<E>)shallow.getTail();
    this.size = shallow.getSize();
  }

  @Override
  public INode2<E> getHead () { return this.head; }
  @Override
  public INode2<E> getTail () { return this.tail; }
  @Override
  public Integer getSize () { return this.size; }

  // Add element as the first node of the list
  @Override
  public void addHead (E el){
    if (el == null)
      return;

    // it the list is empty
    if (this.size <= 0){
      this.head = new Node2<E>(el);
      this.tail = this.head;
      this.size = 1;
      return;
    }

    Node2<E> temp = new Node2<E>(el);
    temp.setNext(this.head);
    this.head = temp;
    this.size++;
  }

  // Add element as the last node of the list
  @Override
  public void addTail (E el){
    if (el == null)
      return;

    // it the list is empty
    if (this.size <= 0){
      this.head = new Node2<E>(el);
      this.tail = this.head;
      this.size = 1;
      return;
    }

    Node2<E> temp = new Node2<E>(el);
    this.tail.setNext(temp);
    this.tail = temp;
    this.size++;
  }

  // Add element at a position of the list
  @Override
  public boolean addAt (E el, int pos){
    if (el == null)
      return false;

    // check if the position is valid
    if (pos <= 0 || pos >= size){
      if (pos == 0){
        this.addHead(el);
        return true;
      }
      else if (pos == size){
        this.addTail(el);
        return true;
      }

      return false;
    }

    int i = 1;
    INode2<E> iter = null;
    // iterate all the list to get to the position
    for (iter = this.head; iter.getNext() != null && i < pos; iter = iter.getNext(), i++);

    // if right, add to the list
    if (i == pos){
      Node2<E> temp = new Node2<E>(el);
      temp.setNext(iter.getNext());
      iter.setNext(temp);
      this.size++;
    }

    return true;
  }

  @Override
  // concatenate a list at the end
  public void addToEnd (IList<E> L){
    if (L == null)
      return;

    // add each node of the list in the input at the end of this
    for (INode2<E> iter = L.getHead(); iter != null; iter = iter.getNext())
      this.addTail(iter.getValue());
    return;
  }

  @Override
  // delete the first node of the list
  public void delHead (){
    if (this.size <= 0)
      return;

    // it the list has only one node
    if (this.size == 1){
      this.tail = null;
      this.head = null;
      this.size--;
      return;
    }

    this.head = (Node2<E>)this.head.getNext();
    this.size--;
  }

  @Override
  // delete the last node of the list
  public void delTail (){
    if (this.size <= 0)
      return;

    // it the list has only one node
    if (this.size == 1){
      this.tail = null;
      this.head = null;
      this.size--;
      return;
    }

    Node2<E> iter;
    // got to the end of the list
    for (iter = (Node2<E>)this.head; iter.getNext() != this.tail; iter = (Node2<E>)iter.getNext());

    iter.setNext(null);
    this.tail = iter;
    this.size--;
  }

  @Override
  // delete the node with the element in input
  public boolean delEl (E el){
    // check if the list is empty
    if (this.size <= 0 || el == null)
      return false;

    // compare to head and tail
    if (el.compareTo(this.head.getValue()) == 0){
      this.delHead();
      return true;
    }
    else if (el.compareTo(this.tail.getValue()) == 0){
      this.delTail();
      return true;
    }

    // check to see if there's more then one element
    if((Node2<E>)this.head.getNext() == null)
      return false;

      // iterate all the elements of the list
    for (Node2<E> prev = (Node2<E>)this.head, iter = (Node2<E>)this.head.getNext(); iter.getNext() != null; iter = (Node2<E>)iter.getNext(), prev = (Node2<E>)prev.getNext()){
      if (el.compareTo(iter.getValue()) == 0){
        prev.setNext(iter.getNext());
        iter = null;
        this.size--;
        return true;
      }
    }

    return false;
  }

  @Override
  // Delete a element by its position
  public boolean delAt (int pos){
    if (this.size <= 0)
      return false;

    // check if the position is valid
    if (pos <= 0 || pos >= size-1){
      if (pos == 0){
        this.delHead();
        return true;
      }
      else if (pos == size-1){
        this.delTail();
        return true;
      }

      return false;
    }

    int i = 1;
    Node2<E> iter = null, prev = null;
    // iterate all the list to get to the position
    for (prev = (Node2<E>)this.head, iter = (Node2<E>)this.head.getNext(); iter.getNext() != null && i < pos; iter = (Node2<E>)iter.getNext(), prev = (Node2<E>)prev.getNext(), i++);

    // if right, delete the node from the list
    if (i == pos){
      prev.setNext(iter.getNext());
      iter = null;
      this.size--;

      return true;
    }

    return false;
  }

  @Override
  // Update an element by having its position as the input
  public void update (E el, int pos){
    if (this.size <= 0 || el == null)
      return;

    // check if the position is valid
    if (pos <= 0 || pos >= size-1){
      if (pos == 0){
        this.head.setValue(el);
        return;
      }
      else if (pos == size-1){
        this.tail.setValue(el);
        return;
      }

      return;
    }

    int i = 0;
    Node2<E> iter = null;
    // iterate all the list to get to the position
    for (iter = (Node2<E>)this.head; iter.getNext() != null && i < pos; iter = (Node2<E>)iter.getNext(), i++);

    // if right, update
    if (i == pos)
      iter.setValue(el);
  }

  @Override
  // get the position by having the element as the input
  public Integer search (E el){
    if (this.size <= 0 || el == null)
      return null;

    // compare the element with the first and last node of the list
    if (el.compareTo(this.head.getValue()) == 0)
      return 0;
    else if (el.compareTo(this.tail.getValue()) == 0)
      return this.size-1;

    // check to see if there's more then one element
    if((Node2<E>)this.head.getNext() == null)
      return null;

    int i = 1;
    // iterate all the list to compare the element
    for (Node2<E> iter = (Node2<E>)this.head.getNext(); iter.getNext() != null && i < this.size; iter = (Node2<E>)iter.getNext(), i++)
      if (el.compareTo(iter.getValue()) == 0)
        return i;

    return -1;
  }

  @Override
  // get the position by having the element as the input
  public E get (E el){
    if (this.size <= 0 || el == null)
      return null;

    // compare the element with the first and last node of the list
    if (el.compareTo(this.head.getValue()) == 0)
      return this.head.getValue();
    else if (el.compareTo(this.tail.getValue()) == 0)
      return this.tail.getValue();

    // check to see if there's more then one element
    if((Node2<E>)this.head.getNext() == null)
      return null;

    int i = 1;
    // iterate all the list to compare the element
    for (Node2<E> iter = (Node2<E>)this.head.getNext(); iter.getNext() != null && i < this.size; iter = (Node2<E>)iter.getNext(), i++)
      if (el.compareTo(iter.getValue()) == 0)
        return iter.getValue();

    return null;
  }

  @Override
  // get an element at a specified position of the list
  public E getAt (int pos){
    // check if the position is valid
    if (pos <= 0 || pos >= size-1){
      if (pos == 0)
        return this.head.getValue();
      else if (pos == size-1)
        return this.tail.getValue();
      return null;
    }

    int i = 0;
    Node2<E> iter = null;
    // iterate all the list to get to the position
    for (iter = (Node2<E>)this.head; iter.getNext() != null && i < pos; iter = (Node2<E>)iter.getNext(), i++);


    // if right, return it
    if (i == pos)
      return iter.getValue();

    return null;
  }

  @Override
  // trasform the list into an array object
  public Array<E> toArray(){
    if (this.size < 0)
      return null;

    final Array<E> array = new Array<E>(this.size);

    int i = 0;
    // iterate all the list to add to the array
    for (Node2<E> iter = (Node2<E>)this.head; iter != null; iter = (Node2)iter.getNext(), i++ )
      array.set(i, iter.getValue());

    return array;
  }

  @Override
  // reset all the list
  public void reset (){
    if (this.size <= 0)
      return;

    this.head = null;
    this.tail = null;
    this.size = 0;

    return;
  }

  @Override
  // how to compare two differents list
  public int compareTo (IList<E> el){ return this.hashCode() - el.hashCode(); }

  @Override
  // how to compare two differents list
  public boolean swap (INode2<E> first, INode2<E> second){
    if (first == null || second == null || first == second)
      return false;

    // get the position of the two nodes in input
    Integer i = this.search(first.getValue()), j = this.search(second.getValue());

    // check the position of the two nodes
    if (i == null || j == null || i == -1 || j == -1)
      return false;

    // swap the two values of the nodes
    E temp = first.getValue();
    first.setValue(second.getValue());
    second.setValue(temp);

    return true;
  }
}
