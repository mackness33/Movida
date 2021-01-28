package movida.mackseverini;

import movida.commons.Movie;
import movida.commons.Person;
import movida.mackseverini.Array;
import movida.mackseverini.List;
import movida.mackseverini.ABR;

public class MovieAbr<E extends Movie> implements IMovieAbr<E>
{
  // usare Array fatto da david
  // insert e delete implementati con metodo set
  protected Integer size;
  protected Array<E> movies;
  protected ABR<Integer, String> titles;
  protected ABR<Integer, Integer> years;
  protected ABR<Integer, Integer> votes;
  protected ABR<Integer, Person> director;

  public MovieAbr()
  {
    this.size = null;
    this.movies = null;
    this.titles = null;
    this.years = null;
    this.votes = null;
    this.director = null;
  }

  public MovieAbr(Array<E> movies)
  {
    this.size = movies.length;
    this.movies = movies;
    this.titles = null;
    this.years = null;
    this.votes = null;
    this.director = null;
  }

  public MovieAbr(Array<E> movies, ABR<Integer, String> titles, ABR<Integer, Integer> years, ABR<Integer, Integer> votes, ABR<Integer, Person> director)
  {
    this.size = movies.length;
    this.movies = movies;
    this.titles = titles;
    this.years = years;
    this.votes = votes;
    this.director = director;
  }

  // retrive the amount of elements totally used
  @Override
  public int getSize () {return this.size;}

  // retrive the amount of elements actually inside
  @Override
  public int getLength ()
  {
    int length = 0;

    for(int i = 0; i < this.size; i++)
    {
        if(this.movies.get(i) != null)
          length++;
    }

    return length;
  }

  // resetting all the abr and main array
  @Override
  public void reset ()
  {
    this.size = null;
    this.titles = null;
    this.years = null;
    this.votes = null;
    this.director = null;

    for(int i = 0; i < this.getLength(); i++)
      this.movies.set(i, null);
  }

  // delete by title. just checkin the existance of a movie with that title
  @Override
  public boolean delete(String title)
  {
    int indexToDelete = this.titles.getIndex(title);
    bool result = this.titles.delete(title) &&
                  this.years.delete(this.movies.get(indexToDelete).getYear()) &&
                  this.votes.delete(this.movies.get(indexToDelete).getVotes()) &&
                  this.director.delete(this.movies.get(indexToDelete).getDirector());

    this.movies.set(i, null);

    return result;
  }

  // update the element if it does already exist else it normally insert it
  @Override
  public int upsert(Movie obj)
  {}

  // search of the element by title
  @Override
  public Movie search(String title)
  {}

  // search of the element by key in the input
  @Override
  public <K extends Comparable<K>> Movie[] searchByKey(K input)
  {}

  // get N elements by key in the input
  @Override
  public Movie[] searchMostOf(Integer num, String type)
  {}

  // get all the elements that contains input's string in the title
  @Override
  public <K extends Comparable<K>> Movie[] searchContains(String title)
  {}

  // get the element based of the id (position in the main array).
  @Override
  public Movie getFromId (Integer id) {return this.movies.get(id);}

  // sort all the hashes
  @Override
  public void sort(IAlg algorithm)
  {}

  // transform in an array object
  @Override
  public Array<Movie> toArray()
  {
    return (Array<Movie>)this.movies;
    // Array<Movie> moviesArray = new Array<Movie>(this.size);
    //
    // for(int i = 0; i < this.size; i++)
    //   moviesArray.set(i, this.movies.get(i));
    //
    // return moviesArray;
  }

  // transform the hash in an primitive array (arr[])
  @Override
  public Movie[] toPrimitive()
  {
    int j = 0;
    Movie moviesArray[] = new Movie[this.size];

    for(int i = 0; i < this.size; i++)
    {
      if(this.movies.get(i) != null)
      {
        moviesArray[j] = this.movies.get(i);
        j++;
      }
    }

    return moviesArray;
  }
}
