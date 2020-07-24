package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node2;
import movida.mackseverini.List;
import movida.mackseverini.Array;
import movida.mackseverini.Set;
import movida.commons.Movie;
import movida.commons.Person;

// BUG: Comparable cannot be used.
// SOLUTION: Comparable cannot be used.
public class Hash2<E extends Comparable<E>> extends ComparableStatic implements movida.mackseverini.IHash<E> {
  protected Array<E> dom;
  protected IList<IList<Integer>> major;
  protected int MAX_LENGTH = 100;
  protected int size;
  protected int length;

  // constructor resides
  @SuppressWarnings("unchecked")
  public Hash2() {
    this.size = 0;
    this.length = 0;
    this.dom = new Array<E> (this.MAX_LENGTH);
    for (int i = 0; i < this.dom.length; i++)
      this.dom.set(i, null);

    this.major = new HashList<IList<Integer>>();
  }

  protected int getSize() { return this.size; }
  protected int getLength() { return this.length; }


  // @Override
  public void reset (){
    this.major.reset();

    for (int i = 0; i < this.dom.length; i++)
      this.dom.set(i, null);
  }

  // @Override
  protected <K> Integer hash (K input){

    if (input instanceof Integer)
      return Math.abs((Integer)input) % this.MAX_LENGTH;
    else if (input instanceof String)
      return Math.abs(((String)input).codePointAt(0)) % this.MAX_LENGTH;
    else if (input instanceof Movie)
      return Math.abs(((Movie)input).getTitle().codePointAt(0)) % this.MAX_LENGTH;
    else if (input instanceof Person)
      return Math.abs(((Person)input).getName().codePointAt(0)) % this.MAX_LENGTH;

    return input.hashCode() % this.MAX_LENGTH;
  }

  @Override
  public boolean insert(E obj){
    this.dom.set(this.size, obj);
    System.out.println("INSERT HASHT!: ");

    Integer hash_key = this.hash(obj.hashCode());
    IList<Integer> list_key = null;

    if ((list_key = ((HashList<IList<Integer>>)this.major).getByKey(hash_key)) == null){
      ((HashList<IList<Integer>>)this.major).addTail(hash_key, new HashList(hash_key));
      list_key = ((HashList<IList<Integer>>)this.major).getTail().getValue();
    }

    ((HashList<Integer>)list_key).addTail(this.size, obj.hashCode());

    this.size++;
    this.length++;

    return true;
  }

  @Override
  public boolean delete(E obj){
    Integer key = this.hash(obj);
    IList<Integer> node = null;

    if ((node = ((HashList<IList<Integer>>)this.major).getByKey(key)) != null){
      this.dom.set(((HashList<Integer>)node).searchKey(obj.hashCode()), null);
      ((HashList<Integer>)node).delEl(obj.hashCode());

      if (node.getSize() <= 0)
        this.major.delEl(node);

      this.length--;
      return true;
    }

    return false;
  }

  @Override
  public boolean search(E obj){
    Integer key = this.hash(obj);
    IList<Integer> node = null;

    if ((node = ((HashList<IList<Integer>>)this.major).getByKey(key)) != null){
      // System.out.println("Node: " + node);
      Integer el_key = ((HashList<Integer>)node).searchKey(obj.hashCode());
      // System.out.println("Key: " + el_key);
      if (el_key != null)
        return this.dom.get(el_key) != null ? true : false;

    }

    return false;
  }

  @Override
  public boolean update(E obj){
    return true;
  }

  public void print (){
    System.out.println("Length: " + this.dom.length);
    E temp = null;
    for (int i = 0; i < this.dom.length; i++){
      // System.out.println("i: " + i);
      // System.out.println("Node: " + this.dom[i]);
      // System.out.println("KEY => " + this.dom[i].getKey());
      // System.out.println("VALUE => " + this.dom[i].getValue() );
      if ((temp = this.dom.get(i)) != null){
        System.out.println("VALUE => " + this.dom.get(i));
      }
    }

    this.major.print();
  }

  public Array<E> toArray() {
    if (this.length < 0)
      return null;

    final Array<E> array = new Array<E>(this.length);
    int i = 0;
    System.out.println("BRUH: " + i);
    HashNode<IList<Integer>> damn = (HashNode<IList<Integer>>)((HashList<IList<Integer>>)this.major).getHead();
    System.out.println("DAMN: " + damn);

    for (HashNode<IList<Integer>> iter = (HashNode<IList<Integer>>)((HashList<IList<Integer>>)this.major).getHead(); iter != null; iter = (HashNode<IList<Integer>>)iter.getNext()){
      System.out.println("ITER KEY: " + iter.getKey());
      for (HashNode<Integer> nodeIter = (HashNode<Integer>)((HashList<Integer>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (HashNode<Integer>)nodeIter.getNext(), i++){
        System.out.println("NODEITER KEY: " + nodeIter.getKey());
        if (nodeIter.getKey() != null && nodeIter.getValue() != null)
          array.set(i, this.dom.get(nodeIter.getKey()));
      }
    }

    return array;
  }

  public E[] toPrimitive() {
    if (this.length < 0)
      return null;

    final Array<E> array = new Array<E>(this.length);
    int i = 0;
    System.out.println("BRUH: " + i);
    HashNode<IList<Integer>> damn = (HashNode<IList<Integer>>)((HashList<IList<Integer>>)this.major).getHead();
    System.out.println("DAMN: " + damn);

    for (HashNode<IList<Integer>> iter = (HashNode<IList<Integer>>)((HashList<IList<Integer>>)this.major).getHead(); iter != null; iter = (HashNode<IList<Integer>>)iter.getNext()){
      System.out.println("ITER KEY: " + iter.getKey());
      for (HashNode<Integer> nodeIter = (HashNode<Integer>)((HashList<Integer>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (HashNode<Integer>)nodeIter.getNext(), i++){
        System.out.println("NODEITER KEY: " + nodeIter.getKey());
        if (nodeIter.getKey() != null && nodeIter.getValue() != null)
          array.set(i, this.dom.get(nodeIter.getKey()));
      }
    }

    return array;
  }

  protected class HashNode<E extends Comparable<E>> extends Node2<E>{
    protected Integer key;

    public HashNode(){
      super();
      this.key = -1;
    }

    public HashNode(int k, E v){
      super(v);
      this.key = k;
    }

    public HashNode(int k, E v, HashNode<E> n){
      super(v, n);
      this.key = k;
    }

    public Integer getKey() { return this.key; }

    public void setKey (Integer k) { this.key = k; }

    @Override
    public void print(){
      // if (this.value != null)
      System.out.println("HashNode: KEY => " + this.key + " VALUE => " + this.value);
    }

    @Override
    public String toString(){
      return "HashNode: KEY => " + this.key + " VALUE => " + this.value;
    }
  }

  protected class HashList<E extends Comparable<E>> extends List<E>{
    protected Integer key;

    public HashList (){
      this.head = null;
      this.tail = null;
      this.size = 0;
      this.key = null;
    }

    public HashList (Integer k, Integer el_key, E el){
      this.head = new HashNode<E>(el_key, el);
      this.tail = this.head;
      this.size = 1;
      this.key = k;
    }

    public HashList (Integer k){
      this.head = null;
      this.tail = null;
      this.size = 0;
      this.key = k;
    }

    public HashList (HashList<E> shallow){
      this.head = (HashNode<E>)shallow.getHead();
      this.tail = (HashNode<E>)shallow.getTail();
      this.size = shallow.getSize();
      this.key = shallow.getKey();
    }

    @Override
    public String toString(){
      this.print();
      return "HashList: KEY => " + this.key + " HEAD => " + this.head;
    }

    public Integer getKey() { return this.key; }

    public void setKey (Integer k) { this.key = k; }

    public E getByKey (Integer k){
      if (this.size <= 0)
        return null;

      for (HashNode<E> iter = (HashNode<E>)this.head; iter != null; iter = (HashNode<E>)iter.getNext())
        if (iter.getKey() == k)
          return iter.getValue();

      return null;
    }

    public boolean delByKey (Integer k){
      if (this.size <= 0)
        return false;

      if (k == ((HashNode<E>)this.head).getKey()){
        this.delHead();
        return true;
      }
      else if (k == ((HashNode<E>)this.tail).getKey()){
        this.delTail();
        return true;
      }

      if((HashNode<E>)this.head.getNext() == null)
        return false;

      int i = 0;
      for (HashNode<E> prev = (HashNode<E>)this.head, iter = (HashNode<E>)this.head.getNext(); iter.getNext() != null; iter = (HashNode<E>)iter.getNext(), prev = (HashNode<E>)prev.getNext()){
        if (iter.getKey() == k){
          prev.setNext(iter.getNext());
          iter = null;
          this.size--;
          return true;
        }
      }

      return false;
    }

    public Integer searchKey (E el){
      if (this.size <= 0)
        return null;

      if (el.compareTo(this.head.getValue()) == 0)
        return ((HashNode<E>)this.head).getKey();
      else if (el.compareTo(this.tail.getValue()) == 0)
        return ((HashNode<E>)this.tail).getKey();

      if((HashNode<E>)this.head.getNext() == null)
        return null;

      int i = 1;
      for (HashNode<E> iter = (HashNode<E>)this.head.getNext(); iter.getNext() != null && i < this.size; iter = (HashNode<E>)iter.getNext(), i++)
        if (el.compareTo(iter.getValue()) == 0)
          return ((HashNode<E>)iter).getKey();

      return null;
    }

    public void addTail (Integer k, E el){
      if (this.size <= 0){
        this.head = new HashNode<E>(k, el);
        this.tail = this.head;
        this.size = 1;
        return;
      }

      HashNode<E> temp = new HashNode<E>(k, el);
      this.tail.setNext(temp);
      this.tail = temp;
      this.size++;
    }

    public void addHead (Integer k, E el){
      if (this.size <= 0){
        this.head = new HashNode<E>(k, el);
        this.tail = this.head;
        this.size = 1;
        return;
      }

      HashNode<E> temp = new HashNode<E>(k, el);
      temp.setNext(this.head);
      this.head = temp;
      this.size++;
    }
  }
}
