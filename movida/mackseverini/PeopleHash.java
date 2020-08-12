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
    this.major = new KeyList<IList<String>>();
    this.active = new KeyList<Integer>();
  }

  @Override
  // insert of a element in the main hash and the keys hash/array
  public boolean insert(Person obj){
    if (obj == null)
      return false;

    this.dom.set(this.size, obj);
    // add to main
    this.addHashKey(obj.getName(), this.major);
    // add keys
    ((KeyList<Integer>)this.active).addTail(this.size, obj.getMovieSize());


    this.size++;
    this.length++;

    return true;
  }

  // TRUE => UPDATE!!
  // FALSE => INSERT!!
  @Override
  // update the element if it does already exist else it normally insert it
  public boolean upsert(Person obj, Integer movie){
    Integer key = this.hash(obj.getName());
    IList<String> node = null;

    // check if the list of the hashed key exist
    if ((node = ((KeyList<IList<String>>)this.major).getByKey(key)) != null){
      Integer el_key = ((KeyList<String>)node).searchKey(obj.getName());
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
    Person movie_to_be_deleted = null;

    // check if the list of the hashed key exist
    if ((node = ((KeyList<IList<String>>)this.major).getByKey(hash_key)) == null)
      return false;

    // get the position of the element in the main array of element
    pos = ((KeyList<String>)node).searchKey(name);

    // delete the element in the main and hashed hashes
    this.dom.set(pos, null);
    node.delEl(name);
    ((KeyList<Integer>)this.active).delByKey(pos);

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
    if ((node = ((KeyList<IList<String>>)this.major).getByKey(key)) != null){
      Integer el_key = ((KeyList<String>)node).searchKey(name);
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

    // since the only possibile search it's done with the active list
    // it is the only one implemented
    // iterate all the list till it arrives to N elements
    for (KeyNode<Integer> iter = (KeyNode<Integer>)this.active.getHead(); iter != null && i < num; iter = (KeyNode<Integer>)iter.getNext()){
      if (this.dom.get(iter.getKey()).isActor()){
        out.addTail(this.dom.get(iter.getKey()));
        i++;
      }
    }

    return (out != null && out.getSize() > 0) ?  this.listToPrimitive(out) : null;
  }

  @Override
  // sort all the hashes
  public void sort(IAlg algorithm){
    this.updateActive();

    this.major = this.sortListOfList(algorithm, this.major);
    this.active = algorithm.sort(this.active);
  }

  // update active infos
  protected void updateActive (){
    for (IKeyNode<Integer> iter = (IKeyNode<Integer>)this.active.getHead(); iter != null; iter = (IKeyNode<Integer>)iter.getNext())
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
    for (KeyNode<IList<String>> iter = (KeyNode<IList<String>>)((KeyList<IList<String>>)this.major).getHead(); iter != null; iter = (KeyNode<IList<String>>)iter.getNext())
      for (KeyNode<String> nodeIter = (KeyNode<String>)((KeyList<String>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (KeyNode<String>)nodeIter.getNext(), i++)
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
    for (KeyNode<IList<String>> iter = (KeyNode<IList<String>>)((KeyList<IList<String>>)this.major).getHead(); iter != null; iter = (KeyNode<IList<String>>)iter.getNext())
      for (KeyNode<String> nodeIter = (KeyNode<String>)((KeyList<String>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (KeyNode<String>)nodeIter.getNext(), i++)
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
