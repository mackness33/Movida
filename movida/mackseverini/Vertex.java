//CLEAN:
package movida.mackseverini;

public class Vertex<E extends Comparable<E>, K extends Comparable<K>> implements Comparable<Vertex<E, K>>{
  protected E value;
  protected IList<Pair<E,K>> adiacence;

  public Vertex(){
    this.value = null;
    this.adiacence = new List<Pair<E,K>>();
  }

  public Vertex(Vertex<E, K> shallow){
    this.value = shallow.getValue();
    this.adiacence = shallow.getAdiacence();
  }

  public Arch(E v){
    this.value = v;
    this.adiacence = new List<Pair<E,K>>();
  }

  //@Override
  public IList<Pair<E,K>> getAdiacence () { return this.adiacence; }
  //@Override
  public E getValue () { return this.value; }

  //@Override
  public void setValue (E v) { this.value = v; }

  public void addAdiacence (E v, K w) { this.adiacence.addHead(new Pair<E,K>(v, w)); }

  //@Override
  public int compareTo (Vertex input) { return this.value.compareTo(input.getValue()); }

  public void print(){
    System.out.println("Vertex: VALUE => " + this.value + " \n\r ADIACENCE => ");
    this.adiacence.print();
  }
}
