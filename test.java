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
import movida.mackseverini.MergeSort;
import movida.mackseverini.KeyNode;
import movida.mackseverini.KeyList;
import movida.mackseverini.ABR;
import movida.mackseverini.MovieAbr;
import movida.mackseverini.Graph;
import movida.mackseverini.CollabGraph;
import movida.mackseverini.Arch;
import movida.mackseverini.IArch;
import movida.mackseverini.IKeyList;
import movida.mackseverini.Set;
import movida.mackseverini.PriorityQueue;
import movida.mackseverini.Vertex;
import movida.mackseverini.Queue;
import movida.mackseverini.Stack;
import movida.mackseverini.DynamicArray;
import movida.mackseverini.IAlg;
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
    // test.graphTest();
    test.movidaTest();
    // test.queueTest();
    // test.stackTest();
    // test.listTest();
    // test.dynamicArrayTest();
    // test.abrTest();
    // test.algTest(new InsertionSort());
    // test.algTest(new MergeSort());
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
    Movie pulpFiction = new Movie("Pulp fiction", 1994, 89, cast6, spielberg);
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

    // movs.printTree(0);


    // DELETE (by title)
    System.out.println("\n\n");
    System.out.println(movs.delete("Il padrin")); // movie not present in tree OK
    System.out.println(movs.delete("Il padrino"));
    // DELETE (by movie)
    System.out.println(movs.delete(padrino)); // movie not present in tree OK
    System.out.println(movs.delete(padrino2));
    System.out.println("\n\n");

    movs.printTree(0);

    // SEARCH (title)
    System.out.println("Search for 'Il padrino': " + movs.search("Il padrino")); // movie not present in tree OK
    System.out.println("\nSearch for 'Il cavaliere oscuro': " + movs.search("Il cavaliere oscuro"));

    // SEARCH (movie)
    System.out.println("\n\nSearch for 'Il padrino': " + movs.search(padrino)); // movie not present in tree OK
    System.out.println("\nSearch for 'Il cavaliere oscuro': " + movs.search(cavaliereOscuro));

    // SEARCH (by key)
    System.out.println("\n\nSearch by key 'votes' of value: 1994 =>\n" + movs.searchByKey(1994)[1]);
    System.out.println("\n\nSearch by key 'votes' of value: 1994 =>\n" + movs.searchByKey(spielberg)[0]);
  }

  public static void algTest(IAlg alg){
    Array<Integer> A = new Array<Integer>(35);
    IList<Integer> L = new List<Integer>();
    IKeyList<Integer, Integer, Integer> KL = new KeyList<Integer, Integer, Integer>();

    for (int i = 0; i < A.length; i++){
      A.set(i, ThreadLocalRandom.current().nextInt(-1000, 1001));
      L.addTail(ThreadLocalRandom.current().nextInt(-1000, 1001));
      // KL.addTail(i, ThreadLocalRandom.current().nextInt(-1000, 1001));
      KL.addTail(ThreadLocalRandom.current().nextInt(-1000, 1001), i);
    }

    for (int i = 0; i < A.length; i++)
      System.out.println("POS: " + i + "  VAL: " + A.get(i));
    int j = 0;
    for(INode2<Integer> iterIN = L.getHead(); iterIN != null; iterIN = iterIN.getNext(), j++)
      System.out.println("POS: " + j + "  VAL: " + iterIN.getValue());

    KL.printAll();

    A = alg.sort(A, true);
    L = alg.sort(L, false);
    // KL = (IKeyList)alg.sort(KL, false);
    KL = (IKeyList)alg.keySort(KL, true);

    System.out.println("SORTED: ");
    for (int i = 0; i < A.length; i++)
      System.out.println("POS: " + i + "  VAL: " + A.get(i));

    j = 0;
    for(INode2<Integer> iterIN = L.getHead(); iterIN != null; iterIN = iterIN.getNext(), j++)
      System.out.println("POS: " + j + "  VAL: " + iterIN.getValue());

    if (KL == null)
      System.out.println("keySort: " + null);
    else
      KL.printAll();

  }

  public static void movidaTest(){
    MovidaCore mb = new MovidaCore();

    movidaConfigTest(mb);
    System.out.println("");
    System.out.println("");

    mb.loadFromFile(new File("movida/assets/esempio-formato-dati.txt"));
    movidaMapTest(mb);
    // movidaGraphTest(mb);

    mb.saveToFile(new File("movida/assets/output.txt"));
  }

  public static void movidaConfigTest(MovidaCore mb){
    System.out.println("MapChange1: " + mb.setMap(movida.commons.MapImplementation.HashConcatenamento));
    System.out.println("MapChange2: " + mb.setMap(movida.commons.MapImplementation.ABR));
    System.out.println("MapChange3: " + mb.setMap(movida.commons.MapImplementation.AVL));

    System.out.println("AlgChange2: " + mb.setSort(movida.commons.SortingAlgorithm.InsertionSort));
    System.out.println("AlgChange1: " + mb.setSort(movida.commons.SortingAlgorithm.MergeSort));
    System.out.println("AlgChange3: " + mb.setSort(movida.commons.SortingAlgorithm.HeapSort));
  }

  public static void movidaMapTest(MovidaCore mb){
    mb.deleteMovieByTitle("Die Hard");
    mb.deleteMovieByTitle("The Junitive");
    mb.deleteMovieByTitle("Air Force One");
    mb.deleteMovieByTitle(null);

    System.out.println("");
    System.out.println("");
    System.out.println("Get Cape Fear: \n\r" + mb.getMovieByTitle("Cape Fear"));
    System.out.println("");
    System.out.println("");
    System.out.println("Get Harrison Ford: \n\r" + mb.getPersonByName("Harrison Ford"));
    System.out.println("");
    System.out.println("");


    // PEOPLE
    Person[] people = mb.getAllPeople();

    if (people != null){
      System.out.println("ARRAY LENGTH: " + people.length);
      System.out.println("TO ARRAY: ");
      for(int i = 0; i < people.length; i++)
        if (people[i] != null)
          System.out.println(people[i]);
    }
    else{
      System.out.println("ARRAY LENGTH: " + 0);
      System.out.println("TO ARRAY: " + people);
    }


    // MOVIES
    Movie[] movies = mb.getAllMovies();

    if (movies != null){
      System.out.println("ARRAY LENGTH: " + movies.length);
      System.out.println("TO ARRAY: ");
      for(int i = 0; i < movies.length; i++)
        if (movies[i] != null)
          System.out.println(movies[i]);
    }
    else{
      System.out.println("ARRAY LENGTH: " + 0);
      System.out.println("TO ARRAY: " + movies);
    }


    // SEARCH BY KEY -> YEAR
    System.out.println("\n\rSEARCHBYKEY YEAR: ");
    Movie[] years = mb.searchMoviesInYear(1997);

    if (years != null){
      for(int i = 0; i < years.length; i++)
        if (years[i] != null)
          System.out.println(years[i]);
    }
    else
      System.out.println(years);


    // SEARCH MOST OF -> RATES
    System.out.println("\n\rSEARCHMOSTOF RATES: ");
    Movie[] rates = mb.searchMostVotedMovies(3);

    if (rates != null){
      for(int i = 0; i < rates.length; i++)
        if (rates[i] != null)
          System.out.println(rates[i]);
    }
    else
      System.out.println(rates);


    // SEARCH CONTAINS -> TITLE
    System.out.println("\n\rSEARCHCONTAINS TITLE: ");
    Movie[] titles = mb.searchMoviesByTitle("tive");

    if (titles != null){
      for(int i = 0; i < titles.length; i++)
        if (titles[i] != null)
          System.out.println(titles[i]);
    }else
      System.out.println(titles);


    // SEARCH BY KEY -> DIRECTORS
    System.out.println("\n\rSEARCHBYKEY DIRECTORS: ");
    Movie[] dirs = mb.searchMoviesDirectedBy("Martin Scorsese");

    if (dirs != null){
      for(int i = 0; i < dirs.length; i++)
        if (dirs[i] != null)
          System.out.println(dirs[i]);
    }else
      System.out.println(dirs);


    // SEARCH MOST OF -> YEAR
    System.out.println("\n\rSEARCHMOSTOF YEARS: ");
    Movie[] dates = mb.searchMostRecentMovies(7);

    if (dates != null){
      for(int i = 0; i < dates.length; i++)
        if (dates[i] != null)
          System.out.println(dates[i]);
    }else
      System.out.println(dates);


    // SEARCH MOST OF -> ACTOR
    System.out.println("\n\rSEARCHALLOF ACTORS: ");
    Movie[] actors = mb.searchMoviesStarredBy("Harrison Ford");

    if (actors != null){
      for(int i = 0; i < actors.length; i++)
        if (actors[i] != null)
          System.out.println(actors[i]);
    }else
      System.out.println(actors);


    // SEARCH MOST OF -> ACTIVE
    System.out.println("\n\rSEARCHMOSTACTIVE ACTORS: ");
    Person[] active = mb.searchMostActiveActors(5);

    if (active != null){
      for(int i = 0; i < active.length; i++)
        if (active[i] != null)
          System.out.println(active[i]);
    }else
      System.out.println(active);
  }

  public static void movidaGraphTest(MovidaCore mb){
    // ----------------- DIRECCT COLLABORATION -----------------------

    // TOMMY LEE JONES
    System.out.println("");
    Person[] collabs_of_Tommy = mb.getDirectCollaboratorsOf(new Person("Tommy Lee Jones"));

    if (collabs_of_Tommy != null){
      System.out.println("Tommy collab LENGTH: " + collabs_of_Tommy.length);
      System.out.println("TO ARRAY: ");
      for(int i = 0; i < collabs_of_Tommy.length; i++)
        if (collabs_of_Tommy[i] != null)
          System.out.println(collabs_of_Tommy[i]);
    }
    else
      System.out.println("Tommy collab LENGTH: " + 0 + "\n\rTO ARRAY: " + collabs_of_Tommy);

    // HARRISON FORD
    System.out.println("");
    Person[] collabs_of_Harrison = mb.getDirectCollaboratorsOf(new Person("Harrison Ford"));

    if (collabs_of_Harrison != null){
      System.out.println("Harrison collab LENGTH: " + collabs_of_Harrison.length);
      System.out.println("TO ARRAY: ");
      for(int i = 0; i < collabs_of_Harrison.length; i++)
        if (collabs_of_Harrison[i] != null)
          System.out.println(collabs_of_Harrison[i]);
    }
    else
      System.out.println("Harrison collab LENGTH: " + 0 + "\n\rTO ARRAY: " + collabs_of_Harrison);



    // ----------------- TEAMS -----------------------

    // TOMMY LEE JONES
    System.out.println("");
    Person[] team_of_Tommy = mb.getTeamOf(new Person("Tommy Lee Jones"));

    if (team_of_Tommy != null){
      System.out.println("Tommy team LENGTH: " + team_of_Tommy.length);
      System.out.println("TO ARRAY: ");
      for(int i = 0; i < team_of_Tommy.length; i++)
        if (team_of_Tommy[i] != null)
          System.out.println(team_of_Tommy[i]);
    }
    else
      System.out.println("Tommy team LENGTH: " + 0 + "\n\rTO ARRAY: " + team_of_Tommy);

    // HARRISON FORD
    System.out.println("");
    Person[] team_of_Harrison = mb.getTeamOf(new Person("Harrison Ford"));

    if (team_of_Harrison != null){
      System.out.println("Harrison team LENGTH: " + team_of_Harrison.length);
      System.out.println("TO ARRAY: ");
      for(int i = 0; i < team_of_Harrison.length; i++)
        if (team_of_Harrison[i] != null)
          System.out.println(team_of_Harrison[i]);
    }
    else
      System.out.println("Harrison team LENGTH: " + 0 + "\n\rTO ARRAY: " + team_of_Harrison);

    // BRUCE WILLIS
    System.out.println("");
    Person[] team_of_Bruce = mb.getTeamOf(new Person("Bruce Willis"));

    if (team_of_Bruce != null){
      System.out.println("Bruce team LENGTH: " + team_of_Bruce.length);
      System.out.println("TO ARRAY: ");
      for(int i = 0; i < team_of_Bruce.length; i++)
        if (team_of_Bruce[i] != null)
          System.out.println(team_of_Bruce[i]);
    }
    else
      System.out.println("Bruce team LENGTH: " + 0 + "\n\rTO ARRAY: " + team_of_Bruce);



    // ----------------- MAXIMUM SPINNING TREE -----------------------

    // TOMMY LEE JONES
    System.out.println("");
    Collaboration[] mst_of_Tommy = mb.maximizeCollaborationsInTheTeamOf(new Person("Tommy Lee Jones"));

    if (mst_of_Tommy != null){
      System.out.println("Tommy mst LENGTH: " + mst_of_Tommy.length);
      System.out.println("TO ARRAY: ");
      for(int i = 0; i < mst_of_Tommy.length; i++)
        if (mst_of_Tommy[i] != null)
          mst_of_Tommy[i].print();
    }
    else
      System.out.println("Tommy mst LENGTH: " + 0 + "\n\rTO ARRAY: " + mst_of_Tommy);


    // HARRISON FORD
    System.out.println("");
    Collaboration[] mst_of_Harrison = mb.maximizeCollaborationsInTheTeamOf(new Person("Harrison Ford"));

    if (mst_of_Harrison != null){
      System.out.println("Harrison mst LENGTH: " + mst_of_Harrison.length);
      System.out.println("TO ARRAY: ");
      for(int i = 0; i < mst_of_Harrison.length; i++)
        if (mst_of_Harrison[i] != null)
          mst_of_Harrison[i].print();
    }
    else
      System.out.println("Harrison mst LENGTH: " + 0 + "\n\rTO ARRAY: " + mst_of_Harrison);


    // BRUCE WILLIS
    System.out.println("");
    Collaboration[] mst_of_Bruce = mb.maximizeCollaborationsInTheTeamOf(new Person("Bruce Willis"));

    if (mst_of_Bruce != null){
      System.out.println("Bruce mst LENGTH: " + mst_of_Bruce.length);
      System.out.println("TO ARRAY: ");
      for(int i = 0; i < mst_of_Bruce.length; i++)
        if (mst_of_Bruce[i] != null)
          mst_of_Bruce[i].print();
    }
    else
      System.out.println("Bruce mst LENGTH: " + 0 + "\n\rTO ARRAY: " + mst_of_Bruce);


    // ALAN RICKMAN
    System.out.println("");
    Collaboration[] mst_of_Alan = mb.maximizeCollaborationsInTheTeamOf(new Person("Alan Rickman"));

    if (mst_of_Alan != null){
      System.out.println("Alan mst LENGTH: " + mst_of_Alan.length);
      System.out.println("TO ARRAY: ");
      for(int i = 0; i < mst_of_Alan.length; i++)
        if (mst_of_Alan[i] != null)
          mst_of_Alan[i].print();
    }
    else
      System.out.println("Alan mst LENGTH: " + 0 + "\n\rTO ARRAY: " + mst_of_Alan);


    // TOM SKERRITT
    System.out.println("");
    Collaboration[] mst_of_Tom = mb.maximizeCollaborationsInTheTeamOf(new Person("Tom Skerritt"));

    if (mst_of_Tom != null){
      System.out.println("Tom mst LENGTH: " + mst_of_Tom.length);
      System.out.println("TO ARRAY: ");
      for(int i = 0; i < mst_of_Tom.length; i++)
        if (mst_of_Tom[i] != null)
          mst_of_Tom[i].print();
    }
    else
      System.out.println("Tom mst LENGTH: " + 0 + "\n\rTO ARRAY: " + mst_of_Tom);
  }

  public static void graphTest(){
    Graph<Integer, Double> G = new Graph<Integer, Double>();

    G.addVertex(0);
    G.addVertex(1);
    G.addVertex(2);
    G.addVertex(3);
    G.addVertex(4);
    G.addVertex(5);
    G.addVertex(6);

    // printGraph(G);

    // for (int i = 0; i < 50; i++){
    //   G.addVertex(ThreadLocalRandom.current().nextInt(0, 10));
    //   // G.addVertex(i);
    //   G.addArch(new Arch<Integer, Double>(ThreadLocalRandom.current().nextInt(0, 10), ThreadLocalRandom.current().nextInt(0, 10), ThreadLocalRandom.current().nextDouble(-1000, 1001)));
    // }


    G.addArch(new Arch<Integer, Double>(5, 2, 1.0));
    G.addArch(new Arch<Integer, Double>(2, 5, 5.0));
    G.addArch(new Arch<Integer, Double>(1, 1, 5.0));
    G.addArch(new Arch<Integer, Double>(3, 4, 5.0));
    G.addArch(new Arch<Integer, Double>(4, 3, 5.0));
    G.addArch(new Arch<Integer, Double>(6, 1, 5.0));
    G.addArch(new Arch<Integer, Double>(3, 6, 5.0));
    G.addArch(new Arch<Integer, Double>(3, 1, 5.0));
    G.addArch(new Arch<Integer, Double>(6, 5, 5.0));
    G.addArch(new Arch<Integer, Double>(5, 5, 5.0));
    G.addArch(new Arch<Integer, Double>(2, 3, 2.0));
    G.addArch(new Arch<Integer, Double>(3, 3, 3.0));
    G.addArch(new Arch<Integer, Double>(2, 2, 11.0));
    G.addArch(new Arch<Integer, Double>(2, 2, 16.0));
    G.addArch(new Arch<Integer, Double>(2, 1, 4.0));

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
    Array<IArch<Integer, Double>> Prim = G.MSTPrim(6, true);


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

    System.out.println("Swap: " + L.swap(L.getHead(), L.getTail()));


    System.out.println("List: ");
    L.print();

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

  public static void stackTest(){
    Stack<Integer> S = new Stack<Integer>();

    S.print();

    S.push(5);

    S.push(8);

    System.out.println("Stack: ");
    S.print();

    S.push(4000);
    System.out.println("Pop: " + S.pop());
    System.out.println("Pop: " + S.pop());
    // System.out.println("Search of 5: " + Q.search(5));
    // System.out.println("Search of 7000: " + Q.search(7000));
    // System.out.println("Get at 0 get at: " + Q.getAt(0));
    // System.out.println("Get at 1 get at: " + Q.getAt(1));
    // System.out.println("Get at 2 get at: " + Q.getAt(2));

    System.out.println("Stack: ");
    S.print();


    Array<Integer> test = S.toArray();

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

    System.out.println("Actual min: " + PQ.find());

    for (int i = 0; i < 36; i++)
      PQ.delete();

    System.out.println("Last: " + PQ.find());

    System.out.println("isEmpty: " + PQ.isEmpty());
  }

  public static void dynamicArrayTest(){
    DynamicArray<Integer> DA = new DynamicArray<Integer>(9);

    System.out.println("DynamicArray: ");
    System.out.println("Length: " + DA.length);
    for (int i = 0; i < DA.length; i++)
      System.out.println("POS: " + i + "\tVALUE: " + DA.get(i));

    DA.set(5, 5);

    DA.set(8, 8);

    System.out.println("DynamicArray: ");
    System.out.println("Length: " + DA.length);
    for (int i = 0; i < DA.length; i++)
      System.out.println("POS: " + i + "\tVALUE: " + DA.get(i));

    DA = DA.spare();

    System.out.println("DynamicArray: ");
    System.out.println("Length: " + DA.length);
    for (int i = 0; i < DA.length; i++)
      System.out.println("POS: " + i + "\tVALUE: " + DA.get(i));

    DA = DA.ensure();

    System.out.println("DynamicArray: ");
    System.out.println("Length: " + DA.length);
    for (int i = 0; i < DA.length; i++)
      System.out.println("POS: " + i + "\tVALUE: " + DA.get(i));

    DA = DA.trim();

    System.out.println("DynamicArray: ");
    System.out.println("Length: " + DA.length);
    for (int i = 0; i < DA.length; i++)
      System.out.println("POS: " + i + "\tVALUE: " + DA.get(i));

    DA = DA.ensure();

    System.out.println("DynamicArray: ");
    System.out.println("Length: " + DA.length);
    for (int i = 0; i < DA.length; i++)
      System.out.println("POS: " + i + "\tVALUE: " + DA.get(i));

    DA.set(2, 2);

    System.out.println("DynamicArray: ");
    System.out.println("Length: " + DA.length);
    for (int i = 0; i < DA.length; i++)
      System.out.println("POS: " + i + "\tVALUE: " + DA.get(i));


    DA = DA.ensure();
    DA = DA.spare();
    DA = DA.ensure();
    DA = DA.spare();
    DA = DA.ensure();
    DA = DA.spare();
    DA = DA.ensure();
    DA = DA.spare();
    DA = DA.ensure();


    System.out.println("DynamicArray: ");
    System.out.println("Length: " + DA.length);
    for (int i = 0; i < DA.length; i++)
      System.out.println("POS: " + i + "\tVALUE: " + DA.get(i));
  }
}
