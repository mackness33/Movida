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

    Set<Integer, IntegerTest> hash = new Set<Integer, IntegerTest>(5, value);
    int max = 50;
    int min = -49;

    System.out.println("LET'S ADD UP");

    for (int i = 0; i < 35; i++)
      hash.insert(ThreadLocalRandom.current().nextInt(min, max), ThreadLocalRandom.current().nextInt(-1000, 1001));

    set.makeSet(44);
    set.makeSet(52);
    set.makeSet(88);
    set.makeSet(21);
    set.makeSet(10);
    set.makeSet(77);
    set.makeSet(64);
    set.makeSet(8);
    set.makeSet(14);

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
