import movida.mackseverini.*;
import movida.mackseverini.List;
import movida.mackseverini.Node2;
import movida.commons.*;

import java.io.File;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class test {

  public static void main(String args[]){
    // List<Integer> L = new List<Integer>(20);
    //
    // System.out.println("List: ");
    // L.print();
    //
    // L.addHead(5);
    //
    // L.addTail(8);
    //
    // System.out.println("List: ");
    // L.print();
    //
    // for (int i = 0; i < 35; i++)
    //   L.addAt(ThreadLocalRandom.current().nextInt(-1000, 1001), 3);
    //
    // L.addAt(400000, 3);
    //
    // L.update(9000, 4);
    // L.update(7000, L.getSize()-1);
    //
    // System.out.println("List: ");
    // L.print();
    //
    // L.delHead();
    // L.delTail();
    //
    // L.update(6000, L.getSize()-1);
    //
    // L.update(15000, 15);
    // L.update(888000, 14);
    // L.update(999000, 13);
    //
    // L.delEl(15000);
    //
    // L.delAt(14);
    //
    // System.out.println("List: ");
    // L.print();
    //
    // System.out.println("Search: " + L.search(999000));
    //
    // Array<Integer> test = L.toArray();
    //
    // System.out.println("Array: ");
    //
    // for(int i = 0; i < test.length; i++)
    //   System.out.println(test.get(i));

    // MovidaDB mb = new MovidaDB();
    // // MovidaCore mb = new MovidaCore();
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
    //   hash.insert(ThreadLocalRandom.current().nextInt(-1000, 1001));
    //
    // hash.insert(33);
    // hash.insert(133);
    // hash.insert(233);
    // hash.insert(333);
    //
    // System.out.println("DELETING!");
    //
    // System.out.println("SEARCH: " + hash.search(133));
    // hash.delete(33);
    // hash.delete(233);
    //
    // System.out.println("SEARCH: " + hash.search(333));
    // System.out.println("SEARCH: " + hash.search(833));
    // System.out.println("SEARCH: " + hash.search(133));
    // System.out.println("SEARCH: " + hash.search(33));
    //
    //
    //
    // System.out.println("LET'S PRINT!");
    //
    // hash.print();
    //
    // hash.insert(33);
    // hash.insert(133);
    // hash.insert(233);
    // hash.insert(333);
    // hash.insert(333);
    //
    // System.out.println("LET'S PRINT!");
    //
    // hash.print();
    //
    // System.out.println("DELETING!");
    //
    // System.out.println("SEARCH: " + hash.search(133));
    // hash.delete(33);
    // hash.delete(233);
    // hash.delete(333);
    //
    // System.out.println("SEARCH: " + hash.search(333));
    //
    // System.out.println("LET'S PRINT!");
    //
    // hash.print();
    //
    // System.out.println("THE END");

    // System.out.println("CONSTRUCTION");
    //
    // Set<Integer, Integer> set = new Set<Integer, Integer>("value");
    // int max = 50;
    // int min = -49;
    //
    // System.out.println("LET'S ADD UP");

    // for (int i = 0; i < 35; i++)
    //   hash.insert(ThreadLocalRandom.current().nextInt(min, max), ThreadLocalRandom.current().nextInt(-1000, 1001));

    System.out.println("CONSTRUCTION");

    Set<Integer, Integer> set = new Set<Integer, Integer>();

    for (int i = 0; i < 35; i++)
      set.makeSet(ThreadLocalRandom.current().nextInt(0, 45), ThreadLocalRandom.current().nextInt(-2000, 2000));

    set.makeSet(45, 0);
    set.makeSet(46, 1);
    set.makeSet(47, 2);
    set.makeSet(48, 3);
    set.makeSet(49, 4);
    set.makeSet(49, 15);
    set.makeSet(49, 78);
    System.out.println("LET'S PRINT!");

    set.print();

    System.out.println("FIND 46: " + set.find(46));
    System.out.println("FIND 48: " + set.find(48));
    System.out.println("FIND 49: " + set.find(49));

    System.out.println("Union 2 with 1");
    set.union(47, 46);
    System.out.println("Union 4 with 2");
    set.union(49, 47);

    for (int i = 0; i < 35; i++)
      set.union(ThreadLocalRandom.current().nextInt(0, 45), ThreadLocalRandom.current().nextInt(0, 45));

    System.out.println("LET'S PRINT!");

    set.print();

    System.out.println("SIZE: " + set.getSize());

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
