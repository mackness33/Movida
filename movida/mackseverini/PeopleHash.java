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
    this.major = new HashList<IList<String>>();
  }

  public boolean insert(Person obj, Integer movie){
    this.dom.set(this.size, obj);
    // this.dom.get(this.size).addMovie(movie);
    System.out.println("IN INSERT");
    System.out.println("SIZE: " + this.size);
    System.out.println("MOVIE SIZE: " + movie);
    this.dom.get(this.size).print();

    this.addHashKey(obj.getName(), this.major);

    this.size++;
    this.length++;

    return true;
  }

  // TRUE => UPDATE!!
  // FALSE => INSERT!!
  public boolean upsert(Person obj, Integer movie){
    // Person person;
    // if (person = this.search(obj.getName()) == null)
    //   people.insert(new Person(name, type, movies.getSize()));
    // else{
    //   person
    //   System.out.println("ALREADY THERE");
    // }
    //
    // Integer key = this.hash(name);
    // IList<String> node = null;
    //
    // System.out.println("IN SEARCH: ");
    // if ((node = ((HashList<IList<String>>)this.major).getByKey(key)) != null){
    //   System.out.println("Node: " + node);
    //   Integer el_key = ((HashList<String>)node).searchKey(name);
    //   System.out.println("Key: " + el_key);
    //   if ()
    // }

    Integer key = this.hash(obj.getName());
    IList<String> node = null;

    System.out.println("UPSERT!: ");

    if ((node = ((HashList<IList<String>>)this.major).getByKey(key)) != null){
      Integer el_key = ((HashList<String>)node).searchKey(obj.getName());
      if (el_key != null){
        this.dom.get(el_key).print();
        this.dom.get(el_key).addMovie(movie);
        return true;
      }
    }

    this.insert(obj, movie);

    return false;
  }

  @Override
  public boolean delete(Person obj){
    System.out.println("SHUT THE DELETE UP!: ");

    return this.delete(obj.getName());
  }

  public boolean delete(String name){
    System.out.println("SHUT THE DELETE UP!: ");

    Integer hash_key = this.hash(name), pos = 0;
    IList<String> node = null;
    Person movie_to_be_deleted = null;

    if ((node = ((HashList<IList<String>>)this.major).getByKey(hash_key)) != null){
      pos = ((HashList<String>)node).searchKey(name);
      this.dom.set(pos, null);
      node.delEl(name);

      if (node.getSize() <= 0)
        this.major.delEl(node);

      this.length--;
      return true;
    }

    return false;
  }

  public void print (){ this.major.printAll(); }

  public Person search(String name){
    Integer key = this.hash(name);
    IList<String> node = null;

    System.out.println("IN SEARCH: ");
    if ((node = ((HashList<IList<String>>)this.major).getByKey(key)) != null){
      System.out.println("Node: " + node);
      Integer el_key = ((HashList<String>)node).searchKey(name);
      System.out.println("Key: " + el_key);
      if (el_key != null)
        return this.dom.get(el_key);
    }

    return null;
  }

  @Override
  public Array<Person> toArray() {
    if (this.length < 0)
      return null;

    final Array<Person> array = new Array<Person>(this.length);
    int i = 0;
    HashNode<IList<String>> damn = (HashNode<IList<String>>)((HashList<IList<String>>)this.major).getHead();
    System.out.println("DAMN: " + damn);

    for (HashNode<IList<String>> iter = (HashNode<IList<String>>)((HashList<IList<String>>)this.major).getHead(); iter != null; iter = (HashNode<IList<String>>)iter.getNext()){
      System.out.println("ITER KEY: " + iter.getKey());
      for (HashNode<String> nodeIter = (HashNode<String>)((HashList<String>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (HashNode<String>)nodeIter.getNext(), i++){
        System.out.println("NODEITER KEY: " + nodeIter.getKey());
        if (nodeIter.getKey() != null && nodeIter.getValue() != null)
          array.set(i, this.dom.get(nodeIter.getKey()));
      }
    }

    return array;
  }


  @Override
  public Person[] toPrimitive() {
    if (this.length < 0)
      return null;

    final Person[] array = new Person[this.length];
    int i = 0;
    HashNode<IList<String>> damn = (HashNode<IList<String>>)((HashList<IList<String>>)this.major).getHead();
    System.out.println("DAMN: " + damn);

    for (HashNode<IList<String>> iter = (HashNode<IList<String>>)((HashList<IList<String>>)this.major).getHead(); iter != null; iter = (HashNode<IList<String>>)iter.getNext()){
      System.out.println("ITER KEY: " + iter.getKey());
      for (HashNode<String> nodeIter = (HashNode<String>)((HashList<String>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (HashNode<String>)nodeIter.getNext(), i++){
        System.out.println("NODEITER KEY: " + nodeIter.getKey());
        if (nodeIter.getKey() != null && nodeIter.getValue() != null)
          array[i] = this.dom.get(nodeIter.getKey());
      }
    }

    return array;
  }

  protected Person[] listToPrimitive (IList<Person> list){
    Person[] prim = new Person[list.getSize()];

    int i = 0;
    for (INode2<Person> iter = list.getHead(); iter != null; iter = iter.getNext(), i++)
      prim[i] = iter.getValue();

    return prim;
  }

  // public <K extends Comparable<K>> Person[] searchByKey(K input){
  //   IList<Movie> out = null;
  //
  //   if (input instanceof Integer)
  //     out = this.searchByHashKey((Integer)input, dates);
  //   else if (input instanceof String){
  //     System.out.println("ENTERING DIRECTORS");
  //     out = this.searchByHashKey((String)input, directors);
  //   }
  //
  //   System.out.println("Size: " + out.getSize());
  //
  //   return (out != null) ?  this.listToPrimitive(out) : null;
  // }
}
