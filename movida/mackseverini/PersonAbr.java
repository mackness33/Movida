package movida.mackseverini;

import movida.commons.Movie;
import movida.commons.Person;
import movida.commons.List;
import movida.mackseverini.ABR;


public class PersonAbr<E extends Person> implements IPersonAbr<E> implements IPersonAbr<E>
{
  ABR<Integer, E> actors;
  ABR<Integer, E> directors;

  public PersonAbr()
  {
    this.actors = null;
    this.directors = null;
  }

  public PersonAbr(Array<E> people)
  {
    int j = 0;
    int k = 0;

    for(int i = 0; i < people.length; i++)
    {
      if((people.get(i)).isActor())
        this.actors.insert(people.get(i));
      else
        this.directors.insert(people.get(i));
    }
  }

  public PersonAbr(Array<E> actors, Array<E> directors)
  {
    this.actors = actors;
    this.directors = directors;
  }

  // retrive the amount of elements totally used
  public int getSize(){return 0;}

  // retrive the amount of elements actually inside
  public int getLength(){return this.actors.getSize() + this.directors.getSize();}

  // update the element if it does already exist else it normally insert it
  public boolean upsert(Person obj, Integer movie)

  // delete of a element in the main hash and the keys hash/array
  public boolean delete(String name)

  // search of the element by title
  public Person search(String name)

  // resetting all the hashes and main array
  public void reset()
  {
    this.actors = null;
    this.directors = null;
  }

  // get N elements by key in the input
  public Person[] searchMostOf(Integer num)

  // sort all the hashes
  public void sort(IAlg algorithm, boolean decrescent)

  // transform in an array object
  public Array<Person> toArray()
  {

  }

  // transform the hash in an primitive array (arr[])
  public Person[] toPrimitive()
}
