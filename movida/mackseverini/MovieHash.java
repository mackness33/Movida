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
public class MovieHash<Movie extends Comparable<Movie>> extends Hash2<Movie> {
  // protected Set<String> casts;
  // protected Set<String> directors;
  // protected Set<Integer> dates;
  // protected Set<Integer> rates;

  // constructor residesHash<Movie>
  @SuppressWarnings("unchecked")
  public MovieHash() {
    super();

    // this.casts = new Set<String> ();
    // this.directors = new Set<String> ();
    // this.dates = new Set<Integer> ();
    // this.rates = new Set<Integer> ();
    // for (int i = 0; i < this.sets.length; i++)
    //   this.sets.set(i, new Set<String>(i, null));
  }

  // TRUE => UPDATE!!
  // FALSE => INSERT!!
  public boolean upsert(Movie obj){
    Integer key = this.hash(obj);
    IList<Integer> node = null;

    // HashNode<Integer> date = new HashNode<Integer>(this.hash(obj), obj.getDate());

    // System.out.println("KEY: " + dates.getKey());
    this.dom.set(this.size, obj);

    if ((node = ((HashList<IList<Integer>>)this.major).getByKey(key)) != null){
      Integer el_key = ((HashList<Integer>)node).searchKey(obj.hashCode());
      if (el_key != null){
        this.dom.set(el_key, obj);
        return true;
      }
    }
    else{
      ((HashList<IList<Integer>>)this.major).addTail(key, new HashList());
      node = ((HashList<IList<Integer>>)this.major).getTail().getValue();
    }

    this.dom.set(this.size, obj);
    ((HashList<Integer>)node).addTail(this.size, obj.hashCode());

    this.size++;

    return false;
  }
}
