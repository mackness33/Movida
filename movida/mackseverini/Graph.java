package movida.mackseverini;

import movida.mackseverini.Arch;
import java.util.Arrays;

// Class used to virtually implements an array without its costraints
public class Graph<E extends Comparable<E>, K extends Comparable<K>>{
  protected Array<E> verteces;
  protected IKeyList<Pair<E>, K, Integer> arches;
  protected int numVertex;
  protected int numArch;
  protected int size;                       // Max position occupied in the array


	// constructor
	public Graph()
	{
    this.verteces = new Array<E>(50);
    this.arches = new KeyList<Pair<E>, K, Integer>();
    this.numVertex = 0;
		this.numArch = 0;
    this.size = 0;

    for (int i = 0; i < this.verteces.length; i++)
      this.verteces.set(i, null);
	}



  // TODO:
	// constructor
	// public Graph(Graph<E, K> shallow)
	// {
	// 	// Creates a new Object array of specified length
	// 	arr = shallow.toPrimitive();
	// 	this.length = shallow.length;
	// }

  // TODO: trasform Array<Arch <>> in IKeyList<Pair<>, ..>
  // public Graph(Array<E> V, Array<Arch<E, K>> A)
	// {
	// 	this.verteces = new Array<E>(V);
  //  this.arches = new IKeyList<Pair<E>, K, Integer>();
  //  this.numVertex = 0;
  //  this.numArch = 0;
  //  this.size = 0;
	// }

  public Array<E> getVerteces () { return this.verteces; }

  public int numVerteces() { return this.numVertex; }
  public int numArches() { return this.numArch; }

  // trasform the list of nodes and weight into an array of arch object
  public Array<Arch<E, K>> getArches(){
    if (this.numArch <= 0)
      return null;

    final Array<Arch<E, K>> array = new Array<Arch<E, K>>(this.numArch);
    int i = 0;
    Pair<E> temp = null;

    // add all the node list per list
    for (IKeyNode<Pair<E>, K> iter = (IKeyNode<Pair<E>, K>)this.arches.getHead(); iter != null; iter = (IKeyNode<Pair<E>, K>)iter.getNext(), i++){
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

  public boolean addArch(Arch<E, K> arch){
    Pair<E> e = new Pair<E>(arch.getFirstVertex(), arch.getSecondVertex());

    if (arch == null && this.arches.search(e) == null)
      return false;

    this.arches.addHead(arch.getWeight(), e);
    this.numArch++;

    return true;
  }

  public boolean addArch(E vertex1, E vertex2, K weight){
    if (vertex1 == null && vertex2 == null && weight == null)
      return false;

    Pair<E> e = new Pair<E>(vertex1, vertex2);

    if (this.arches.search(e) != null)
      return false;

    this.arches.addHead(weight, e);
    this.numArch++;

    return true;
  }

  public boolean delVertex(E vertex){
    if (vertex == null)
      return false;

    for (int i = 0; i < this.verteces.length; i++){
      if (vertex == this.verteces.get(i)){
        this.delArchOfVertex(this.verteces.get(i));

        this.verteces.set(i, null);
        this.numVertex--;

        return true;
      }
    }

    return false;
  }

  protected void delArchOfVertex(E vertex){
    if (vertex == null)
      return;

    Pair<E> e = this.arches.getHead().getValue();

    e.print();

    while (this.arches.getHead() != null){
      e = this.arches.getHead().getValue();

      System.out.println("First compare: " + (vertex.compareTo(e.getFirstValue()) != 0 && vertex.compareTo(e.getSecondValue()) != 0));
      System.out.println("First Addend: " + (vertex.compareTo(e.getFirstValue()) != 0) + "  Val: " + e.getFirstValue());
      System.out.println("Second Addend: " + (vertex.compareTo(e.getSecondValue()) != 0) + "  Val: " + e.getSecondValue());

      if (vertex.compareTo(e.getFirstValue()) != 0 && vertex.compareTo(e.getSecondValue()) != 0)
        break;

      this.arches.delHead();
      this.numArch--;
    }

    ((KeyNode<Pair<E>, K>)this.arches.getHead()).print();
    ((KeyNode<Pair<E>, K>)this.arches.getHead().getNext()).print();

    // iterate all the elements of the list
    for (IKeyNode<Pair<E>, K> prev = (IKeyNode<Pair<E>, K>)this.arches.getHead(), iter = (IKeyNode<Pair<E>, K>)this.arches.getHead().getNext(); iter != null; iter = (KeyNode<Pair<E>, K>)iter.getNext()){
      e = iter.getValue();

      if (vertex.compareTo(e.getFirstValue()) == 0 || vertex.compareTo(e.getSecondValue()) == 0){
        prev.setNext(iter.getNext());
        iter = prev;
        this.size--;
      }

      if (iter != prev)
        prev = (IKeyNode<Pair<E>, K>)prev.getNext();
    }

    return;
  }

  public boolean delArch(Arch<E, K> arch){
  if (arch == null)
    return false;

    Pair<E> e = new Pair<E>(arch.getFirstVertex(), arch.getSecondVertex());

    this.arches.delEl(e);

    if (this.numArch == this.arches.getSize())
      return false;

    this.numArch--;

    return true;
  }

  // public boolean search(Arch<E, V> arch);

  protected class Pair <E extends Comparable<E>> implements Comparable<Pair<E>>{
    protected E value1;
		protected E value2;

    public Pair(){
      this.value1 = null;
      this.value2 = null;
    }

    public Pair(E v1, E v2){
      this.value1 = v1;
      this.value2 = v2;
    }

    //@Override
    public E getFirstValue() { return this.value1; }
    //@Override
    public E getSecondValue() { return this.value2; }

    //@Override
    public void setFirstValue (E v) { this.value1 = v; }
    //@Override
    public void setSecondValue (E v) { this.value2 = v; }

    //@Override
    public int compareTo (Pair<E> input) {
      int res = Math.abs(this.value1.compareTo(input.getFirstValue()) + this.value1.compareTo(input.getSecondValue())) -
        Math.abs(this.value2.compareTo(input.getFirstValue()) + this.value2.compareTo(input.getSecondValue()));

      System.out.println("COMPARE res: " + res);
      return res;

    }

    //@Override
    public void print(){ System.out.println("Pair: VALUE => " + this.value1 + " VALUE2 => " + this.value2); }
  }
}
