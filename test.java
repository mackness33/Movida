import movida.classes.MovidaDB;
import java.io.File;
import java.util.LinkedList;
import movida.classes.Movie;

public class test {

  public static void main(String args[]){
    LinkedList<Movie> M = new LinkedList<Movie>();
    MovidaDB mb = new MovidaDB(M);

    mb.loadFromFile(new File("movida/assets/esempio-formato-dati.txt"));

    mb.printMovies();
  }
}
