package movida.mackseverini;
import movida.mackseverini.INode;

public interface IList<E extends Comparable<E>> extends Comparable<IList<E>>{
  public Integer getSize ();
  public INode2<E> getHead ();
  public INode2<E> getTail ();

  public void addHead (E el);
  public void addTail (E el);
  public void addAt (E el, int pos);
  public void addToEnd (IList<E> L);

  public void delHead ();
  public void delTail ();
  public boolean delEl (E el);
  public boolean delAt (int pos);

  public void update (E el, int pos);

  public Integer search (E el);

  public E getAt (int pos);

  public void print ();
  public void printAll ();

  public void reset ();

  public int compareTo (IList<E> el);

  public Array<E> toArray();
}
