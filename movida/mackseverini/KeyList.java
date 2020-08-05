package movida.mackseverini;
import movida.mackseverini.Node2;
import movida.mackseverini.INode2;
import movida.mackseverini.IKeyNode;
import movida.mackseverini.KeyNode;
import movida.mackseverini.Array;

public class KeyList<E extends Comparable<E>> extends movida.mackseverini.List<E> implements movida.mackseverini.IKeyList<E>{
  protected Integer key;

  public KeyList (){
    this.head = null;
    this.tail = null;
    this.size = 0;
    this.key = null;
  }

  public KeyList (Integer k, Integer el_key, E el){
    this.head = new KeyNode<E>(el_key, el);
    this.tail = this.head;
    this.size = 1;
    this.key = k;
  }

  public KeyList (Integer k){
    this.head = null;
    this.tail = null;
    this.size = 0;
    this.key = k;
  }

  public KeyList (KeyList<E> shallow){
    this.head = (KeyNode<E>)shallow.getHead();
    this.tail = (KeyNode<E>)shallow.getTail();
    this.size = shallow.getSize();
    this.key = shallow.getKey();
  }

  // @Override
  // public String toString(){
  //   this.print();
  //   return "KeyList: KEY => " + this.key + " HEAD => " + this.head;
  // }

  @Override
  public void print(){
    System.out.println("KeyList: KEY => " + this.key + " HEAD => " + this.head);
  }

  public void printAll(){
    System.out.println("KeyList: KEY => " + this.key + " HEAD => " + this.head);
    if (this.head != null && this.head instanceof KeyNode)
      ((KeyNode<E>)this.head).printAll();
  }

  @Override
  public Integer getKey() { return this.key; }

  @Override
  public void setKey (Integer k) { this.key = k; }

  @Override
  public E getByKey (Integer k){
    if (this.size <= 0)
      return null;

    for (KeyNode<E> iter = (KeyNode<E>)this.head; iter != null; iter = (KeyNode<E>)iter.getNext())
      if (iter.getKey() == k)
        return iter.getValue();

    return null;
  }

  @Override
  public boolean delByKey (Integer k){
    if (this.size <= 0)
      return false;

    if (k == ((KeyNode<E>)this.head).getKey()){
      this.delHead();
      return true;
    }
    else if (k == ((KeyNode<E>)this.tail).getKey()){
      this.delTail();
      return true;
    }

    if((KeyNode<E>)this.head.getNext() == null)
      return false;

    int i = 0;
    for (KeyNode<E> prev = (KeyNode<E>)this.head, iter = (KeyNode<E>)this.head.getNext(); iter.getNext() != null; iter = (KeyNode<E>)iter.getNext(), prev = (KeyNode<E>)prev.getNext()){
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
  public Integer searchKey (E el){
    if (this.size <= 0)
      return null;

    if (el.compareTo(this.head.getValue()) == 0)
      return ((KeyNode<E>)this.head).getKey();
    else if (el.compareTo(this.tail.getValue()) == 0)
      return ((KeyNode<E>)this.tail).getKey();

    if((KeyNode<E>)this.head.getNext() == null)
      return null;

    int i = 1;
    for (KeyNode<E> iter = (KeyNode<E>)this.head.getNext(); iter.getNext() != null && i < this.size; iter = (KeyNode<E>)iter.getNext(), i++)
      if (el.compareTo(iter.getValue()) == 0)
        return ((KeyNode<E>)iter).getKey();

    return null;
  }

  @Override
  public void addTail (Integer k, E el){
    if (this.size <= 0){
      this.head = new KeyNode<E>(k, el);
      this.tail = this.head;
      this.size = 1;
      return;
    }

    KeyNode<E> temp = new KeyNode<E>(k, el);
    this.tail.setNext(temp);
    this.tail = temp;
    this.size++;
  }

  @Override
  public void addHead (Integer k, E el){
    if (this.size <= 0){
      this.head = new KeyNode<E>(k, el);
      this.tail = this.head;
      this.size = 1;
      return;
    }

    KeyNode<E> temp = new KeyNode<E>(k, el);
    temp.setNext(this.head);
    this.head = temp;
    this.size++;
  }

  @Override
  public void addBlue (Integer k, E el, Integer pos){
    System.out.println("COMEON: ");
    if (pos <= 0 || pos >= size){
      if (pos == 0){
        this.addHead(k, el);
        return;
      }
      else if (pos == size){
        this.addTail(k, el);
        return;
      }

      return;
    }

    int i = 1;
    KeyNode<E> iter = null;
    for (iter = (KeyNode<E>)this.head; iter.getNext() != null && i < pos; iter = (KeyNode<E>)iter.getNext(), i++);

    if (i == pos){
      KeyNode<E> temp = new KeyNode<E>(k, el);
      temp.setNext((KeyNode<E>)iter.getNext());
      iter.setNext(temp);
      // System.out.println("ITER: " + iter);
      // System.out.println("ITER NEXT: " + iter.getNext());
      // System.out.println("TEMP: " + temp);
      // System.out.println("TEMP NEXT: " + temp.getNext());
      this.size++;
    }
  }
}
