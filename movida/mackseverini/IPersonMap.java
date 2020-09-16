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
public interface IPersonMap<E extends Person> extends IMap<Person> {

  // retrive the amount of elements totally used
  public int getSize ();

  // retrive the amount of elements actually inside
  public int getLength ();

  // update the element if it does already exist else it normally insert it
  public boolean upsert(Person obj, Integer movie);

  // delete of a element in the main hash and the keys hash/array
  public boolean delete(String name);

  // search of the element by title
  public Person search(String name);

  // resetting all the hashes and main array
  public void reset ();

  // get N elements by key in the input
  public Person[] searchMostOf(Integer num);

  // sort all the hashes
  public void sort(IAlg algorithm);

  // transform in an array object
  public Array<Person> toArray();

  // transform the hash in an primitive array (arr[])
  public Person[] toPrimitive();
}
