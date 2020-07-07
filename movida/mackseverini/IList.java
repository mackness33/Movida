package movida.mackseverini;
import movida.mackseverini.INode;

public interface IList<E extends Comparable<E>> extends Comparable<IList<E>>{
  public Integer getSize ();
  public INode2<E> getHead ();
  public INode2<E> getTail ();

  public void addHead (E el);
  public void addTail (E el);
  public void addAt (E el, int pos);

  public void delHead ();
  public void delTail ();
  public void delEl (E el);
  public void delAt (int pos);

  public void update (E el, int pos);

  public Integer search (E el);

  public E getAt (int pos);

  public void print ();

  public void reset ();

  public int compareTo (IList<E> el);

  public Array<E> toArray();
}