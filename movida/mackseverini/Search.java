package movida.mackseverini;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import movida.commons.Movie;
import movida.commons.Person;
import java.lang.Integer;

public class Search{

  //Search use to understand if the obj is in the list and where
  //TODO: if necessary make it generics for generical use
  public static /*<T extends Integer/*extends Comparable<T>*/ Integer binarySearch(List<Integer> map, Integer el){
    // Object [] array = map.toArray();     //the array must be already sorted!
    Integer [] array = new Integer [80];     //the array must be already sorted!
    array = map.toArray(array);     //the array must be already sorted!
    Integer first = 0, last = map.size() - 1, middle = last/2 + 1;

    while (first <= last){
      System.out.println("first = " + first);
      System.out.println("last = " + last);
      System.out.println("middle = " + middle);

      if (first == el)
        return first;
      else if (last == el)
        return last;
      else if (middle == el)
        return middle;

      //`middle == first` == `first == last` so not needed
      if (first == last || middle == last || first > last)
        break;

      System.out.println("checks: " + (array[middle] < el));
      if (array[middle] > el){
        last = middle - 1;
        middle = middle - ((middle - first) / 2) + 1;
      }
      else{
        first = middle + 1;
        middle = middle + ((last - middle) / 2) + 1;
      }
    }

    return new Integer(-1);
  }

  //Search use to understand if the obj is in the list and where
  //TODO: if necessary make it generics for generical use
  // public static <T, S extends Comparable<T>> Integer dumbSearch(List<? extends S> map, S el){
  public static <T, S extends Comparable<T>> int dumbSearch(List<S> map, T elem){
  // public static int dumbSearch(List<Movie> map, Movie el){
    int i = 0;
    int res = 0;
    for (S node : map){
      System.out.println("i: " + i);
      res = node.compareTo(elem);
      System.out.println("res: " + res);
      if (res == 0)
        return i;
      else
        i++;
    }
    return -1;
  }
}
