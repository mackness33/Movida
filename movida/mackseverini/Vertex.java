package movida.mackseverini;

import movida.mackseverini.KeyList;
import movida.mackseverini.IKeyList;
import movida.mackseverini.IVertex;

// FEATURE: can add a generics to let people choose with what type they want to save the vertex
public class Vertex<E extends Comparable<E>, K extends Comparable<K>> implements IVertex<E, K>{
  protected E value;
  protected IKeyList<Integer, K, Integer> adiacence;

  public Vertex(){
    this.value = null;
    this.adiacence = new KeyList<Integer, K, Integer>();
  }

  public Vertex(IVertex<E, K> shallow){
    this.value = shallow.getValue();
    this.adiacence = shallow.getAdiacence();
  }

  public Vertex(E v){
    this.value = v;
    this.adiacence = new KeyList<Integer, K, Integer>();
  }

  public Vertex(E v, IKeyList<Integer, K, Integer> a){
    this.value = v;
    this.adiacence = a;
  }

  @Override
  public IKeyList<Integer, K, Integer> getAdiacence () { return this.adiacence; }
  @Override
  public E getValue () { return this.value; }

  @Override
  public void setValue (E v) { this.value = v; }


  @Override
  public void addAdiacence (Integer v, K w) { this.adiacence.addHead(w, v); }

  @Override
  public boolean upsertAdiacence (Integer v, K weight) {
    if (v == null || weight == null)
      return false;

    // if the node hasn't been updated than add a new one
    return (this.updAdiacence(v, weight)) ? true : this.addHead(weight, v);
  }

  // let the addHead of list return a boolean
  private boolean addHead(K weight, Integer vertex){
    this.adiacence.addHead(weight, vertex);
    return true;
  }

  @Override
  public boolean updAdiacence (Integer v, K weight) {
    if (v == null)
      return false;

    // if the node is found, uppdate the node
    return (this.adiacence.searchKey(v) == null) ? false : this.adiacence.updElKey(v, weight);
  }

  @Override
  public boolean delAdiacence (Integer v) { return this.adiacence.delEl(v); }

  @Override
  public int compareTo (IVertex<E, K> input) { return this.value.compareTo(input.getValue()); }

  public int compareTo (E input) { return this.value.compareTo(input); }

  // CLEAN
  @Override
  public void print(){
    System.out.println("Vertex: VALUE => " + this.value + " \n\r\t ADIACENCE => ");
    this.adiacence.printAll();
  }

  @Override
  public String toString(){ return this.value.toString(); }
}
