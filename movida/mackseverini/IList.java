package movida.mackseverini;
import movida.mackseverini.Node;

public interface IList<E extends Comparable<E>>{
  public Integer getSize ();
  public Node<E> getHead ();
  public Node<E> getTail ();

  public void addHead (E el);
  public void addTail (E el);
  public void addAt (E el, int pos);

  public void delHead ();
  public void delTail ();
  public void delEl (E el);
  public void delAt (int pos);

  public void update (E el, int pos);

  public Integer search (E el);

  public void print ();
}
