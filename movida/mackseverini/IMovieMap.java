package movida.mackseverini;

import movida.mackseverini.Array;

import movida.commons.Movie;

// interface that describe the operation of Map specifically created for Movies
public interface IMovieMap<E extends Movie> extends IMap<Movie>, movida.mackseverini.IConfig<movida.commons.MapImplementation>{
  // retrive the amount of elements totally used
  public int getSize ();

  // retrive the amount of elements actually inside
  public int getLength ();

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

  // get the position based of the movie in input (position in the main array). Done for PersonHash.
  public Integer getPosFromMovie (Movie input);

  // sort all the hashes
  public void sort(IAlg algorithm, boolean decrescent);

  // transform in an array object
  public Array<Movie> toArray();

  // transform the hash in an primitive array (arr[])
  public Movie[] toPrimitive();

  // transform the hash in an primitive array (arr[])
  public void print();
}
