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
  Array<E> movies;
  ABR<Integer, String> titles;
  ABR<Integer, Integer> years;
  ABR<Integer, Integer> votes;
  ABR<Integer, Person> director;

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

  @Override
  public String getTitle() {return null;}

  @Override
	public Integer getYear() {return null;}

  @Override
	public Integer getVotes() {return null;}

  @Override
	public Person getDirector() {return null;}
}
