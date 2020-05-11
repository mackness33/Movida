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

public class MovidaDB implements movida.commons.IMovidaDB {
  private LinkedList<Movie> movies;
  private LinkedList<Person> people;

  public MovidaDB(){
    this.movies = null;
    this.people = null;
  }

  public MovidaDB(LinkedList<Movie> M, LinkedList<Person> P){
    this.movies = M;
    this.people = P;
  }

  public void init_class(){
    this.movies = new LinkedList<Movie>();
    this.people = new LinkedList<Person>();
  }

  public void printMovies(){
    // System.out.println("Before print!");
    System.out.println("Film uploaded: " + this.movies.size());
    Iterator<Movie> iterator = this.movies.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      System.out.println();

    }
    // for (Movie film : this.movies.iterator())
    //   System.out.println(film);
    // System.out.println("After print!");
  }

  public void printPeople(){
    // System.out.println("Before print!");
    System.out.println("People added: " + this.people.size());
    Iterator<Person> iterator = this.people.iterator();
    while (iterator.hasNext())
      System.out.println(iterator.next());
    // for (Movie film : this.movies.iterator())
    //   System.out.println(film);
    // System.out.println("After print!");
  }

  @Override
  public void loadFromFile(File f){
    System.out.println("START STREAM");

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

    System.out.println("END STREAM");
  }

  private void addMovie(String [] movie){
    Person [] people = new Person [10];
    String [] useless = movie[3].split(",");
    int pos = -1;
    Movie temp = null;

    this.addPerson(movie[4]);

    for(int i = 0; i < 10; i++){
      // System.out.println("i : " + i);
      if (i < useless.length){
        this.addPerson(useless[i].trim());
        people[i] = new Person(useless[i].trim());
      }
      else
        people[i] = null;
    }

    temp = new Movie(movie[0], new Integer(movie[1]), new Integer(movie[2]), people, new Person(movie[4]));
    pos = Search.<Movie, Movie>dumbSearch(movies, temp);

    System.out.println("pos: " + pos);
    if (pos < 0)
      movies.add(temp);
    else
      movies.get(pos).update(temp);


    // System.out.println("Add the ugly asses up!");
  }

  private void addPerson(String name){
    Person temp = null;
    int pos = -1;
    temp = new Person(name);
    pos = Search.<Person, Person>dumbSearch(people, temp);

    System.out.println("pos: " + pos);
    if (pos < 0)
      people.add(temp);
    else
      System.out.println("ALREADY THERE");


    System.out.println("Add of person!");
  }

  @Override
	public void saveToFile(File f){}

	@Override
	public void clear(){}

	@Override
	public int countMovies(){ return 0; }

	@Override
	public int countPeople(){ return 0; }

	@Override
	public boolean deleteMovieByTitle(String title){ return false; }

  @Override
	public Movie getMovieByTitle(String title){ return null; }

  @Override
	public Person getPersonByName(String name){ return null; }

  @Override
	public Movie[] getAllMovies(){ return null; }

	@Override
	public Person[] getAllPeople(){ return null; }
}
