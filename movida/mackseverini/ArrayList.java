package movida.mackseverini;

public class ArrayList<E extends Comparable<E>> extends java.util.ArrayList<E> implements Comparable<ArrayList<E>>{
  @Override
  public int compareTo(ArrayList<E> in){
    if (in.size() != this.size())
      return (in.size() < this.size()) ? 1 : -1;

    int row = 0, col = 0;
    for (E i : in){
      col = 0;
      for (E j : this)
        col += j.compareTo(i);
      row += col;
    }

    return (row == 0) ? 0 : (row < 0) ? -1 : 1;
  }

  public void print(){
    int count = 0;
    for (E i : this){
      if (i == null)
        System.out.println("Pos: " + count + " El: null");
      else
        System.out.println("Pos: " + count + " El: " + i);
      count++;
    }
  }
}
