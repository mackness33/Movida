package movida.mackseverini;

import movida.mackseverini.Arch;
import movida.mackseverini.PriorityQueue;
import movida.mackseverini.Vertex;
import movida.mackseverini.IList;
import movida.mackseverini.List;
import movida.mackseverini.IKeyList;
import movida.mackseverini.KeyList;
import movida.mackseverini.Node2;
import movida.mackseverini.Queue;
import movida.mackseverini.INode2;

import java.util.Arrays;

// Implementation of a Graph
public class Graph<E extends Comparable<E>, K extends Comparable<K>> implements movida.mackseverini.IGraph<E, K>{
  protected Array<IVertex<E, K>> verteces;                      // Array of verteces
  protected IList<IArch<Integer, K>> arches;   // List of Arches
  protected int numVertex;                                          // Number of Vertex inserted
  protected int numArch;                                            // Number of Arches
  protected int size;                                               // Max position occupied in the array

	// constructor
	public Graph(){
    this.verteces = new Array<IVertex<E, K>>(50);
    this.arches = new List<IArch<Integer, K>>();
    this.numVertex = 0;
		this.numArch = 0;
    this.size = 0;

    for (int i = 0; i < this.verteces.length; i++)
      this.verteces.set(i, null);
	}

  @Override
  public Array<IVertex<E, K>> getVerteces () { return this.verteces; }

  // trasform the list of nodes and weight into an array of arch object
  @Override
  public Array<IArch<E, K>> getArches(){
    if (this.numArch <= 0)
      return null;

    final Array<IArch<E, K>> array = new Array<IArch<E, K>>(this.numArch);
    int i = 0;
    IArch<Integer, K> temp = null;

    // add all the node list per list
    for (INode2<IArch<Integer, K>> iter = (INode2<IArch<Integer, K>>)this.arches.getHead(); iter != null; iter = (INode2<IArch<Integer, K>>)iter.getNext(), i++){
      temp = iter.getValue();
      if (temp.getWeight() != null && temp.getFirstVertex() != null && temp.getSecondVertex() != null)
        array.set(i, new Arch(this.verteces.get(temp.getFirstVertex()).getValue(), this.verteces.get(temp.getSecondVertex()).getValue(), temp.getWeight()));
    }

    return array;
  }

  @Override
  public int numArches() { return this.numArch; }
  @Override
  public int numVerteces() { return this.numVertex; }

  @Override
  // add a vertex
  public boolean addVertex(E vertex){ return this.addGenericVertex(this.verteces, vertex); }

  // add a new generic vertex
	protected <T extends Comparable<T>> boolean addGenericVertex(Array<IVertex<E, T>> list_of_vtx, E vertex){
    if (vertex == null)
      return false;

		// if the vertex is not present in the array add it else return false
    if (!this.checkAndAddVertex(list_of_vtx, new Vertex<E, T>(vertex)))
			return false;

		// increse number of verteces present in the graph
    this.numVertex++;

    return true;
  }

  // check if the array is present or add it
  protected <T extends Comparable<T>> boolean checkAndAddVertex(Array<IVertex<E, T>> list_of_vtx, IVertex<E, T> vtx_to_add){
    // if already present do not add up
    for (int i = 0; i < list_of_vtx.length; i++)
      if (list_of_vtx.get(i) != null)
        if (vtx_to_add.compareTo(list_of_vtx.get(i)) == 0)
          return false;

    // set a the new Vertex and increment number of vertex
    list_of_vtx.set(this.numVertex, vtx_to_add);
    return true;
  }

  @Override
  // add a new arch
  public boolean addArch(IArch<E, K> arch){
    if (arch == null)
      return false;

    // create a pair with the verteces
    GraphPair<Integer> nodes = this.findVerteces(this.verteces, arch.getFirstVertex(), arch.getSecondVertex());

    // if the arch is not already present add the arch and the adiacence to the verteces
    return (this.containsArch(this.arches, nodes)) ? false : this.addArchAndAdiacences(this.arches, this.verteces, new Arch<Integer,K>(nodes.getFirstValue(), nodes.getSecondValue(), arch.getWeight()), arch.getWeight());
  }

  @Override
  // add a new arch
  public boolean addArch(E vertex1, E vertex2, K weight){
    if (vertex1 == null && vertex2 == null && weight == null)
      return false;

    // create a pair with the verteces
    GraphPair<Integer> nodes = this.findVerteces(this.verteces, vertex1, vertex2);

    // if the arch is not already present add the arch and the adiacence to the verteces
    return (this.containsArch(this.arches, nodes)) ? false : this.addArchAndAdiacences(this.arches, this.verteces, new Arch<Integer,K>(nodes.getFirstValue(), nodes.getSecondValue(), weight), weight);
  }

  // add the arch and the adiacence to the verteces
  protected <T extends Comparable<T>> boolean addArchAndAdiacences(IList<IArch<Integer, K>> list_of_arch, Array<IVertex<E, T>> list_of_vtx, IArch<Integer, K> arch, T weight){
    if (arch == null)
      return false;

    list_of_arch.addHead(arch);   // add the arch
    list_of_vtx.get(arch.getFirstVertex()).upsertAdiacence(arch.getSecondVertex(), weight);   // add the adiacence to the first vertex
    if (arch.getFirstVertex() != arch.getSecondVertex())    // if the verteces are equal don't add it twice
      list_of_vtx.get(arch.getSecondVertex()).upsertAdiacence(arch.getFirstVertex(), weight);
    this.numArch++;         // increment number of arches

    return true;
  }

  @Override
  // check if the arch is present
  public boolean containsArch(IArch<E, K> arch){
    Integer res = this.findArch(this.arches, this.verteces, arch);
    return (res != null ) ? ((res >= 0 ) ? true : false) : false;
  }

  // find the position of the arch if present
  protected Integer findArch(IList<IArch<Integer, K>> list_of_arch, Array<IVertex<E, K>> list_of_vtx,  IArch<E, K> arch){
    if (arch == null)
      return null;

    // create a pair with the verteces
    GraphPair<Integer> nodes = this.findVerteces(list_of_vtx, arch.getFirstVertex(), arch.getSecondVertex());

    // find the pair of nodes
    return this.findArch(list_of_arch, nodes);
  }

  // check if the arch is present
  protected boolean containsArch(IList<IArch<Integer, K>> list_of_arch, GraphPair<Integer> nodes){
    Integer res = this.findArch(list_of_arch, nodes);
    return (res != null ) ? ((res >= 0 ) ? true : false) : false;
  }

  // find the position of the arch if present
  protected Integer findArch(IList<IArch<Integer, K>> list_of_arch, GraphPair<Integer> nodes){
    // verteces are not presents
    if (nodes == null)
      return -1;

    // get position of  the arch, if present return true.
    return list_of_arch.search(new Arch<Integer,K>(nodes.getFirstValue(), nodes.getSecondValue(), null));
  }

  // find the position of the arch if present
  protected <T extends Comparable<T>> IArch<T,K> searchArch(IList<IArch<T, K>> list_of_arch, GraphPair<T> nodes){
    // verteces are not presents
    if (nodes == null)
      return null;

    // get position of  the arch, if present return true.
    return list_of_arch.get(new Arch<T,K>(nodes.getFirstValue(), nodes.getSecondValue(), null));
  }

  // check if the verteces are already present in the Graph
  protected <T extends Comparable<T>> boolean containsVerteces(Array<IVertex<E, T>> list_of_vtx, E vertex1, E vertex2){ return (this.findVerteces(list_of_vtx, vertex1, vertex2) != null); }

  // find the position of the verteces in input
  protected <T extends Comparable<T>> GraphPair<Integer> findVerteces(Array<IVertex<E, T>> list_of_vtx, E vertex1, E vertex2){
    if (vertex1 == null || vertex2 == null)
      return null;

    Integer first = -1, second = -1;  // pos of the first and second vertex of the arch

    // search for the first and second position of the vertex
    for (int i = 0; i < list_of_vtx.length; i++){
      if (list_of_vtx.get(i) != null){
        // if the verteces are the same node and it is found
        if (vertex1.compareTo(list_of_vtx.get(i).getValue()) == 0 && vertex2.compareTo(list_of_vtx.get(i).getValue()) == 0){
          first = second = i;
          break;
        }
        // if the first node is found
        else if (vertex1.compareTo(list_of_vtx.get(i).getValue()) == 0)
          first = i;
        // if the second node is found
        else if (vertex2.compareTo(list_of_vtx.get(i).getValue()) == 0)
          second = i;
      }
    }

    // if one of the vertex is not present return false
    if (first == -1 || second == -1)
      return null;

    // return a new pair with the position of the verteces
    return new GraphPair<Integer>(first, second);
  }

  @Override
  // search a vertex, if present true else false
  public boolean containsVertex(E vertex){
    Integer res = this.findVertex(this.verteces, vertex);
    return (res != null ) ? ((res >= 0 ) ? true : false) : false;
  }

  // find a vertex
  protected <T extends Comparable<T>> Integer findVertex(Array<IVertex<E, T>> list_of_vtx, E vertex){
    if (vertex == null)
      return null;

    // check each vertex
    for (int i = 0; i < list_of_vtx.length; i++)
      if (list_of_vtx.get(i) != null)
        if (vertex.compareTo(list_of_vtx.get(i).getValue()) == 0)
          return i;

    return null;
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
        this.delArchOfVertex(this.verteces, this.arches, i);        // delete all the arches having this vertex

        // delete the verteces and decrease number of verteces
        this.verteces.set(i, null);
        this.numVertex--;

        return true;
      }
    }

    return false;
  }

  // delete all the arches having the (pos of the) vertex in input
  protected <T extends Comparable<T>> boolean delArchOfVertex(Array<IVertex<E, T>> list_of_vtx, IList<IArch<Integer, K>> list_of_arch, Integer vertex){
    // check if the input is valid
    if (vertex == null || numArch <= 0)
      return false;
    if (vertex < 0  || vertex >= list_of_vtx.length)
      return false;

    // check if the head of the list has the vertex and deleted it
    this.checkAndDelHeadOfArchesList(list_of_vtx, list_of_arch, vertex);

    // find the arches with the vertex and delete them
    this.checkAndDelArch(list_of_vtx, list_of_arch, vertex);

    return true;
  }

  // check if the head of the list has the vertex and deleted it
  protected <T extends Comparable<T>> void checkAndDelHeadOfArchesList(Array<IVertex<E, T>> list_of_vtx, IList<IArch<Integer, K>> list_of_arch, Integer vertex){
    IArch<Integer,K> arch = null;

    // check of the head. Cycle till the head of the list have the input vertex
    while ((arch = list_of_arch.getHead().getValue()) != null){
      // if vertex is not part of the head break
      if (vertex != arch.getFirstVertex() && vertex != arch.getSecondVertex())
        break;

      // delete arch and adiacence of the verteces
      list_of_arch.delHead();
      list_of_vtx.get(arch.getFirstVertex()).delAdiacence(arch.getSecondVertex());
      if (arch.getFirstVertex() != arch.getSecondVertex())    // if the verteces are equal don't add it twice
        list_of_vtx.get(arch.getSecondVertex()).delAdiacence(arch.getFirstVertex());

      this.numArch--;
    }
  }

  // find the arches with the vertex and delete them
  protected <T extends Comparable<T>> void checkAndDelArch(Array<IVertex<E, T>> list_of_vtx, IList<IArch<Integer, K>> list_of_arch, Integer vertex){
    IArch<Integer,K> arch = null;

    // iterate all the elements of the list
    for (INode2<IArch<Integer, K>> prev = (INode2<IArch<Integer, K>>)list_of_arch.getHead(), iter = (INode2<IArch<Integer, K>>)list_of_arch.getHead().getNext(); iter != null; iter = (Node2<IArch<Integer, K>>)iter.getNext()){
      arch = iter.getValue();

      // if vertex is part of the arch
      if (vertex.compareTo(arch.getFirstVertex()) == 0 || vertex.compareTo(arch.getSecondVertex()) == 0){
        // disconnect the node
        prev.setNext(iter.getNext());
        iter = prev;

        // delete the adiacence of the verteces
        list_of_vtx.get(arch.getFirstVertex()).delAdiacence(arch.getSecondVertex());
        if (arch.getFirstVertex() != arch.getSecondVertex())    // if the verteces are equal don't add it twice
          list_of_vtx.get(arch.getSecondVertex()).delAdiacence(arch.getFirstVertex());
        this.size--;
      }

      // if iter and prev are different got to the next node of prev
      if (iter != prev)
        prev = (INode2<IArch<Integer, K>>)prev.getNext();
    }
  }

  @Override
  // delete an arch
  public boolean delArch(IArch<E, K> arch){
    if (arch == null)
      return false;

    // create a pair with the verteces
    GraphPair<Integer> nodes = this.findVerteces(this.verteces, arch.getFirstVertex(), arch.getSecondVertex());

    // if the pair is found then deleted it finally delete the adiacence of its verteces
    return (nodes == null) ? false : this.delArchAndAdiacences(this.verteces, this.arches, new Arch<Integer,K>(nodes.getFirstValue(), nodes.getSecondValue(), arch.getWeight()));
  }

  // delete the arch and the adiacence in its verteces
  protected <T extends Comparable<T>> boolean delArchAndAdiacences(Array<IVertex<E, T>> list_of_vtx, IList<IArch<Integer, K>> list_of_arch, IArch<Integer, K> arch){
    // if the arch is found and delete than delete also the adiacence of its verteces
    if (list_of_arch.delEl(arch)){
      list_of_vtx.get(arch.getFirstVertex()).delAdiacence(arch.getSecondVertex());
      if (arch.getFirstVertex() != arch.getSecondVertex())    // if the verteces are equal don't add it twice
        list_of_vtx.get(arch.getSecondVertex()).delAdiacence(arch.getFirstVertex());
      this.numArch--;

      return true;
    }

    return false;
  }

  @Override
  public Array<E> BFS(E vertex){
    Integer pos_vertex = this.findVertex(this.verteces, vertex);                                      // pos of the selected vertex
    if (pos_vertex == null)
      return null;

    Array<E> output = new Array<E>(this.numVertex);
    Queue<Integer> Q = new Queue<Integer>(); // Queue
    Array<Boolean> bfsVerteces = new Array<Boolean>(50);

    // initizialization of the method
    BFSinitizialization(this.verteces, Q, bfsVerteces, output, pos_vertex);

    // the main part of the method
    return BFSmain(this.verteces, Q, bfsVerteces, output);
  }

  // initizialization of BFS
  protected <T extends Comparable<T>> void BFSinitizialization(Array<IVertex<E, T>> list_of_vtx, Queue<Integer> Q, Array<Boolean> bfsVerteces, Array<E> output, Integer pos){
    // initialize arch array
    for (int i = 0, count = 0; i < bfsVerteces.length; i++, count++)
      // if not present set to false
      if (list_of_vtx.get(i) != null)
        bfsVerteces.set(i, false);
      // else set to null
      else
        bfsVerteces.set(i, null);

    // initialize arch array
    for (int i = 0; i < output.length; i++)
      output.set(i, null);

    // insert the root vertex
    bfsVerteces.set(pos, true);
    Q.enqueue(pos);
  }

  protected <T extends Comparable<T>> Array<E> BFSmain(Array<IVertex<E, T>> list_of_vtx, Queue<Integer> Q, Array<Boolean> bfsVerteces, Array<E> output){
    Boolean temp = null;

    // till PQ is empty
    for(Integer pos_vertex = 0, count = 0; !Q.isEmpty(); count++){
      pos_vertex = Q.dequeue();
      output.set(count, list_of_vtx.get(pos_vertex).getValue());

      // for each adiacence of the vertex
      for (IKeyNode<Integer, T> iter = (IKeyNode<Integer, T>)list_of_vtx.get(pos_vertex).getAdiacence().getHead(); iter != null; iter = (IKeyNode<Integer, T>)iter.getNext()){
        // if the vertex hasn't been already checked
        if (!bfsVerteces.get(iter.getValue())){
          bfsVerteces.set(iter.getValue(), true);
          Q.enqueue(iter.getValue());
        }
      }
    }

    return output;
  }

  @Override
  // it return the Minimum Spinnig Tree of the graph using Primm's algorithm
  public Array<IArch<E, K>> MSTPrim(E vertex, boolean isMin){
    Integer pos_vertex = null;                                      // pos of the selected vertex
    if ((pos_vertex = this.findVertex(this.verteces, vertex)) == null)
      return null;

    Array<IArch<E,K>> A = new Array<IArch<E,K>>(this.numArch);        // output array
    IArch<E,K> arch = new Arch<E, K>(vertex, vertex, ((INode2<IArch<Integer, K>>)this.arches.getHead()).getValue().getWeight());                               // temporary arch
    PriorityQueue<Integer, K> PQ = new PriorityQueue<Integer, K>(); // PriorityQueue

    // adding a random weight. at the end it will be resetted back to null
    // random weight is needed because with null it will all crash.
    // the random weight won't affect the algorithm in any way
    this.MSTinitizialization(A, PQ, vertex, pos_vertex, arch.getWeight());
    A.set(0, new Arch<E,K>(arch));            // add the arch to the output arch
    arch.reset();
    // till PQ is empty;  reset the temporary arch
    for(Integer pos_arch = 0, last_arch = 1; !PQ.isEmpty(); arch.reset()){
      // find min and delete it
      pos_vertex = PQ.find();
      PQ.delete()
      arch.setSecondVertex(this.verteces.get(pos_vertex).getValue());        // set second arch vertex to the selected one

      // for each adiacence of the vertex
      for (IKeyNode<Integer, K> iter = (IKeyNode<Integer, K>)this.verteces.get(pos_vertex).getAdiacence().getHead(); iter != null; iter = (IKeyNode<Integer, K>)iter.getNext(), arch.setWeight(null)){
        // checks values
        if (this.MSTchecks(this.verteces, iter, pos_vertex)){
          pos_arch = this.MSTsetArch(this.verteces, A, PQ, iter, arch); // set arch's first vertex and weight and get the position of the arch in the ouput if present
          last_arch = this.MSTaction(A, PQ, iter, arch, pos_arch, last_arch, pos_vertex, isMin);  // do the main action of the algorithm and get the position of last_arch used
        }
      }
    }

    // setting the first vertex to null
    A.get(0).setWeight(null);

    return A;
  }

  // initizialization of mst
  protected <T extends Comparable<T>> void MSTinitizialization(Array<IArch<E,K>> A, PriorityQueue<Integer, T> PQ, E vertex, Integer pos, T weight){
    // initialize arch array
    for (int i = 0; i < A.length; i++)
      A.set(i, null);

    // insert the root vertex
    PQ.insert(pos, weight);
  }

  // set of the arch in mst
  protected <T extends Comparable<T>> Integer MSTsetArch(Array<IVertex<E, T>> list_of_vtx, Array<IArch<E,K>> A, PriorityQueue<Integer, T> PQ, IKeyNode<Integer, T> iter, IArch<E,K> arch){
    // set first vertex of the temporary arch
    arch.setFirstVertex(list_of_vtx.get(iter.getValue()).getValue());
    // if the arch is already present pass to the next vertex
    for (int i = 0; i < A.length; i++){
      // if the element at the index is null than no need to continue
      if (A.get(i) == null)
        return -1;

      // if the element of the vertex is already in the output array then get its weight and remember the position
      if (list_of_vtx.get(iter.getValue()).getValue().compareTo(A.get(i).getFirstVertex()) == 0){
        arch.setWeight(A.get(i).getWeight());
        return i;
      }
    }

    return -1;
  }

  protected Integer MSTaction(Array<IArch<E,K>> A, PriorityQueue<Integer, K> PQ, IKeyNode<Integer, K> iter, IArch<E,K> arch, Integer pos_arch, Integer last_arch, Integer pos_vertex, boolean isMin){
    // if there's no weight associated to the vertex
    if (arch.getWeight() == null){
      PQ.insert(iter.getValue(), iter.getKey());      // insert the vertex to the PriorityQueue
      arch.setWeight(iter.getKey());                  // set the weight of the arch
      A.set(last_arch, new Arch<E,K>(arch));       // add the arch to the output arch

      return last_arch+1;                                            // increment pos of the last arch in the output array
    }
    // else if weight of the adiacence minor than the weight of the arch AND the vertex of the adiacence is in the PriorityQueue
    else if (this.min_max_compare(iter.getKey(), A.get(pos_arch).getWeight(), isMin) && PQ.check(iter.getValue())){
      A.get(pos_arch).setWeight(iter.getKey());                                   // set weight with the adiacence
      A.get(pos_arch).setSecondVertex(this.verteces.get(pos_vertex).getValue());        // set the new second Vertex
      PQ.decreaseKey(iter.getValue(), iter.getKey());                             // decreaseKey by weight of the adiacence weight
    }

    return last_arch;
  }

  // compareTo but decided based on the boolean in input
  protected <T extends Comparable<T>> boolean min_max_compare(T obj, T obj2, boolean isMin){ return (isMin) ? (obj.compareTo(obj2) < 0) : (obj.compareTo(obj2) > 0); }

  // check of mst
  protected <T extends Comparable<T>> boolean MSTchecks(Array<IVertex<E, T>> list_of_vtx, IKeyNode<Integer, T> adiacence, Integer vertex){
    // check if value is null
    if (adiacence.getValue() == null || adiacence.getKey() == null)
      return false;

    // check if the vertex is null
    if (list_of_vtx.get(adiacence.getValue()) == null)
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
        // check to find comparison with a same-value pair
        if (comp1 == comp2)
          return Math.abs(comp1 + comp2) / 2;   // return 1 or 0

        // return 0 if -1+1
        return Math.abs(comp1 + comp2);
      }

      return 1;
    }
  }
}
