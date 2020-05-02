package movida.classes;

import java.util.LinkedList;
import java.util.Iterator;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class MovidaDB implements movida.interfaces.IMovidaDB {
  @Override
  public void loadFromFile(File f){
    System.out.println("START STREAM");

    try{

      // Creates a FileReader Object
      // FileReader fr = new FileReader(file);
      // char [] a = new char[50];
      // fr.read(a);   // reads the content to the array

      BufferedReader br = new BufferedReader(new FileReader(f));

      String st;
      while ((st = br.readLine()) != null)
        System.out.println(st);

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
