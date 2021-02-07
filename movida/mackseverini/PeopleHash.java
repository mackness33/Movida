package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node2;
import movida.mackseverini.Array;
import movida.mackseverini.Set;
import movida.mackseverini.Hash2;
import movida.mackseverini.KeyHash;

import movida.commons.Movie;
import movida.commons.Person;

// Class created specially for the Person
public class PeopleHash<E extends Person> extends KeyHash<Person> implements IPersonMap<Person>{
  protected IList<IList<String>> major;
  protected IList<Integer> active;

  @SuppressWarnings("unchecked")
  public PeopleHash() {
    super();
    this.major = new KeyList<IList<String>, Integer, Integer>();
    this.active = new KeyList<Integer, Integer, Integer>();
  }

  @Override
  public final movida.commons.MapImplementation getType(){ return movida.commons.MapImplementation.HashConcatenamento; }

  @Override
  // insert of a element in the main hash and the keys hash/array
  public boolean insert(Person obj){
    if (obj == null)
      return false;

    this.dom.set(this.size, obj);
    // add to main
    this.addHashKey(obj.getName(), this.major);
    // add keys
    ((KeyList<Integer, Integer, Integer>)this.active).addTail(this.size, obj.getMovieSize());


    this.size++;
    this.length++;

    return true;
  }

  // TRUE => UPDATE!!
  // FALSE => INSERT!!
  @Override
  // update the element if it does already exist else it normally insert it
  public boolean upsert(Person obj, Integer movie){
    if (obj == null || movie == null)
      return false;

    Integer key = this.hash(obj.getName());
    IList<String> node = null;

    // check if the list of the hashed key exist
    if ((node = ((KeyList<IList<String>, Integer, Integer>)this.major).getByKey(key)) != null){
      Integer el_key = ((KeyList<String, Integer, Integer>)node).searchKey(obj.getName());
      // if the key of the element in the hash is not null update all the data structures
      if (el_key != null){
        this.dom.get(el_key).addMovie(movie);
        return true;
      }
    }

    // else insert the object
    this.insert(obj);

    return false;
  }

  // @Override
  // delete by the obj. It's the same as the other method "delete"
  public boolean decreaseMovie(Person obj, Integer movie){
    if (obj == null || movie == null)
      return false;

    Integer hash_key = this.hash(obj.getName()), pos = -1;
    IList<String> node = null;

    // check if the list of the hashed key exist
    if ((node = ((KeyList<IList<String>, Integer, Integer>)this.major).getByKey(hash_key)) == null)
      return false;

    // get the position of the element in the main array of element
    pos = ((KeyList<String, Integer, Integer>)node).searchKey(obj.getName());

    if (pos == null || pos == -1)
      return false;

    boolean delmovie = this.dom.get(pos).delMovie(movie);
    System.out.println("deleting: " + this.dom.get(pos).getName() + "\tmovie: " + movie);
    System.out.println("res: " + delmovie);
    System.out.println("sizeListOfMovie: " + this.dom.get(pos).getMovieSize());
    // if(!this.dom.get(pos).delMovie(movie))
    if(!delmovie)
      return false;

    if(this.dom.get(pos).getMovieSize() > 0)
      return true;

    return this.delete(obj.getName());
  }


  @Override
  // delete by the obj. It's the same as the other method "delete"
  public boolean delete(Person obj){
    if (obj == null)
      return false;

    return this.delete(obj.getName());
  }

  @Override
  // delete of a element in the main hash and the keys hash/array
  public boolean delete(String name){
    if (name == null)
      return false;

    Integer hash_key = this.hash(name), pos = 0;
    IList<String> node = null;

    // check if the list of the hashed key exist
    if ((node = ((KeyList<IList<String>, Integer, Integer>)this.major).getByKey(hash_key)) == null)
      return false;

    // get the position of the element in the main array of element
    pos = ((KeyList<String, Integer, Integer>)node).searchKey(name);

    // delete the element in the main and hashed hashes
    this.dom.set(pos, null);
    node.delEl(name);
    ((KeyList<Integer, Integer, Integer>)this.active).delByKey(pos);

    if (node.getSize() <= 0)
      this.major.delEl(node);

    this.length--;

    return true;
  }

  public void print (){
    this.major.printAll();
    System.out.println("ACTIVE: ");
    this.active.printAll();
  }

  @Override
  // search of the element by title
  public Person search(String name){
    Integer key = this.hash(name);
    IList<String> node = null;

    // check if the list of the hashed value of the key in input already exist
    if ((node = ((KeyList<IList<String>, Integer, Integer>)this.major).getByKey(key)) != null){
      Integer el_key = ((KeyList<String, Integer, Integer>)node).searchKey(name);
      if (el_key != null)
        return this.dom.get(el_key);
    }

    return null;
  }

  @Override
  // resetting all the hashes and main array
  public void reset (){
    this.major.reset();
    this.active.reset();
    super.reset();
  }

  @Override
  // get N elements by key in the input
  public Person[] searchMostOf(Integer num){
    IList<Person> out = new List<Person>();
    int i = 0;

    InsertionSort is = new InsertionSort();

    this.updateActive();

    IList<Integer> sorted_list = is.sort(this.active, false);

    // since the only possibile search it's done with the active list
    // it is the only one implemented
    // iterate all the list till it arrives to N elements
    for (KeyNode<Integer, Integer> iter = (KeyNode<Integer, Integer>)sorted_list.getHead(); iter != null && i < num; iter = (KeyNode<Integer, Integer>)iter.getNext()){
      if (this.dom.get(iter.getKey()).isActor()){
        out.addTail(this.dom.get(iter.getKey()));
        i++;
      }
    }

    return (out != null && out.getSize() > 0) ?  this.listToPrimitive(out) : null;
  }

  @Override
  // sort all the hashes
  public void sort(IAlg algorithm, boolean decrescent){
    this.updateActive();

    this.major = this.sortListOfList(algorithm, this.major, decrescent);
    this.active = algorithm.sort(this.active, decrescent);
  }

  // update active infos
  protected void updateActive (){
    for (IKeyNode<Integer, Integer> iter = (IKeyNode<Integer, Integer>)this.active.getHead(); iter != null; iter = (IKeyNode<Integer, Integer>)iter.getNext())
      iter.setValue(((Person)this.dom.get(iter.getKey())).getMovieSize());
  }

  @Override
  // transform in an array object
  public Array<Person> toArray() {
    if (this.length < 0)
      return null;


    final Array<Person> array = new Array<Person>(this.length);
    int i = 0;

    // add all the node list per list
    for (KeyNode<IList<String>, Integer> iter = (KeyNode<IList<String>, Integer>)((KeyList<IList<String>, Integer, Integer>)this.major).getHead(); iter != null; iter = (KeyNode<IList<String>, Integer>)iter.getNext())
      for (KeyNode<String, Integer> nodeIter = (KeyNode<String, Integer>)((KeyList<String, Integer, Integer>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (KeyNode<String, Integer>)nodeIter.getNext(), i++)
        if (nodeIter.getKey() != null && nodeIter.getValue() != null)
          array.set(i, this.dom.get(nodeIter.getKey()));

    return array;
  }


  @Override
  // transform the hash in an primitive array (arr[])
  public Person[] toPrimitive() {
    if (this.length < 0)
      return null;

    final Person[] array = new Person[this.length];
    int i = 0;


    // add all the node list per list
    for (KeyNode<IList<String>, Integer> iter = (KeyNode<IList<String>, Integer>)((KeyList<IList<String>, Integer, Integer>)this.major).getHead(); iter != null; iter = (KeyNode<IList<String>, Integer>)iter.getNext())
      for (KeyNode<String, Integer> nodeIter = (KeyNode<String, Integer>)((KeyList<String, Integer, Integer>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (KeyNode<String, Integer>)nodeIter.getNext(), i++)
        if (nodeIter.getKey() != null && nodeIter.getValue() != null)
          array[i] = this.dom.get(nodeIter.getKey());

    return array;
  }

  // protected method to convet a list of movie into a primitive array
  protected Person[] listToPrimitive (IList<Person> list){
    Person[] prim = new Person[list.getSize()];

    int i = 0;
    for (INode2<Person> iter = list.getHead(); iter != null; iter = iter.getNext(), i++)
      prim[i] = iter.getValue();

    return prim;
  }

}
