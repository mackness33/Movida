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
    int min = -49;

    System.out.println("LET'S ADD UP");

    for (int i = 0; i < 35; i++)
      hash.<Integer>insert(ThreadLocalRandom.current().nextInt(min, max), ThreadLocalRandom.current().nextInt(-1000, 1001));

    hash.<Integer>insert(33, 0);
    hash.<Integer>insert(33, 1);
    hash.<Integer>insert(33, 2);
    hash.<Integer>insert(33, 3);

    System.out.println("DELETING!");

    hash.delete(33, 0);
    hash.delete(33, 1);
    hash.delete(33, 2);
    hash.delete(33, 3);

    System.out.println("LET'S PRINT!");

    hash.print();

    System.out.println("THE END");
  }
}
