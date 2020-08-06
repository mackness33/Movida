package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node2;
import movida.mackseverini.Array;
import movida.mackseverini.Set;
import movida.mackseverini.Hash2;
import movida.mackseverini.KeyHash;

import movida.commons.Movie;
import movida.commons.Person;

// BUG: Comparable cannot be used.
// SOLUTION: Comparable cannot be used.
public class PeopleHash<E extends Person> extends KeyHash<Person> {
  protected IList<IList<String>> major;
  protected IList<Integer> active;

  @SuppressWarnings("unchecked")
  public PeopleHash() {
    super();
    this.major = new KeyList<IList<String>>();
    this.active = new KeyList<Integer>();
  }

  public boolean insert(Person obj){
    this.dom.set(this.size, obj);
    this.addHashKey(obj.getName(), this.major);
    ((KeyList<Integer>)this.active).addTail(this.size, obj.getMovieSize());


    this.size++;
    this.length++;

    return true;
  }

  // TRUE => UPDATE!!
  // FALSE => INSERT!!
  public boolean upsert(Person obj, Integer movie){
    Integer key = this.hash(obj.getName());
    IList<String> node = null;

    if ((node = ((KeyList<IList<String>>)this.major).getByKey(key)) != null){
      Integer el_key = ((KeyList<String>)node).searchKey(obj.getName());
      if (el_key != null){
        // this.dom.get(el_key).print();
        this.dom.get(el_key).addMovie(movie);
        return true;
      }
    }

    this.insert(obj);

    return false;
  }

  @Override
  public boolean delete(Person obj){ return this.delete(obj.getName()); }

  public boolean delete(String name){

    Integer hash_key = this.hash(name), pos = 0;
    IList<String> node = null;
    Person movie_to_be_deleted = null;

    if ((node = ((KeyList<IList<String>>)this.major).getByKey(hash_key)) != null){
      pos = ((KeyList<String>)node).searchKey(name);
      this.dom.set(pos, null);
      node.delEl(name);

      if (node.getSize() <= 0)
        this.major.delEl(node);

      this.length--;
      return true;
    }

    ((KeyList<Integer>)this.active).delByKey(pos);

    return false;
  }

  public void print (){
    this.major.printAll();
    System.out.println("ACTIVE: ");
    this.active.printAll();
  }

  public Person search(String name){
    Integer key = this.hash(name);
    IList<String> node = null;

    if ((node = ((KeyList<IList<String>>)this.major).getByKey(key)) != null){
      Integer el_key = ((KeyList<String>)node).searchKey(name);
      if (el_key != null)
        return this.dom.get(el_key);
    }

    return null;
  }

  public void reset (){
    this.major.reset();
    this.active.reset();
    super.reset();
  }

  public Person[] searchMostOf(Integer num){
    IList<Person> out = new List<Person>();
    int i = 0;

    for (KeyNode<Integer> iter = (KeyNode<Integer>)this.active.getHead(); iter != null && i < num; iter = (KeyNode<Integer>)iter.getNext()){
      if (this.dom.get(iter.getKey()).isActor()){
        out.addTail(this.dom.get(iter.getKey()));
        i++;
      }
    }

    return (out != null && out.getSize() > 0) ?  this.listToPrimitive(out) : null;
  }

  public void sort(IAlg algorithm){
    for (IKeyNode<Integer> iter = (IKeyNode<Integer>)this.active.getHead(); iter != null; iter = (IKeyNode<Integer>)iter.getNext())
      iter.setValue(((Person)this.dom.get(iter.getKey())).getMovieSize());

    this.major = this.sortListOfList(algorithm, this.major);
    this.active = algorithm.sort(this.active);
  }

  @Override
  public Array<Person> toArray() {
    if (this.length < 0)
      return null;

    final Array<Person> array = new Array<Person>(this.length);
    int i = 0;

    for (KeyNode<IList<String>> iter = (KeyNode<IList<String>>)((KeyList<IList<String>>)this.major).getHead(); iter != null; iter = (KeyNode<IList<String>>)iter.getNext())
      for (KeyNode<String> nodeIter = (KeyNode<String>)((KeyList<String>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (KeyNode<String>)nodeIter.getNext(), i++)
        if (nodeIter.getKey() != null && nodeIter.getValue() != null)
          array.set(i, this.dom.get(nodeIter.getKey()));

    return array;
  }


  public Person[] toPrimitive() {
    if (this.length < 0)
      return null;

    final Person[] array = new Person[this.length];
    int i = 0;

    for (KeyNode<IList<String>> iter = (KeyNode<IList<String>>)((KeyList<IList<String>>)this.major).getHead(); iter != null; iter = (KeyNode<IList<String>>)iter.getNext())
      for (KeyNode<String> nodeIter = (KeyNode<String>)((KeyList<String>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (KeyNode<String>)nodeIter.getNext(), i++)
        if (nodeIter.getKey() != null && nodeIter.getValue() != null)
          array[i] = this.dom.get(nodeIter.getKey());

    return array;
  }

  protected Person[] listToPrimitive (IList<Person> list){
    Person[] prim = new Person[list.getSize()];

    int i = 0;
    for (INode2<Person> iter = list.getHead(); iter != null; iter = iter.getNext(), i++)
      prim[i] = iter.getValue();

    return prim;
  }

}
