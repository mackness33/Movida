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
  protected ABR<Integer, String> director;

  public MovieAbr()
  {
    this.movies = new Array<E>(0);
    this.titles = new ABR();
    this.years = new ABR();
    this.votes = new ABR();
    this.director = new ABR();
  }

  public MovieAbr(Array<E> movies)
  {
    this.movies = movies;
    this.titles = new ABR();
    this.years = new ABR();
    this.votes = new ABR();
    this.director = new ABR();
  }

  public MovieAbr(Array<E> movies, ABR<Integer, String> titles, ABR<Integer, Integer> years, ABR<Integer, Integer> votes, ABR<Integer, String> director)
  {
    this.movies = movies;
    this.titles = titles;
    this.years = years;
    this.votes = votes;
    this.director = director;
  }

  @Override
  public final movida.commons.MapImplementation getType(){ return movida.commons.MapImplementation.ABR; }

  public void print(){}

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
    this.titles = new ABR();
    this.years = new ABR();
    this.votes = new ABR();
    this.director = new ABR();

    for(int i = 0; i < this.movies.length; i++)
      this.movies.set(i, null);
  }

  public boolean insert(Movie movieToInsert)
  {
    // empty array
    if(this.movies.length == 0)
      this.movies = new Array<E>(5);
    // full array (double length)
    else if(this.getLength() == this.movies.length)
    {
      Array<E> newMovies = new Array<E>(this.movies.length * 2);

      for(int i = 0; i < this.movies.length; i++)
        newMovies.set(i, this.movies.get(i));
      for(int i = this.movies.length; i < newMovies.length; i++)
        newMovies.set(i, null);

        this.movies = new Array<E>(newMovies);
    }

    int i = 0;
    boolean inserted = false;

    // inserting in the array
    while((i < this.movies.length) && (this.movies.get(i) != null))
      i++;

    if(i < this.movies.length)
      this.movies.set(i, (E)movieToInsert);

    // inserting in the ABRs
    return this.titles.insert(i, movieToInsert.getTitle()) &&
    this.years.insert(i, movieToInsert.getYear()) &&
    this.votes.insert(i, movieToInsert.getVotes()) &&
    this.director.insert(i, movieToInsert.getDirector().getName());
  }

  // delete a movie by title
  @Override
  public boolean delete(String title)
  {
    // System.out.println("\n\n\n\nDELETE: " + title + "\n\n\n\n");

    boolean result = false;
    Integer indexToDelete = this.titles.getIndex(title);

    if(indexToDelete != null)
    {
      result =  this.titles.deleteByKey(title, indexToDelete) &&
                this.years.deleteByKey(this.movies.get(indexToDelete).getYear(), indexToDelete) &&
                this.votes.deleteByKey(this.movies.get(indexToDelete).getVotes(), indexToDelete) &&
                this.director.deleteByKey(this.movies.get(indexToDelete).getDirector().getName(), indexToDelete);

      this.movies.set(indexToDelete, null);
    }

    return result;
  }

  @Override
  public boolean delete(Movie movieToDelete)
  {
    if(movieToDelete != null)
      return this.delete(movieToDelete.getTitle());
    else
      return false;
  }

  // update the element if it does already exist else it normally insert it
  @Override
  public int upsert(Movie obj)
  {
    // System.out.println("\n\n\n\nUPSERT: " + obj.getTitle() + "\nYEAR: " + obj.getYear() + "\nSEARCH: " + this.search(obj.getTitle()) + "\n\n\n\n");

    if(this.search(obj.getTitle()) != null)
    {
        // System.out.println("\n\n\n\nUDELETE FROM UPSERT\n\n\n\n");

        this.delete(obj.getTitle());
    }

    // System.out.println("\n\n\n\nINSERT: " + obj.getTitle() + "\n\n\n\n");
    this.insert(obj);
    return (this.titles.get(obj.getTitle())).getKey();
  }

  // search of the element by title
  @Override
  public Movie search(String title)
  {
    if(this.titles.get(title) != null)
      return this.movies.get((this.titles.get(title)).getKey());
    else
      return null;
  }

  // search of the element
  @Override
  public boolean search(Movie movieToFind)
  {
    if(movieToFind == null)
      return false;
    else
      return this.search(movieToFind.getTitle()) != null;
  }

  // search of the element by key in the input
  @Override
  public <K extends Comparable<K>> Movie[] searchByKey(K input)
  {
    // System.out.println("\n\n\n\n");
    // for(int j = 0; j < this.movies.length; j++)
    //   System.out.println(this.movies.get(j));
    // System.out.println("\n\n\n\n");

    if(input == null)
      return null;
    else
    {
      Array<Movie> moviesByKey = new Array<Movie>(this.getLength());
      int i = 0;

      if(input instanceof Integer)  // YEAR CASE
      {
        Array<Integer> indexes = new Array(this.years.getAll((Integer)input));

        // System.out.println("\n\n\n\nINDEXES:");
        // for(i = 0; i < indexes.length; i++)
        //   System.out.println(indexes.get(i));
        // System.out.println("\n\n\n\n");

        for(i = 0; i < indexes.length; i++)
          if(indexes.get(i) != null)
            moviesByKey.set(i, this.movies.get(indexes.get(i)));
      }
      else if(input instanceof String)  // DIRECTOR CASE
      {
        Array<Integer> indexes = new Array(this.director.getAll((String)input));

        for(i = 0; i < indexes.length; i++)
          if(indexes.get(i) != null)
            moviesByKey.set(i, this.movies.get(indexes.get(i)));
      }
      else
      {
        System.out.println("!! WRONG TYPE: " + input.getClass() + " !!");
        return null;
      }

      Movie[] moviesByKeyArray = new Movie[this.getLength()];

      // System.out.println("\n\n\n\nMOVIES BY KEY:");
      // for(int j = 0; j < moviesByKey.length; j++)
      //   System.out.println(moviesByKey.get(j));
      // System.out.println("\n\n\n\n");

      for(int j = 0; j < this.getLength(); j++)
        moviesByKeyArray[j] = moviesByKey.get(j);

      return moviesByKeyArray;
    }
  }

  // get N elements by key in the input
  @Override
  public Movie[] searchMostOf(Integer num, String type)
  {
    if(type == null || num < 1)
      return null;
    else if(num > this.getLength())
      num = this.getLength();

    Array<Movie> moviesByKey = new Array<Movie>(num);
    Movie[] moviesByKeyArray = new Movie[num];
    Array<Integer> indexes;
    int i = 0;

    // System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nMOVIES IN ABR\n\n");
    // this.years.printAbr();
    // System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nMOVIES IN ARRAY\n\n");
    // for(i = 0; i < this.movies.length; i++)
    //   System.out.println(this.movies.get(i));
    // System.out.println("\n\n\n\n");

    switch(type)
    {
      case "year":
        indexes = new Array<Integer>(this.years.getNumMax(num));
        for(i = 0; i < num; i++)
          moviesByKey.set(i, this.movies.get(indexes.get(i)));
        break;

      case "votes":
        indexes = new Array<Integer>(this.votes.getNumMax(num));
        for(i = 0; i < num; i++)
          moviesByKey.set(i, this.movies.get(indexes.get(i)));
        break;

      default:
        System.out.println("\n\nWRONG TYPE\n\n");
        break;
    }

    // System.out.println("\n\n\n\n");
    // for(i = 0; i < num; i++)
    //   System.out.println(moviesByKey.get(i));
    // System.out.println("\n\n\n\n");

    for(i = 0; i < num; i++)
      moviesByKeyArray[i] = moviesByKey.get(i);

    return moviesByKeyArray;
  }

  // get all the elements that contains input's string in the title
  @Override
  public <K extends Comparable<K>> Movie[] searchContains(String title)
  {
    if(title == null)
      return null;

    Movie[] moviesWithTitle = new Movie[this.movies.length];

    for(int i = 0; i < this.movies.length; i++)
    {
      if(this.movies.get(i) != null)
        if(((this.movies.get(i)).getTitle()).contains(title) == true)
          moviesWithTitle[i] = this.movies.get(i);
    }

    return moviesWithTitle;
  }

  // get the element based of the id (position in the main array).
  @Override
  public Movie getFromId (Integer id) {return this.movies.get(id);}

  // sort all the trees
  @Override
  public void sort(IAlg algorithm, boolean decrescent){}

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

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  //                                              USEFUL ONLY FOR TESTING                                           //

  public void printTree(int treeType)
  {
    switch(treeType)
    {
      case(0):
        System.out.println("ALL TREES\n\n");
        System.out.println("\n\nTITLES TREE\n\n");
        this.titles.printAbr();
        System.out.println("\n\nYEARS TREE\n\n");
        this.years.printAbr();
        System.out.println("\n\nVOTES TREE\n\n");
        this.votes.printAbr();
        System.out.println("\n\nDIRECTORS TREE\n\n");
        this.director.printAbr();
        break;

      case(1):
        System.out.println("TITLES TREE\n\n");
        this.titles.printAbr();
        break;

      case(2):
        System.out.println("YEARS TREE\n\n");
        this.years.printAbr();
        break;

      case(3):
        System.out.println("VOTES TREE\n\n");
        this.votes.printAbr();
        break;

      case(4):
        System.out.println("DIRECTORS TREE\n\n");
        this.director.printAbr();
        break;

      default:
        System.out.println("WRONG NUMBER YOU DUMB");
        break;
    }
  }
}
