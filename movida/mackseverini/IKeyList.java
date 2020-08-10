package movida.mackseverini;

import movida.mackseverini.INode;

public interface IKeyList<E extends Comparable<E>> extends IList<E>{
  public void print();

  public void printAll();

  public Integer getKey();

  public void setKey (Integer k);

  public E getByKey (Integer k);

  public boolean delByKey (Integer k);

  public Integer searchKey (E el);

  public void addTail (Integer k, E el);

  public void addHead (Integer k, E el);

  public void addBlue (Integer k, E el, Integer pos);
}
