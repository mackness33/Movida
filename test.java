import movida.mackseverini.MovidaDB;
import movida.mackseverini.MovidaCore;
import movida.mackseverini.Search;
import java.io.File;
import java.util.LinkedList;
import movida.commons.Movie;
import java.util.concurrent.ThreadLocalRandom;

public class test {

  public static void main(String args[]){
    // MovidaDB mb = new MovidaDB();
    MovidaCore mb = new MovidaCore();
    mb.init_class();

    mb.loadFromFile(new File("movida/assets/esempio-formato-dati.txt"));

    mb.printMovies();
    mb.printPeople();
  }
}
