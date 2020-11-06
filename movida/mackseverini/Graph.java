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

    System.out.println("What?");
    for (int i = 0; i < this.verteces.length; i++){
      if (this.verteces.get(i) != null){
        if (arch.getFirstVertex().compareTo(this.verteces.get(i).getValue()) == 0 && arch.getSecondVertex().compareTo(this.verteces.get(i).getValue()) == 0){
          // System.out.println("The hell?");
          first = second = i;
          break;
        }
        else if (arch.getFirstVertex().compareTo(this.verteces.get(i).getValue()) == 0)
          first = i;
        else if (arch.getSecondVertex().compareTo(this.verteces.get(i).getValue()) == 0)
          second = i;
        // System.out.println("Damn?");
      }
    }

    if (first == -1 || second == -1){
      System.out.println();
      return false;
    }

    System.out.println("Search of " + arch.getFirstVertex() + "-" + arch.getSecondVertex());
    boolean search_res = this.searchArch(arch);
    System.out.println("Search res: " + search_res);

    if (search_res){
      System.out.println();
      return false;
    }

    GraphPair<Integer> e = new GraphPair<Integer>(first, second);

    // Integer res = this.arches.search(e);
    //
    // if (res != null){
    //   if(res == 0){
    //     this.arches.addHead(weight, e);
    //     this.verteces.get(first).addAdiacence(second, weight);
    //     this.verteces.get(second).addAdiacence(first, weight);
    //     this.numArch++;
    //
    //     return true;
    //   }
    // }
    //
    // return false;

    // System.out.println("RES: " + res);
    System.out.println("Holy shit");

    this.arches.addHead(arch.getWeight(), e);
    this.verteces.get(first).addAdiacence(second, arch.getWeight());
    if (first != second)
      this.verteces.get(second).addAdiacence(first, arch.getWeight());
    this.numArch++;

    System.out.println("Adding (" + arch.getFirstVertex() + "-" + arch.getSecondVertex() + ") \n\r");
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
    System.out.println("RES: " + res);

    if (res != null){
      if(res < 0){
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
    // System.out.println("RES: " + res);
    return (res != null ) ? ((res >= 0 ) ? true : false) : false;
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
      // else
        // System.out.println("Vertex: null");
    }
  }

  // TODO: need to add weight to arches
  // TODO: do compareTo for object without operator(-)
  public Array<Arch<E, Double>> MSTPrim(E vertex){
    if (vertex == null)
      return null;

    Array<Arch<E,Double>> A = new Array<Arch<E,Double>>(this.numArch);        // output array
    Arch<E,Double> arch = new Arch<E,Double>();                               // temporary arch
    Integer pos_vertex = null;                                                // pos of the selected vertex
    PriorityQueue<Integer, Double> PQ = new PriorityQueue<Integer, Double>(); // PriorityQueue

    //  if present get vertex pos
    for (int i = 0; i < this.verteces.length; i++)
      if (this.verteces.get(i) != null)
        if (this.verteces.get(i).compareTo(new Vertex(vertex)) == 0)
          pos_vertex = i;

    // if not present or the object is null return null
    if (pos_vertex == null)
      return null;
    if (this.verteces.get(pos_vertex) == null)
      return null;


    // initialize arch array
    for (int i = 0; i < A.length; i++)
      A.set(i, null);

    // insert the root vertex
    PQ.insert(pos_vertex, 0.0);
    A.set(0, new Arch<E, Double>(this.verteces.get(pos_vertex).getValue(), this.verteces.get(pos_vertex).getValue(), 0.0));

    System.out.println("check");
    System.out.println("pos vertex: " + pos_vertex);

    // temp = pos of the vertex;  till PQ is empty;  reset the temporary arch
    for(Integer temp = 0, pos_arch = 0, j = 1; !PQ.isEmpty(); arch.reset(), pos_vertex = temp){
      PQ.print();
      temp = PQ.findMin();
      System.out.println("min: " + temp);
      PQ.delMin();

      arch.setSecondVertex(this.verteces.get(temp).getValue());        // set first arch vertex to the selected one

      System.out.println("Adiacence: " + this.verteces.get(temp).getAdiacence().getSize());

      // for each adiacence of the vertex
      for (IKeyNode<Integer, Double> iter = (IKeyNode<Integer, Double>)this.verteces.get(temp).getAdiacence().getHead(); iter != null; iter = (IKeyNode<Integer, Double>)iter.getNext(), arch.setWeight(null)){
        // check if value is null
        if (iter.getValue() == null || iter.getKey() == null) break;

        // check if value is null
        if (this.verteces.get(iter.getValue()) == null) break;

        // set second vertex of the temporary arch
        arch.setFirstVertex(this.verteces.get(iter.getValue()).getValue());
        System.out.println("vert: " + this.verteces.get(iter.getValue()).getValue());

        boolean same = false;
        boolean next = false;

        if (arch.getFirstVertex().compareTo(arch.getSecondVertex()) == 0){
          System.out.println("Same node arch at " + j + ": " + iter.getValue());
          // arch.setWeight(iter.getKey());
          // A.set(j, new Arch<E, Double>(arch));;
          same = true;
        }
        // else if (iter.getValue() == pos_vertex){
        //   next = true;
        // }
        else{  // if the arch is already present pass to the next vertex
          for (int i = 0; i < A.length; i++){
            if (A.get(i) == null)
              break;
            System.out.println("Compare of verteces: " + this.verteces.get(iter.getValue()).getValue().compareTo(A.get(i).getFirstVertex()));
            if (this.verteces.get(iter.getValue()).getValue().compareTo(A.get(i).getFirstVertex()) == 0){
              arch.setWeight(A.get(i).getWeight());
              pos_arch = i;
              next = true;
              break;
            }
          }
        }

        // System.out.println("next: " + next);

        if (same){ }
        // else if (next){}
        else if (arch.getWeight() == null){
          // System.out.println("Checkin 2 " + PQ.check(iter.getValue()));
          // if (!PQ.check(iter.getValue())){
            System.out.println("Inserting at " + j + ": " + iter.getValue());
            PQ.insert(iter.getValue(), iter.getKey());
          // }
          // else{
          //   System.out.println("Iter Weight: " + iter.getKey());
          //   System.out.println("MainArch Weight: " + A.get(pos_arch).getWeight());
          //   PQ.decreaseKey(iter.getValue(), iter.getKey());
          // }
          arch.setWeight(iter.getKey());
          A.set(j, new Arch<E, Double>(arch));
          j++;
        }
        else if (iter.getKey().compareTo(A.get(pos_arch).getWeight()) < 0 && PQ.check(iter.getValue())){
          // BUG: deacreaseKey NOT WORKING PROPERLY
          // TODO: Second vertex of arch must changed
          System.out.println("Iter Weight: " + iter.getKey());
          System.out.println("MainArch Weight: " + A.get(pos_arch).getWeight());
          A.get(pos_arch).setWeight(iter.getKey());
          System.out.println("Decrease: " + PQ.decreaseKey(iter.getValue(), iter.getKey()));
          System.out.println("AFTER Weight: " + A.get(pos_arch).getWeight());
        }


        System.out.println("Arch end a Adiacence: ");
        for (int i = 0; i < A.length; i++)
          if (A.get(i) != null)
            A.get(i).print();

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
      int x1 = this.value1.compareTo(input.getFirstValue());
      int x2 = this.value1.compareTo(input.getSecondValue());
      int y1 = this.value2.compareTo(input.getFirstValue());
      int y2 = this.value2.compareTo(input.getSecondValue());
      int comp1 = x1 + x2, comp2 = y1 + y2;

      if (Math.abs(x1) + Math.abs(x2) < 2 && Math.abs(y1) + Math.abs(y2) < 2){
        System.out.print("input: ");
        input.print();
        System.out.print("GraphPair: ");
        this.print();
        System.out.println("RES in comp: " +  comp1 + comp2);
        if (comp1 == comp2)
          return (comp1 + comp2) / 2;
        return comp1 + comp2;
      }

      return 1;
    }

    //@Override
    public void print(){ System.out.println("GraphPair: VALUE => " + this.value1 + " VALUE2 => " + this.value2); }
  }
}
