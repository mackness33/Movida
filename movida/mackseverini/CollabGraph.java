package movida.mackseverini;

import movida.commons.Movie;
import movida.commons.Person;
import movida.commons.Collaboration;

import movida.mackseverini.List;
import movida.mackseverini.Vertex;
import movida.mackseverini.KeyList;
import movida.mackseverini.ArrayList;
import movida.mackseverini.PriorityQueue;


// This class implements a Graph specifically used for the collaboration of the project
public class CollabGraph extends movida.mackseverini.Graph<Person, ArrayList<Movie>>{
	protected Array<IVertex<Person, Double>> verteces;                      // Array of verteces

  // constructor
	public CollabGraph(){
    this.verteces = new Array<IVertex<Person, Double>>(50);
    this.arches = new List<IArch<Integer, ArrayList<Movie>>>();
    this.numVertex = 0;
		this.numArch = 0;
    this.size = 0;

    for (int i = 0; i < this.verteces.length; i++)
      this.verteces.set(i, null);
	}

	// add of a movie to the Graph.
	// a movie can be seen as an arch between two actors (verteces)
  public boolean addMovie(Movie movie){
    if (movie == null)
      return false;

		// add the actors as verteces
    Person [] cast = movie.getCast();
    for (int i = 0; i < cast.length; i++)
      this.addVertex(cast[i]);

		// add the arch for each pair of actors that worked on the movie
		int num_inserted = 0;
    for (int i = 0; i < cast.length - 1; i++)
      for (int j = i + 1; j < cast.length; j++)
				if (this.addArch(cast[i], cast[j], movie))
					num_inserted++;

		// if num_inserted == 0, no arch has been added
		return (num_inserted != 0);
  }

	// get the adiacences of a specific actor in input
	public Person [] getAdiacencesOf(Person actor){

		IList<Integer> adiacences = null;

		// search the actor in the array of verteces and get its adiacences
		for (int i = 0; i < this.verteces.length; i++){
			if (actor.compareTo(this.verteces.get(i).getValue()) == 0){
				adiacences = this.verteces.get(i).getAdiacence();
				break;
			}
		}

		Person [] output = new Person [adiacences.getSize()];
		int i = 0;
		// trasform the List into an array
		for (INode2<Integer> iter = (INode2<Integer>)adiacences.getHead(); iter != null; iter = (INode2<Integer>)iter.getNext(), i++)
			output[i] = this.verteces.get(iter.getValue()).getValue();

		return output;
	}

	// True: Add the movie to the Arch
	// False: Add the arch as it is
  public boolean addArch(Person actor1, Person actor2, Movie movie){
    if (actor1 == null || actor2 == null || movie == null)
      return false;

		// create a pair with the verteces
		GraphPair<Integer> nodes = this.findVerteces(this.verteces, actor1, actor2);

		// get the position of the arch with the pair of nodes
		Integer pos = this.findArch(this.arches, nodes);

		// if the arch is found add the movie
		if (pos != null){
			if (pos > -1){
				Double score = ((CollabArch<Integer>)this.arches.getAt(pos)).incWeight(movie);			// increase and get the score of the arch
				// upsert the adiacence of the verteces
				this.verteces.get(nodes.getFirstValue()).upsertAdiacence(nodes.getSecondValue(), score);
		    if (nodes.getFirstValue() != nodes.getSecondValue())    // if the verteces are equal don't add it twice
		      this.verteces.get(nodes.getSecondValue()).upsertAdiacence(nodes.getFirstValue(), score);

				return true;
			}
		}

		// else add a new arch
    return this.addArchAndAdiacences(this.arches, this.verteces, new CollabArch<Integer>(nodes.getFirstValue(), nodes.getSecondValue(), movie), Double.valueOf(movie.getVotes()));
  }

	// delete of the input movie
	public boolean delMovie(Movie movie){
    if (movie == null)
      return false;

		Person [] cast = movie.getCast();
		int num_deleted = 0;
		// delete the movie for each pair of actors
    for (int i = 0; i < cast.length - 1; i++)
      for (int j = i + 1; j < cast.length; j++)
				if (this.delArch(cast[i], cast[j], movie))
					num_deleted++;

		// if num_deleted == 0 no arch has been deleted
		return (num_deleted != 0);
  }

	// delete an arch
	public boolean delArch(Person actor1, Person actor2, Movie movie){
		if (actor1 == null || actor2 == null || movie == null)
      return false;

    // create a pair with the verteces
    GraphPair<Integer> nodes = this.findVerteces(this.verteces, actor1, actor2);

		// if (nodes == null)
		// 	return false;

		// get the position of the arch with the pair of nodes
		Integer pos = this.findArch(this.arches, nodes);

		// if the arch is found add the movie
		if (pos != null){
			if (pos > -1){
				Double score = ((CollabArch<Integer>)this.arches.getAt(pos)).decWeight(movie);

				// if there's no movies in the arch delete the verteces too
				if (score == -1)
					this.delArchAndAdiacences(this.verteces, this.arches, new CollabArch<Integer>(nodes.getFirstValue(), nodes.getSecondValue(), movie));
				// else update the score in the verteces
				else{
		      this.verteces.get(nodes.getFirstValue()).updAdiacence(nodes.getSecondValue(), score);
					if (nodes.getFirstValue() != nodes.getSecondValue())    // if the verteces are equal don't add it twice
			      this.verteces.get(nodes.getSecondValue()).updAdiacence(nodes.getFirstValue(), score);
				}

				return true;
			}
		}

		return false;
  }

	// CLEAN
	public void print(){
    for(int i = 0, size = 0; i < this.verteces.length; i++){
			if (this.verteces.get(i) != null){
				System.out.println("POS: " + size);
				this.verteces.get(i).print();
				size += 1;
			}
		}

    System.out.println("\n\rArch length: " + this.arches.getSize());

    for (INode2<IArch<Integer, ArrayList<Movie>>> iter = (INode2<IArch<Integer, ArrayList<Movie>>>)this.arches.getHead(); iter != null; iter = (Node2<IArch<Integer, ArrayList<Movie>>>)iter.getNext())
      if (iter.getValue() != null)
        iter.getValue().print();
  }

	// add a new actor
	public boolean addVertex(Person vertex){ return this.addGenericVertex(this.verteces, vertex); }

	// visit the Graph from the actor (vertex) in input
  public Person [] visitStartingFrom(Person actor){
    Integer pos_actor = this.findVertex(this.verteces, actor);       // get the position of the selected vertex
    if (pos_actor == null)
      return null;

    Array<Person> output = new Array<Person>(this.numVertex);
    Queue<Integer> Q = new Queue<Integer>();
    Array<Boolean> bfsActors = new Array<Boolean>(50);

		// initizialization of the method
    BFSinitizialization(this.verteces, Q, bfsActors, output, pos_actor);

		// the main part of the method
    output = BFSmain(this.verteces, Q, bfsActors, output);

		// trasform the Array into a primitive version array[]
		Person [] temp = new Person [output.length];
		for (int i = 0; i < output.length; i++)
			temp[i] = output.get(i);

		return temp;
  }

	// get the maximum spinnig tree of the CollabGraph
	public Collaboration[] getCollaborationMaxST(Person actor){
		Array<IArch<Person, ArrayList<Movie>>> output = this.MSTPrim(actor, false);		// do the MSTPrim

		if (output == null)
			return null;

		Collaboration [] temp = new Collaboration [output.length];
		CollabArch<Person> iter = null;
		// trasform the array of arches into an array of Collaboration
		for (int i = 1; i < output.length; i++)
			if ((iter = (CollabArch<Person>)output.get(i)) != null)
				temp[i] = new Collaboration(iter.getFirstVertex(), iter.getSecondVertex(), iter.getWeight());

		return temp;
	}

  @Override
	// it return the Minimum Spinnig Tree of the graph using Primm's algorithm
  public Array<IArch<Person, ArrayList<Movie>>> MSTPrim(Person vertex, boolean isMin){
    Integer pos_vertex = this.findVertex(this.verteces, vertex);       // get the position of the selected vertex
    if (pos_vertex == null)
      return null;

    Array<IArch<Person,ArrayList<Movie>>> A = new Array<IArch<Person,ArrayList<Movie>>>(this.numArch);        // output array
    IArch<Person,ArrayList<Movie>> arch = new CollabArch<Person>(vertex, vertex, ((INode2<IArch<Integer, ArrayList<Movie>>>)this.arches.getHead()).getValue().getWeight()); // temporary arch
    PriorityQueue<Integer, Double> PQ = new PriorityQueue<Integer, Double>(false); // PriorityQueue

    // adding a random weight. at the end it will be resetted back to null
    // random weight is needed because with null it will all crash.
    // the random weight won't affect the algorithm in any way
    this.MSTinitizialization(A, PQ, vertex, pos_vertex, ((CollabArch)arch).getScore());
		A.set(0, new CollabArch<Person>(arch));            // add the arch to the output arch
    arch.reset();					// reset the temporary arch

    // till PQ is empty;  reset the temporary arch
    for(Integer pos_arch = 0, last_arch = 1; !PQ.isEmpty(); arch.reset()){
      // find min and delete it
      pos_vertex = PQ.find();
      PQ.delete();
      arch.setSecondVertex(this.verteces.get(pos_vertex).getValue());        // set second arch's vertex to the selected one

      // for each adiacence of the vertex
      for (IKeyNode<Integer, Double> iter = (IKeyNode<Integer, Double>)this.verteces.get(pos_vertex).getAdiacence().getHead(); iter != null; iter = (IKeyNode<Integer, Double>)iter.getNext(), arch.setWeight(null)){
        // checks values
        if (this.MSTchecks(this.verteces, iter, pos_vertex)){
          pos_arch = this.MSTsetArch(this.verteces, A, PQ, iter, arch); 	// set arch's first vertex and weight and get the position of the arch in the ouput if present
          last_arch = this.MSTaction(A, PQ, iter, (CollabArch<Person>)arch, pos_arch, last_arch, pos_vertex, isMin);	// do the main action of the algorithm and get the position of last_arch used
        }
      }
    }

    // setting the first vertex back to null
    A.get(0).setWeight(null);

    return A;
  }

	protected Integer MSTaction(Array<IArch<Person,ArrayList<Movie>>> A, PriorityQueue<Integer, Double> PQ, IKeyNode<Integer, Double> iter, CollabArch<Person> arch, Integer pos_arch, Integer last_arch, Integer pos_vertex, boolean isMin){
    // if there's no weight associated to the vertex
    if (arch.getWeight() == null){
			PQ.insert(iter.getValue(), iter.getKey());      // insert the vertex to the PriorityQueue
      arch.setWeight(((CollabArch<Integer>)this.searchArch(this.arches, new GraphPair<Integer>(iter.getValue(), pos_vertex))).getWeight());		// set the weight of the arch
      A.set(last_arch, new CollabArch<Person>(arch));       // add the arch to the output arch

      return last_arch+1;                                            // increment pos of the last arch in the output array
    }
    // else if weight of the adiacence minor than the weight of the arch AND the vertex of the adiacence is in the PriorityQueue
		else if (this.min_max_compare(iter.getKey(), ((CollabArch<Person>)A.get(pos_arch)).getScore(), isMin) && PQ.check(iter.getValue())){
      A.get(pos_arch).setWeight(((CollabArch<Integer>)this.searchArch(this.arches, new GraphPair<Integer>(iter.getValue(), pos_vertex))).getWeight());	// set weight with the adiacence
      A.get(pos_arch).setSecondVertex(this.verteces.get(pos_vertex).getValue());        // set the new second Vertex
      PQ.decreaseKey(iter.getValue(), iter.getKey());                             // decreaseKey by weight of the adiacence weight
    }

    return last_arch;
  }


	// special class to manage the Collaboration arches in the MSTPrim
	// slightly different arch
  public class CollabArch<E extends Comparable<E>> extends movida.mackseverini.Arch<E, ArrayList<Movie>>{
    public CollabArch(E a1, E a2, Movie m){
      this.vertex1 = a1;
      this.vertex2 = a2;
			this.initWeight(m);
    }

		public CollabArch(E a1, E a2, ArrayList<Movie> M){
      this.vertex1 = a1;
      this.vertex2 = a2;
      this.weight = M;
    }

    public CollabArch(E a1, E a2){
      this.vertex1 = a1;
      this.vertex2 = a2;
      this.weight = new ArrayList<Movie>();
    }

		public CollabArch(IArch<E, ArrayList<Movie>> shallow){
      this.vertex1 = shallow.getFirstVertex();
      this.vertex2 = shallow.getSecondVertex();
      this.weight = shallow.getWeight();
    }

		// get the score method of the Collaboration
    public Double getScore(){
			if (this.weight == null)
				return -1.0;

			if (this.weight.isEmpty())
				return -1.0;

      Double score = 0.0;

      for (Movie m : this.weight)
        score += m.getVotes();

      return score / this.weight.size();
    }

		// increse of the weight (in this case add of a movie)
    public Double incWeight (Movie m) {
			// if the weight of the arch is null, inizialize it and add the movie
      if (this.weight == null)
        this.initWeight(m);
			// else upsert the movie
      else{
				int index = this.findMovie(m);		// get the position of the movie

				// if not found add the movie
				if (index == -1)
					this.weight.add(m);
				// else update the movie
				else
					this.weight.set(index, m);
			}

			// return the new score
			return this.getScore();
    }

		// search the movie in the ArrayList of Movie
		private int findMovie(Movie input){
			int index = -1, i = 0;
			for (Movie film : this.weight){
				if (film.compareTo(input) == 0)
					index = i;
				i++;
			}

			return index;
		}

		// decrease of the weight (in this case delete of a movie)
    public Double decWeight (Movie m) {
			// if the weight isn't null remove the movie from it
      if (this.weight != null)
        this.weight.remove(m);

			// return of the new score
			return this.getScore();
    }

		// init of the weight and add of a movie
    public void initWeight(Movie m){
      this.weight = new ArrayList<Movie>();
      this.weight.add(m);
    }

		// compare of two CollabArch
    public int compareTo (CollabArch input){
			if (input == null)
				return -2;

			int i = 0;
			// compare the verteces of the input arch
			if ((i = super.compareTo(input)) != 0)
				return i;

			// compare of the score of the input arch
			return (input.getScore() == this.getScore()) ? 0 : ((input.getScore() < this.getScore()) ? 1 : -1);
    }

		// CLEAN
		@Override
	  public void print(){
			System.out.print("Arch: WEIGHT => ");
			if (this.weight != null)
				this.weight.print();
			else
				System.out.println("NULL");
	    System.out.println("FIRST VERTEX => " + this.vertex1 + "  SECOND VERTEX => " + this.vertex2 + "\n\r");
	  }
  }
}
