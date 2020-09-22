package movida.mackseverini;

import java.util.Arrays;

// Class used to virtually implements an array without its costraints
public class Graph<E extends Comparable<E>, K extends Comparable<K>> implements IMap<E>{
  protected Array<E> verteces;
  protected IKeyList<Extreme<E>, K, Integer> arches;
  protected int numVertex;
  protected int numArch;
  protected int size;                       // Max position occupied in the array


	// constructor
	public Graph()
	{
    this.verteces = new Array<E>();
    this.arches = new KeyList<Extreme<E>, K, Integer>();
    this.numVertex = 0;
		this.numArch = 0;
    this.size = 0;
	}



  // TODO:
	// constructor
	// public Graph(Graph<E, K> shallow)
	// {
	// 	// Creates a new Object array of specified length
	// 	arr = shallow.toPrimitive();
	// 	this.length = shallow.length;
	// }

  // TODO: trasform Array<Arch <>> in IKeyList<Extreme<>, ..>
  // public Graph(Array<E> V, Array<Arch<E, K>> A)
	// {
	// 	this.verteces = new Array<E>(V);
  //  this.arches = new IKeyList<Extreme<E>, K, Integer>();
  //  this.numVertex = 0;
  //  this.numArch = 0;
  //  this.size = 0;
	// }

  public Array<E> getVerteces () { return this.vertices; }

  public int numVerteces() { return this.numVertex; }
  public int numArches() { return this.numArch; }

  // trasform the list of nodes and weight into an array of arch object
  public Array<Arch> getArches(){
    if (this.numArch <= 0)
      return null;

    final Array<Arch<E, K>> array = new Array<Arch<E, K>>(this.numArch);
    Extreme<E> temp = null;

    // add all the node list per list
    for (IKeyNode<Extreme<E>, K> iter = this.arches.getHead(); iter != null; iter = iter.getNext()){
      temp = iter.getValue();
      if (iter.getKey() != null && temp.getFirstValue() != null && temp.getSecondValue() != null)
        array.set(i, new Arch(temp.getFirstValue(), temp.getSecondValue(), iter.getKey()));
    }

    return array;
  }

  public boolean addVertex(E vertex){
    if (vertex == null)
      return false;

    for (int i = 0; i < this.verteces.length; i++)
      if (vertex == this.verteces.get(i))
        return false;

    this.verteces.set(this.numVertex, vertex);
    this.numVertex++;

    return true;
  }

  public boolean addArch(Arch<E, V> arch){
    Exteme<E> e = new Extreme<E>();

    if (arch == null && this.arches.search(e))
      return false;

    this.arches.addHead(arch.getWeight(), e);
    this.numArch++;

    return true;
  }

  public boolean delVertex(E vertex){
    if (vertex == null)
      return false;

    for (int i = 0; i < this.verteces.length; i++)
      if (vertex == this.verteces.get(i)){
        this.verteces.set(i, null);
        this.numVertex--;
        return true;
      }

    return false;
  }

  public boolean delArch(Arch<E, V> arch){
  if (arch == null)
    return false;

    Exteme<E> e = new Extreme<E>();

    this.arches.delEl(e);

    if (this.numArch == this.arches.getSize())
      return false;

    this.numArch--;

    return true;
  }

  // public boolean search(Arch<E, V> arch);

  protected class Extreme <E extends Comparable<E>> implements Comparable<Extreme<E>>{
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
    public int compareTo (Extreme<T> input) { return this.value1.compareTo(input.getFirstVertex()) && this.value2.compareTo(input.getSecondVertex()); }

    @Override
    public void print(){ System.out.println("Extreme: VALUE => " + this.value1 + " VALUE2 => " + this.value2); }
  }
}
