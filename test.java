import movida.mackseverini.MovidaDB;
import java.io.File;
import java.util.LinkedList;
import movida.commons.Movie;

public class test {

  public static void main(String args[]){
    // LinkedList<Movie> M = new LinkedList<Movie>();
    // MovidaDB mb = new MovidaDB(M);
    MovidaDB mb = new MovidaDB();
    mb.init_class();

    mb.loadFromFile(new File("movida/assets/esempio-formato-dati.txt"));

    mb.printMovies();
  }
}
