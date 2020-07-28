package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node2;
import movida.mackseverini.Array;
import movida.mackseverini.Set;
import movida.mackseverini.Hash2;

import movida.commons.Movie;
import movida.commons.Person;

// BUG: Comparable cannot be used.
// SOLUTION: Comparable cannot be used.
public class MovieHash<E extends Movie> extends Hash2<Movie> {
  // protected Set<String> casts;
  // protected Set<String> directors;
  // protected Set<Integer> dates;
  // protected Set<Integer> rates;
  protected IList<IList<Integer>> dates;
  protected IList<IList<String>> major;
  // protected IList<Integer> rates;

  // constructor residesHash<Movie>
  @SuppressWarnings("unchecked")
  public MovieHash() {
    super();

    // this.casts = new Set<String> ();
    // this.directors = new Set<String> ();
    // this.dates = new Set<Integer> ();
    // this.rates = new Set<Integer> ();
    // for (int i = 0; i < this.sets.length; i++)
    this.dates = new HashList<IList<Integer>>();
    this.major = new HashList<IList<String>>();
    //   this.sets.set(i, new Set<String>(i, null));
  }


  @Override
  public boolean insert(Movie obj){
    this.dom.set(this.size, obj);

    this.addHashKey(obj.getTitle(), this.major);
    this.addHashKey(obj.getYear(), this.dates);

    this.size++;
    this.length++;

    return true;
  }

  @Override
  public boolean delete(Movie obj){
    System.out.println("SHUT THE DELETE UP!: ");

    Integer hash_key = this.hash(obj.getTitle());
    IList<String> node = null;

    if ((node = ((HashList<IList<String>>)this.major).getByKey(hash_key)) != null){
      this.dom.set(((HashList<String>)node).searchKey(obj.getTitle()), null);
      ((HashList<String>)node).delEl(obj.getTitle());

      if (node.getSize() <= 0)
        this.major.delEl(node);

    }
    else
      return false;

    this.delHashKey(obj.getYear(), this.dates);
    // this.delHashKey(obj.getYear(), this.dates);

    this.length--;

    return true;
  }

  public void reset (){
    this.major.reset();
    this.dates.reset();
    super.reset();
  }

  public boolean delete(String title){
    System.out.println("SHUT THE DELETE UP!: ");

    Integer hash_key = this.hash(title);
    IList<String> node = null;
    Movie movie_to_be_deleted = null;

    if ((node = ((HashList<IList<String>>)this.major).getByKey(hash_key)) != null){
      Integer pos_el =((HashList<String>)node).searchKey(title);
      movie_to_be_deleted = this.dom.get(pos_el);
      this.dom.set(pos_el, null);
      node.delEl(title);

      if (node.getSize() <= 0)
        this.major.delEl(node);

    }
    else
      return false;

    this.delHashKey(movie_to_be_deleted.getYear(), this.dates);
    // this.delHashKey(obj.getYear(), this.dates);

    System.out.println("BEFORE MOVIE LENGTH!: " + this.length);

    this.length--;

    System.out.println("BEFORE MOVIE LENGTH!: " + this.length);

    return true;
  }

  public void print (){
    this.major.print();
    System.out.println("YEAR!: ");
    this.dates.print();
  }

  // TRUE => UPDATE!!
  // FALSE => INSERT!!
  public boolean upsert(Movie obj){
    Integer key = this.hash(obj.getTitle());
    IList<String> node = null;

    System.out.println("UPSERT!: ");

    if ((node = ((HashList<IList<String>>)this.major).getByKey(key)) != null){
      Integer el_key = ((HashList<String>)node).searchKey(obj.getTitle());
      if (el_key != null){
        this.dom.set(el_key, obj);
        return true;
      }
    }
    else{
      ((HashList<IList<String>>)this.major).addTail(key, new HashList(key));
      node = ((HashList<IList<String>>)this.major).getTail().getValue();
    }

    this.dom.set(this.size, obj);
    ((HashList<String>)node).addTail(this.size, obj.getTitle());

    this.addHashKey(obj.getYear(), this.dates);

    this.size++;
    this.length++;

    return false;
  }

  protected <K extends Comparable<K>> boolean addHashKey(K key, IList<IList<K>> list){
    Integer hash_key = this.hash(key);
    IList<K> list_key = null;

    if ((list_key = ((HashList<IList<K>>)list).getByKey(hash_key)) == null){
      ((HashList<IList<K>>)list).addTail(hash_key, new HashList(hash_key));
      list_key = ((HashList<IList<K>>)list).getTail().getValue();
    }

    ((HashList<K>)list_key).addTail(this.size, key);
    return true;
  }

  protected <K extends Comparable<K>> boolean delHashKey(K key, IList<IList<K>> list){
    Integer hash_key = this.hash(key);
    IList<K> node = null;

    if ((node = ((HashList<IList<K>>)list).getByKey(hash_key)) != null){
      node.delEl(key);

      if (node.getSize() <= 0)
        list.delEl(node);

      return true;
    }

    return false;
  }


  public Movie search(String title){
    Integer key = this.hash(title);
    IList<String> node = null;

    if ((node = ((HashList<IList<String>>)this.major).getByKey(key)) != null){
      // System.out.println("Node: " + node);
      Integer el_key = ((HashList<String>)node).searchKey(title);
      // System.out.println("Key: " + el_key);
      if (el_key != null)
        return this.dom.get(el_key);
    }

    return null;
  }

  public <K extends Comparable<K>> Movie[] searchByKey(K input){
    IList<Movie> out = null;
    Array<Movie> transition_array = null;

    if (input instanceof Integer)
      out = this.genericSearchByKey((Integer)input, dates);
    // elseif (input instanceof String)
    //   out = genericSearchByKey(new Person(input), directors);

    return (out != null) ?  listToPrimitive(out) : null;
  }

  protected <K extends Comparable<K>> IList<Movie> genericSearchByKey(K input, IList<IList<K>> key_hash){
    Integer key = this.hash(input);
    IList<K> node = null;
    IList<Movie> output = new HashList<Movie>();


    if ((node = ((HashList<IList<K>>)key_hash).getByKey(key)) != null)
      // System.out.println("Node: " + node);
      for (HashNode<K> iter = (HashNode<K>)node.getHead(); iter != null; iter = (HashNode<K>)iter.getNext())
        // System.out.println("Iter: " + iter);
        if (input.compareTo(iter.getValue()) == 0)
          // System.out.println("Key: " + iter.getKey());
          output.addTail(this.dom.get(iter.getKey()));


    return (output.getSize() <= 0) ? null : output;
  }

  @Override
  public Array<Movie> toArray() {
    if (this.length < 0)
      return null;

    final Array<Movie> array = new Array<Movie>(this.length);
    int i = 0;
    System.out.println("BRUH: " + i);
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
  public Movie[] toPrimitive() {
    if (this.length < 0)
      return null;

    final Movie[] array = new Movie[this.length];
    int i = 0;
    System.out.println("BRUH: " + i);
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

  protected Movie[] listToPrimitive (IList<Movie> list){
    Movie[] prim = new Movie[list.getSize()];

    int i = 0;
    for (INode2<Movie> iter = list.getHead(); iter != null; iter = iter.getNext(), i++)
      prim[i] = iter.getValue();

    return prim;
  }
}
