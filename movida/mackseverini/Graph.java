package movida.mackseverini;

import java.util.Arrays;

// Class used to virtually implements an array without its costraints
public class Graph<E extends Comparable<E>, K extends Comparable<K>> {
  protected Array<E> verteces;
  protected IKeyList<Extreme<E>, K, Integer> arches;
  protected int numVertex;
  protected int numArch;
  protected int size;                       // Max position occupied in the array

	// constructor
	public Graph()
	{
    this.verteces = new Array<E>();
    this.arches = new IKeyList<Extreme<E>, K, Integer>();
    this.size = 0;
		this.length = 0;
	}

  // TODO:
	// constructor
	// public Graph(Graph<E, K> shallow)
	// {
	// 	// Creates a new Object array of specified length
	// 	arr = shallow.toPrimitive();
	// 	this.length = shallow.length;
	// }

  // TODO:
  // public Graph(Array<E> V, IKeyList<Extreme<E>, K, Integer> A)
	// {
	// 	// Creates a new Object array of specified length
  //   for (int i = 0; i < ; i++){
  //     vertece = V;
  //     arches = A;
  //   }
	// }

  public Array<E> getVerteces () { return this.vertices; }

  public int numVerteces() { return this.numVertex; }
  public int numArches() { return this.numArch; }

  public IKeyList<Extreme<E>, K, Integer> arches

  protected class Extreme <E extends Comparable<E>>{
    protected E value1;
		protected E value2;

    public Extreme(){
      this.value1 = null;
      this.value2 = null;
    }

    public Extreme(E v1, E v2){
      this.value1 = v1;
      this.value2 = v2;
    }

    @Override
    public E getFirstValue() { return this.value1; }
    @Override
    public E getSecondValue() { return this.value2; }

    @Override
    public void setFirstValue (E v) { this.value1 = v; }
    @Override
    public void setSecondValue (E v) { this.value2 = v; }


    @Override
    public void print(){ System.out.println("Extreme: VALUE => " + this.value1 + " VALUE2 => " + this.value2); }
  }
}
