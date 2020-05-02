package movida.classes;

import java.util.LinkedList;
import java.util.Iterator;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import Movie;

public class MovidaDB implements movida.interfaces.IMovidaDB {
  private LinkedList<Movie> movies;

  public MovieDB(){
    movies = new LinkedList<Movie>();
  }

  @Override
  public void loadFromFile(File f){
    System.out.println("START STREAM");

    try{
      BufferedReader br = new BufferedReader(new FileReader(f));
      String [] movie = new String [5], [] line = new String [2];
      int i = 0;
      String stream = "";

      while ((stream = br.readLine()) != null){
        System.out.println(st);

        line = stream.split(':');

        // Using a switch so even though the movie is describe in a unordered way
        //  it'll still be right
        switch(line[0]){
          case "Title": movie[0] = line[1].trim(); break;
          case "Year": movie[1] = line[1].trim(); break;
          case "Director": movie[2] = line[1].trim(); break;
          case "Cast": movie[3] = line[1].trim(); break;
          case "Votes": movie[4] = line[1].trim(); break;
          case "":{
            movies

          }
        }

      }

      br.close();
    }
    catch(Exception e){
      System.out.println(e);
    }

    System.out.println("END STREAM");
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
