//CLEAN:
package movida.mackseverini;

public class Arch<E extends Comparable<E>, K extends Comparable<K>> implements Comparable<Arch<E, K>>{
  protected K weight;
  protected E vertex1;
  protected E vertex2;

  public Arch(){
    this.weight = null;
    this.vertex1 = null;
    this.vertex2 = null;
  }

  public Arch(Arch<E, K> shallow){
    this.weight = shallow.getWeight();
    this.vertex1 = shallow.getFirstVertex();
    this.vertex2 = shallow.getSecondVertex();
  }

  public Arch(E v1, E v2, K w){
    this.weight = w;
    this.vertex1 = v1;
    this.vertex2 = v2;
  }

  //@Override
  public K getWeight () { return this.weight; }
  //@Override
  public E getFirstVertex () { return this.vertex1; }
  //@Override
  public E getSecondVertex () { return this.vertex2; }

  //@Override
  public void setWeight (K w) { this.weight = w; }
  //@Override
  public void setFirstVertex (E v1) { this.vertex1 = v1; }
  //@Override
  public void setSecondVertex (E v2) { this.vertex2 = v2; }

  //@Override
  public int compareTo (Arch<E, K> input) {
    if (input == null)
      return 1;
      
    int comp1 = this.vertex1.compareTo(input.getFirstVertex()) + this.vertex1.compareTo(input.getSecondVertex());
    int comp2 = this.vertex2.compareTo(input.getFirstVertex()) + this.vertex2.compareTo(input.getSecondVertex());
    int res = comp1 + comp2;

    if (Math.abs(comp1) < 2 && Math.abs(comp2) < 2){
      if (comp1 == comp2)
        return res/2;
      return res;
    }

    return 1;
  }

  public void reset () {
    this.weight = null;
    this.vertex1 = this.vertex2 = null;
  }

  public void print(){
    System.out.println("Arch: WEIGHT => " + this.weight + "  FIRST VERTEX => " + this.vertex1 + "  SECOND VERTEX => " + this.vertex2);
  }
}
