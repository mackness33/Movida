package movida.mackseverini;

import movida.mackseverini.Arch;
import movida.mackseverini.PriorityQueue;
import movida.mackseverini.Vertex;
import movida.mackseverini.IList;
import movida.mackseverini.List;
import movida.mackseverini.IKeyList;
import movida.mackseverini.KeyList;

import java.util.Arrays;

// Class used to virtually implements an array without its costraints
public class Graph<E extends Comparable<E>, K extends Comparable<K>> implements movida.mackseverini.IGraph<E, K>{
  protected Array<Vertex<E, K>> verteces;                      // Array of verteces
  protected IKeyList<GraphPair<Integer>, K, Integer> arches;   // List of Arches
  protected int numVertex;                                          // Number of Vertex inserted
  protected int numArch;                                            // Number of Arches
  protected int size;                                               // Max position occupied in the array


	// constructor
	public Graph(){
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

  @Override
  public Array<Vertex<E, K>> getVerteces () { return this.verteces; }
  // trasform the list of nodes and weight into an array of arch object
  @Override
  public Array<IArch<E, K>> getArches(){
    if (this.numArch <= 0)
      return null;

    final Array<IArch<E, K>> array = new Array<IArch<E, K>>(this.numArch);
    int i = 0;
    GraphPair<Integer> temp = null;

    // add all the node list per list
    for (IKeyNode<GraphPair<Integer>, K> iter = (IKeyNode<GraphPair<Integer>, K>)this.arches.getHead(); iter != null; iter = (IKeyNode<GraphPair<Integer>, K>)iter.getNext(), i++){
      temp = iter.getValue();
      if (iter.getKey() != null && temp.getFirstValue() != null && temp.getSecondValue() != null)
        array.set(i, new Arch(this.verteces.get(temp.getFirstValue()), this.verteces.get(temp.getFirstValue()), iter.getKey()));
        // this.editArch(this.verteces.get(temp.getFirstValue()).getValue(), this.verteces.get(temp.getSecondValue()).getValue(), iter.getKey(), i);
    }

    return array;
  }

  protected void editArch (Array<IArch<E, K>> A, E v1, E v2, K w, Integer i){
    A.set(i, new Arch(v1, v2, w));
  }

  @Override
  public int numArches() { return this.numArch; }
  @Override
  public int numVerteces() { return this.numVertex; }

  // add a vertex
  @Override
  public boolean addVertex(E vertex){
    if (vertex == null)
      return false;

    // if already present do not add up
    for (int i = 0; i < this.verteces.length; i++)
      if (this.verteces.get(i) != null)
        if (vertex.compareTo(this.verteces.get(i).getValue()) == 0)
          return false;

    // set a the new Vertex and increment number of vertex
    this.verteces.set(this.numVertex, new Vertex(vertex));
    this.numVertex++;

    return true;
  }

  // add an arch
  @Override
  public boolean addArch(IArch<E, K> arch){
    if (arch == null)
      return false;

    // create a pair with the verteces
    GraphPair<Integer> nodes = this.findVerteces(arch.getFirstVertex(), arch.getSecondVertex());

    if (nodes == null){
      System.out.println("What's happening!");
      arch.print();
    }

    if (this.containsArch(nodes))
      return false;

    return this.addArchAndAdiacences(nodes, arch.getWeight());
  }

  @Override
  public boolean addArch(E vertex1, E vertex2, K weight){
    if (vertex1 == null && vertex2 == null && weight == null)
      return false;

    // create a pair with the verteces
    GraphPair<Integer> nodes = this.findVerteces(vertex1, vertex2);

    if (this.containsArch(nodes))
      return false;

    this.addArchAndAdiacences(nodes, weight);

    return true;
  }

  protected boolean addArchAndAdiacences(GraphPair<Integer> nodes, K weight){
    if (nodes == null || weight == null){
      System.out.println("Here is the prob!");
      return false;
    }

    // add the arch and add adiacences to the verteces
    this.arches.addHead(weight, nodes);
    this.verteces.get(nodes.getFirstValue()).addAdiacence(nodes.getSecondValue(), weight);
    if (nodes.getFirstValue() != nodes.getSecondValue())    // if the verteces are equal don't add it twice
      this.verteces.get(nodes.getSecondValue()).addAdiacence(nodes.getFirstValue(), weight);
    this.numArch++;         // increment number of arches

    return true;
  }

  // check if the arch is present
  @Override
  public boolean containsArch(IArch<E, K> arch){
    Integer res = this.findArch(arch);
    return (res != null ) ? ((res >= 0 ) ? true : false) : false;
  }

  // find the position of the arch if present
  protected Integer findArch(IArch<E, K> arch){
    if (arch == null)
      return null;

    GraphPair<Integer> nodes = findVerteces(arch.getFirstVertex(), arch.getSecondVertex());

    // verteces are not presents
    if (nodes == null)
      return -1;

    // get position of  the arch, if present return true.
    Integer res = this.arches.search(nodes);
    System.out.println("RES: " + res);
    return res;
  }

  // check if the arch is present
  protected boolean containsArch(GraphPair<Integer> nodes){
    Integer res = this.findArch(nodes);
    return (res != null ) ? ((res >= 0 ) ? true : false) : false;
  }

  // find the position of the arch if present
  protected Integer findArch(GraphPair<Integer> nodes){
    // verteces are not presents
    System.out.println("OI: " + 1);
    if (nodes == null)
      return -1;

    // get position of  the arch, if present return true.
    Integer res = this.arches.search(nodes);
    System.out.println("RES: " + res);
    return res;
  }

  protected boolean containsVerteces(E vertex1, E vertex2){ return (this.findVerteces(vertex1, vertex2) != null); }

  protected GraphPair<Integer> findVerteces(E vertex1, E vertex2){
    if (vertex1 == null || vertex2 == null)
      return null;

    Integer first = -1, second = -1;  // pos of the first and second vertex of the arch

    // search for the first and second position of the vertex
    for (int i = 0; i < this.verteces.length; i++){
      if (this.verteces.get(i) != null){
        if (vertex1.compareTo(this.verteces.get(i).getValue()) == 0 && vertex2.compareTo(this.verteces.get(i).getValue()) == 0){
          first = second = i;
          break;
        }
        else if (vertex1.compareTo(this.verteces.get(i).getValue()) == 0)
          first = i;
        else if (vertex2.compareTo(this.verteces.get(i).getValue()) == 0)
          second = i;
      }
    }

    // if one of the vertex is not present return false
    if (first == -1 || second == -1){
      System.out.println("Guess what!");
      return null;
    }

    return new GraphPair<Integer>(first, second);
  }

  // search a vertex, if present true else false
  @Override
  public boolean containsVertex(E vertex){
    Integer res = this.findVertex(vertex);
    return (res != null ) ? ((res >= 0 ) ? true : false) : false;
  }

  protected Integer findVertex(E vertex){
    if (vertex == null)
      return null;

    // check each vertex
    for (int i = 0; i < this.verteces.length; i++)
      if (this.verteces.get(i) != null)
        if (vertex.compareTo(this.verteces.get(i).getValue()) == 0)
          return i;

    return -1;
  }

  // delete of a vertex
  @Override
  public boolean delVertex(E vertex){
    if (vertex == null)
      return false;

    // search for the vertex
    for (int i = 0; i < this.verteces.length; i++){
      // if present
      if (vertex.compareTo(this.verteces.get(i).getValue()) == 0){
        this.delArchOfVertex(i);        // delete all the arches having this vertex

        // delete the verteces and decrease number of verteces
        this.verteces.set(i, null);
        this.numVertex--;

        return true;
      }
    }

    return false;
  }

  // delete all the arches having the (pos of the) vertex in input
  protected boolean delArchOfVertex(Integer vertex){
    // check if the input is valid
    if (vertex == null || numArch <= 0)
      return false;
    if (vertex < 0  || vertex >= this.verteces.length)
      return false;

    GraphPair<Integer> nodes = null;

    // check of the head. Cycle till the head of the list have the input vertex
    while (this.arches.getHead() != null){
      nodes = this.arches.getHead().getValue();

      // if vertex is not part of the head break
      if (vertex != nodes.getFirstValue() && vertex != nodes.getSecondValue())
        break;

      // delete arch and adiacence of the verteces
      this.arches.delHead();
      this.verteces.get(nodes.getFirstValue()).delAdiacence(nodes.getSecondValue());
      this.verteces.get(nodes.getSecondValue()).delAdiacence(nodes.getFirstValue());
      this.numArch--;
    }

    // iterate all the elements of the list
    for (IKeyNode<GraphPair<Integer>, K> prev = (IKeyNode<GraphPair<Integer>, K>)this.arches.getHead(), iter = (IKeyNode<GraphPair<Integer>, K>)this.arches.getHead().getNext(); iter != null; iter = (KeyNode<GraphPair<Integer>, K>)iter.getNext()){
      nodes = iter.getValue();

      // if vertex is part of the arch
      if (vertex.compareTo(nodes.getFirstValue()) == 0 || vertex.compareTo(nodes.getSecondValue()) == 0){
        // disconnect the node
        prev.setNext(iter.getNext());
        iter = prev;

        // delete the adiacence of the verteces
        this.verteces.get(nodes.getFirstValue()).delAdiacence(nodes.getSecondValue());
        this.verteces.get(nodes.getSecondValue()).delAdiacence(nodes.getFirstValue());
        this.size--;
      }

      if (iter != prev)
        prev = (IKeyNode<GraphPair<Integer>, K>)prev.getNext();
    }

    return true;
  }

  // delete an arch
  @Override
  public boolean delArch(IArch<E, K> arch){
    if (arch == null)
      return false;

    // create a pair with the verteces
    GraphPair<Integer> nodes = this.findVerteces(arch.getFirstVertex(), arch.getSecondVertex());

    // if the pair is found and deleted then delete the adiacence of the verteces
    this.delArchAndAdiacences(nodes, arch.getWeight());

    return true;
  }


  protected void delArchAndAdiacences(GraphPair<Integer> nodes, K weight){
    if (this.arches.delEl(nodes)){
      this.verteces.get(nodes.getFirstValue()).delAdiacence(nodes.getSecondValue());
      this.verteces.get(nodes.getSecondValue()).delAdiacence(nodes.getFirstValue());
      this.numArch--;
    }
  }

  // print all the verteces
  @Override
  public void printVerteces(){
    for (int i = 0; i < this.verteces.length; i++){
      if (this.verteces.get(i) != null)
        this.verteces.get(i).print();
      // else
        // System.out.println("Vertex: null");
    }
  }

  // TODO: need to add weight to arches
  // TODO: do compareTo for object without operator(-)
  // it return the Minimum Spinnig Tree of the graph using Primm's algorithm
  @Override
  public Array<IArch<E, K>> MSTPrim(E vertex){
    Integer pos_vertex = null;                                      // pos of the selected vertex
    if ((pos_vertex = this.MSTfirstcheck(vertex)) == null)
      return null;

    Array<IArch<E,K>> A = new Array<IArch<E,K>>(this.numArch);        // output array
    IArch<E,K> arch = new Arch<E, K>(vertex, vertex, ((IKeyNode<E, K>)this.arches.getHead()).getKey());                               // temporary arch
    PriorityQueue<Integer, K> PQ = new PriorityQueue<Integer, K>(); // PriorityQueue

    // adding a random weight. at the end it will be resetted back to null
    // random weight are needed because with null it will all crash.
    // the random weight won't affect the algorithm in any way
    MSTinitizialization((Array<IArch<E,K>>)A, PQ, vertex, pos_vertex, new Arch<E, K>(vertex, vertex, ((IKeyNode<E, K>)this.arches.getHead()).getKey()));
    arch.reset();
    A = MSTmain((Array<IArch<E,K>>)A, PQ, arch, vertex, pos_vertex);

    // setting the first vertex to null
    A.get(0).setWeight(null);

    return A;
  }

  protected Integer MSTfirstcheck(E vertex){
    if (vertex == null)
      return null;

    Integer pos = null;
    //  if present get vertex pos
    for (int i = 0; i < this.verteces.length; i++)
      if (this.verteces.get(i) != null)
        if (this.verteces.get(i).compareTo(new Vertex(vertex)) == 0)
          pos = i;

    // if not present or the object is null return null
    if (pos == null)
      return null;
    if (this.verteces.get(pos) == null)
      return null;

    return pos;
  }

  protected void MSTinitizialization(Array<IArch<E,K>> A, PriorityQueue<Integer, K> PQ, E vertex, Integer pos, IArch<E,K> newArch){
    // initialize arch array
    for (int i = 0; i < A.length; i++)
      A.set(i, null);

    // insert the root vertex
    this.MSTaddOuputArch(A, 0, newArch);
    PQ.insert(pos, A.get(0).getWeight());
  }

  protected void MSTaddOuputArch(Array<IArch<E,K>> A, Integer pos, IArch<E, K> arch){
    A.set(pos, new Arch<E,K>(arch));            // add the arch to the output arch
  }

  protected Array<IArch<E,K>> MSTmain(Array<IArch<E,K>> A, PriorityQueue<Integer, K> PQ, IArch<E,K> arch, E vertex, Integer pos_vertex){

    // till PQ is empty;  reset the temporary arch
    for(Integer pos_arch = 0, last_arch = 1; !PQ.isEmpty(); arch.reset()){
      // find min and delete it
      pos_vertex = PQ.findMin();
      PQ.delMin();

      arch.setSecondVertex(this.verteces.get(pos_vertex).getValue());        // set second arch vertex to the selected one

      // for each adiacence of the vertex
      for (IKeyNode<Integer, K> iter = (IKeyNode<Integer, K>)this.verteces.get(pos_vertex).getAdiacence().getHead(); iter != null; iter = (IKeyNode<Integer, K>)iter.getNext(), arch.setWeight(null)){
        // checks values
        if (this.MSTchecks(iter, pos_vertex)){
          // set first vertex of the temporary arch
          arch.setFirstVertex(this.verteces.get(iter.getValue()).getValue());

          // if the arch is already present pass to the next vertex
          for (int i = 0; i < A.length; i++){
            // if the element at the index is null than no need to continue
            if (A.get(i) == null)
              break;
            // if the element of the vertex is already in the output array then get its weight and remember the position
            if (this.verteces.get(iter.getValue()).getValue().compareTo(A.get(i).getFirstVertex()) == 0){
              arch.setWeight(A.get(i).getWeight());
              pos_arch = i;
              break;
            }
          }

          last_arch = this.MSTaction(A, PQ, iter, arch, pos_arch, last_arch, pos_vertex);
        }
      }
    }

    return A;
  }

  protected Integer MSTaction(Array<IArch<E,K>> A, PriorityQueue<Integer, K> PQ, IKeyNode<Integer, K> iter, IArch<E,K> arch, Integer pos_arch, Integer last_arch, Integer pos_vertex){
    // if there's no weight associated to the vertex
    if (arch.getWeight() == null){
      PQ.insert(iter.getValue(), iter.getKey());      // insert the vertex to the PriorityQueue
      arch.setWeight(iter.getKey());                  // set the weight of the arch
      this.MSTaddOuputArch(A, last_arch, arch);       // add the arch to the output arch
      return last_arch+1;                                            // increment pos of the last arch in the output array
    }
    // else if weight of the adiacence minor than the weight of the arch AND the vertex of the adiacence is in the PriorityQueue
    else if (iter.getKey().compareTo(A.get(pos_arch).getWeight()) < 0 && PQ.check(iter.getValue())){
      A.get(pos_arch).setWeight(iter.getKey());                                   // set weight with the adiacence
      A.get(pos_arch).setSecondVertex(this.verteces.get(pos_vertex).getValue());        // set the new second Vertex
      PQ.decreaseKey(iter.getValue(), iter.getKey());                             // decreaseKey by weight of the adiacence weight
    }

    return last_arch;
  }

  private boolean MSTchecks(IKeyNode<Integer, K> adiacence, Integer vertex){
    // check if value is null
    if (adiacence.getValue() == null || adiacence.getKey() == null)
      return false;

    // check if the vertex is null
    if (this.verteces.get(adiacence.getValue()) == null)
      return false;

    // check if the two verteces are the same
    if (adiacence.getValue() == vertex)
      return false;

    // alright
    return true;
  }

  // class to handle pair of verteces
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

    public E getFirstValue() { return this.value1; }
    public E getSecondValue() { return this.value2; }

    public void setFirstValue (E v) { this.value1 = v; }
    public void setSecondValue (E v) { this.value2 = v; }

    // compare the input with this pair
    // for now it just understand if the pairs are the exactly the same
    // TODO: decide a int for null and major and minor
    @Override
    public int compareTo (GraphPair<E> input) {
      // if input is null return fixed number
      if (input == null)
        return 1;

      // compare each value with the other
      int x1 = this.value1.compareTo(input.getFirstValue());
      int x2 = this.value1.compareTo(input.getSecondValue());
      int y1 = this.value2.compareTo(input.getFirstValue());
      int y2 = this.value2.compareTo(input.getSecondValue());
      int comp1 = x1 + x2, comp2 = y1 + y2;

      // if the difference between the values is minor of 2 for both the verteces
      if (Math.abs(x1) + Math.abs(x2) < 2 && Math.abs(y1) + Math.abs(y2) < 2){
        System.out.print("input: ");
        input.print();
        System.out.print("arch: ");
        this.print();
        System.out.println("RES in arch: " + comp1 + comp2);
        // check to find comparison with a same-value pair
        if (comp1 == comp2)
          return Math.abs(comp1 + comp2) / 2;   // return 1 or 0

        // return 0 if -1+1
        return Math.abs(comp1 + comp2);
      }

      return 1;
    }

    public void print(){ System.out.println("GraphPair: VALUE => " + this.value1 + " VALUE2 => " + this.value2); }
  }
}
