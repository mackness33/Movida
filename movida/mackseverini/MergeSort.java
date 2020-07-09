package movida.mackseverini;

public class MergeSort<T extends Comparable>
{
  public static <T>[] mergeSort(<T>[] vector)
  {
    if(vector.length <= 1)
      return vector;

      int pivot = vector.length / 2;

      <T>[] left = new <T>[pivot];
      <T>[] right;

    if(vector.length % 2 == 0)
      right = new <T>[pivot];
    else
      right = new <T>[pivot + 1];

    // COPIA DEGLI ELEMENTI DEL VETTORE INIZIALE PER LA PARTE SX E DX
    for(int i = 0; i < pivot; i++)
      left[i] = vector[i];
    for(int i = 0; i < right.length; i++)
      right[i] = vector[pivot + i];

    // RICORSIONE SU PARTE SX E DX
    left = mergeSort(left);
    right = mergeSort(right);

    // UNA VOLTA DIVISO L'ARRAY IN PARTE SX E DX SI ORDINANO LE DUE PARTI FONDENDOLE
    return merge(left, right);
  }

  public static int[] merge(<T>[] left, <T>[] right)
  {
    <T>[] merged = new int[left.length + right.length];
    int l = 0, r = 0, i = 0;
    /* PER CONTROLLO
    System.out.print("LEFT = ");
    printArray(left);
    System.out.print("RIGHT = ");
    printArray(right);
    */

    // FINCHE' NON VIENE SCORSO INTERAMENTE UNO TRA LEFT E RIGHT
    while(l < left.length && r < right.length)
    {
      if(left[l].compareTo(right[r]) <= 0)
      {
        //System.out.println("\nCHOOSEN LEFT"); PER CONTROLLO
        merged[i++] = left[l++];
      }
      else
      {
        //System.out.println("\nCHOOSEN RIGHT"); PER CONTROLLO
        merged[i++] = right[r++];
      }
      /* PER CONTROLLO
      System.out.println("MERGED[" + i + "] = " + merged[i]);
      System.out.println("i = " + i);
      System.out.println("l = " + l);
      System.out.println("r = " + r);
      */
    }

    // FINISCE DI SCORRERE L'ALTRO ARRAY
    if(l < left.length)
    {
      while(l < left.length)
        merged[i++] = left[l++];
    }
    else
    {
      while(r < right.length)
        merged[i++] = right[r++];
    }

    return merged;
  }
  /* PER CONTROLLO
  public static void printArray(int[] a)
  {
    for(int i = 0; i < a.length; i++)
      System.out.print(a[i] + " ");

      System.out.println();
    }
    */
}
