package movida.mackseverini;

import movida.mackseverini.Array;

import movida.commons.Person;

// interface that describe the operation of Map specifically created for Movies
public interface IPersonMap<E extends Person> extends IMap<Person>, movida.mackseverini.IConfig<movida.commons.MapImplementation>{

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
  public void sort(IAlg algorithm, boolean decrescent);

  // transform in an array object
  public Array<Person> toArray();

  // transform the hash in an primitive array (arr[])
  public Person[] toPrimitive();
}
