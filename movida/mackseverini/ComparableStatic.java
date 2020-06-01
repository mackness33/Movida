package movida.mackseverini;

public class ComparableStatic{
  public static <T extends Comparable<T>> Integer compare(T obj1, T obj2) {
    if (obj1 == null && obj2 == null)
      return 0;
    else if (obj1 == null)
      return Integer.MAX_VALUE;
    else if (obj2 == null)
      return Integer.MIN_VALUE;
    return obj1.compareTo(obj2);
  }

  public static <T, S extends Comparable<T>> Integer compare2(S obj1, T obj2) {
    return obj1.compareTo(obj2);
  }
}
