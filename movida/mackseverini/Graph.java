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

    Array<Arch<E,K>> A = new Array<Arch<E,K>>(this.numArch);
    Arch<E,K> arch = new Arch<E,K>();
    Integer pos_vertex = null;
    PriorityQueue<Integer, Integer> PQ = new PriorityQueue<Integer, Integer>();

    for (int i = 0; i < this.verteces.length; i++)
      if (this.verteces.get(i).compareTo(new Vertex(vertex)) == 0)
        pos_vertex = i;

    if (this.verteces.get(pos_vertex) == null || pos_vertex == null)
      return null;

    for (int i = 0; i < A.length; i++)
      A.set(i, null);

    PQ.insert(pos_vertex, 0);

    for(Integer temp = 0, pos_arch = 0; !PQ.isEmpty(); arch.reset()){
      temp = PQ.findMin();
      PQ.delMin();
      arch.setFirstVertex(this.verteces.get(temp).getValue());

      for (IKeyNode<Integer, K> iter = (IKeyNode<Integer, K>)this.verteces.get(temp).getAdiacence().getHead(); iter != null; iter = (IKeyNode<Integer, K>)iter.getNext()){
        arch.setSecondVertex(this.verteces.get(iter.getValue()).getValue());

        for (int i = 0; i < A.length; i++){
          if (arch.compareTo(A.get(pos_arch)) == 0){
            arch.setWeight(A.get(i).getWeight());
            pos_arch = i;
            break;
          }
        }

        if (arch.getWeight() == null){
          PQ.insert(temp, iter.getKey());
          A.set(pos_arch, new Arch<E, K>(arch));
        }
        else if (iter.getKey().compareTo(A.get(pos_arch).getWeight()) < 0){
          PQ.decreaseKey(pos_vertex, A.get(pos_arch).getWeight() - iter.getKey());
          A.get(pos_arch).setWeight(iter.getKey());
        }
      }
    }

    return A;
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
