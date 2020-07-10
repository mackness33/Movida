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

public class MovidaDB implements movida.commons.IMovidaDB {
  private MovieHash<Movie> movies;
  private PeopleHash people;

  public MovidaDB(){
    this.movies = null;
    this.people = null;
  }

  public MovidaDB(MovieHash M, PeopleHash P){
    this.movies = M;
    this.people = P;
  }

  public void init_class(){
    this.movies = new MovieHash();
    this.people = new PeopleHash();
  }

  public void printMovies(){
    System.out.println("Film uploaded: " + this.movies.getSize());
    movies.print();
  }

  public void printPeople(){
    System.out.println("People added: " + this.people.getSize());
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

    System.out.println("END STREAM");
  }

  private void addMovie(String [] movie){
    Person [] cast = new Person [10];
    String [] cast_name = movie[3].split(",");
    int pos = -1;
    Movie temp = null;

    this.addPerson(movie[4]);

    for(int i = 0; i < 10; i++){
      if (i < cast_name.length){
        this.addPerson(cast_name[i].trim());
        cast[i] = new Person(cast_name[i].trim());
      }
      else{
        cast[i] = null;
      }
    }


    temp = new Movie(movie[0], new Integer(movie[1]), new Integer(movie[2]), cast, new Person(movie[4]));

    movies.upsert(temp);

    System.out.println("Add the ugly asses up!" + temp.getYear());
  }

  private void addPerson(String name){
    Person temp = new Person(name);

    if (!people.search(temp)){
      people.insert(temp);
    }
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
