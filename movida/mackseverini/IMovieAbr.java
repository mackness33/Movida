package movida.mackseverini;

import movida.commons.Movie;
import movida.commons.Person;

public interface IMovieAbr<E extends Movie>
{
  public String getTitle();

	public Integer getYear();

	public Integer getVotes();

	public Person getDirector();
  }
