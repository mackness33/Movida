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
public interface IMovieMap<E extends Movie> extends IMap<Movie> {

  // resetting all the hashes and main array
  public void reset ();

  // delete by title. just checkin the existance of a movie with that title
  public boolean delete(String title);

  // update the element if it does already exist else it normally insert it
  public int upsert(Movie obj);

  // search of the element by title
  public Movie search(String title);

  // search of the element by key in the input
  public <K extends Comparable<K>> Movie[] searchByKey(K input);

  // get N elements by key in the input
  public Movie[] searchMostOf(Integer num, String type);

  // get all the elements that contains input's string in the title
  public <K extends Comparable<K>> Movie[] searchContains(String title);

  // get the element based of the id (position in the main array). Done for PersonHash.
  public Movie getFromId (Integer id);

  // sort all the hashes
  public void sort(IAlg algorithm);

  // transform in an array object
  public Array<Movie> toArray();

  // transform the hash in an primitive array (arr[])
  public Movie[] toPrimitive();
}
