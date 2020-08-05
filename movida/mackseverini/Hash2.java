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

    this.major = new KeyList<IList<Integer>>();
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

    Integer hash_key = this.hash(obj.hashCode());
    IList<Integer> list_key = null;

    if ((list_key = ((KeyList<IList<Integer>>)this.major).getByKey(hash_key)) == null){
      ((KeyList<IList<Integer>>)this.major).addTail(hash_key, new KeyList(hash_key));
      list_key = ((KeyList<IList<Integer>>)this.major).getTail().getValue();
    }

    ((KeyList<Integer>)list_key).addTail(this.size, obj.hashCode());

    this.size++;
    this.length++;

    return true;
  }

  @Override
  public boolean delete(E obj){
    Integer key = this.hash(obj);
    IList<Integer> node = null;

    if ((node = ((KeyList<IList<Integer>>)this.major).getByKey(key)) != null){
      this.dom.set(((KeyList<Integer>)node).searchKey(obj.hashCode()), null);
      ((KeyList<Integer>)node).delEl(obj.hashCode());

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

    if ((node = ((KeyList<IList<Integer>>)this.major).getByKey(key)) != null){
      Integer el_key = ((KeyList<Integer>)node).searchKey(obj.hashCode());
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

    for (int i = 0; i < this.dom.length; i++)
      if ((temp = this.dom.get(i)) != null)
        System.out.println("VALUE => " + this.dom.get(i));

    this.major.print();
  }

  public Array<E> toArray() {
    if (this.length < 0)
      return null;

    final Array<E> array = new Array<E>(this.length);
    int i = 0;

    for (KeyNode<IList<Integer>> iter = (KeyNode<IList<Integer>>)((KeyList<IList<Integer>>)this.major).getHead(); iter != null; iter = (KeyNode<IList<Integer>>)iter.getNext())
      for (KeyNode<Integer> nodeIter = (KeyNode<Integer>)((KeyList<Integer>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (KeyNode<Integer>)nodeIter.getNext(), i++)
        if (nodeIter.getKey() != null && nodeIter.getValue() != null)
          array.set(i, this.dom.get(nodeIter.getKey()));

    return array;
  }

}
