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
public class CollabGraph extends movida.mackseverini.Graph<Person, ArrayList<Movie>>{
	protected Array<Vertex<Person, Double>> verteces;                      // Array of verteces
  protected IKeyList<GraphPair<Integer>, ArrayList<Movie>, Integer> arches;   // List of Arches

  // constructor
	public CollabGraph(){
    this.verteces = new Array<Vertex<Person, ArrayList<Movie>>>(50);
    this.arches = new KeyList<GraphPair<Integer>, Array<Movie>, Integer>();
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

  protected void editArch (E v1, E v2, K w, Integer i){
    array.set(i, new CollabArch(v1, v2, w);
  }

  // TODO: Divied Movie in multiple CollabArches and give in input to addArch(..)
  // TODO: Add each actor
  // TODO: Add of CollabArch -> Prob: what if A and B already exist?
  //        => add the movie to the weight of the CollabArch

  public boolean addMovie(Movie movie){
    if (movie == null)
      return false;

    Person [] cast = movie.getCast();
    for (int i = 0; i < cast.length; i++)
      this.addVertex(cast[i]);

    for (int i = 0; i < cast.length - 1; i++){
      for (int j = i + 1; j < cast.length; j++){
        // TODO: add Arch
				this.addArch(cast[i], cast[j], movie);
        this.addArch(new CollabArch(cast[i], cast[j], movie));
      }
    }
  }

	@Override
	// TODO: check if the arch is already present
	//		True: Add the movie to the Arch
	//		False: Add the arch as it is
  public boolean addArch(IArch<E, K> arch){
    if (arch == null)
      return false;

		// create a pair with the verteces
		GraphPair<Integer> nodes = this.findVerteces(arch.getFirstVertex(), arch.getSecondVertex());

		if (this.containsArch(nodes))
			return false;

    return this.addArchAndAdiacences(nodes, arch.getWeight());
  }

	// find the position of the arch if present
  protected Integer findArch(IArch<E, K> arch){
    if (arch == null)
      return null;

    GraphPair<Integer> nodes = findVerteces(arch.getFirstVertex(), arch.getSecondVertex());

		Integer pos = this.findArch(nodes);

		(res != null ) ? ((res >= 0 ) ? true : false) : false;
  }

	protected boolean addArchAndAdiacences(GraphPair<Integer> nodes, Movie movie){
    if (nodes == null || movie == null)
      return false;

    // add the arch and add adiacences to the verteces
    this.arches.addHead(M, nodes);
    this.verteces.get(nodes.getFirstValue()).addAdiacence(nodes.getSecondValue(), weight);
    if (nodes.getFirstValue() != nodes.getSecondValue())    // if the verteces are equal don't add it twice
      this.verteces.get(nodes.getSecondValue()).addAdiacence(nodes.getFirstValue(), weight);
    this.numArch++;         // increment number of arches

    return true;
  }

  // TODO:
  public class CollabArch extends Arch<Person, ArrayList<Movie>>{
    public CollabArch(Person a1, Person a2, Movie m){
      this.vertex1 = a1;
      this.vertex2 = a2;
      this.weight = ArrayList<Movie>();
      this.weight.add(m);
    }

    public CollabArch(Person a1, Person a2){
      this.vertex1 = a1;
      this.vertex2 = a2;
      this.weight = ArrayList<Movie>();
    }

    @Override
    public Person getActorA() {
      return this.vertex1;
    }

    @Override
    public Person getActorB() {
      return this.vertex2;
    }

    @Override
    public Double getScore(){

      Double score = 0.0;

      for (Movie m : weight)
        score += m.getVotes();

      return score / movies.size();
    }

    public void incWeight (Movie m) {
      if (this.weight == null)
        this.initWeight(m);
      else
        this.weight.add(m);
    }

    public void decWeight (Movie m) {
      if (this.weight == null)
        this.initWeight(m);
      else
        this.weight.remove(m);
    }

    public void initWeight(Movie m){
      this.weight = new ArrayList<Movie>();
      this.weight.add(m);
    }

    @Override
    public int compareTo (CollabArch input){
			if (input == null)
				return -2;

      return (input.getScore() == this.getScore()) ? 0 : ((input.getScore() < this.getScore()) ? 1 : -1);
    }
  }
}
