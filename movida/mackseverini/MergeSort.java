package movida.mackseverini;

public class MergeSort implements IAlg
{
  public MergeSort(){}


  public <T extends Comparable<T>> Array<T> sort(Array<T> array, boolean isMin)
  {
    if(array.length < 2)
      return array;

    int pivot = array.length / 2;
    int i = 0;
    Array<T> left = new Array<T>(pivot);
    Array<T> right;

    if(vector.length % 2 == 0)
      right = new Array<T>(pivot);
    else
      right = new Array<T>(pivot + 1);

    // division of initial array in left part and right part
    for(i = 0; i < pivot; i++)
      left.set(i, array.get(i));
    for(i = 0; i < right.length; i++)
      right.set(i, array.get(pivot + i));

    // recursion on left and right parts
    left = this.sort(left, isMin);
    right = this.sort(right, isMin);

    // once divided array in left and right part merge them to sort
    return merge(left, right, siMin);
  }

  public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> sort(IList<E> list, boolean isMin)
  {
    return null;
  }

  public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> keySort(IKeyList<E, T, K> list, boolean isMin);
  {
    return null;
  }


  public static Array<T> merge(Array<T> left, Array<T> right, boolean isMin)
  {
    Array<T> merged = new Array<T>(left.length + right.length);
    int l = 0;
    int r = 0;

    // until sorted all of left or all of right
    // decreasing order
    if(isMin == true)
    {
      while(l < left.length && r < right.length)
      {
        // first the greater, if r > l then r else l
        if((left.get(l)).compareTo(right.get(r)) < 0)
          merged.set(i++, right.get(r++));
        else
          merged.set(i++, left.get(l++));
      }
    }
    // increasing order
    else
    {
      while(l < left.length && r < right.length)
      {
        // first the smaller, if r < l then r else l
        if((left.get(l)).compareTo(right.get(r)) > 0)
          merged.set(i++, right.get(r++));
        else
          merged.set(i++, left.get(l++));
      }
    }

    // sort remaining elements of not fully sorted array
    if(l < left.length)
    {
      while(l < left.length)
        merged.set(i++, left.get(l++));
    }
    else
    {
      while(r < right.length)
        merged.set(i++, right.get(r++));
    }

    return merged;
  }



  // public static Array<T> mergeSort(Array<T> vector)
  // {
  //   if(vector.length <= 1)
  //     return vector;
  //
  //     int pivot = vector.length / 2;
  //
  //     <T>[] left = new <T>[pivot];
  //     <T>[] right;
  //
  //   if(vector.length % 2 == 0)
  //     right = new <T>[pivot];
  //   else
  //     right = new <T>[pivot + 1];
  //
  //   // COPIA DEGLI ELEMENTI DEL VETTORE INIZIALE PER LA PARTE SX E DX
  //   for(int i = 0; i < pivot; i++)
  //     left[i] = vector[i];
  //   for(int i = 0; i < right.length; i++)
  //     right[i] = vector[pivot + i];
  //
  //   // RICORSIONE SU PARTE SX E DX
  //   left = mergeSort(left);
  //   right = mergeSort(right);
  //
  //   // UNA VOLTA DIVISO L'ARRAY IN PARTE SX E DX SI ORDINANO LE DUE PARTI FONDENDOLE
  //   return merge(left, right);
  // }
}
