package movida.mackseverini;

// class needed because java.util.ArrayList didn't implements Comparable
public class ArrayList<E extends Comparable<E>> extends java.util.ArrayList<E> implements Comparable<ArrayList<E>>{
  @Override
  public int compareTo(ArrayList<E> in){
    if (in.size() != this.size())
      return (in.size() < this.size()) ? 1 : -1;

    // compare each node of the array in input, with this
    int row = 0, col = 0;
    for (E i : in){
      col = 0;
      for (E j : this)
        col += j.compareTo(i);
      row += col;
    }

    // even if they're not in the same order it will still return 0
    return (row == 0) ? 0 : (row < 0) ? -1 : 1;
  }

}
