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

  // constructor
	public CollabGraph(){
    this.verteces = new Array<Vertex<Person, Double>>(50);
    this.arches = new List<IArch<Integer, K>>();
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
  public boolean addArch(Person actor1, Person actor2, Movie movie){
    if (actor1 == null || actor2 == null || movie == null)
      return false;

		// create a pair with the verteces
		GraphPair<Integer> nodes = this.findVerteces(actor1, actor2);

		Integer pos = this.findArch(nodes);
		if (res == null || res == -1){
			// arch.search(pos).addMovie(movie);
			// this.verteces.get(nodes.getFirstValue()).addMovie(movie);
	    // if (nodes.getFirstValue() != nodes.getSecondValue())    // if the verteces are equal don't add it twice
	    //   this.verteces.get(nodes.getSecondValue()).addMovie(movie));
		}

		// TODO: create a new CollabVertex
    return this.addArchAndAdiacences(new CollabArch(nodes.getFirstValue(), nodes.getSecondValue(), movie));
  }

  // TODO:
  public class CollabArch extends Arch<Integer, ArrayList<Movie>>{
    public CollabArch(Integer a1, Integer a2, Movie m){
      this.vertex1 = a1;
      this.vertex2 = a2;
      this.weight = ArrayList<Movie>();
      this.weight.add(m);
    }

    public CollabArch(Integer a1, Integer a2){
      this.vertex1 = a1;
      this.vertex2 = a2;
      this.weight = ArrayList<Movie>();
    }

    // @Override
    // public Person getActorA() {
    //   return this.vertex1;
    // }
		//
    // @Override
    // public Person getActorB() {
    //   return this.vertex2;
    // }
		//
    // @Override
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

	public class CollabVertex extends Vertex<Person, Double>{
		public boolean addMovie (Integer v, Movie m) {
			if (m == null)
				return false;

			Double score = this.adiacence.searchKey(v);

			if (score == null)
				return false;

			this.adiacence.updElKey(v, ((score == null) ? 0 : score) + m.getVotes());
		}

		public boolean delMovie (Integer v, Movie m) {
			if (m == null)
				return false;

			Double score = this.adiacence.searchKey(v);

			if (score == null)
				return false;
			// WARNING: not really checked
			this.adiacence.updElKey(v, Math.abs(((score == null) ? 0 : score) - m.getVotes()));
		}
  }
}
