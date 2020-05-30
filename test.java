import movida.mackseverini.*;
import movida.commons.*;

import java.io.File;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class test {

  public static void main(String args[]){
    // MovidaDB mb = new MovidaDB();
    // MovidaCore mb = new MovidaCore();
    // mb.init_class();
    //
    // mb.loadFromFile(new File("movida/assets/esempio-formato-dati.txt"));
    //
    // mb.printMovies();
    // mb.printPeople();

    System.out.println("CONSTRUCTION");

    IntHash hash = new IntHash();
    int max = 50;
    int min = -50;

    System.out.println("LET'S ADD UP");

    for (int i = 0; i < 35; i++)
      hash.insert(ThreadLocalRandom.current().nextInt(min, max));

    System.out.println("LET'S PRINT!");

    hash.print();

    System.out.println("THE END");
  }
}
