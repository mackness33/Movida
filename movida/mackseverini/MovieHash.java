
package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node2;
import movida.mackseverini.Array;
import movida.mackseverini.Set;
import movida.mackseverini.Hash2;
import movida.mackseverini.KeyHash;

import movida.commons.Movie;
import movida.commons.Person;

// Class created specially for the Movies
public class MovieHash<E extends Movie> extends KeyHash<Movie> {
  protected IList<Integer> rates;
  protected IList<IList<String>> major;
  protected IList<IList<Year>> dates;
  protected IList<IList<String>> directors;

  // constructor residesHash<Movie>
  @SuppressWarnings("unchecked")
  public MovieHash() {
    super();

    this.dates = new KeyList<IList<Year>>();
    this.rates = new KeyList<Integer>();
    this.major = new KeyList<IList<String>>();
    this.directors = new KeyList<IList<String>>();
  }

  @Override
  // insert of a element in the main hash and the keys hash/array
  public boolean insert(Movie obj){
    if (obj == null)
      return false;

    this.dom.set(this.size, obj);

    // add to main
    this.addHashKey(obj.getTitle(), this.major);
    // add keys
    this.addHashKey(new Year(obj.getYear()), this.dates);
    this.addHashKey(obj.getDirector().getName(), this.directors);
    ((KeyList<Integer>)this.rates).addTail(this.size, obj.getVotes());

    this.size++;
    this.length++;

    return true;
  }

  @Override
  // delete of a element in the main hash and the keys hash/array
  public boolean delete(Movie obj){
    if (obj == null)
      return false;

    Integer hash_key = this.hash(obj.getTitle());
    IList<String> node = null;
    Integer pos = -1;

    // check if the list of the hashed key exist
    if ((node = ((KeyList<IList<String>>)this.major).getByKey(hash_key)) == null)
      return false;

    // get the position of the element in the main array of element
    pos = ((KeyList<String>)node).searchKey(obj.getTitle());
    this.dom.set(pos, null);
    // delete the element in the main hash
    ((KeyList<String>)node).delEl(obj.getTitle());

    if (node.getSize() <= 0)
      this.major.delEl(node);

    // delete the keys' node in the keys hashes
    this.delHashKey(new Year(obj.getYear()), this.dates, pos);
    this.delHashKey(obj.getDirector().getName(), this.directors, pos);
    ((KeyList<Integer>)this.rates).delByKey(pos);

    this.length--;

    return true;
  }

  // resetting all the hashes and main array
  public void reset (){
    this.major.reset();
    this.dates.reset();
    this.directors.reset();
    this.rates.reset();
    super.reset();
  }

  // delete by title. just checkin the existance of a movie with that title
  public boolean delete(String title){
    return this.delete(this.search(title));
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

  // update the element if it does already exist else it normally insert it
  public int upsert(Movie obj){
    if (obj == null)
      return -1;

    Integer key = this.hash(obj.getTitle());
    IList<String> node = null;

    // check if the list of the hashed key exist
    if ((node = ((KeyList<IList<String>>)this.major).getByKey(key)) != null){
      Integer el_key = ((KeyList<String>)node).searchKey(obj.getTitle());
      // if the key of the element in the hash is not null update all the data structures
      if (el_key != null){
        this.updHashKey(new Year(this.dom.get(el_key).getYear()), new Year(obj.getYear()), this.dates, el_key);
        this.updHashKey(this.dom.get(el_key).getDirector().getName(), obj.getDirector().getName(), this.directors, el_key);
        ((KeyList<Integer>)this.rates).updByKey(obj.getVotes(), el_key);
        this.dom.set(el_key, obj);
        return el_key;
      }
    }
    else{
      // if there's not the list of the hashed key than create it
      ((KeyList<IList<String>>)this.major).addTail(key, new KeyList(key));
      node = ((KeyList<IList<String>>)this.major).getTail().getValue();
    }

    // insert the new movie. Same as INSERT(Movie ..)
    this.dom.set(this.size, obj);
    ((KeyList<String>)node).addTail(this.size, obj.getTitle());

    this.addHashKey(new Year(obj.getYear()), this.dates);
    this.addHashKey(obj.getDirector().getName(), this.directors);
    ((KeyList<Integer>)this.rates).addTail(this.size, obj.getVotes());

    this.size++;
    this.length++;

    return this.size-1;
  }


  // search of the element by title
  public Movie search(String title){
    Integer key = this.hash(title);
    IList<String> node = null;

    // check if the list of the hashed value of the key in input already exist
    if ((node = ((KeyList<IList<String>>)this.major).getByKey(key)) != null){
      Integer el_key = ((KeyList<String>)node).searchKey(title);
      if (el_key != null)
        return this.dom.get(el_key);
    }

    return null;
  }


  // search of the element by key in the input
  public <K extends Comparable<K>> Movie[] searchByKey(K input){
    IList<Movie> out = null;

    // if integer is searching by year
    if (input instanceof Integer)
      out = this.searchByHashKey(new Year((Integer)input), dates);
    // if string is searching by directors
    else if (input instanceof String)
      out = this.searchByHashKey((String)input, directors);

    // if the output is not null transform the output in array and return it
    return (out != null) ?  this.listToPrimitive(out) : null;
  }

  // get N elements by key in the input
  public Movie[] searchMostOf(Integer num, String type){
    IList<Movie> out = null;

    // check the type
    switch (type.toLowerCase()){
      case "votes": out = this.searchMostOfHashKey(num, this.rates);break;
      case "year": {
        int i = 0;
        if (this.length > 0){
          out = new List<Movie>();

          // iterate each list of hashed keys and add the ouputs till we arrive to N elements
          for (KeyNode<IList<Year>> iter = (KeyNode<IList<Year>>)this.dates.getHead(); iter != null && i < num; iter = (KeyNode<IList<Year>>)iter.getNext(), i = out.getSize())
            out.addToEnd(this.searchMostOfHashKey(num, iter.getValue()));
        }
      };break;
      default: System.out.println("Wrong input");
    }

    return (out != null && out.getSize() > 0) ?  this.listToPrimitive(out) : null;
  }

  // get all the elements that contains input's string in the title
  public <K extends Comparable<K>> Movie[] searchContains(String title){
    IList<Movie> out = null;

    // get the output
    out = this.searchContainsHashKey(title, this.major);

    // transform to array and return it
    return (out != null) ?  this.listToPrimitive(out) : null;
  }

  // get the element based of the id (position in the main array). Done for PersonHash.
  public Movie getFromId (Integer id){ return (id > this.size || id < 0) ? null : this.dom.get(id); }

  // sort all the hashes
  public void sort(IAlg algorithm){
    this.major = this.sortListOfList(algorithm, this.major);
    this.dates = this.sortListOfList(algorithm, this.dates);
    this.directors = this.sortListOfList(algorithm, this.directors);
    this.rates = algorithm.sort(this.rates);
  }

  @Override
  // transform in an array object
  public Array<Movie> toArray() {
    if (this.length < 0)
      return null;

    final Array<Movie> array = new Array<Movie>(this.length);
    int i = 0;

    // add all the node list per list
    for (KeyNode<IList<String>> iter = (KeyNode<IList<String>>)((KeyList<IList<String>>)this.major).getHead(); iter != null; iter = (KeyNode<IList<String>>)iter.getNext())
      for (KeyNode<String> nodeIter = (KeyNode<String>)((KeyList<String>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (KeyNode<String>)nodeIter.getNext(), i++)
        if (nodeIter.getKey() != null && nodeIter.getValue() != null)
          array.set(i, this.dom.get(nodeIter.getKey()));

    return array;
  }

  // transform the hash in an primitive array (arr[])
  public Movie[] toPrimitive() {
    if (this.length < 0)
      return null;

    final Movie[] array = new Movie[this.length];
    int i = 0;

    // add all the node list per list
    for (KeyNode<IList<String>> iter = (KeyNode<IList<String>>)((KeyList<IList<String>>)this.major).getHead(); iter != null; iter = (KeyNode<IList<String>>)iter.getNext())
      for (KeyNode<String> nodeIter = (KeyNode<String>)((KeyList<String>)iter.getValue()).getHead(); nodeIter != null; nodeIter = (KeyNode<String>)nodeIter.getNext(), i++)
        if (nodeIter.getKey() != null && nodeIter.getValue() != null)
          array[i] = this.dom.get(nodeIter.getKey());

    return array;
  }


  // protected method to convet a list of movie into a primitive array
  protected Movie[] listToPrimitive (IList<Movie> list){
    Movie[] prim = new Movie[list.getSize()];

    int i = 0;
    for (INode2<Movie> iter = list.getHead(); iter != null; iter = iter.getNext(), i++)
      prim[i] = iter.getValue();

    return prim;
  }
}
