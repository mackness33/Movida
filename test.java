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
import movida.mackseverini.ABR;
import movida.mackseverini.MovieAbr;
import movida.mackseverini.Graph;
import movida.mackseverini.CollabGraph;
import movida.mackseverini.Arch;
import movida.mackseverini.IArch;
import movida.mackseverini.Set;
import movida.mackseverini.PriorityQueue;
import movida.mackseverini.Vertex;
import movida.mackseverini.Queue;
// import movida.mackseverini.MergeSort;
import movida.commons.*;

import java.io.File;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class test {

  public static class TestInteger implements Comparable<TestInteger>
  {
    int value;

    public TestInteger(int value) {this.value = value;}

    @Override
    public int compareTo(TestInteger valueToCompare) {return this.value - valueToCompare.value;}

    @Override
    public String toString() {return Integer.toString(this.value);}
  }

  public static void main(String args[]){
    // test.algTest();
    // test.graphTest();
    // test.movidaTest();
    // test.queueTest();
    // test.listTest();
    test.abrTest();
    // test.AlgTest();
    // test.graphMovidaTest();
    // test.priorityQueueTest();
  }

  public static void abrTest()
  {
    MovieAbr movs = new MovieAbr();

    Person darabont = new Person("Frank Darabont", true, 2);
    Person coppola = new Person("Francis Ford Coppola", true, 1);
    Person nolan = new Person("Christopher Nolan", true, 3);
    Person jackson = new Person("Peter Jackson", true, 4);
    Person fincher = new Person("David Fincher", true, 5);
    Person tarantino = new Person("Quentin Tarantino", true, 6);
    Person spielberg = new Person("Stephen Spielberg", true, 7);
    Person lumet = new Person("Sidney Lumet", true, 8);
    Person brando = new Person("Marlon Brando", false, 1);
    Person freeman = new Person("Morgan Freeman", false, 2);
    Person gunton = new Person("Bob Gunton", false, 2);
    Person pacino = new Person("Al Pacino", false, 3);
    Person bloom = new Person("Orlando Bloom", false, 4);
    Person bale = new Person("Christian Bale", false, 5);
    Person travolta = new Person("Jhon Travolta", false, 6);
    Person neeson = new Person("Liam Neeson", false, 7);
    Person fonda = new Person("Henry Fonda", false, 8);

    Person[] cast1 = new Person[1];
    cast1[0] = brando;
    Person[] cast2 = new Person[2];
    cast2[0] = gunton;
    cast2[1] = freeman;
    Person[] cast3 = new Person[1];
    cast3[0] = pacino;
    Person[] cast4 = new Person[1];
    cast4[0] = bloom;
    Person[] cast5 = new Person[1];
    cast5[0] = bale;
    Person[] cast6 = new Person[1];
    cast6[0] = travolta;
    Person[] cast7 = new Person[1];
    cast7[0] = neeson;
    Person[] cast8 = new Person[1];
    cast8[0] = fonda;

    // Top 8 movies according to Imdb
    Movie aliLiberta = new Movie("Le ali della libertà", 1994, 93, cast2, darabont);
    Movie padrino = new Movie("Il padrino", 1972, 92, cast1, coppola);
    Movie cavaliereOscuro = new Movie("Il cavaliere oscuro", 2008, 90, cast5, fincher);
    Movie padrino2 = new Movie("Il padrino - Parte 2", 1974, 90, cast3, nolan);
    Movie parolaGiurati = new Movie("La parola ai giurati", 1957, 90, cast8, lumet);
    Movie signoreAnelliRitornoRe = new Movie("Il signore degli anelli - Il ritorno del re", 2003, 89, cast4, jackson);
    Movie pulpFiction = new Movie("Pulp fiction", 1994, 89, cast6, tarantino);
    Movie schindler = new Movie("Schindler's list", 1993, 89, cast7, spielberg);

    // INSERT
    movs.insert(padrino);
    movs.insert(aliLiberta);
    movs.insert(padrino2);
    movs.insert(signoreAnelliRitornoRe);
    movs.insert(cavaliereOscuro);
    movs.insert(pulpFiction);
    movs.insert(schindler);
    movs.insert(parolaGiurati);

    movs.printTree(0);
  }

  public static void algTest(){
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
    mb.deleteMovieByTitle("The Junitive");
    mb.deleteMovieByTitle("Air Force One");
    mb.deleteMovieByTitle(null);
    /*
    System.out.println("Hello buddy: \n\r" + mb.getMovieByTitle("Cape Fear"));
    System.out.println("Hello fam: \n\r" + mb.getPersonByName("Harrison Ford"));

    System.out.println("MapChange1: \n\r" + mb.setMap(movida.commons.MapImplementation.HashConcatenamento));
    System.out.println("MapChange2: \n\r" + mb.setMap(movida.commons.MapImplementation.ABR));

    System.out.println("AlgChange1: \n\r" + mb.setSort(movida.commons.SortingAlgorithm.InsertionSort));
    System.out.println("AlgChange2: \n\r" + mb.setSort(movida.commons.SortingAlgorithm.MergeSort));
    */

    Movie[] movies = mb.getAllMovies();
    Person[] people = mb.getAllPeople();

    Person[] collabs_of_Tommy = mb.getDirectCollaboratorsOf(new Person("Tommy Lee Jones"));
    Person[] collabs_of_Harrison = mb.getDirectCollaboratorsOf(new Person("Harrison Ford"));

    System.out.println("Tommey LENGTH: " + collabs_of_Tommy.length);
    System.out.println("TO ARRAY: ");
    for(int i = 0; i < collabs_of_Tommy.length; i++)
      System.out.println(collabs_of_Tommy[i]);

    System.out.println("ARRAY LENGTH: " + collabs_of_Harrison.length);
    System.out.println("TO ARRAY: ");
    for(int i = 0; i < collabs_of_Harrison.length; i++)
      System.out.println(collabs_of_Harrison[i]);

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

  public static void graphTest(){
    Graph<Integer, Double> G = new Graph<Integer, Double>();

    // G.addVertex(0);
    // G.addVertex(1);
    // G.addVertex(2);
    // G.addVertex(3);
    // G.addVertex(4);
    // G.addVertex(5);
    // G.addVertex(6);

    // printGraph(G);

    for (int i = 0; i < 50; i++){
      G.addVertex(ThreadLocalRandom.current().nextInt(0, 10));
      // G.addVertex(i);
      G.addArch(new Arch<Integer, Double>(ThreadLocalRandom.current().nextInt(0, 10), ThreadLocalRandom.current().nextInt(0, 10), ThreadLocalRandom.current().nextDouble(-1000, 1001)));
    }


    // G.addArch(new Arch<Integer, Double>(5, 2, 1.0));
    // G.addArch(new Arch<Integer, Double>(2, 5, 5.0));
    // G.addArch(new Arch<Integer, Double>(1, 1, 5.0));
    // G.addArch(new Arch<Integer, Double>(3, 4, 5.0));
    // G.addArch(new Arch<Integer, Double>(4, 3, 5.0));
    // G.addArch(new Arch<Integer, Double>(6, 1, 5.0));
    // G.addArch(new Arch<Integer, Double>(3, 6, 5.0));
    // G.addArch(new Arch<Integer, Double>(3, 1, 5.0));
    // G.addArch(new Arch<Integer, Double>(6, 5, 5.0));
    // G.addArch(new Arch<Integer, Double>(5, 5, 5.0));
    // G.addArch(new Arch<Integer, Double>(2, 3, 2.0));
    // G.addArch(new Arch<Integer, Double>(3, 3, 3.0));
    // G.addArch(new Arch<Integer, Double>(2, 2, 11.0));
    // G.addArch(new Arch<Integer, Double>(2, 2, 16.0));
    // G.addArch(new Arch<Integer, Double>(2, 1, 4.0));

    printGraph(G);

    G.delArch(new Arch<Integer, Double>(2, 2, null));
    G.delVertex(3);

    printGraph(G);

    System.out.println("Searchin for 3: " + G.containsVertex(3));
    System.out.println("Searchin for 2: " + G.containsVertex(2));

    G.printVerteces();

    System.out.println("Searchin for 3-3: " + G.containsArch(new Arch<Integer, Double>(3,3, null)));
    System.out.println("Searchin for 2-1: " + G.containsArch(new Arch<Integer, Double>(2,1, null)));
    System.out.println("Searchin for 5-5: " + G.containsArch(new Arch<Integer, Double>(5,5, null)));
    System.out.println("Searchin for 0-0: " + G.containsArch(new Arch<Integer, Double>(0,0, null)));
    System.out.println("Searchin for 2-2: " + G.containsArch(new Arch<Integer, Double>(2,2, null)));
    System.out.println("Searchin for 1-1: " + G.containsArch(new Arch<Integer, Double>(1,1, null)));
    System.out.println("Searchin for 1-3: " + G.containsArch(new Arch<Integer, Double>(1,3, null)));
    System.out.println("Searchin for 4-0: " + G.containsArch(new Arch<Integer, Double>(4,0, null)));

    Array<IArch<Integer, Double>> Show = new Array<IArch<Integer, Double>>(10);
    Array<IArch<Integer, Double>> Prim = G.MSTPrim(6);


    if (Prim == null)
      System.out.println("Prim : null");

    for (int i = 0; i < Prim.length; i++)
      if (Prim.get(i) == null)
        System.out.println("Arch : null");
      else
        Prim.get(i).print();

    // Array<Integer> BFS = new Array<Integer>(10);
    Array<Integer> BFS = G.BFS(6);


    if (BFS == null)
      System.out.println("BFS : null");

    for (int i = 0; i < BFS.length; i++)
      if (BFS.get(i) == null)
        System.out.println("Vertex : null");
      else
        System.out.println("Vertex : " + BFS.get(i));
  }

  public static void printGraph(Graph g){
    Array<Arch<Integer, Integer>> A = g.getArches();
    Array<Integer> V = g.getVerteces();

    for(int i = 0; i < V.length; i++)
      if (V.get(i) != null)
        System.out.println("Vertex: " + V.get(i));
    if (A != null){
      System.out.println("\n\rArch length: " + A.length);

      for(int i = 0; i < A.length; i++)
        if (A.get(i) != null)
          A.get(i).print();
    }
  }

  public static void setTest(){
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
  }

  public static void hashTest(){
    System.out.println("CONSTRUCTION");

    Hash2<Integer> hash = new Hash2<Integer>();
    int max = 50;
    int min = -49;

    System.out.println("LET'S ADD UP");

    for (int i = 0; i < 35; i++)
      hash.insert(ThreadLocalRandom.current().nextInt(-1000, 1001));

    hash.insert(33);
    hash.insert(133);
    hash.insert(233);
    hash.insert(333);

    System.out.println("DELETING!");

    System.out.println("SEARCH: " + hash.search(133));
    hash.delete(33);
    hash.delete(233);

    System.out.println("SEARCH: " + hash.search(333));
    System.out.println("SEARCH: " + hash.search(833));
    System.out.println("SEARCH: " + hash.search(133));
    System.out.println("SEARCH: " + hash.search(33));



    System.out.println("LET'S PRINT!");

    hash.print();

    hash.insert(33);
    hash.insert(133);
    hash.insert(233);
    hash.insert(333);
    hash.insert(333);

    System.out.println("LET'S PRINT!");

    hash.print();

    System.out.println("DELETING!");

    System.out.println("SEARCH: " + hash.search(133));
    hash.delete(33);
    hash.delete(233);
    hash.delete(333);

    System.out.println("SEARCH: " + hash.search(333));

    System.out.println("LET'S PRINT!");

    hash.print();

    System.out.println("THE END");


    // System.out.println("SEARCH: " + hash.search(33, 1));
    // hash.delete(33, 0);
    // hash.delete(33, 2);

    // System.out.println("SEARCH: " + hash.search(33, 3));
    // System.out.println("SEARCH: " + hash.search(33, 8));
    // System.out.println("SEARCH: " + hash.search(33, 1));
    // System.out.println("SEARCH: " + hash.search(33, 0));



    System.out.println("LET'S PRINT!");

    hash.print();

    // hash.insert(33, 0);
    // hash.insert(33, 1);
    // hash.insert(33, 2);
    // hash.insert(33, 3);
    // hash.insert(33, 3);

    System.out.println("LET'S PRINT!");

    hash.print();

    System.out.println("DELETING!");

    // System.out.println("SEARCH: " + hash.search(33, 1));
    // hash.delete(33, 0);
    // hash.delete(33, 2);
    // hash.delete(33, 3);

    // System.out.println("SEARCH: " + hash.search(33, 3));

    System.out.println("LET'S PRINT!");

    hash.print();

    System.out.println("THE END");
  }

  public static void listTest(){
    List<Integer> L = new List<Integer>();

    L.print();

    L.addHead(5);

    L.addTail(8);

    System.out.println("List: ");
    L.print();

    L.addAt(400000, 3);

    L.update(9000, 4);
    L.update(7000, L.getSize()-1);
    System.out.println("Search and get at: " + L.getAt(L.search(7000)));
    System.out.println("Search of 5: " + L.search(5));
    System.out.println("Search of 7000: " + L.search(7000));
    System.out.println("Get at 0 get at: " + L.getAt(0));
    System.out.println("Get at 1 get at: " + L.getAt(1));
    System.out.println("Get at 2 get at: " + L.getAt(2));

    System.out.println("List: ");
    L.print();

    L.delHead();
    L.delTail();

    L.update(6000, L.getSize()-1);

    L.update(15000, 15);
    L.update(888000, 14);
    L.update(999000, 13);

    L.delEl(15000);

    L.delAt(14);

    System.out.println("List: ");
    L.print();

    System.out.println("Search: " + L.search(999000));

    Array<Integer> test = L.toArray();

    System.out.println("Array: ");

    for(int i = 0; i < test.length; i++)
      System.out.println(test.get(i));
  }

  public static void queueTest(){
    Queue<Integer> Q = new Queue<Integer>();

    Q.print();

    Q.enqueue(5);

    Q.enqueue(8);

    System.out.println("Queue: ");
    Q.print();

    Q.enqueue(4000);
    System.out.println("Dequeue: " + Q.dequeue());
    System.out.println("Dequeue: " + Q.dequeue());
    // System.out.println("Search of 5: " + Q.search(5));
    // System.out.println("Search of 7000: " + Q.search(7000));
    // System.out.println("Get at 0 get at: " + Q.getAt(0));
    // System.out.println("Get at 1 get at: " + Q.getAt(1));
    // System.out.println("Get at 2 get at: " + Q.getAt(2));

    System.out.println("Queue: ");
    Q.print();


    Array<Integer> test = Q.toArray();

    System.out.println("Array: ");

    for(int i = 0; i < test.length; i++)
      System.out.println(test.get(i));
  }

  public static void priorityQueueTest(){
    PriorityQueue<Integer, Integer> PQ = new PriorityQueue<Integer, Integer>();


    for (int i = 5; i < 40; i++)
      PQ.insert(ThreadLocalRandom.current().nextInt(5, 1001), ThreadLocalRandom.current().nextInt(5, 40));

    PQ.insert(1, 1);
    PQ.insert(2, 2);
    PQ.insert(3, 3);
    PQ.insert(4, 4);

    System.out.println("Priority Queue: ");
    PQ.print();

    System.out.println("Delete val: " + PQ.delete(40));
    System.out.println("Delete val: " + PQ.delete(70));
    System.out.println("Delete val: " + PQ.delete(3));
    System.out.println("Delete val: " + PQ.delete(1));

    PQ.print();

    PQ.insert(0, 0);

    PQ.print();

    PQ.increaseKey(2, 0);
    PQ.increaseKey(0, 1111);

    PQ.print();

    System.out.println("Actual min: " + PQ.findMin());

    for (int i = 0; i < 36; i++)
      PQ.delMin();

    System.out.println("Last: " + PQ.findMin());

    System.out.println("isEmpty: " + PQ.isEmpty());
  }

}
