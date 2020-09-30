package movida.mackseverini;

import movida.mackseverini.Arch;
import movida.mackseverini.PriorityQueue;
import movida.mackseverini.Vertex;
import movida.mackseverini.Pair;
import movida.mackseverini.IList;
import movida.mackseverini.List;
import movida.mackseverini.IKeyList;
import movida.mackseverini.KeyList;

import java.util.Arrays;

// Class used to virtually implements an array without its costraints
public class Graph<E extends Comparable<E>, K extends Comparable<K>>{
  protected Array<Vertex<E, K>> verteces;
  protected IKeyList<GraphPair<Integer>, K, Integer> arches;
  protected int numVertex;
  protected int numArch;
  protected int size;                       // Max position occupied in the array


	// constructor
	public Graph()
	{
    this.verteces = new Array<Vertex<E, K>>(50);
    this.arches = new KeyList<GraphPair<Integer>, K, Integer>();
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
  //  this.arches = new IKeyList<Pair<Vertex<E,K>>, K, Integer>();
  //  this.numVertex = 0;
  //  this.numArch = 0;
  //  this.size = 0;
	// }

  public Array<Vertex<E, K>> getVerteces () { return this.verteces; }

  public int numVerteces() { return this.numVertex; }
  public int numArches() { return this.numArch; }

  // trasform the list of nodes and weight into an array of arch object
  public Array<Arch<E, K>> getArches(){
    if (this.numArch <= 0)
      return null;

    final Array<Arch<E, K>> array = new Array<Arch<E, K>>(this.numArch);
    int i = 0;
    GraphPair<Integer> temp = null;

    // add all the node list per list
    for (IKeyNode<GraphPair<Integer>, K> iter = (IKeyNode<GraphPair<Integer>, K>)this.arches.getHead(); iter != null; iter = (IKeyNode<GraphPair<Integer>, K>)iter.getNext(), i++){
      temp = iter.getValue();
      if (iter.getKey() != null && temp.getFirstValue() != null && temp.getSecondValue() != null)
        array.set(i, new Arch(this.verteces.get(temp.getFirstValue()), this.verteces.get(temp.getSecondValue()), iter.getKey()));
    }

    return array;
  }

  public boolean addVertex(E vertex){
    if (vertex == null)
      return false;

    for (int i = 0; i < this.verteces.length; i++)
      if (vertex == this.verteces.get(i))
        return false;

    this.verteces.set(this.numVertex, new Vertex(vertex));
    this.numVertex++;

    return true;
  }

  public boolean addArch(Arch<E, K> arch){
    if (arch == null)
      return false;

    Integer first = -1, second = -1;

    for (int i = 0; i < this.verteces.length; i++){
      if (this.verteces.get(i) != null){
        if (arch.getFirstVertex().compareTo(this.verteces.get(i).getValue()) == 0 && arch.getSecondVertex().compareTo(this.verteces.get(i).getValue()) == 0)
          first = second = i;
        else if (arch.getFirstVertex().compareTo(this.verteces.get(i).getValue()) == 0)
          first = i;
        else if (arch.getSecondVertex().compareTo(this.verteces.get(i).getValue()) == 0)
          second = i;
      }
    }

    if (first == -1 || second == -1)
      return false;

    if (this.searchArch(arch))
      return false;

    GraphPair<Integer> e = new GraphPair<Integer>(first, second);

    this.arches.addHead(arch.getWeight(), e);
    this.numArch++;

    return true;
  }

  public boolean addArch(E vertex1, E vertex2, K weight){
    if (vertex1 == null && vertex2 == null && weight == null)
      return false;


    Integer first = -1, second = -1;

    for (int i = 0; i < this.verteces.length; i++){
      if (this.verteces.get(i) != null){
        if (vertex1.compareTo(this.verteces.get(i).getValue()) == 0 && vertex2.compareTo(this.verteces.get(i).getValue()) == 0)
          first = second = i;
        else if (vertex1.compareTo(this.verteces.get(i).getValue()) == 0)
          first = i;
        else if (vertex2.compareTo(this.verteces.get(i).getValue()) == 0)
          second = i;
      }
    }

    if (first == -1 || second == -1)
      return false;

    GraphPair<Integer> e = new GraphPair<Integer>(first, second);

    Integer res = this.arches.search(e);

    if (res != null){
      if(res != 0){
        this.arches.addHead(weight, e);

        this.numArch++;

        return true;
      }
    }

    return false;
  }

  public boolean delVertex(E vertex){
    if (vertex == null)
      return false;

    for (int i = 0; i < this.verteces.length; i++){
      if (vertex.compareTo(this.verteces.get(i).getValue()) == 0){
        this.delArchOfVertex(i);

        this.verteces.set(i, null);
        this.numVertex--;

        return true;
      }
    }

    return false;
  }

  protected void delArchOfVertex(Integer vertex){
    if (vertex == null || numArch <= 0)
      return;

    if (vertex < 0  || vertex >= this.verteces.length)
      return;

    GraphPair<Integer> e = null;

    while (this.arches.getHead() != null){
      e = this.arches.getHead().getValue();

      if (vertex.compareTo(e.getFirstValue()) != 0 && vertex.compareTo(e.getSecondValue()) != 0)
        break;

      this.arches.delHead();
      this.numArch--;
    }

    // iterate all the elements of the list
    for (IKeyNode<GraphPair<Integer>, K> prev = (IKeyNode<GraphPair<Integer>, K>)this.arches.getHead(), iter = (IKeyNode<GraphPair<Integer>, K>)this.arches.getHead().getNext(); iter != null; iter = (KeyNode<GraphPair<Integer>, K>)iter.getNext()){
      e = iter.getValue();

      if (vertex.compareTo(e.getFirstValue()) == 0 || vertex.compareTo(e.getSecondValue()) == 0){
        prev.setNext(iter.getNext());
        iter = prev;
        this.size--;
      }

      if (iter != prev)
        prev = (IKeyNode<GraphPair<Integer>, K>)prev.getNext();
    }

    return;
  }

  public boolean delArch(Arch<E, K> arch){
    if (arch == null)
      return false;

    Integer first = -1, second = -1;

    for (int i = 0; i < this.verteces.length; i++){
      if (this.verteces.get(i) != null){
        if (arch.getFirstVertex().compareTo(this.verteces.get(i).getValue()) == 0 && arch.getSecondVertex().compareTo(this.verteces.get(i).getValue()) == 0)
          first = second = i;
        else if (arch.getFirstVertex().compareTo(this.verteces.get(i).getValue()) == 0)
          first = i;
        else if (arch.getSecondVertex().compareTo(this.verteces.get(i).getValue()) == 0)
          second = i;
      }
    }

    if (first == -1 || second == -1)
      return false;


    GraphPair<Integer> e = new GraphPair<Integer>(first, second);

    if (this.arches.delEl(e));
      this.numArch--;

    return true;
  }

  public boolean searchArch(Arch<E, K> arch){
    Integer first = -1, second = -1;

    for (int i = 0; i < this.verteces.length; i++){
      if (this.verteces.get(i) != null){
        if (arch.getFirstVertex().compareTo(this.verteces.get(i).getValue()) == 0 && arch.getSecondVertex().compareTo(this.verteces.get(i).getValue()) == 0)
          first = second = i;
        else if (arch.getFirstVertex().compareTo(this.verteces.get(i).getValue()) == 0)
          first = i;
        else if (arch.getSecondVertex().compareTo(this.verteces.get(i).getValue()) == 0)
          second = i;
      }
    }

    if (first == -1 || second == -1)
      return false;

    Integer res = this.arches.search(new GraphPair<Integer>(first, second));
    System.out.println("RES: " + res);
    return (res != null ) ? ((res == 0 ) ? true : false) : false;
  }

  public boolean searchVertex(E vertex){
    if (vertex == null)
      return false;

    for (int i = 0; i < this.verteces.length; i++)
      if (this.verteces.get(i) != null)
        if (vertex.compareTo(this.verteces.get(i).getValue()) == 0)
          return true;

    return false;
  }

  public Arch[] MSTPrim(E vertex){
    if (vertex == null)
      return null;

    Arch[] A = new Arch[this.numArch];
    boolean[] mark = new boolean[this.numArch];
    Integer pos_vertex = null, weight_arch = null;
    Vertex<E, K> V = null;
    PriorityQueue<GraphPair<Integer, Integer>, Integer> PQ = new PriorityQueue<GraphPair<Integer, Integer>, Integer>();

    for (int i = 0; i < this.verteces.length; i++){
      if (this.verteces.get(i).compareTo(new Vertex(vertex)) == 0){
        pos_vertex = i;
        V = this.verteces.get(i);
      }
    }

    if (V == null || pos_vertex == null)
      return null;

    for (int i = 0; i < A.length; i++)
      A.set(i, null);

    PQ.insert(new GraphVertex<Integer, Integer>(pos_vertex, 0), 0);

    GraphPair<Integer, Integer> temp = null;
    for(int i = 0; i < A.length && !PQ.isEmpty(); i++){
      temp = PQ.findMin();
      // weight_arch = PQ.getKey(temp, 1);
      PQ.delMin();

      V = this.verteces.get(temp.getFirstValue());
      for (IKeyNode<Integer, Integer> iter = V.getAdiacence(); iter != null; iter = (IKeyNode<Integer, Integer>)iter.getNext()){
        temp = new GraphPair<Integer, Integer>(iter.getValue(), );
        if (mark[i] == null){
          PQ.insert(new GraphVertex<Integer, Integer>(temp.getValue(), temp.getKey()), iter.getKey());
          A[i] = new Arch<E, K>(V.getValue(), this.verteces.get(temp.getValue()).getValue(), iter.getKey());
        }
        else if (iter.getKey() < A[i].getWeight()){
          PQ.decreaseKey(this.verteces.get(temp.getValue()), A[i].getWeight() - iter.getKey())
          A[i] = new Arch<E, K>(V.getValue(), this.verteces.get(iter.getValue()).getValue(), iter.getKey());
        }
      }
    }

    return null;
  }


  protected class GraphVertex <E extends Comparable<E>, K extends Comparable<K>> extends Vertex<E, K>{ //implements Comparable<GraphVertex<E, K>>{
    int final pos_in_arch;

    public GraphVertex(int p){
      super();
      pos_in_arch = p;
    }

    public GraphVertex(Vertex<E, K> shallow, int p){
      super(shallow);
      pos_in_arch = p;
    }

    public GraphVertex(E v, int p){
      super(v);
      pos_in_arch = p;
    }

    public GraphVertex(E v, IKeyList<E, K, Integer> a, int p){
      super(v, a);
      pos_in_arch = p;
    }

  protected class GraphPair <E extends Comparable<E>> implements Comparable<GraphPair<E>>{
    protected E value1;
		protected E value2;

    public GraphPair(){
      this.value1 = null;
      this.value2 = null;
    }

    public GraphPair(E v1, E v2){
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
    public int compareTo (GraphPair<E> input) {
      int comp1 = this.value1.compareTo(input.getFirstValue()) + this.value1.compareTo(input.getSecondValue());
      int comp2 = this.value2.compareTo(input.getFirstValue()) + this.value2.compareTo(input.getSecondValue());
      int res = comp1 + comp2;

      if (Math.abs(comp1) < 2 && Math.abs(comp2) < 2){
        if (comp1 == comp2)
          return res/2;
        return res;
      }

      return 1;
    }

    //@Override
    public void print(){ System.out.println("GraphPair: VALUE => " + this.value1 + " VALUE2 => " + this.value2); }
  }
}
