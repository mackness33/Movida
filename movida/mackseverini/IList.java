package movida.mackseverini;
import movida.mackseverini.INode;

// interface that describe the basic operation of List
public interface IList<E extends Comparable<E>> extends Comparable<IList<E>>{
  public Integer getSize ();
  public INode2<E> getHead ();
  public INode2<E> getTail ();

  public void addHead (E el);
  public void addTail (E el);
  public boolean addAt (E el, int pos);
  public void addToEnd (IList<E> L);  // add a List at the end of this

  public void delHead ();
  public void delTail ();
  public boolean delEl (E el);
  public boolean delAt (int pos);

  public void update (E el, int pos);   // update an element by the position in input

  public Integer search (E el);   // get the position of the element in input

  public E get (E el);        // get the saved element by giving the element in input

  public E getAt (int pos);   // get the element of the position in input

  public void print ();       // CLEAN
  public void printAll ();    // CLEAN

  public boolean swap (INode2<E> first, INode2<E> second);    // swap two nodes

  public void reset ();

  public int compareTo (IList<E> el);

  public Array<E> toArray();
}
