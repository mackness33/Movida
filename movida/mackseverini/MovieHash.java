
package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node2;
import movida.mackseverini.Array;
import movida.mackseverini.Set;
import movida.mackseverini.Hash2;
import movida.mackseverini.Utils;
import movida.mackseverini.KeyHash;

import movida.commons.Movie;
import movida.commons.Person;

// BUG: Comparable cannot be used.
// SOLUTION: Comparable cannot be used.
public class MovieHash<E extends Movie> extends KeyHash<Movie> {
  protected IList<Integer> rates;
  protected IList<IList<String>> major;
  protected IList<IList<Integer>> dates;
  protected IList<IList<String>> directors;

  // constructor residesHash<Movie>
  @SuppressWarnings("unchecked")
  public MovieHash() {
    super();

    this.dates = new HashList<IList<Integer>>();
    this.rates = new HashList<Integer>();
    this.major = new HashList<IList<String>>();
    this.directors = new HashList<IList<String>>();
  }


  @Override
  public boolean insert(Movie obj){
    this.dom.set(this.size, obj);

    this.addHashKey(obj.getTitle(), this.major);
    this.addHashKey(obj.getYear(), this.dates);
    this.addHashKey(obj.getDirector().getName(), this.directors);
    ((HashList<Integer>)this.rates).addTail(this.size, obj.getVotes());

    this.size++;
    this.length++;

    return true;
  }

  @Override
  public boolean delete(Movie obj){
    System.out.println("SHUT THE DELETE UP!: ");

    Integer hash_key = this.hash(obj.getTitle());
    IList<String> node = null;
    Integer pos = -1;

    if ((node = ((HashList<IList<String>>)this.major).getByKey(hash_key)) != null){
      pos = ((HashList<String>)node).searchKey(obj.getTitle());
      this.dom.set(pos, null);
      ((HashList<String>)node).delEl(obj.getTitle());

      if (node.getSize() <= 0)
        this.major.delEl(node);
    }
    else
      return false;

    this.delHashKey(obj.getYear(), this.dates, pos);
    this.delHashKey(obj.getDirector().getName(), this.directors, pos);
    ((HashList<Integer>)this.rates).delByKey(pos);

    this.length--;

    return true;
  }

  public void reset (){
    this.major.reset();
    this.dates.reset();
    this.directors.reset();
    this.rates.reset();
    super.reset();
  }

  public boolean delete(String title){
    Integer hash_key = this.hash(title), pos = 0;
    IList<String> node = null;
    Movie movie_to_be_deleted = null;

    if ((node = ((HashList<IList<String>>)this.major).getByKey(hash_key)) != null){
      pos = ((HashList<String>)node).searchKey(title);
      movie_to_be_deleted = this.dom.get(pos);
      this.dom.set(pos, null);
      node.delEl(title);

      if (node.getSize() <= 0)
        this.major.delEl(node);

    }
    else
      return false;

    this.delHashKey(movie_to_be_deleted.getYear(), this.dates, pos);
    this.delHashKey(movie_to_be_deleted.getDirector().getName(), this.directors, pos);
    ((HashList<Integer>)this.rates).delByKey(pos);

    this.length--;

    return true;
  }

  public void print (){
    this.major.printAll();
    System.out.println("YEAR!: ");
    this.dates.printAll();
    System.out.println("DIRECTOR!: ");
    this.directors.printAll();
    System.out.println("VOTES!: ");
    this.rates.printAll();
  }

  // TRUE => UPDATE!!
  // FALSE => INSERT!!
  public int upsert(Movie obj){
    Integer key = this.hash(obj.getTitle());
    IList<String> node = null;

    if ((node = ((HashList<IList<String>>)this.major).getByKey(key)) != null){
      Integer el_key = ((HashList<String>)node).searchKey(obj.getTitle());
      if (el_key != null){
        this.dom.set(el_key, obj);
        return el_key;
      }
    }
    else{
      ((HashList<IList<String>>)this.major).addTail(key, new HashList(key));
      node = ((HashList<IList<String>>)this.major).getTail().getValue();
    }

    this.dom.set(this.size, obj);
    ((HashList<String>)node).addTail(this.size, obj.getTitle());

    this.addHashKey(obj.getYear(), this.dates);
    this.addHashKey(obj.getDirector().getName(), this.directors);
    ((HashList<Integer>)this.rates).addTail(this.size, obj.getVotes());

    this.size++;
    this.length++;

    return this.size-1;
  }

  public Movie search(String title){
    Integer key = this.hash(title);
    IList<String> node = null;

    if ((node = ((HashList<IList<String>>)this.major).getByKey(key)) != null){
      Integer el_key = ((HashList<String>)node).searchKey(title);
      if (el_key != null)
        return this.dom.get(el_key);
    }

    return null;
  }

  public <K extends Comparable<K>> Movie[] searchByKey(K input){
    IList<Movie> out = null;

    if (input instanceof Integer)
      out = this.searchByHashKey((Integer)input, dates);
    else if (input instanceof String)
      out = this.searchByHashKey((String)input, directors);

    return (out != null) ?  this.listToPrimitive(out) : null;
  }

  public Movie[] searchMostOf(Integer num, String type){
    IList<Movie> out = null;

    switch (type.toLowerCase()){
      case "votes": out = this.searchMostOfHashKey(num, this.rates);break;
      case "year": {
        int i = 0;
        if (this.length > 0){
          out = new List<Movie>();

          for (HashNode<IList<Integer>> iter = (HashNode<IList<Integer>>)this.dates.getHead(); iter != null && i < num; iter = (HashNode<IList<Integer>>)iter.getNext(), i = out.getSize())
            out.addToEnd(this.searchMostOfHashKey(num, iter.getValue()));
        }
      };break;
      default: System.out.println("Wrong input");
    }

    return (out != null && out.getSize() > 0) ?  this.listToPrimitive(out) : null;
  }

  public <K extends Comparable<K>> Movie[] searchContains(String title){
    IList<Movie> out = null;

    out = this.searchContainsHashKey(title, this.major);

    return (out != null) ?  this.listToPrimitive(out) : null;
  }

  public Movie getFromId (Integer id){ return (id > this.size || id < 0 ) ? null : this.dom.get(id); }

  @Override
  public Array<Movie> toArray() {
    if (this.length < 0)
      return null;

    final Array<Movie> array = new Array<Movie>(this.length);
    int i = 0;

    for (HashNode<IList<String>> iter = (HashNode<IList<String>>)((HashList<IList<String>>)this.major).getHead(); iter != null; iter = (HashNode<IList<String>>)iter.getNext())
      for (HashNode<String> nodeIter = (HashNode<String>)((HashList<String>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (HashNode<String>)nodeIter.getNext(), i++)
        if (nodeIter.getKey() != null && nodeIter.getValue() != null)
          array.set(i, this.dom.get(nodeIter.getKey()));

    return array;
  }

  @Override
  public Movie[] toPrimitive() {
    if (this.length < 0)
      return null;

    final Movie[] array = new Movie[this.length];
    int i = 0;

    for (HashNode<IList<String>> iter = (HashNode<IList<String>>)((HashList<IList<String>>)this.major).getHead(); iter != null; iter = (HashNode<IList<String>>)iter.getNext())
      for (HashNode<String> nodeIter = (HashNode<String>)((HashList<String>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (HashNode<String>)nodeIter.getNext(), i++)
        if (nodeIter.getKey() != null && nodeIter.getValue() != null)
          array[i] = this.dom.get(nodeIter.getKey());

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
