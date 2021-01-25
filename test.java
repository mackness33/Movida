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
// import movida.mackseverini.MovieAbr;
import movida.mackseverini.Graph;
import movida.mackseverini.Arch;
import movida.mackseverini.IArch;
import movida.mackseverini.Set;
import movida.mackseverini.PriorityQueue;
import movida.mackseverini.Vertex;
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

    // INSERT

    TestInteger value20 = new TestInteger(20);
    TestInteger value10 = new TestInteger(10);
    TestInteger value30 = new TestInteger(30);
    TestInteger value5 = new TestInteger(5);
    TestInteger value1 = new TestInteger(1);
    TestInteger value25 = new TestInteger(25);
    TestInteger value7 = new TestInteger(7);
    TestInteger value35 = new TestInteger(35);
    TestInteger value37 = new TestInteger(37);
    TestInteger value15 = new TestInteger(15);
    TestInteger value23 = new TestInteger(23);
    TestInteger value13 = new TestInteger(13);
    TestInteger value17 = new TestInteger(17);
    TestInteger value33 = new TestInteger(33);
    TestInteger value27 = new TestInteger(27);
    ABR<Integer, TestInteger> abr = new ABR();
    abr.insert(1, value20);
    abr.insert(2, value10);
    abr.insert(3, value30);
    abr.insert(4, value5);
    abr.insert(5, value1);
    abr.insert(6, value25);
    abr.insert(7, value7);
    abr.insert(8, value35);
    abr.insert(9, value37);
    abr.insert(10, value15);
    abr.insert(11, value23);
    abr.insert(12, value13);
    abr.insert(13, value17);
    abr.insert(14, value33);
    abr.insert(15, value27);
    abr.printAbr();

    // Search
    System.out.println("Search key 1: " + abr.searchByKey(1) +
                      "\nSearch key 2: " + abr.searchByKey(2) +
                      "\nSearch key 3: " + abr.searchByKey(3) +
                      "\nSearch key 4: " + abr.searchByKey(4) +
                      "\nSearch key 5: " + abr.searchByKey(5) +
                      "\nSearch key 6: " + abr.searchByKey(6) +
                      "\nSearch key 7: " + abr.searchByKey(7) +
                      "\nSearch key 8: " + abr.searchByKey(8) +
                      "\nSearch key 9: " + abr.searchByKey(9) +
                      "\nSearch key 10: " + abr.searchByKey(10) +
                      "\nSearch key 11: " + abr.searchByKey(11) +
                      "\nSearch key 12: " + abr.searchByKey(12) +
                      "\nSearch key 13: " + abr.searchByKey(13) +
                      "\nSearch key 14: " + abr.searchByKey(14) +
                      "\nSearch key 15: " + abr.searchByKey(15) +
                      "\nSearch key 16: " + abr.searchByKey(16));

    System.out.println("Delete node with key 1: " + abr.deleteByKey(1));
    abr.printAbr();



    // // SEARCH
    //
    // // System.out.println("Search value 20: " + abr.search(value20));
    // // System.out.println("Search value 10: " + abr.search(value10));
    // // System.out.println("Search value 30: " + abr.search(value30));
    // // System.out.println("Search value 5: " + abr.search(value5));
    // // System.out.println("Search value 15: " + abr.search(value15));
    // // System.out.println("Search value 25: " + abr.search(value25));
    // // System.out.println("Search value 35: " + abr.search(value35));
    // // System.out.println("Search value 1: " + abr.search(value1));
    // // System.out.println("Search value 7: " + abr.search(value7));
    // // System.out.println("Search value 13: " + abr.search(value13));
    // // System.out.println("Search value 17: " + abr.search(value17));
    // // System.out.println("Search value 23: " + abr.search(value23));
    // // System.out.println("Search value 27: " + abr.search(value27));
    // // System.out.println("Search value 33: " + abr.search(value33));
    // // System.out.println("Search value 37: " + abr.search(value37));
    // // TestInteger valueNotInserted100 = new TestInteger(100);
    // // System.out.println("Search value 100: " + abr.search(valueNotInserted100));
    //
    // // DELETE
    //
    // // LEAF
    // abr.delete(value37);
    // // abr.printAbr();
    //
    // // INTERMEDIATE NODE
    //
    // // ONLY LEFT CHILD THAT HAS LEFT CHILD
    // TestInteger value31 = new TestInteger(31);
    // abr.insert(20, value31);
    // abr.delete(value35);
    // // abr.printAbr();
    //
    // // ONLY LEFT CHILD
    // abr.delete(value33);
    // // abr.printAbr();
    //
    // // ONLY RIGHT CHILD
    // abr.delete(value1);
    // abr.delete(value5);
    // // abr.printAbr();
    //
    // // 2 CHILDREN
    // // PREDECESSOR IS LEFT CHILD
    // abr.delete(value10);
    // // abr.printAbr();
    //
    // // PREDECESSOR IS MAX OF LEFT SUBTREE
    // // PREDECESSOR WITH NO CHILD
    // abr.delete(value30);
    // // abr.printAbr();
    //
    // abr.delete(value23);
    // abr.delete(value31);
    // abr.delete(value25);
    //
    // // PREDECESSOR WITH LEFT SUBTREE
    // TestInteger value2 = new TestInteger(2);
    // TestInteger value6 = new TestInteger(6);
    // TestInteger value4 = new TestInteger(4);
    // TestInteger value3 = new TestInteger(3);
    // abr.insert(21, value2);
    // abr.insert(22, value1);
    // abr.insert(23, value6);
    // abr.insert(24, value4);
    // abr.insert(25, value3);
    // abr.insert(26, value5);
    // abr.delete(value7);
    // // abr.printAbr();
    //
    // // ROOT
    // // 2 CHILDREN
    // // PREDECESSOR IS MAX OF LEFT SUBTREE
    // // PREDECESSOR WITH LEFT SUBTREE
    // abr.delete(value3);
    // abr.delete(value5);
    // abr.delete(value4);
    // abr.delete(value1);
    // abr.delete(value17);
    // abr.insert(27, value10);
    // TestInteger value14 = new TestInteger(14);
    // abr.insert(28, value14);
    // abr.delete(value20);
    // // abr.printAbr();
    //
    // // PREDECESSOR WITHOUT LEFT SUBTREE
    // abr.delete(value15);
    // // abr.printAbr();
    //
    // // PREDECESSOR IS LEFT CHILD
    // // LEFT CHILD HAS LEFT CHILD
    // abr.delete(value10);
    // abr.delete(value13);
    // abr.delete(value14);
    // // abr.printAbr();
    //
    // // PREDECESSOR IS LEFT CHILD
    // abr.delete(value6);
    // // abr.printAbr();
    //
    // // ONLY RIGHT CHILD
    // abr.delete(value2);
    // // abr.printAbr();
    //
    // // ONLY LEFT CHILD
    // abr.insert(30, value33);
    // abr.delete(value27);
    // abr.printAbr();

    // DELETE

    // NODE NOT IN THE TREE

    // ABR<TestInteger> abr = new ABR<TestInteger>();
    // TestInteger value20 = new TestInteger(20);
    // TestInteger value10 = new TestInteger(10);
    // // EMPTY TREE
    // System.out.println("\nDELETE 10: " + abr.delete(value10));
    // // ONLY ROOT
    // abr.insert(value20);
    // abr.printAbr();
    // System.out.println("\nDELETE 10: " + abr.delete(value10));
    // // GENERIC TREE
    // TestInteger value9 = new TestInteger(9);
    // abr.insert(value9);
    // TestInteger value30 = new TestInteger(30);
    // abr.insert(value30);
    // TestInteger value1 = new TestInteger(1);
    // abr.insert(value1);
    // TestInteger value11 = new TestInteger(11);
    // abr.insert(value11);
    // TestInteger value25 = new TestInteger(25);
    // abr.insert(value25);
    // TestInteger value35 = new TestInteger(35);
    // abr.insert(value35);
    // abr.printAbr();
    // System.out.println("\nDELETE 10: " + abr.delete(value10));


    // ROOT

    // TestInteger value20 = new TestInteger(20);
    // TestInteger value10 = new TestInteger(10);
    // TestInteger value30 = new TestInteger(30);
    // TestInteger value5 = new TestInteger(5);
    // TestInteger value15 = new TestInteger(15);
    // TestInteger value13 = new TestInteger(13);
    // TestInteger value11 = new TestInteger(11);
    // TestInteger value14 = new TestInteger(14);
    // ABR<TestInteger> abr = new ABR<TestInteger>();
    // abr.insert(value20);
    // abr.insert(value10);
    // abr.insert(value30);
    // abr.insert(value5);
    // abr.insert(value15);
    // abr.insert(value13);
    // abr.insert(value11);
    // abr.insert(value14);
    // abr.printAbr();
    // abr.delete(value20);
    // System.out.println();
    // abr.printAbr();

    // DELETE
    // LEAF

    // ABR<TestInteger> abr = new ABR<TestInteger>();
    // TestInteger value30 = new TestInteger(30);
    // abr.insert(value30);
    // TestInteger value40 = new TestInteger(40);
    // abr.insert(value40);
    // TestInteger value20 = new TestInteger(20);
    // abr.insert(value20);
    // TestInteger value10 = new TestInteger(10);
    // abr.insert(value10);
    // TestInteger value25 = new TestInteger(25);
    // abr.insert(value25);
    // TestInteger value35 = new TestInteger(35);
    // abr.insert(value35);
    // abr.printAbr();
    // abr.delete(value25);
    // System.out.println();
    // abr.printAbr();


    // DELETE
    // INTERMEDIATE NODE

    // TestInteger value30 = new TestInteger(30);
    // TestInteger value40 = new TestInteger(40);
    // TestInteger value20 = new TestInteger(20);
    // TestInteger value10 = new TestInteger(10);
    // TestInteger value25 = new TestInteger(25);
    // TestInteger value5 = new TestInteger(5);
    // TestInteger value15 = new TestInteger(15);
    // TestInteger value13 = new TestInteger(13);
    // TestInteger value11 = new TestInteger(11);
    // TestInteger value14 = new TestInteger(14);
    // ABR<TestInteger> abr = new ABR<TestInteger>();
    // abr.insert(value30);
    // abr.insert(value40);
    // abr.insert(value20);
    // abr.insert(value10);
    // abr.insert(value25);
    // abr.insert(value5);
    // abr.insert(value15);
    // abr.insert(value13);
    // abr.insert(value11);
    // abr.insert(value14);
    // abr.printAbr();
    // abr.delete(value20);
    // System.out.println();
    // abr.printAbr();


    // SEARCH

    // TestInteger value20 = new TestInteger(20);
    // TestInteger value10 = new TestInteger(10);
    // TestInteger value30 = new TestInteger(30);
    // TestInteger value5 = new TestInteger(5);
    // TestInteger value1 = new TestInteger(1);
    // TestInteger value25 = new TestInteger(25);
    // TestInteger value7 = new TestInteger(7);
    // TestInteger value35 = new TestInteger(35);
    // TestInteger value37 = new TestInteger(37);
    // TestInteger value15 = new TestInteger(15);
    // TestInteger value23 = new TestInteger(23);
    // TestInteger value13 = new TestInteger(13);
    // TestInteger value17 = new TestInteger(17);
    // TestInteger value33 = new TestInteger(33);
    // TestInteger value27 = new TestInteger(27);
    // ABR<TestInteger> abr = new ABR();
    // abr.insert(value20);
    // abr.insert(value10);
    // abr.insert(value30);
    // abr.insert(value5);
    // abr.insert(value1);
    // abr.insert(value25);
    // abr.insert(value7);
    // abr.insert(value35);
    // abr.insert(value37);
    // abr.insert(value15);
    // abr.insert(value23);
    // abr.insert(value13);
    // abr.insert(value17);
    // abr.insert(value33);
    // abr.insert(value27);
    // abr.printAbr();
    // TestInteger value40 = new TestInteger(40);
    // System.out.println("SEARCH FOR 10: " + abr.search(value10) + "\nSEARCH FOR 40: " + abr.search(value40));


    // UPDATE

    // ABR<TestInteger> abr = new ABR<TestInteger>();
    // TestInteger value20 = new TestInteger(20);
    // TestInteger value10 = new TestInteger(10);
    // TestInteger valueNotInTree50 = new TestInteger(50);
    // TestInteger valueToUpdate100 = new TestInteger(100);
    // System.out.println(abr.update(valueToUpdate100, valueNotInTree50));
    // abr.insert(value20);
    // System.out.println(abr.update(valueToUpdate100, valueNotInTree50));
    // abr.printAbr();
    // System.out.println(abr.update(valueToUpdate100, value20));
    // abr.printAbr();

    // TestInteger value20 = new TestInteger(20);
    // TestInteger value10 = new TestInteger(10);
    // TestInteger value30 = new TestInteger(30);
    // TestInteger value5 = new TestInteger(5);
    // TestInteger value15 = new TestInteger(15);
    // TestInteger value13 = new TestInteger(13);
    // TestInteger value11 = new TestInteger(11);
    // TestInteger value14 = new TestInteger(14);
    // TestInteger valueUpdated40 = new TestInteger(40);
    // ABR<TestInteger> abr = new ABR<TestInteger>();
    // abr.insert(value20);
    // abr.insert(value10);
    // abr.insert(value30);
    // abr.insert(value5);
    // abr.insert(value15);
    // abr.insert(value13);
    // abr.insert(value11);
    // abr.insert(value14);
    // abr.printAbr();
    // abr.update(valueUpdated40, value20);
    // System.out.println();
    // abr.printAbr();

    // TestInteger value30 = new TestInteger(30);
    // TestInteger value40 = new TestInteger(40);
    // TestInteger value20 = new TestInteger(20);
    // TestInteger value10 = new TestInteger(10);
    // TestInteger value25 = new TestInteger(25);
    // TestInteger value5 = new TestInteger(5);
    // TestInteger value15 = new TestInteger(15);
    // TestInteger value13 = new TestInteger(13);
    // TestInteger value11 = new TestInteger(11);
    // TestInteger value14 = new TestInteger(14);
    // ABR<TestInteger> abr = new ABR<TestInteger>();
    // abr.insert(value30);
    // abr.insert(value40);
    // abr.insert(value20);
    // abr.insert(value10);
    // abr.insert(value25);
    // abr.insert(value5);
    // abr.insert(value15);
    // abr.insert(value13);
    // abr.insert(value11);
    // abr.insert(value14);
    // abr.printAbr();
    // TestInteger valueUpdated45 = new TestInteger(45);
    // abr.update(valueUpdated45, value20);
    // System.out.println();
    // abr.printAbr();


    // // TestInteger value5 = new TestInteger(5);
    // // abr.insert(value5);
    // // System.out.println("ROOT'S LEFT CHILD: " + abr.getRoot().getLeftChild().getKey());
    // TestInteger value15 = new TestInteger(15);
    // abr.insert(value15);
    // System.out.println("ROOT'S RIGHT CHILD: " + abr.getRoot().getRightChild().getKey());
    // TestInteger value5 = new TestInteger(5);
    // abr.insert(value5);
    // System.out.println("ROOT'S LEFT CHILD: " + abr.getRoot().getLeftChild().getKey());
    // TestInteger value1 = new TestInteger(1);
    // abr.insert(value1);
    // System.out.println("ROOT'S LEFT LEFT CHILD: " + abr.getRoot().getLeftChild().getLeftChild().getKey());
    // TestInteger value7 = new TestInteger(7);
    // abr.insert(value7);
    // System.out.println("ROOT'S LEFT RIGHT CHILD: " + abr.getRoot().getLeftChild().getRightChild().getKey());
    // TestInteger value20 = new TestInteger(20);
    // abr.insert(value20);
    // System.out.println("ROOT'S RIGHT RIGHT CHILD: " + abr.getRoot().getRightChild().getRightChild().getKey());
    // TestInteger value13 = new TestInteger(13);
    // abr.insert(value13);
    // System.out.println("ROOT'S RIGHT LEFT CHILD: " + abr.getRoot().getRightChild().getLeftChild().getKey());



    // TEST DELETE PARAMETRIZZATO

    // ABR<TestInteger> abr = new ABR();
    //
    // TestInteger value20 = new TestInteger(20);
    // abr.insert(value20);
    // System.out.println("ROOT: " + abr.getRoot().getKey());
    // //System.out.println("DELETE ROOT 20: " + abr.delete(value20) + " NEW ROOT: " + abr.getRoot());
    // TestInteger value10 = new TestInteger(10);
    // abr.insert(value10);
    // System.out.println("ROOT'S LEFT CHILD: " + abr.getRoot().getLeftChild().getKey());
    // //System.out.println("DELETE ROOT 20: " + abr.delete(value20) + " NEW ROOT: " + abr.getRoot().getKey() + " NEW ROOT'S LEFT CHILD: " + abr.getRoot().getLeftChild());
    // TestInteger value30 = new TestInteger(30);
    // abr.insert(value30);
    // System.out.println("ROOT'S RIGHT CHILD: " + abr.getRoot().getRightChild().getKey());
    // //System.out.println("DELETE ROOT 20: " + abr.delete(value20) + " NEW ROOT: " + abr.getRoot().getKey() + " NEW ROOT'S LEFT CHILD: " + abr.getRoot().getLeftChild() + " NEW ROOT'S RIGHT CHILD: " + abr.getRoot().getRightChild().getKey());
    // TestInteger value5 = new TestInteger(5);
    // abr.insert(value5);
    // System.out.println("ROOT'S LEFT LEFT CHILD: " + abr.getRoot().getLeftChild().getLeftChild().getKey());
    // //System.out.println("DELETE ROOT 20: " + abr.delete(value20) + " NEW ROOT: " + abr.getRoot().getKey() + " NEW ROOT'S RIGHT CHILD: " + abr.getRoot().getRightChild().getKey() + " NEW ROOT'S LEFT CHILD: " + abr.getRoot().getLeftChild().getKey() + " NEW ROOT'S LEFT LEFT CHILD: " + abr.getRoot().getLeftChild().getLeftChild());
    // TestInteger value15 = new TestInteger(15);
    // abr.insert(value15);
    // System.out.println("ROOT'S LEFT RIGHT CHILD: " + abr.getRoot().getLeftChild().getRightChild().getKey());
    // //System.out.println("DELETE ROOT 20: " + abr.delete(value20) + " NEW ROOT: " + abr.getRoot().getKey() + " NEW ROOT'S RIGHT CHILD: " + abr.getRoot().getRightChild().getKey() + " NEW ROOT'S LEFT CHILD: " + abr.getRoot().getLeftChild().getKey() + " NEW ROOT'S LEFT LEFT CHILD: " + abr.getRoot().getLeftChild().getLeftChild().getKey() + " NEW ROOT'S LEFT RIGHT CHILD: " + abr.getRoot().getLeftChild().getRightChild());
    // TestInteger value13 = new TestInteger(13);
    // abr.insert(value13);
    // TestInteger value12 = new TestInteger(12);
    // abr.insert(value12);
    // TestInteger value14 = new TestInteger(14);
    // abr.insert(value14);
    // System.out.println("ROOT'S LEFT RIGHT LEFT CHILD: " + abr.getRoot().getLeftChild().getRightChild().getLeftChild().getKey());
    // System.out.println("DELETE ROOT 20: " + abr.delete(value20) + " NEW ROOT: " + abr.getRoot().getKey() + " NEW ROOT'S LEFT CHILD: " + abr.getRoot().getLeftChild().getKey() + " NEW ROOT'S RIGHT CHILD: " + abr.getRoot().getRightChild().getKey() + " NEW ROOT'S LEFT LEFT CHILD: " + abr.getRoot().getLeftChild().getLeftChild().getKey() + " NEW ROOT'S LEFT RIGHT CHILD: " + abr.getRoot().getLeftChild().getRightChild().getKey() + " NEW ROOT'S LEFT RIGHT LEFT CHILD: " + abr.getRoot().getLeftChild().getRightChild().getLeftChild().getKey() + " NEW ROOT'S LEFT RIGHT RIGHT CHILD: " + abr.getRoot().getLeftChild().getRightChild().getRightChild().getKey());

    //TEST SEARCH PARAMETRIZZATO

    // ABR abr = new ABR();
    // TestInteger value20 = new TestInteger(20);
    // TestInteger value10 = new TestInteger(10);
    // TestInteger value30 = new TestInteger(30);
    // TestInteger value5 = new TestInteger(5);
    // TestInteger value15 = new TestInteger(15);
    // TestInteger value25 = new TestInteger(25);
    // TestInteger value35 = new TestInteger(35);
    // TestInteger value1 = new TestInteger(1);
    // TestInteger value7 = new TestInteger(7);
    // TestInteger value13 = new TestInteger(13);
    // TestInteger value17 = new TestInteger(17);
    // TestInteger value21 = new TestInteger(21);
    // TestInteger value27 = new TestInteger(27);
    // TestInteger value31 = new TestInteger(31);
    // TestInteger value37 = new TestInteger(37);
    // abr.insert(value20);
    // abr.insert(value10);
    // abr.insert(value30);
    // abr.insert(value5);
    // abr.insert(value15);
    // abr.insert(value25);
    // abr.insert(value35);
    // abr.insert(value1);
    // abr.insert(value7);
    // abr.insert(value13);
    // abr.insert(value17);
    // abr.insert(value21);
    // abr.insert(value27);
    // abr.insert(value31);
    // abr.insert(value37);
    // System.out.println("SEARCH 20: " + abr.search(value20));
    // System.out.println("SEARCH 10: " + abr.search(value10));
    // System.out.println("SEARCH 30: " + abr.search(value30));
    // System.out.println("SEARCH 5: " + abr.search(value5));
    // System.out.println("SEARCH 15: " + abr.search(value15));
    // System.out.println("SEARCH 25: " + abr.search(value25));
    // System.out.println("SEARCH 35: " + abr.search(value35));
    // System.out.println("SEARCH 1: " + abr.search(value1));
    // System.out.println("SEARCH 7: " + abr.search(value7));
    // System.out.println("SEARCH 13: " + abr.search(value13));
    // System.out.println("SEARCH 17: " + abr.search(value17));
    // System.out.println("SEARCH 21: " + abr.search(value21));
    // System.out.println("SEARCH 27: " + abr.search(value27));
    // System.out.println("SEARCH 31: " + abr.search(value31));
    // System.out.println("SEARCH 37: " + abr.search(value37));
    // TestInteger value370 = new TestInteger(370);
    // System.out.println("SEARCH 370: " + abr.search(value370));

    //TEST UPDATE PARAMETRIZZATO

    // ABR abr = new ABR();
    // TestInteger value20 = new TestInteger(20);
    // TestInteger value10 = new TestInteger(10);
    // TestInteger value30 = new TestInteger(30);
    // TestInteger value5 = new TestInteger(5);
    // TestInteger value15 = new TestInteger(15);
    // TestInteger value25 = new TestInteger(25);
    // TestInteger value35 = new TestInteger(35);
    // TestInteger value1 = new TestInteger(1);
    // TestInteger value7 = new TestInteger(7);
    // TestInteger value13 = new TestInteger(13);
    // TestInteger value17 = new TestInteger(17);
    // TestInteger value21 = new TestInteger(21);
    // TestInteger value27 = new TestInteger(27);
    // TestInteger value31 = new TestInteger(31);
    // TestInteger value37 = new TestInteger(37);
    // abr.insert(value20);
    // abr.insert(value10);
    // abr.insert(value30);
    // abr.insert(value5);
    // abr.insert(value15);
    // abr.insert(value25);
    // abr.insert(value35);
    // abr.insert(value1);
    // abr.insert(value7);
    // abr.insert(value13);
    // abr.insert(value17);
    // abr.insert(value21);
    // abr.insert(value27);
    // abr.insert(value31);
    // abr.insert(value37);

    // TestInteger value22 = new TestInteger(22);
    // abr.update(value22,value20);
    // System.out.println("ROOT CHANGED FROM 20 TO 22: " + abr.getRoot().getRightChild().getLeftChild().getLeftChild().getRightChild().getKey() + " NEW ROOT: " + abr.getRoot().getKey());
    // TestInteger value11 = new TestInteger(11);
    // abr.update(value11, value10);
    // System.out.println("ROOT'S LEFT CHILD CHANGED FROM 10 TO 11: " + abr.getRoot().getLeftChild().getRightChild().getLeftChild().getLeftChild().getKey() + " NEW ROOT'S LEFT CHILD: " + abr.getRoot().getLeftChild().getKey());
    // TestInteger value32 = new TestInteger(32);
    // abr.update(value32, value30);
    // System.out.println("ROOT'S RIGHT CHILD CHANGED FROM 30 TO 32: " + abr.getRoot().getRightChild().getRightChild().getLeftChild().getRightChild().getKey() + " NEW ROOT'S RIGHT CHILD: " + abr.getRoot().getRightChild().getKey());
    // TestInteger value20 = new TestInteger(20);
    // TestInteger value15 = new TestInteger(15);
    // TestInteger value6 = new TestInteger(6);
    // TestInteger value18 = new TestInteger(18);
    // TestInteger value1 = new TestInteger(1);
    // TestInteger value10 = new TestInteger(10);
    // TestInteger value8 = new TestInteger(8);
    // TestInteger value7 = new TestInteger(7);
    // TestInteger value9 = new TestInteger(9);
    // abr.insert(value20);
    // abr.insert(value15);
    // abr.insert(value6);
    // abr.insert(value18);
    // abr.insert(value1);
    // abr.insert(value10);
    // abr.insert(value8);
    // abr.insert(value7);
    // abr.insert(value9);
    // System.out.println("ROOT'S LEFT CHILD CHANGED TO 19 FROM 15: " + abr.update(value19, value15) + "NEW ROOT'S LEFT CHILD: " + abr.getRoot().getLeftChild().getKey() + "NEW ROOT'S LEFT LEFT RIGHT: " + abr.getRoot().getLeftChild().getLeftChild().getRightChild().getKey() + " UPDATED VALUE ROOT LEFT RIGHT RIGHT: " + abr.getRoot().getLeftChild().getRightChild().getRightChild().getKey());
    // TestInteger value20 = new TestInteger(20);
    // TestInteger value19 = new TestInteger(19);
    // TestInteger value18 = new TestInteger(18);
    // abr.insert(value20);
    // abr.insert(value19);
    // abr.insert(value18);
    // TestInteger value21 = new TestInteger(21);
    // abr.update(value21, value19);
    // System.out.println(abr.getRoot().getRightChild().getKey() + "\n"  + abr.getRoot().getLeftChild().getKey() + "\n" + abr.getRoot().getLeftChild().getLeftChild());

    // TestInteger value20 = new TestInteger(20);
    // TestInteger value18 = new TestInteger(18);
    // TestInteger value17 = new TestInteger(17);
    // TestInteger value19 = new TestInteger(19);
    // abr.insert(value20);
    // abr.insert(value18);
    // abr.insert(value17);
    // abr.insert(value19);
    // TestInteger value21 = new TestInteger(21);
    // abr.update(value21, value18);
    // System.out.println(abr.getRoot().getRightChild().getKey() + "\n"  + abr.getRoot().getLeftChild().getKey() + "\n" + abr.getRoot().getLeftChild().getLeftChild() + "\n" + abr.getRoot().getLeftChild().getRightChild().getKey());

    // TestInteger value20 = new TestInteger(20);
    // TestInteger value15 = new TestInteger(15);
    // TestInteger value10 = new TestInteger(10);
    // TestInteger value5 = new TestInteger(5);
    // TestInteger value17 = new TestInteger(17);
    // abr.insert(value20);
    // abr.insert(value15);
    // abr.insert(value10);
    // abr.insert(value5);
    // abr.insert(value17);
    // TestInteger value21 = new TestInteger(21);
    // abr.update(value21, value15);
    // System.out.println(abr.getRoot().getRightChild().getKey() + "\n"  + abr.getRoot().getLeftChild().getKey() + "\n" + abr.getRoot().getLeftChild().getLeftChild().getKey() + "\n" + abr.getRoot().getLeftChild().getRightChild().getKey());

    // TestInteger value20 = new TestInteger(20);
    // TestInteger value15 = new TestInteger(15);
    // TestInteger value10 = new TestInteger(10);
    // TestInteger value5 = new TestInteger(5);
    // TestInteger value17 = new TestInteger(17);
    // TestInteger value13 = new TestInteger(13);
    // abr.insert(value20);
    // abr.insert(value15);
    // abr.insert(value10);
    // abr.insert(value5);
    // abr.insert(value17);
    // abr.insert(value13);
    // TestInteger value21 = new TestInteger(21);
    // abr.update(value21, value15);
    // System.out.println(abr.getRoot().getRightChild().getKey() + "\n"  + abr.getRoot().getLeftChild().getKey() + "\n" + abr.getRoot().getLeftChild().getLeftChild().getKey() + "\n" + abr.getRoot().getLeftChild().getRightChild().getKey() + "\n" + abr.getRoot().getLeftChild().getLeftChild().getLeftChild().getKey() + "\n" + abr.getRoot().getLeftChild().getLeftChild().getRightChild());

    // TestInteger value20 = new TestInteger(20);
    // TestInteger value15 = new TestInteger(15);
    // TestInteger value10 = new TestInteger(10);
    // TestInteger value5 = new TestInteger(5);
    // TestInteger value17 = new TestInteger(17);
    // TestInteger value13 = new TestInteger(13);
    // TestInteger value11 = new TestInteger(11);
    // abr.insert(value20);
    // abr.insert(value15);
    // abr.insert(value10);
    // abr.insert(value5);
    // abr.insert(value17);
    // abr.insert(value13);
    // abr.insert(value11);
    // TestInteger value21 = new TestInteger(21);
    // abr.update(value21, value15);
    // System.out.println(abr.getRoot().getRightChild().getKey() + "\n"  + abr.getRoot().getLeftChild().getKey() + "\n" + abr.getRoot().getLeftChild().getLeftChild().getKey() + "\n" + abr.getRoot().getLeftChild().getRightChild().getKey() + "\n" + abr.getRoot().getLeftChild().getLeftChild().getLeftChild().getKey() + "\n" + abr.getRoot().getLeftChild().getLeftChild().getRightChild().getKey());

    // TestInteger value20 = new TestInteger(20);
    // TestInteger value15 = new TestInteger(15);
    // TestInteger value10 = new TestInteger(10);
    // TestInteger value17 = new TestInteger(17);
    // TestInteger value5 = new TestInteger(5);
    // TestInteger value14 = new TestInteger(14);
    // TestInteger value12 = new TestInteger(12);
    // TestInteger value11 = new TestInteger(11);
    // TestInteger value13 = new TestInteger(13);
    // abr.insert(value20);
    // abr.insert(value15);
    // abr.insert(value10);
    // abr.insert(value17);
    // abr.insert(value5);
    // abr.insert(value14);
    // abr.insert(value12);
    // abr.insert(value11);
    // abr.insert(value13);
    // TestInteger value21 = new TestInteger(21);
    // abr.update(value21, value15);
    // System.out.println(abr.getRoot().getRightChild().getKey() + "\n"  + abr.getRoot().getLeftChild().getKey() + "\n" + abr.getRoot().getLeftChild().getLeftChild().getKey() + "\n" + abr.getRoot().getLeftChild().getRightChild().getKey() + "\n" + abr.getRoot().getLeftChild().getLeftChild().getLeftChild().getKey() + "\n" + abr.getRoot().getLeftChild().getLeftChild().getRightChild().getKey() + "\n" + abr.getRoot().getLeftChild().getLeftChild().getRightChild().getLeftChild().getKey() + "\n" + abr.getRoot().getLeftChild().getLeftChild().getRightChild().getRightChild().getKey());


    // TestInteger value1 = new TestInteger(1);
    // System.out.println(value1 instanceof TestInteger);

    //System.out.println(abr.getRoot().getRightChild().getKey().toString());
    /*Person dioporco = new Person("dioporco");
    Person porcamadonna = new Person("porcamadonna");
    System.out.println(dioporco.compareTo(porcamadonna));*/

    //TEST ABRNODE
    //(modificare AbrNode da protected a public per testare con le seguenti righe)
    //test costruttori
/*  ABR.AbrNode root = abr.new AbrNode(50);
    ABR.AbrNode empty = abr.new AbrNode();
    System.out.println("TEST COSTRUTTORI\n");
    System.out.println("Empty node key: " + empty.getKey());
    System.out.println("root key: " + root.getKey());
    ABR.AbrNode left = abr.new AbrNode(20);
    ABR.AbrNode right = abr.new AbrNode(70);
    ABR.AbrNode root2 = abr.new AbrNode(60, left, right);
    System.out.println("Root2 key: " + root2.getKey());
    System.out.println("root2 left child: " + (root2.getLeftChild()).getKey());
    System.out.println("root2 right child: " + (root2.getRightChild()).getKey());

    //test set figli
    System.out.println("\n\nTEST SET FIGLI\n");
    root.setLeftChild(left);
    root.setRightChild(right);
    System.out.println("Root left child: " + (root.getLeftChild()).getKey());
    System.out.println("Root right child: " + (root.getRightChild()).getKey());

    //test set key
    (root.getLeftChild()).setKey(10);
    System.out.println("Root left child key changed: " + (root.getLeftChild()).getKey());
*/


    //TEST ABR

    //TEST INSERT
/*    abr.insert(50);
    abr.insert(30);
    abr.insert(70);
    abr.insert(20);
    abr.insert(40);
    abr.insert(60);
    abr.insert(80);
    abr.printAbr(abr.getRoot());
*/
    //TEST DELETE
      // ONLY ROOT CASE
/*    abr.insert(50);
    abr.printAbr(abr.getRoot());
    abr.delete(50);
    System.out.println("\n");
    abr.printAbr(abr.getRoot());
*/      // ROOT 1 CHILD CASE (LEFT)
/*    abr.insert(50);
    abr.insert(30);
    abr.insert(20);
    abr.printAbr(abr.getRoot());
    abr.delete(50);
    System.out.println("\n");
    abr.printAbr(abr.getRoot());
*/      // ROOT 1 CHILD CASE (RIGHT)
/*    abr.insert(50);
    abr.insert(60);
    abr.insert(70);
    abr.printAbr(abr.getRoot());
    abr.delete(50);
    System.out.println("\n");
    abr.printAbr(abr.getRoot());
*/      // ROOT 2 CHILDREN CASE
/*    abr.insert(50);
    abr.insert(40);
    abr.insert(60);
    abr.insert(30);
    abr.insert(45);
    abr.insert(43);
    abr.insert(44);
    abr.printAbr(abr.getRoot());
    abr.delete(50);
    System.out.println("\n");
    abr.printAbr(abr.getRoot());
*/  // LEAF CASE
/*    abr.insert(50);
    abr.insert(40);
    abr.insert(45);
    abr.printAbr(abr.getRoot());
    abr.delete(45);
    System.out.println("\n");
    abr.printAbr(abr.getRoot());
*/  // INTERMEDIATE CASE
      // 1 CHILD ONLY (LEFT)
/*    abr.insert(50);
    abr.insert(40);
    abr.insert(30);
    abr.insert(20);
    abr.insert(35);
    abr.printAbr(abr.getRoot());
    abr.delete(40);
    System.out.println("\n");
    abr.printAbr(abr.getRoot());
*/
/*    abr.insert(50);
    abr.insert(60);
    abr.insert(55);
    abr.insert(51);
    abr.insert(56);
    abr.printAbr(abr.getRoot());
    abr.delete(60);
    System.out.println("\n");
    abr.printAbr(abr.getRoot());
*/
      // 1 CHILD ONLY (RIGHT)
/*    abr.insert(50);
    abr.insert(40);
    abr.insert(45);
    abr.insert(41);
    abr.insert(46);
    abr.printAbr(abr.getRoot());
    abr.delete(40);
    System.out.println("\n");
    abr.printAbr(abr.getRoot());
*/
      // 2 CHILDREN CASE
/*      abr.insert(50);
      abr.insert(40);
      abr.insert(30);
      abr.insert(45);
      abr.insert(20);
      abr.insert(35);
      abr.insert(31);
      abr.insert(36);
      abr.printAbr(abr.getRoot());
      abr.delete(40);
      System.out.println("\n");
      abr.printAbr(abr.getRoot());

      System.out.println("\n\n"+abr.getRoot().getLeftChild().getLeftChild().getRightChild().getLeftChild().getKey());
*/
/*      abr.insert(50);
      abr.insert(60);
      abr.insert(55);
      abr.insert(70);
      abr.insert(51);
      abr.insert(59);
      abr.insert(56);
      abr.printAbr(abr.getRoot());
      abr.delete(60);
      System.out.println("\n");
      abr.printAbr(abr.getRoot());

      System.out.println("\n\n"+abr.getRoot().getRightChild().getLeftChild().getRightChild().getKey());
*/
    // TEST SEARCH
/*    abr.insert(50);
    abr.insert(40);
    abr.insert(30);
    abr.insert(70);
    abr.insert(80);
    abr.insert(60);
    abr.insert(45);
    abr.printAbr(abr.getRoot());
    System.out.println("\n\n50:" + abr.search(50));
    System.out.println("40:" + abr.search(40));
    System.out.println("30:" + abr.search(30));
    System.out.println("70:" + abr.search(70));
    System.out.println("80:" + abr.search(80));
    System.out.println("60:" + abr.search(60));
    System.out.println("45:" + abr.search(45));
    System.out.println("10:" + abr.search(10));
    System.out.println("100:" + abr.search(100));
*/
    // TEST UPDATE
/*    abr.insert(50);
    abr.insert(70);
    abr.insert(40);
    abr.insert(30);
    abr.insert(65);
    abr.printAbr(abr.getRoot());
    abr.update(35, 50);
    System.out.println("\n");
    abr.printAbr(abr.getRoot());
    System.out.println("\n\n"+ abr.getRoot().getRightChild().getLeftChild().getKey());
*/
    // test.AlgTest();
    test.graphMovidaTest();
    // test.priorityQueueTest();
  }

  /*public static void AlgTest(){
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

  }

  public static void graphMovidaTest(){
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
