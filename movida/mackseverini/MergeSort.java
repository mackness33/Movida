package movida.mackseverini;

public class MergeSort implements IAlg
{
  public MergeSort(){}

  public final movida.commons.SortingAlgorithm getType() { return movida.commons.SortingAlgorithm.MergeSort; }

  public <T extends Comparable<T>> Array<T> sort(Array<T> array, boolean isMin)
  {
    if(array.length < 2)
      return array;

    int pivot = array.length / 2;       // WARING: make sure to approximate for defect
    Array<T> left = new Array<T>(pivot);
    Array<T> right = new Array<T>((array.length % 2 == 0) ? pivot : pivot+1);

    // division of initial array in left part and right part
    for(int i = 0; i < pivot; i++)
      left.set(i, array.get(i));
    for(int i = 0; i < right.length; i++)
      right.set(i, array.get(pivot + i));

    // recursion on left and right parts
    left = this.sort(left, isMin);
    right = this.sort(right, isMin);

    // once divided array in left and right part merge them to sort
    return this.merge(left, right, isMin);
  }

  protected <T extends Comparable<T>> Array<T> merge(Array<T> left, Array<T> right, boolean isMin)
  {
    Array<T> merged = new Array<T>(left.length + right.length);
    int l = 0, r = 0, i = 0;
    while(l < left.length && r < right.length){
      if(min_max_compare(left.get(l), right.get(r), isMin))
      merged.set(i++, right.get(r++));
      else
      merged.set(i++, left.get(l++));
    }


    // sort remaining elements of not fully sorted array
    if(l < left.length)
    while(l < left.length)
    merged.set(i++, left.get(l++));
    else
    while(r < right.length)
    merged.set(i++, right.get(r++));

    return merged;
  }

  public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> sort(IList<E> list, boolean isMin)
  {
    if(list.getSize() < 2)
      return list;

    int pivot = list.getSize() / 2;       // WARING: make sure to approximate for defect
    IList<E> left = null;
    IList<E> right = null;
    INode2<E> iter = list.getHead();

    if (list instanceof KeyList){
      left = new KeyList<E, T, K>();
      right = new KeyList<E, T, K>();
    }
    else{
      left = new List<E>();
      right = new List<E>();
    }

    // if(list.getSize() % 2 == 0)
    //   right = new Array<T>(pivot);
    // else
    //   right = new Array<T>(pivot + 1);

    // division of initial array in left part and right part
    for(int i = 0; i < pivot; iter = iter.getNext(), i++)
      this.addTail(left, iter);

    for(; iter != null; iter = iter.getNext())
      this.addTail(right, iter);

    // recursion on left and right parts
    left = this.sort(left, isMin);
    right = this.sort(right, isMin);

    // once divided array in left and right part merge them to sort
    return this.merge(left, right, isMin);
  }

  protected <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IList<E> merge(IList<E> left, IList<E> right, boolean isMin)
  {
    IList<E> merged = null;
    int l = 0, r = 0, i = 0;
    INode2<E> iterLeft = left.getHead();
    INode2<E> iterRight = right.getHead();

    if (left instanceof IKeyList)
      merged = new KeyList<E, T, K>();
    else
      merged = new List<E>();

    while(iterLeft != null && iterRight != null){
      if(min_max_compare(iterLeft.getValue(), iterRight.getValue(), isMin)){
        this.addTail(merged, iterLeft);
        iterLeft = iterLeft.getNext();
      }
      else{
        this.addTail(merged, iterRight);
        iterRight = iterRight.getNext();
      }
    }

    // sort remaining elements of not fully sorted array
    if(iterLeft != null)
      for(; iterLeft != null; iterLeft = iterLeft.getNext())
        this.addTail(merged, iterLeft);
    else
      for(; iterRight != null; iterRight = iterRight.getNext())
        this.addTail(merged, iterRight);

    return merged;
  }

  protected <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IKeyList<E, T, K> keyMerge(IKeyList<E, T, K> left, IKeyList<E, T, K> right, boolean isMin)
  {
    IKeyList<E, T, K> merged = new KeyList<E, T, K>();
    int l = 0, r = 0, i = 0;
    IKeyNode<E, T> iterLeft = (IKeyNode<E, T>)left.getHead();
    IKeyNode<E, T> iterRight = (IKeyNode<E, T>)right.getHead();

    while(iterLeft != null && iterRight != null){
      if(min_max_compare(iterLeft.getKey(), iterRight.getKey(), isMin)){
        this.addTail(merged, iterLeft);
        iterLeft = (IKeyNode<E, T>)iterLeft.getNext();
      }
      else{
        this.addTail(merged, iterRight);
        iterRight = (IKeyNode<E, T>)iterRight.getNext();
      }
    }

    // sort remaining elements of not fully sorted array
    if(iterLeft != null)
      for(; iterLeft != null; iterLeft = (IKeyNode<E, T>)iterLeft.getNext())
        this.addTail(merged, iterLeft);
    else
      for(; iterRight != null; iterRight = (IKeyNode<E, T>)iterRight.getNext())
        this.addTail(merged, iterRight);

    return merged;
  }

  public <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> IKeyList<E, T, K> keySort(IKeyList<E, T, K> list, boolean isMin)
  {
    if(list.getSize() < 2)
      return list;

    int pivot = list.getSize() / 2;       // WARING: make sure to approximate for defect
    IKeyList<E, T, K> left = new KeyList<E, T, K>();
    IKeyList<E, T, K> right = new KeyList<E, T, K>();
    INode2<E> iter = list.getHead();

    // division of initial array in left part and right part
    for(int i = 0; i < pivot; iter = iter.getNext(), i++)
      this.addTail(left, iter);

    for(; iter != null; iter = iter.getNext())
      this.addTail(right, iter);

    // recursion on left and right parts
    left = this.keySort(left, isMin);
    right = this.keySort(right, isMin);

    // once divided array in left and right part merge them to sort
    return this.keyMerge(left, right, isMin);
  }

  protected <T extends Comparable<T>> boolean min_max_compare(T obj, T obj2, boolean isMin){ return (isMin) ? (obj.compareTo(obj2) < 0) : (obj.compareTo(obj2) > 0); }

  protected <E extends Comparable<E>, T extends Comparable<T>, K extends Comparable<K>> void addTail(IList<E> list, INode2<E> nodeToAdd){
    if (list instanceof KeyList)
    ((IKeyList<E, T, K>)list).addTail(((IKeyNode<E, T>)nodeToAdd).getKey(), nodeToAdd.getValue());
    else
    list.addTail(nodeToAdd.getValue());
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
