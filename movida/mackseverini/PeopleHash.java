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
public class PeopleHash<Person extends Comparable<Person>> extends Hash2<Person> {
  // protected Set<String> casts;
  // protected Set<String> directors;
  // protected Set<Integer> dates;
  // protected Set<Integer> rates;

  // constructor residesHash<Movie>
  @SuppressWarnings("unchecked")
  public PeopleHash() {
    super();
  }

  // public Person search(String name){
  //   Integer key = this.hash(title);
  //   IList<Integer> node = null;
  //
  //   if ((node = ((HashList<IList<Integer>>)this.major).getByKey(key)) != null){
  //     // System.out.println("Node: " + node);
  //     Integer el_key = ((HashList<Integer>)node).searchKey(name);
  //     // System.out.println("Key: " + el_key);
  //     if (el_key != null)
  //       return this.dom.get(el_key);
  //   }
  //
  //   return false;
  // }
}
