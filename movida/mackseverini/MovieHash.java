package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node;
import movida.mackseverini.Array;
import movida.mackseverini.Set;
import movida.mackseverini.Hash;

import movida.commons.Movie;
import movida.commons.Person;

// BUG: Comparable cannot be used.
// SOLUTION: Comparable cannot be used.
public class MovieHash<Movie extends Comparable<Movie>> extends Hash<Movie> {
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

  public boolean upsert(Movie obj){
    Integer key = this.hash(obj);

    if (this.dom.get(key).getValue() != null){
      for (ListNode<Movie> head = dom.get(key); head != null; head = head.getNext()){
        // System.out.println("Head: " + head + " val: " + head.getValue());
        // System.out.println("Next: " + head.getNext());

        if (this.compare(head.getValue(), obj) == 0){
          head.setValue(obj);
          return true;
        }
      }
    }

    this.insert(obj);

    return false;
  }
}
