package movida.mackseverini;

import movida.commons.Movie;
import movida.commons.Person;
import movida.mackseverini.Array;
import movida.mackseverini.ABR;

public class PersonAbr<E extends Person> implements IPersonAbr<E>
{
  protected Array<E> people;
  protected ABR<Integer, String> names;
  protected ABR<Integer, Integer> numMovsParticipated;

  public PersonAbr()
  {
    this.people = new Array<E>(0);
    this.names = new ABR();
    this.numMovsParticipated = new ABR();
  }

  public PersonAbr(Array<E> people, ABR<Integer, String> names, ABR<Integer, Integer> numMovsParticipated)
  {
    this.people = people;
    this.names = names;
    this.numMovsParticipated = numMovsParticipated;
  }

  @Override
  public final movida.commons.MapImplementation getType() {return movida.commons.MapImplementation.ABR;}

  // retrive the amount of elements totally used
  @Override
  public int getSize(){return 0;}

  // retrive the amount of elements actually inside
  @Override
  public int getLength()
  {
    int length = 0;

    for(int i = 0; i < this.people.length; i++)
    {
        if(this.people.get(i) != null)
          length++;
    }

    return length;
  }

  // update the element if it does already exist else it normally insert it
  @Override
  public boolean upsert(Person personToInsert, Integer movie)
  {
    if(personToInsert == null || movie == null)
      return false;

    if(this.search(personToInsert))
    {
      Integer indexToUpdate = this.names.getIndex(personToInsert.getName());

      if(indexToUpdate != null && this.people.get(indexToUpdate).isActor())
      {
        Integer numMovies = this.people.get(indexToUpdate).getMovieSize();
        this.people.get(indexToUpdate).addMovie(movie);
        this.numMovsParticipated.update(indexToUpdate, indexToUpdate, this.people.get(indexToUpdate).getMovieSize(), numMovies);

        return true;
      }

      return false;
    }
    else
      return this.insert(personToInsert);
  }

  @Override
  public boolean insert(Person personToInsert)
  {
    if(personToInsert == null)
      return true;

    // empty array
    if(this.people == null || this.people.length == 0)
      this.people = new Array<E>(5);
    // full array (double length)
    else if(this.getLength() == this.people.length)
    {
      Array<E> newPeople = new Array<E>(this.people.length * 2);

      for(int i = 0; i < this.people.length; i++)
        newPeople.set(i, this.people.get(i));
      for(int i = this.people.length; i < newPeople.length; i++)
        newPeople.set(i, null);

        this.people = new Array<E>(newPeople);
    }

    int i = 0;
    boolean inserted = false;

    // inserting in the array
    while((i < this.people.length) && (this.people.get(i) != null))
      i++;

    if(i < this.people.length)
      this.people.set(i, (E)personToInsert);

    // inserting in the ABRs
    return this.names.insert(i, personToInsert.getName()) && ((personToInsert.isActor()) ? (this.numMovsParticipated.insert(i, personToInsert.getMovieSize())) : true);
  }

  @Override
  public boolean decreaseMovie(Person personToDecrease, Integer movie){
    if (personToDecrease == null || movie == null)
      return false;

    if(this.search(personToDecrease))
    {
      Integer indexToUpdate = this.names.getIndex(personToDecrease.getName());

      if(indexToUpdate != null && this.people.get(indexToUpdate).isActor())
      {
        Integer numMovies = this.people.get(indexToUpdate).getMovieSize();
        this.people.get(indexToUpdate).delMovie(movie);

        if(this.people.get(indexToUpdate).getMovieSize() == 0)
          this.delete(this.people.get(indexToUpdate));
        else
          this.numMovsParticipated.update(indexToUpdate, indexToUpdate, this.people.get(indexToUpdate).getMovieSize(), numMovies);

        return true;
      }
      else
        return false;
    }
    else
      return false;
  }



  // delete of a element in the main hash and the keys ABR/array
  @Override
  public boolean delete(String name)
  {
    if(name == null)
      return false;
    else
    {
      Integer indexToDelete = this.names.getIndex(name);
      Person personToDelete = null;

      if(indexToDelete != null)
      {
        personToDelete = this.people.get(indexToDelete);
        this.people.set(indexToDelete, null);
      }

      return this.names.delete(name) && this.numMovsParticipated.delete(personToDelete.getMovieSize());
    }
  }

  @Override
  public boolean delete(Person personToDelete)
  {
    if(personToDelete != null)
      return this.delete(personToDelete.getName());
    else
      return false;
  }

  // search of the element by title
  @Override
  public Person search(String name)
  {
    if(name == null || this.names == null)
      return null;
    else
    {
      Integer indexFound = this.names.getIndex(name);
      if(indexFound != null)
        return this.people.get(this.names.getIndex(name));
      else
        return null;
    }
  }

  @Override
  public boolean search(Person personToFind)
  {
    if(personToFind == null)
      return false;

    return (this.search(personToFind.getName()) != null);
  }

  // resetting all the hashes and main array
  @Override
  public void reset()
  {
    this.people = null;
    this.names = null;
    this.numMovsParticipated = null;
  }

  // get N actors by key in the input
  @Override
  public Person[] searchMostOf(Integer num)
  {
    if(num < 1)
      return null;
    else if(num > this.getLength())
      num = this.getLength();

    Array<Person> peopleByKey = new Array<Person>(num);
    Person[] peopleByKeyArray = new Person[num];
    Array<Integer> indexes;
    int i = 0;

    if(this.names.getNumMax(num) != null)
      indexes = new Array<Integer>(this.numMovsParticipated.getNumMax(num));
    else
      return null;

    for(i = 0; i < num; i++)
      peopleByKey.set(i, this.people.get(indexes.get(i)));

    for(i = 0; i < num; i++)
      peopleByKeyArray[i] = peopleByKey.get(i);

    return peopleByKeyArray;
  }

  // sort all the hashes
  @Override
  public void sort(IAlg algorithm, boolean decrescent){}

  // transform in an array object
  @Override
  public Array<Person> toArray() {return (Array<Person>)this.people;}

  // transform the hash in an primitive array (arr[])
  @Override
  public Person[] toPrimitive()
  {
    if(this.people == null)
      return null;
    else
    {

      int j = 0;
      Person peopleArray[] = new Person[this.people.length];

      for(int i = 0; i < this.people.length; i++)
      {
        if(this.people.get(i) != null)
        {
          peopleArray[j] = this.people.get(i);
          j++;
        }
      }

      return peopleArray;
    }
  }
}
