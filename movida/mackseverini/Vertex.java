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

		K old_weight = this.adiacence.searchKey(v);

		if (old_weight == null){
      this.adiacence.addHead(weight, v);
      return true;
    }

    System.out.println("The v is: " + v);
    System.out.println("The old_weight is: " + old_weight);
    System.out.println("The this is: " + this.value);
    return this.adiacence.updElKey(v, weight);
  }

  @Override
  public boolean delAdiacence (Integer v) { return this.adiacence.delEl(v); }

  @Override
  public int compareTo (IVertex<E, K> input) { return this.value.compareTo(input.getValue()); }

  public int compareTo (E input) { return this.value.compareTo(input); }

  @Override
  public void print(){
    System.out.println("Vertex: VALUE => " + this.value + " \n\r\t ADIACENCE => ");
    this.adiacence.printAll();
  }

  @Override
  public String toString(){ return this.value.toString(); }

  // CLEAN:
  protected class Pair <E extends Comparable<E>, K extends Comparable<K>> implements Comparable<Pair<E, K>>{
    protected E value;
		protected K key;

    public Pair(){
      this.value = null;
      this.key = null;
    }

    public Pair(E v, K k){
      this.value = v;
      this.key = k;
    }

    //@Override
    public E getValue() { return this.value; }
    //@Override
    public K getKey() { return this.key; }

    //@Override
    public void setValue (E v) { this.value = v; }
    //@Override
    public void setKey (K k) { this.key = k; }

    //@Override
    public int compareTo (Pair<E, K> input) { return this.key.compareTo(input.getKey()); }

    //@Override
    public void print(){ System.out.println("Pair: VALUE => " + this.value + " KEY => " + this.key); }
  }
}
