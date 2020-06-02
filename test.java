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

    // System.out.println("CONSTRUCTION");
    //
    // Hash<Integer> hash = new Hash<Integer>();
    // int max = 50;
    // int min = -49;
    //
    // System.out.println("LET'S ADD UP");
    //
    // for (int i = 0; i < 35; i++)
    //   hash.insert(ThreadLocalRandom.current().nextInt(min, max), ThreadLocalRandom.current().nextInt(-1000, 1001));
    //
    // hash.insert(33, 0);
    // hash.insert(33, 1);
    // hash.insert(33, 2);
    // hash.insert(33, 3);
    //
    // System.out.println("DELETING!");
    //
    // System.out.println("SEARCH: " + hash.search(33, 1));
    // hash.delete(33, 0);
    // hash.delete(33, 2);
    //
    // System.out.println("SEARCH: " + hash.search(33, 3));
    // System.out.println("SEARCH: " + hash.search(33, 8));
    // System.out.println("SEARCH: " + hash.search(33, 1));
    // System.out.println("SEARCH: " + hash.search(33, 0));
    //
    //
    //
    // System.out.println("LET'S PRINT!");
    //
    // hash.print();
    //
    // hash.insert(33, 0);
    // hash.insert(33, 1);
    // hash.insert(33, 2);
    // hash.insert(33, 3);
    // hash.insert(33, 3);
    //
    // System.out.println("LET'S PRINT!");
    //
    // hash.print();
    //
    // System.out.println("DELETING!");
    //
    // System.out.println("SEARCH: " + hash.search(33, 1));
    // hash.delete(33, 0);
    // hash.delete(33, 2);
    // hash.delete(33, 3);
    //
    // System.out.println("SEARCH: " + hash.search(33, 3));
    //
    // System.out.println("LET'S PRINT!");
    //
    // hash.print();
    //
    // System.out.println("THE END");

    System.out.println("CONSTRUCTION");

    Set<Integer, Integer> set = new Set<Integer, Integer>("value");
    int max = 50;
    int min = -49;

    System.out.println("LET'S ADD UP");

    // for (int i = 0; i < 35; i++)
    //   hash.insert(ThreadLocalRandom.current().nextInt(min, max), ThreadLocalRandom.current().nextInt(-1000, 1001));

    for (int i = 0; i < 35; i++)
      set.makeSet(ThreadLocalRandom.current().nextInt(0, 45), ThreadLocalRandom.current().nextInt(-2000, 2000));

    set.makeSet(45, 0);
    set.makeSet(46, 1);
    set.makeSet(47, 2);
    set.makeSet(48, 3);
    set.makeSet(49, 4);
    System.out.println("LET'S PRINT!");

    set.print();

    System.out.println("FIND 46: " + set.find(46));
    System.out.println("FIND 48: " + set.find(48));

    System.out.println("Union 2 with 1");
    set.union(47, 46);
    System.out.println("Union 4 with 2");
    set.union(49, 47);


    System.out.println("LET'S PRINT!");

    set.print();

    // System.out.println("SEARCH: " + hash.search(33, 1));
    // hash.delete(33, 0);
    // hash.delete(33, 2);
    //
    // System.out.println("SEARCH: " + hash.search(33, 3));
    // System.out.println("SEARCH: " + hash.search(33, 8));
    // System.out.println("SEARCH: " + hash.search(33, 1));
    // System.out.println("SEARCH: " + hash.search(33, 0));
    //
    //
    //
    // System.out.println("LET'S PRINT!");
    //
    // hash.print();
    //
    // hash.insert(33, 0);
    // hash.insert(33, 1);
    // hash.insert(33, 2);
    // hash.insert(33, 3);
    // hash.insert(33, 3);
    //
    // System.out.println("LET'S PRINT!");
    //
    // hash.print();
    //
    // System.out.println("DELETING!");
    //
    // System.out.println("SEARCH: " + hash.search(33, 1));
    // hash.delete(33, 0);
    // hash.delete(33, 2);
    // hash.delete(33, 3);
    //
    // System.out.println("SEARCH: " + hash.search(33, 3));
    //
    // System.out.println("LET'S PRINT!");
    //
    // hash.print();
    //
    // System.out.println("THE END");
  }
}
