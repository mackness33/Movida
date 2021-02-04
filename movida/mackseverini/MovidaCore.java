package movida.mackseverini;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import java.lang.Integer;

import movida.commons.Movie;
import movida.commons.Person;
import movida.commons.MapImplementation;
import movida.commons.SortingAlgorithm;
import movida.commons.Collaboration;

import movida.mackseverini.Search;
import movida.mackseverini.MovieAbr;
import movida.mackseverini.MovieHash;
import movida.mackseverini.PeopleHash;
import movida.mackseverini.CollabGraph;
import movida.mackseverini.InsertionSort;

public class MovidaCore implements movida.commons.IMovidaDB, movida.commons.IMovidaSearch, movida.commons.IMovidaConfig, movida.commons.IMovidaCollaborations{
  private IMovieMap<Movie> movies;
  private IPersonMap<Person> people;
  private CollabGraph graph;
  private IAlg sortAlgorithm;

  public MovidaCore(){
    this.graph = new CollabGraph();
    // if("MovidaConfig usa hash")
    // this.movies = new MovieHash();
    this.movies = null;
    // this.people = new PeopleHash();
    this.people = null;
    // else
      // this.movies = new MovieAbr();
      // this.people = new PeopleAbr();
    // if("MovidaConfig usa InsertionSort")
    // this.sortAlgorithm = new InsertionSort();
    this.sortAlgorithm = null;
    // else
      // this.sortAlgorithm = new MergeSort();
  }

  public MovidaCore(IMovieMap M, IPersonMap P ){
    this.graph = new CollabGraph();
    this.movies = M;
    this.people = P;
    this.sortAlgorithm = null;
  }

  public void init_class(){
    this.movies = new MovieHash();
    this.people = new PeopleHash();
  }

  /**
   * Carica i dati da un file, organizzato secondo il formato MOVIDA (vedi esempio-formato-dati.txt)
   *
   * Un film e' identificato in modo univoco dal titolo (case-insensitive), una persona dal nome (case-insensitive).
   * Semplificazione: non sono gestiti omonimi e film con lo stesso titolo.
   *
   * I nuovi dati sono aggiunti a quelli gia' caricati.
   *
   * Se esiste un film con lo stesso titolo il record viene sovrascritto.
   * Se esiste una persona con lo stesso nome non ne viene creata un'altra.
   *
   * Se il file non rispetta il formato, i dati non sono caricati e
   * viene sollevata un'eccezione.
   *
   * @param f il file da cui caricare i dati
   *
   * @throws MovidaFileException in caso di errore di caricamento
   */
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

      graph.print();
      br.close();
    }
    catch(Exception e){
      System.out.println(e);
    }

    System.out.println("START SORT");

    movies.sort(sortAlgorithm, true);
    people.sort(sortAlgorithm, true);

    System.out.println("END SORT");

    System.out.println("END STREAM");
  }

  private void addMovie(String [] movie){
    Person [] cast = new Person [10];
    String [] cast_name = movie[3].split(",");
    int pos = -1;
    Movie temp = null;

    for(int i = 0; i < 10; i++)
      if (i < cast_name.length)
        cast[i] = new Person(cast_name[i].trim());
      else
        cast[i] = null;


    temp = new Movie(movie[0], new Integer(movie[1]), new Integer(movie[2]), cast, new Person(movie[4], false, movies.getSize()));

    int id_el = movies.upsert(temp);
    this.addPerson(movie[4], false, id_el);
    for(int i = 0; i < 10; i++)
      if (i < cast_name.length)
        this.addPerson(cast_name[i].trim(), true, id_el);

    graph.addMovie(temp);
  }

  private void addPerson(String name, boolean type, int id){
    people.upsert(new Person(name, type, id), id);

    System.out.println("Add of person!");
  }

  /**
  * Salva tutti i dati su un file.
  *
  * Il file e' sovrascritto.
  * Se non e' possibile salvare, ad esempio per un problema di permessi o percorsi,
  * viene sollevata un'eccezione.
  *
  * @param f il file su cui salvare i dati
  *
  * @throws MovidaFileException in caso di errore di salvataggio
  */
  @Override
	public void saveToFile(File f){}

  /**
  * Cancella tutti i dati.
  *
  * Sara' quindi necessario caricarne altri per proseguire.
  */
	@Override
	public void clear(){
    movies.reset();
    people.reset();
  }

  /**
  * Restituisce il numero di film
  *
  * @return numero di film totali
  */
	@Override
	public int countMovies(){ return movies.getLength(); }

  /**
  * Restituisce il numero di persone
  *
  * @return numero di persone totali
  */
	@Override
	public int countPeople(){ return people.getLength(); }

  /**
  * Cancella il film con un dato titolo, se esiste.
  *
  * @param title titolo del film
  * @return <code>true</code> se il film � stato trovato e cancellato,
  * 		   <code>false</code> in caso contrario
  */
	@Override
	public boolean deleteMovieByTitle(String title){ return this.deleteFromGraphAndMap(title); }

  private boolean deleteFromGraphAndMap(String title){
    Movie film = movies.search(title);
    boolean isDeleted =  movies.delete(title);

    if (film != null || isDeleted)
      return graph.delMovie(film) && isDeleted;

    return false;
  }

  /**
  * Restituisce il record associato ad un film
  *
  * @param title il titolo del film
  * @return record associato ad un film
  */
  @Override
	public Movie getMovieByTitle(String title){ return movies.search(title); }

  /**
  * Restituisce il record associato ad una persona, attore o regista
  *
  * @param name il nome della persona
  * @return record associato ad una persona
  */
  @Override
	public Person getPersonByName(String name){ return people.search(name); }


  /**
  * Restituisce il vettore di tutti i film
  *
  * @return array di film
  */
  @Override
  public Movie[] getAllMovies(){ return movies.toPrimitive(); }

  /**
  * Restituisce il vettore di tutte le persone
  *
  * @return array di persone
  */
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

  /**
	 * Seleziona l'implementazione del dizionario
	 *
	 * Se il dizionario scelto non e' supportato dall'applicazione
	 * la configurazione non cambia
	 *
	 * @param m l'implementazione da selezionare
	 * @return <code>true</code> se la configurazione e' stata modificata, <code>false</code> in caso contrario
	 */
	public boolean setMap(MapImplementation map){
    System.out.println("actual Map: " + ((this.movies != null) ? this.movies.getType() : null));
    System.out.println("input Map: " + map);
    if (map == null)
      return false;
    else if (this.movies != null && map == this.movies.getType())
      return false;

    Movie[] films = (this.movies != null) ? this.movies.toPrimitive() : null;
    Person[] persons = (this.people != null) ? this.people.toPrimitive() : null;

    switch (map){
      case HashConcatenamento: {
        this.movies = new MovieHash();
        this.people = new PeopleHash();
      }break;

      // case ABR: System.out.println("Tring ABR!");
      case ABR: {
        this.movies = new MovieAbr();
        this.people = new PeopleHash();
      }break;

      default: {
        System.out.println("This map has not been implemented!");
        return false;
      }
    }

    if (films != null && persons != null){
      for (int  i = 0; i < films.length; i++)
        this.movies.insert(films[i]);

      for (int  i = 0; i < persons.length; i++)
        this.people.insert(persons[i]);
    }

    return true;
  }

  /**
	 * Seleziona l'algoritmo di ordinamento.
	 * Se l'algortimo scelto non e' supportato dall'applicazione
	 * la configurazione non cambia
	 *
	 * @param a l'algoritmo da selezionare
	 * @return <code>true</code> se la configurazione e' stata modificata, <code>false</code> in caso contrario
	 */
	public boolean setSort(SortingAlgorithm alg){
    // if ()
    System.out.println("actual Alg: " + ((this.sortAlgorithm != null) ? this.sortAlgorithm.getType() : null));
    System.out.println("input Alg: " + alg);
    if (alg == null)
      return false;
    else if (this.sortAlgorithm != null && alg == this.sortAlgorithm.getType())
      return false;

    switch (alg){
      case InsertionSort: this.sortAlgorithm = new InsertionSort();break;
      case MergeSort: System.out.println("Tring MergeSort!");//this.sortAlgorithm = new MergeSort();break;
      default: {
        System.out.println("This sort has not been implemented!");
        return false;
      }
    }

    return true;
  }


	/**
	 * Identificazione delle collaborazioni
	 * dirette di un attore
	 *
	 * Restituisce gli attori che hanno partecipato
	 * ad almeno un film con l'attore
	 * <code>actor</code> passato come parametro.
	 *
	 * @param actor attore di cui cercare i collaboratori diretti
	 * @return array di persone
	 */
	public Person[] getDirectCollaboratorsOf(Person actor) { return graph.getAdiacencesOf(actor); }

	/**
	 * Identificazione del team di un attore
	 *
	 * Restituisce gli attori che hanno
	 * collaborazioni dirette o indirette
	 * con l'attore <code>actor</code> passato come parametro.
	 *
	 * Vedi slide per maggiori informazioni su collaborazioni e team.
	 *
	 * @param actor attore di cui individuare il team
	 * @return array di persone
	 */
	public Person[] getTeamOf(Person actor) { return graph.visitStartingFrom(actor); }

	/**
	 * Identificazione dell'insieme di collaborazioni
	 * caratteristiche (ICC) del team di cui un attore fa parte
	 * e che ha lo score complessivo pi� alto
	 *
	 * Vedi slide per maggiori informazioni su score e ICC.
	 *
	 * Si noti che questo metodo richiede l'invocazione
	 * del metodo precedente <code>getTeamOf(Person actor)</code>
	 *
	 * @param actor attore di cui individuare il team
	 * @return array di collaborazioni
	 */
	public Collaboration[] maximizeCollaborationsInTheTeamOf(Person actor){ return graph.getCollaborationMaxST(actor); }
}
