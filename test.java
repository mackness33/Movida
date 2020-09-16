// import movida.mackseverini.*;
import movida.mackseverini.MovidaDB;
import movida.mackseverini.MovidaCore;
import movida.mackseverini.Hash2;
import movida.mackseverini.MovieHash;
import movida.mackseverini.PeopleHash;
import movida.mackseverini.IList;
import movida.mackseverini.List;
import movida.mackseverini.Node2;
import movida.mackseverini.INode2;
import movida.mackseverini.Array;
import movida.mackseverini.KeyHash;
import movida.mackseverini.InsertionSort;
import movida.mackseverini.KeyNode;
import movida.mackseverini.KeyList;
// import movida.mackseverini.MergeSort;
import movida.commons.*;

import java.io.File;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class test {

  public static void main(String args[]){
    // test.AlgTest();
    test.movidaTest();
    // Integer a = 5;
    // //
    // System.out.println("res: " + a.compareTo(5));
    // L.print();
    //
    // L.addHead(5);
    //
    // L.addTail(8);
    //
    // System.out.println("List: ");
    // L.print();
    //

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



      // Array<Movie> movies = mb.getAllMoviesArray();
      //
      // System.out.println("ARRAY LENGTH: " + movies.length);
      // System.out.println("TO ARRAY: ");
      // for(int i = 0; i < movies.length; i++)
      //   System.out.println(movies.get(i));
    // System.out.println("CONSTRUCTION");
    //
    // Hash2<Integer> hash = new Hash2<Integer>();
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
    //
    // for (int i = 0; i < 35; i++)
    //   hash.insert(ThreadLocalRandom.current().nextInt(min, max), ThreadLocalRandom.current().nextInt(-1000, 1001));

    // System.out.println("CONSTRUCTION");
    //
    // Set<Integer, Integer> set = new Set<Integer, Integer>();
    //
    // for (int i = 0; i < 35; i++)
    //   set.makeSet(ThreadLocalRandom.current().nextInt(0, 45), ThreadLocalRandom.current().nextInt(-2000, 2000));
    //
    // set.makeSet(45, 0);
    // set.makeSet(46, 1);
    // set.makeSet(47, 2);
    // set.makeSet(48, 3);
    // set.makeSet(49, 4);
    // set.makeSet(49, 15);
    // set.makeSet(49, 78);
    // System.out.println("LET'S PRINT!");
    //
    // set.print();
    //
    // System.out.println("FIND 46: " + set.find(46));
    // System.out.println("FIND 48: " + set.find(48));
    // System.out.println("FIND 49: " + set.find(49));
    //
    // System.out.println("Union 2 with 1");
    // set.union(47, 46);
    // System.out.println("Union 4 with 2");
    // set.union(49, 47);
    //
    // for (int i = 0; i < 35; i++)
    //   set.union(ThreadLocalRandom.current().nextInt(0, 45), ThreadLocalRandom.current().nextInt(0, 45));
    //
    // System.out.println("LET'S PRINT!");
    //
    // set.print();
    //
    // System.out.println("SIZE: " + set.getSize());

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

  public static void AlgTest(){
    Array<Integer> A = new Array<Integer>(35);
    IList<Integer> L = new List<Integer>();

    for (int i = 0; i < A.length; i++){
      A.set(i, ThreadLocalRandom.current().nextInt(-1000, 1001));
      L.addTail(ThreadLocalRandom.current().nextInt(-1000, 1001));
    }

    for (int i = 0; i < A.length; i++)
      System.out.println("POS: " + i + "  VAL: " + A.get(i));
    int j = 0;
    for(INode2<Integer> iterIN = L.getHead(); iterIN != null; iterIN = iterIN.getNext(), j++)
      System.out.println("POS: " + j + "  VAL: " + iterIN.getValue());


    InsertionSort is = new InsertionSort();
    A = is.sort(A);
    L = is.sort(L);

    System.out.println("SORTED: ");
    for (int i = 0; i < A.length; i++)
      System.out.println("POS: " + i + "  VAL: " + A.get(i));

    j = 0;
    for(INode2<Integer> iterIN = L.getHead(); iterIN != null; iterIN = iterIN.getNext(), j++)
      System.out.println("POS: " + j + "  VAL: " + iterIN.getValue());

  }



  public static void movidaTest(){
    MovidaCore mb = new MovidaCore();
    // MovidaCore mb = new MovidaCore();
    // mb.init_class();

    mb.loadFromFile(new File("movida/assets/esempio-formato-dati.txt"));

    mb.deleteMovieByTitle("Die Hard");
    mb.deleteMovieByTitle(null);

    System.out.println("Hello buddy: \n\r" + mb.getMovieByTitle("Cape Fear"));
    System.out.println("Hello fam: \n\r" + mb.getPersonByName("Harrison Ford"));

    System.out.println("MapChange1: \n\r" + mb.setMap(movida.commons.MapImplementation.HashConcatenamento));
    System.out.println("MapChange2: \n\r" + mb.setMap(movida.commons.MapImplementation.ABR));

    System.out.println("AlgChange1: \n\r" + mb.setSort(movida.commons.SortingAlgorithm.InsertionSort));
    System.out.println("AlgChange2: \n\r" + mb.setSort(movida.commons.SortingAlgorithm.MergeSort));

    Movie[] movies = mb.getAllMovies();
    Person[] people = mb.getAllPeople();
/*
    System.out.println("ARRAY LENGTH: " + people.length);
    System.out.println("TO ARRAY: ");
    for(int i = 0; i < people.length; i++)
      System.out.println(people[i]);

    System.out.println("ARRAY LENGTH: " + movies.length);
    System.out.println("TO ARRAY: ");
    for(int i = 0; i < movies.length; i++)
      System.out.println(movies[i]);


    System.out.println("\n\rSEARCHBYKEY YEAR: ");
    Movie[] years = mb.searchMoviesInYear(1997);

    if (years == null)
      System.out.println("bigass problem");
    for(int i = 0; i < years.length; i++)
      System.out.println(years[i]);


    System.out.println("\n\rSEARCHMOSTOF RATES: ");
    Movie[] rates = mb.searchMostVotedMovies(2);

    for(int i = 0; i < rates.length; i++)
      System.out.println(rates[i]);

    System.out.println("\n\rSEARCHCONTAINS TITLE: ");
    Movie[] tites = mb.searchMoviesByTitle("tive");

    if (tites == null)
      System.out.println("NO MOVIES");
    else{
      for(int i = 0; i < tites.length; i++)
      System.out.println(tites[i]);
    }

    System.out.println("\n\rSEARCHBYKEY DIRECTORS: ");
    Movie[] dirs = mb.searchMoviesDirectedBy("Martin Scorsese");

    for(int i = 0; i < dirs.length; i++)
      System.out.println(dirs[i]);


    System.out.println("\n\rSEARCHMOSTOF YEARS: ");
    Movie[] dates = mb.searchMostRecentMovies(7);

    for(int i = 0; i < dates.length; i++)
      System.out.println(dates[i]);

    System.out.println("\n\rSEARCHMOSTOF ACTORS: ");
    Movie[] actors = mb.searchMoviesStarredBy("Harrison Ford");

    for(int i = 0; i < actors.length; i++)
      System.out.println(actors[i]);

    System.out.println("\n\rSEARCHMOSTACTIVE ACTORS: ");
    Person[] active = mb.searchMostActiveActors(5);

    for(int i = 0; i < active.length; i++)
      System.out.println(active[i]);
      */
  }
}
