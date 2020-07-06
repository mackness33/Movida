package movida.mackseverini;
import movida.mackseverini.Node2;
import movida.mackseverini.INode2;
import movida.mackseverini.Array;

public class List<E extends Comparable<E>>{
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

  public List (List<E> shallow){
    this.head = (Node2<E>)shallow.getHead();
    this.tail = (Node2<E>)shallow.getTail();
    this.size = shallow.getSize();
  }

  public INode2<E> getHead () { return this.head; }
  public INode2<E> getTail () { return this.tail; }
  public Integer getSize () { return this.size; }

  public void addHead (E el){
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

  public void addTail (E el){
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

  public void addAt (E el, int pos){
    if (pos <= 0 || pos >= size){
      if (pos == 0){
        this.addHead(el);
        return;
      }
      else if (pos == size){
        this.addTail(el);
        return;
      }

      return;
    }

    int i = 1;
    Node2<E> iter = null;
    for (iter = (Node2<E>)this.head; iter.getNext() != null && i < pos; iter = (Node2<E>)iter.getNext(), i++);

    if (i == pos){
      Node2<E> temp = new Node2<E>(el);
      temp.setNext(iter.getNext());
      iter.setNext(temp);
      this.size++;
    }
  }

  public void delHead (){
    if (this.size <= 0)
      return;

    if (this.size == 1){
      this.tail = null;
      this.head = null;
      this.size--;
      return;
    }

    this.head = (Node2<E>)this.head.getNext();
    this.size--;
  }

  public void delTail (){
    if (this.size <= 0)
      return;

    if (this.size == 1){
      this.tail = null;
      this.head = null;
      this.size--;
      return;
    }

    Node2<E> iter;
    for (iter = (Node2<E>)this.head; iter.getNext() != this.tail; iter = (Node2<E>)iter.getNext());

    iter.setNext(null);
    this.tail = iter;
    this.size--;
  }

  public void delEl (E el){
    if (this.size <= 0)
      return;

    if (el.compareTo(this.head.getValue()) == 0){
      this.delHead();
      return;
    }
    else if (el.compareTo(this.tail.getValue()) == 0){
      this.delTail();
      return;
    }

    for (Node2<E> prev = (Node2<E>)this.head, iter = (Node2<E>)this.head.getNext(); iter.getNext() != null; iter = (Node2<E>)iter.getNext(), prev = (Node2<E>)prev.getNext()){
      if (el.compareTo(iter.getValue()) == 0){
        prev.setNext(iter.getNext());
        iter = null;
        this.size--;
        return;
      }
    }
  }

  public void delAt (int pos){
    if (this.size <= 0)
      return;

    if (pos <= 0 || pos >= size-1){
      if (pos == 0){
        this.delHead();
        return;
      }
      else if (pos == size-1){
        this.delTail();
        return;
      }

      return;
    }

    int i = 1;
    Node2<E> iter = null, prev = null;
    for (prev = (Node2<E>)this.head, iter = (Node2<E>)this.head.getNext(); iter.getNext() != null && i < pos; iter = (Node2<E>)iter.getNext(), prev = (Node2<E>)prev.getNext(), i++);

    if (i == pos){
      prev.setNext(iter.getNext());
      iter = null;
      this.size--;
    }
  }

  public void update (E el, int pos){
    if (this.size <= 0)
      return;

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
    for (iter = (Node2<E>)this.head; iter.getNext() != null && i < pos; iter = (Node2<E>)iter.getNext(), i++);

    if (i == pos)
      iter.setValue(el);
  }

  public Integer search (E el){
    if (this.size <= 0)
      return -1;

    if (el.compareTo(this.head.getValue()) == 0)
      return 0;
    else if (el.compareTo(this.tail.getValue()) == 0)
      return this.size-1;

    int i = 1;
    for (Node2<E> iter = (Node2<E>)this.head.getNext(); iter.getNext() != null && i < this.size; iter = (Node2<E>)iter.getNext(), i++)
      if (el.compareTo(iter.getValue()) == 0)
        return i;

    return -1;
  }

  public Array<E> toArray(){
    final Array<E> array = new Array<E>(this.size);

    int i = 0;
    for (Node2<E> iter = (Node2<E>)this.head; iter != null; iter = (Node2)iter.getNext(), i++ )
      array.set(i, iter.getValue());

    return array;
  }

  public void print (){
    if(this.head != null)
      ((Node2<E>)this.head).printAll();
  }
}
