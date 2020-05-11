import movida.mackseverini.MovidaDB;
import movida.mackseverini.Search;
import java.io.File;
import java.util.LinkedList;
import movida.commons.Movie;
import java.util.concurrent.ThreadLocalRandom;

public class test {

  public static void main(String args[]){
    MovidaDB mb = new MovidaDB();
    mb.init_class();

    mb.loadFromFile(new File("movida/assets/esempio-formato-dati.txt"));

    mb.printMovies();
    mb.printPeople();

    //test I use, can be cleaned!

    // int min = 1, max = 70;
    // LinkedList<Integer> M = new LinkedList<Integer>();
    // // nextInt is normally exclusive of the top value,
    // // so add 1 to make it inclusive
    // int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
    //
    // for (int i = min; i <= max; i++)
    //   M.add(i);
    //
    // Integer rst_binary = Search.binarySearch(M, randomNum);
    // Integer rst_dumb = Search.dumbSearch(M, randomNum);
    //
    // System.out.println("size = " + M.size());
    // System.out.println("random = " + randomNum);
    // System.out.println("result binary = " + rst_binary);
    // System.out.println("result dumb = " + rst_dumb);
  }
}
