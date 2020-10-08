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
public class Graph<E extends Comparable<E>>{
  protected Array<Vertex<E, Double>> verteces;
  protected IKeyList<GraphPair<Integer>, Double, Integer> arches;
  protected int numVertex;
  protected int numArch;
  protected int size;                       // Max position occupied in the array


	// constructor
	public Graph()
	{
    this.verteces = new Array<Vertex<E, Double>>(50);
    this.arches = new KeyList<GraphPair<Integer>, Double, Integer>();
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

  public Array<Vertex<E, Double>> getVerteces () { return this.verteces; }

  public int numVerteces() { return this.numVertex; }
  public int numArches() { return this.numArch; }

  // trasform the list of nodes and weight into an array of arch object
  public Array<Arch<E, Double>> getArches(){
    if (this.numArch <= 0)
      return null;

    final Array<Arch<E, Double>> array = new Array<Arch<E, Double>>(this.numArch);
    int i = 0;
    GraphPair<Integer> temp = null;

    // add all the node list per list
    for (IKeyNode<GraphPair<Integer>, Double> iter = (IKeyNode<GraphPair<Integer>, Double>)this.arches.getHead(); iter != null; iter = (IKeyNode<GraphPair<Integer>, Double>)iter.getNext(), i++){
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
      if (this.verteces.get(i) != null)
        if (vertex.compareTo(this.verteces.get(i).getValue()) == 0)
          return false;

    this.verteces.set(this.numVertex, new Vertex(vertex));
    this.numVertex++;

    return true;
  }

  public boolean addArch(Arch<E, Double> arch){
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
    this.verteces.get(first).addAdiacence(second, arch.getWeight());
    this.verteces.get(second).addAdiacence(first, arch.getWeight());
    this.numArch++;

    return true;
  }

  public boolean addArch(E vertex1, E vertex2, Double weight){
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
        this.verteces.get(first).addAdiacence(second, weight);
        this.verteces.get(second).addAdiacence(first, weight);
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
      this.verteces.get(e.getFirstValue()).delAdiacence(e.getSecondValue());
      this.verteces.get(e.getSecondValue()).delAdiacence(e.getFirstValue());
      this.numArch--;
    }

    // iterate all the elements of the list
    for (IKeyNode<GraphPair<Integer>, Double> prev = (IKeyNode<GraphPair<Integer>, Double>)this.arches.getHead(), iter = (IKeyNode<GraphPair<Integer>, Double>)this.arches.getHead().getNext(); iter != null; iter = (KeyNode<GraphPair<Integer>, Double>)iter.getNext()){
      e = iter.getValue();

      if (vertex.compareTo(e.getFirstValue()) == 0 || vertex.compareTo(e.getSecondValue()) == 0){
        prev.setNext(iter.getNext());
        iter = prev;
        this.verteces.get(e.getFirstValue()).delAdiacence(e.getSecondValue());
        this.verteces.get(e.getSecondValue()).delAdiacence(e.getFirstValue());
        this.size--;
      }

      if (iter != prev)
        prev = (IKeyNode<GraphPair<Integer>, Double>)prev.getNext();
    }

    return;
  }

  public boolean delArch(Arch<E, Double> arch){
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

    if (this.arches.delEl(e));{
      this.verteces.get(first).delAdiacence(second);
      this.verteces.get(second).delAdiacence(first);
      this.numArch--;
    }

    return true;
  }

  public boolean searchArch(Arch<E, Double> arch){
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

  public void printVerteces(){
    for (int i = 0; i < this.verteces.length; i++){
      if (this.verteces.get(i) != null)
        this.verteces.get(i).print();
      else
        System.out.println("Vertex: null");
    }
  }

  public Array<Arch<E, Double>> MSTPrim(E vertex){
    if (vertex == null)
      return null;

    Array<Arch<E,Double>> A = new Array<Arch<E,Double>>(this.numArch);
    Arch<E,Double> arch = new Arch<E,Double>();
    Integer pos_vertex = null;
    PriorityQueue<Integer, Double> PQ = new PriorityQueue<Integer, Double>();

    for (int i = 0; i < this.verteces.length; i++)
      if (this.verteces.get(i) != null)
        if (this.verteces.get(i).compareTo(new Vertex(vertex)) == 0)
          pos_vertex = i;

    if (pos_vertex == null)
      return null;
    if (this.verteces.get(pos_vertex) == null)
      return null;


    for (int i = 0; i < A.length; i++)
      A.set(i, null);

    PQ.insert(pos_vertex, 0.0);
    System.out.println("check");
    System.out.println("pos vertex: " + pos_vertex);

    for(Integer temp = 0, pos_arch = 0, j = 0; !PQ.isEmpty() && j < 50; arch.reset()){
      temp = PQ.findMin();
      System.out.println("min: " + temp);
      PQ.delMin();
      arch.setFirstVertex(this.verteces.get(temp).getValue());

      System.out.println("Adiacence: " + this.verteces.get(temp).getAdiacence().getSize());
      for (IKeyNode<Integer, Double> iter = (IKeyNode<Integer, Double>)this.verteces.get(temp).getAdiacence().getHead(); iter != null; iter = (IKeyNode<Integer, Double>)iter.getNext(), j++, arch.setWeight(null)){
        if (iter.getValue() == null) break;

        if (this.verteces.get(iter.getValue()) == null) break;

        arch.setSecondVertex(this.verteces.get(iter.getValue()).getValue());
        System.out.println("vert: " + this.verteces.get(iter.getValue()).getValue());

        for (int i = 0; i < A.length; i++){
          if (arch.compareTo(A.get(i)) == 0){
            arch.setWeight(A.get(i).getWeight());
            pos_arch = i;
            break;
          }
        }
        System.out.println("pos_arch: " + pos_arch);

        if (arch.getWeight() == null){
          System.out.println("Inserting at " + j + ": " + iter.getValue());
          PQ.insert(iter.getValue(), iter.getKey());
          A.set(j, new Arch<E, Double>(arch));
        }
        else if (iter.getKey().compareTo(A.get(pos_arch).getWeight()) < 0){
          System.out.println("Iter Weight: " + iter.getKey());
          System.out.println("MainArch Weight: " + A.get(pos_arch).getWeight());
          PQ.decreaseKey(iter.getValue(), A.get(pos_arch).getWeight() - iter.getKey());
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
