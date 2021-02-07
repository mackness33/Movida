package movida.mackseverini;

import movida.mackseverini.IArch;

/**
 * Classe usata per rappresentare un generico arco.
 *
 * Ogni arco e' formato da un peso, e due vertici che devono essere dello stesso tipo.
 */
public class Arch<E extends Comparable<E>, K extends Comparable<K>> implements IArch<E, K>{
  protected K weight;             // weight of the arch
  protected E vertex1;            // first vertex
  protected E vertex2;            // second vertex

  // constructor
  public Arch(){
    this.weight = null;
    this.vertex1 = null;
    this.vertex2 = null;
  }

  // copy constructor
  public Arch(IArch<E, K> shallow){
    this.weight = shallow.getWeight();
    this.vertex1 = shallow.getFirstVertex();
    this.vertex2 = shallow.getSecondVertex();
  }

  // constructor
  public Arch(E v1, E v2, K w){
    this.weight = w;
    this.vertex1 = v1;
    this.vertex2 = v2;
  }

  @Override
  public K getWeight () { return this.weight; }
  @Override
  public E getFirstVertex () { return this.vertex1; }
  @Override
  public E getSecondVertex () { return this.vertex2; }

  @Override
  public void setWeight (K w) { this.weight = w; }
  @Override
  public void setFirstVertex (E v1) { this.vertex1 = v1; }
  @Override
  public void setSecondVertex (E v2) { this.vertex2 = v2; }


  // compare the input with this arch. Weight is not counted
  // it understands if the arches have the same verteces
  // it will return 1 if they're different, 0 if equal
  // there's no way to decide if one arch is "greater" of the other
  @Override
  public int compareTo (IArch<E, K> input) {
    // if input is null return fixed number
    if (input == null)
      return 1;

    // compare each vertex with the other
    int x1 = this.vertex1.compareTo(input.getFirstVertex());
    int x2 = this.vertex1.compareTo(input.getSecondVertex());
    int y1 = this.vertex2.compareTo(input.getFirstVertex());
    int y2 = this.vertex2.compareTo(input.getSecondVertex());
    int comp1 = x1 + x2, comp2 = y1 + y2;

    // if the difference between the verteces is minor of 2 for both the verteces
    if (Math.abs(x1) + Math.abs(x2) < 2 && Math.abs(y1) + Math.abs(y2) < 2){
      // check to find comparison with a same-vertex arch
      if (comp1 == comp2)
        return Math.abs(comp1 + comp2) / 2;   // return 1 or 0

      // return 0 if -1+1
      return Math.abs(comp1 + comp2);
    }

    return 1;
  }

  // reset the arch values
  @Override
  public void reset () {
    this.weight = null;
    this.vertex1 = this.vertex2 = null;
  }

}
