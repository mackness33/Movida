package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node2;
import movida.mackseverini.List;
import movida.mackseverini.Array;
import movida.mackseverini.Set;
import movida.commons.Movie;
import movida.commons.Person;


public class Hash2<E extends Comparable<E>> implements movida.mackseverini.IHash<E> {
  protected Array<E> dom;                   // Array with all the elements
  protected IList<IList<Integer>> major;    // List of list that virtually operates as the hash
  protected int MAX_LENGTH = 100;
  protected int size;                       // Max position occupied in the array
  protected int length;                     // Counts the num of the elements currently in the hash


  @SuppressWarnings("unchecked")
  public Hash2() {
    this.size = 0;
    this.length = 0;
    this.dom = new Array<E> (this.MAX_LENGTH);
    this.major = new KeyList<IList<Integer>>();

    for (int i = 0; i < this.dom.length; i++)
      this.dom.set(i, null);
  }

  protected int getSize() { return this.size; }
  protected int getLength() { return this.length; }


  // reset of all the data structure
  @Override
  public void reset (){
    this.major.reset();

    for (int i = 0; i < this.dom.length; i++)
      this.dom.set(i, null);
  }

  // hash of all the useful types of class
  protected <K> Integer hash (K input){

    if (input instanceof Hash2.Year)
      return Math.abs(((Year)input).year + 79) % this.MAX_LENGTH;     // +79 because we this offset I can properly sort the years without using the whole number
    else if (input instanceof Integer)
      return Math.abs((Integer)input) % this.MAX_LENGTH;
    else if (input instanceof String)
      return Math.abs(((String)input).codePointAt(0)) % this.MAX_LENGTH;    // get the integer version of the first character of the input string
    else if (input instanceof Movie)
      return Math.abs(((Movie)input).getTitle().codePointAt(0)) % this.MAX_LENGTH;
    else if (input instanceof Person)
      return Math.abs(((Person)input).getName().codePointAt(0)) % this.MAX_LENGTH;

    // last possibility is to get the hashCode of the element
    return input.hashCode() % this.MAX_LENGTH;
  }

  // insert of a element. A lot similar to KeyHash.addHashKey(..)
  @Override
  public boolean insert(E obj){
    this.dom.set(this.size, obj);

    Integer hash_key = this.hash(obj);
    IList<Integer> list_key = null;

    // check if the list of the hashed value of the key in input already exist
    if ((list_key = ((KeyList<IList<Integer>>)this.major).getByKey(hash_key)) == null){
      // if not create it
      ((KeyList<IList<Integer>>)this.major).addTail(hash_key, new KeyList(hash_key));
      list_key = ((KeyList<IList<Integer>>)this.major).getTail().getValue();
    }

    // else add at the end
    ((KeyList<Integer>)list_key).addTail(this.size, obj.hashCode());

    this.size++;
    this.length++;

    return true;
  }

  // delete of a element. A lot similar to KeyHash.delHashKey(..)
  @Override
  public boolean delete(E obj){
    Integer key = this.hash(obj);
    IList<Integer> node = null;

    // check if the list of the hashed value of the key in input already exist
    if ((node = ((KeyList<IList<Integer>>)this.major).getByKey(key)) != null){
      // set to null the element in the dom (array that stores all the element)
      this.dom.set(((KeyList<Integer>)node).searchKey(obj.hashCode()), null);
      // delete the node by knowing the hashCode
      ((KeyList<Integer>)node).delEl(obj.hashCode());

      // delete the whole list if it's empty
      if (node.getSize() <= 0)
        this.major.delEl(node);

      // SIZE not decrement because it's used only to easy stored the first MAX_LENGTH element without having too much problem
      this.length--;
      return true;
    }

    return false;
  }

  // search of the element
  @Override
  public boolean search(E obj){
    Integer key = this.hash(obj);
    IList<Integer> node = null;

    // check if the list of the hashed value of the key in input already exist
    if ((node = ((KeyList<IList<Integer>>)this.major).getByKey(key)) != null){
      Integer el_key = ((KeyList<Integer>)node).searchKey(obj.hashCode());
      if (el_key != null)
        // return false if the element has been deleted or it's not present
        return this.dom.get(el_key) != null ? true : false;
    }

    return false;
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

  // sort the virtual hash first by the hashed value, and the internal list by the values
  protected <K extends Comparable<K>> IList<IList<K>> sortListOfList(IAlg algorithm, IList<IList<K>> list){
    list = algorithm.keySort((IKeyList)list);     // sort by keys

    // sort by value for each list
    for (INode2<IList<K>> iter = list.getHead(); iter != null; iter = iter.getNext())
      iter.setValue(algorithm.sort((IKeyList<K>)iter.getValue()));

    return list;
  }

  // sort of the major virtual hash
  public void sort(IAlg algorithm){ this.major = this.sortListOfList(algorithm, this.major); }

  // conversion to array
  @Override
  public Array<E> toArray() {
    if (this.length < 0)
      return null;

    final Array<E> array = new Array<E>(this.length);
    int i = 0;

    // add each node of the hash to the array
    for (IKeyNode<IList<Integer>> iter = (KeyNode<IList<Integer>>)((KeyList<IList<Integer>>)this.major).getHead(); iter != null; iter = (KeyNode<IList<Integer>>)iter.getNext())
      for (KeyNode<Integer> nodeIter = (KeyNode<Integer>)((KeyList<Integer>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (KeyNode<Integer>)nodeIter.getNext(), i++)
        if (nodeIter.getKey() != null && nodeIter.getValue() != null)
          array.set(i, this.dom.get(nodeIter.getKey()));

    return array;
  }

  // Special class for defining a new object Year.
  // Created because we want to differs Integers from years so we hashed it in a different way
  protected class Year implements Comparable<Year>{
    public Integer year;

    public Year(Integer y){ this.year = y; }

    @Override
    public int compareTo(Year y){ return this.year.compareTo(y.year); }

    @Override
    public String toString(){ return Integer.toString(this.year); }
  }
}
