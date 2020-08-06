package movida.mackseverini;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import movida.commons.Movie;
import movida.commons.Person;
import java.lang.Integer;
import movida.mackseverini.Search;
import movida.mackseverini.MovieHash;
import movida.mackseverini.PeopleHash;
import movida.mackseverini.InsertionSort;

public class MovidaCore implements movida.commons.IMovidaDB, movida.commons.IMovidaSearch{
  private MovieHash<Movie> movies;
  private PeopleHash people;
  private IAlg sortAlgorithm;

  public MovidaCore(){
    this.movies = new MovieHash();
    this.people = new PeopleHash();
    this.sortAlgorithm = new InsertionSort();
  }

  public MovidaCore(MovieHash M, PeopleHash P ){
    this.movies = M;
    this.people = P;
    this.sortAlgorithm = null;
  }

  public void init_class(){
    this.movies = new MovieHash();
    this.people = new PeopleHash();
  }

  public void printMovies(){
    System.out.println("Film uploaded: " + this.movies.getLength());
    movies.print();
  }

  public void printPeople(){
    System.out.println("People added: " + this.people.getLength());
    people.print();
  }

  @Override
  public void loadFromFile(File f){
    System.out.println("START STREAM");
    Integer i = 0;
    try{
      BufferedReader br = new BufferedReader(new FileReader(f));
      String [] movie = new String [5];
      String [] line = new String [2];
      String stream = "";

      while ((stream = br.readLine()) != null){
        System.out.println(stream);

        line = stream.split(":");

        // Using a switch so even though the movie is describe in a unordered way
        //  it'll still be right
        switch(line[0]){
          case "Title": movie[0] = line[1].trim(); break;
          case "Year": movie[1] = line[1].trim(); break;
          case "Votes": movie[2] = line[1].trim(); break;
          case "Cast": movie[3] = line[1].trim(); break;
          case "Director": movie[4] = line[1].trim(); break;
          case "": this.addMovie(movie); break;
          default: System.out.println("Something went wrong!");
        }
      }

      this.addMovie(movie);

      br.close();
    }
    catch(Exception e){
      System.out.println(e);
    }

    System.out.println("START SORT");

    movies.sort(sortAlgorithm);
    people.sort(sortAlgorithm);

    System.out.println("END SORT");

    System.out.println("END STREAM");
  }

  private void addMovie(String [] movie){
    Person [] cast = new Person [10];
    String [] cast_name = movie[3].split(",");
    int pos = -1;
    Movie temp = null;

    for(int i = 0; i < 10; i++){
      if (i < cast_name.length){
        cast[i] = new Person(cast_name[i].trim());
      }
      else{
        cast[i] = null;
      }
    }


    temp = new Movie(movie[0], new Integer(movie[1]), new Integer(movie[2]), cast, new Person(movie[4], false, movies.getSize()));

    int id_el = movies.upsert(temp);
    this.addPerson(movie[4], false, id_el);
    for(int i = 0; i < 10; i++)
      if (i < cast_name.length)
        this.addPerson(cast_name[i].trim(), true, id_el);
  }

  private void addPerson(String name, boolean type, int id){
    people.upsert(new Person(name, type, id), id);

    System.out.println("Add of person!");
  }

  @Override
	public void saveToFile(File f){}

	@Override
	public void clear(){
    movies.reset();
    people.reset();
  }

	@Override
	public int countMovies(){ return movies.getLength(); }

	@Override
	public int countPeople(){ return people.getLength(); }

	@Override
	public boolean deleteMovieByTitle(String title){ return movies.delete(title); }

  @Override
	public Movie getMovieByTitle(String title){ return movies.search(title); }

  @Override
	public Person getPersonByName(String name){ return people.search(name); }

  @Override
  public Movie[] getAllMovies(){ return movies.toPrimitive(); }

	@Override
	public Person[] getAllPeople(){ return people.toPrimitive(); }

  /**
	 * Ricerca film per titolo.
	 *
	 * Restituisce i film il cui titolo contiene la stringa
	 * <code>title</code> passata come parametro.
	 *
	 * Per il match esatto usare il metodo <code>getMovieByTitle(String s)</code>
	 *
	 * Restituisce un vettore vuoto se nessun film rispetta il criterio di ricerca.
	 *
	 * @param title titolo del film da cercare
	 * @return array di film
	 */
  @Override
	public Movie[] searchMoviesByTitle(String title){ return movies.searchContains(title); }

	/**
	 * Ricerca film per anno.
	 *
	 * Restituisce i film usciti in sala nell'anno
	 * <code>anno</code> passato come parametro.
	 *
	 * Restituisce un vettore vuoto se nessun film rispetta il criterio di ricerca.
	 *
	 * @param year anno del film da cercare
	 * @return array di film
	 */
  @Override
	public Movie[] searchMoviesInYear(Integer year){ return movies.searchByKey(year); }

	/**
	 * Ricerca film per regista.
	 *
	 * Restituisce i film diretti dal regista il cui nome � passato come parametro.
	 *
	 * Restituisce un vettore vuoto se nessun film rispetta il criterio di ricerca.
	 *
	 * @param name regista del film da cercare
	 * @return array di film
	 */
	@Override
  public Movie[] searchMoviesDirectedBy(String name){ return movies.searchByKey(name); }

	/**
	 * Ricerca film per attore.
	 *
	 * Restituisce i film a cui ha partecipato come attore
	 * la persona il cui nome � passato come parametro.
	 *
	 * Restituisce un vettore vuoto se nessun film rispetta il criterio di ricerca.
	 *
	 * @param name attore coinvolto nel film da cercare
	 * @return array di film
	 */
	@Override
  public Movie[] searchMoviesStarredBy(String name){
    Person actor = people.search(name);

    if (actor.getMovieSize() <= 0)
      return null;

    IList<Integer> ids = actor.getMovies();
    Movie[] out = new Movie[ids.getSize()];
    int i = 0;

    for (INode2<Integer> iter = ids.getHead(); iter != null; iter = iter.getNext(), i++)
      out[i] = movies.getFromId(iter.getValue());

    System.out.println("Size: " + out.length);

    return (out.length > 0) ?  out : null;
  }

	/**
	 * Ricerca film pi� votati.
	 *
	 * Restituisce gli <code>N</code> film che hanno
	 * ricevuto pi� voti, in ordine decrescente di voti.
	 *
	 * Se il numero di film totali � minore di N restituisce tutti i film,
	 * comunque in ordine.
	 *
	 * @param N numero di film che la ricerca deve resistuire
	 * @return array di film
	 */
	@Override
  public Movie[] searchMostVotedMovies(Integer N){ return movies.searchMostOf(N, "votes"); }

	/**
	 * Ricerca film pi� recenti.
	 *
	 * Restituisce gli <code>N</code> film pi� recenti,
	 * in base all'anno di uscita in sala confrontato con l'anno corrente.
	 *
	 * Se il numero di film totali � minore di N restituisce tutti i film,
	 * comunque in ordine.
	 *
	 * @param N numero di film che la ricerca deve resistuire
	 * @return array di film
	 */
	@Override
  public Movie[] searchMostRecentMovies(Integer N){ return movies.searchMostOf(N, "year"); }

	/**
	 * Ricerca gli attori pi� attivi.
	 *
	 * Restituisce gli <code>N</code> attori che hanno partecipato al numero
	 * pi� alto di film
	 *
	 * Se il numero di attori � minore di N restituisce tutti gli attori,
	 * comunque in ordine.
	 *
	 * @param N numero di attori che la ricerca deve resistuire
	 * @return array di attori
	 */
	@Override
  public Person[] searchMostActiveActors(Integer N){ return people.searchMostOf(N); }
}
