package movida.mackseverini;

import movida.commons.Movie;
import movida.commons.Person;
import movida.mackseverini.Array;
import movida.mackseverini.List;
import movida.mackseverini.ABR;

public class MovieAbr<E extends Movie> implements IMovieAbr<E>
{
  protected Array<E> movies;
  protected ABR<Integer, String> titles;
  protected ABR<Integer, Integer> years;
  protected ABR<Integer, Integer> votes;
  protected ABR<Integer, Person> director;

  public MovieAbr()
  {
    this.movies = null;
    this.titles = null;
    this.years = null;
    this.votes = null;
    this.director = null;
  }

  public MovieAbr(Array<E> movies)
  {
    this.movies = movies;
    this.titles = null;
    this.years = null;
    this.votes = null;
    this.director = null;
  }

  public MovieAbr(Array<E> movies, ABR<Integer, String> titles, ABR<Integer, Integer> years, ABR<Integer, Integer> votes, ABR<Integer, Person> director)
  {
    this.movies = movies;
    this.titles = titles;
    this.years = years;
    this.votes = votes;
    this.director = director;
  }

  // retrive the amount of elements totally used
  @Override
  public int getSize ()
  {return 0;} //                        DA IMPLEMENTARE

  // retrive the amount of elements actually inside
  @Override
  public int getLength ()
  {
    int length = 0;

    for(int i = 0; i < this.movies.length; i++)
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

    for(int i = 0; i < this.movies.size; i++)
      this.movies.set(i, null);
  }

  public boolean insert(Movie movieToInsert)
  {
    if(this.getLength() == this.size)
    {
      System.out.println("ERROR: NO MORE SPACE, DELETE A MOVIE TO FREE SPACE");
      return false;
    }
    else
    {
      int i = 0;
      boolean inserted = false;

      // inserting in the array
      while((i < this.movies.length) && (inserted == false))
      {
        if(this.movies.get(i) == null)
        {
          this.movies.set(i, movieToInsert);
          inserted = true;
        }

        i++;
      }

      // inserting in the ABRs
      return this.titles.insert(movieToInsert.getTitle()) &&
      this.years.insert(movieToInsert.getYear()) &&
      this.votes.insert(movieToInsert.getVotes()) &&
      this.director.insert(movieToInsert.getDirector());
    }
  } //                        DA TESTARE

  // delete by title. just checkin the existance of a movie with that title
  @Override
  public boolean delete(String title)
  {
    int indexToDelete = this.titles.getIndex(title);
    boolean result = this.titles.delete(title) &&
                  this.years.delete(this.movies.get(indexToDelete).getYear()) &&
                  this.votes.delete(this.movies.get(indexToDelete).getVotes()) &&
                  this.director.delete(this.movies.get(indexToDelete).getDirector());

    this.movies.set(indexToDelete, null);

    return result;
  }

  @Override
  public boolean delete(Movie movieToDelete)
  {return false;} //                        DA IMPLEMENTARE

  // update the element if it does already exist else it normally insert it
  @Override
  public int upsert(Movie obj)
  {return 0;} //                        DA IMPLEMENTARE

  // search of the element by title
  @Override
  public Movie search(String title)
  {return null;} //                        DA IMPLEMENTARE

  @Override
  public boolean search(Movie movieToFind)
  {return false;} //                        DA IMPLEMENTARE

  // search of the element by key in the input
  @Override
  public <K extends Comparable<K>> Movie[] searchByKey(K input)
  {return null;} //                        DA IMPLEMENTARE

  // get N elements by key in the input
  @Override
  public Movie[] searchMostOf(Integer num, String type)
  {return null;} //                        DA IMPLEMENTARE

  // get all the elements that contains input's string in the title
  @Override
  public <K extends Comparable<K>> Movie[] searchContains(String title)
  {return null;} //                        DA IMPLEMENTARE

  // get the element based of the id (position in the main array).
  @Override
  public Movie getFromId (Integer id) {return this.movies.get(id);}

  // sort all the hashes
  @Override
  public void sort(IAlg algorithm)
  {} //                        DA IMPLEMENTARE

  // transform in an array object
  @Override
  public Array<Movie> toArray() {return (Array<Movie>)this.movies;}

  // transform the hash in an primitive array (arr[])
  @Override
  public Movie[] toPrimitive()
  {
    int j = 0;
    Movie moviesArray[] = new Movie[this.movies.length];

    for(int i = 0; i < this.movies.length; i++)
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
