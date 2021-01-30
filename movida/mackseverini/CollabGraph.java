package movida.mackseverini;


import movida.commons.Movie;
import movida.commons.Person;
import movida.commons.Collaboration;
import movida.commons.MapImplementation;
import movida.commons.SortingAlgorithm;
import movida.mackseverini.Arch;
import movida.mackseverini.PriorityQueue;
import movida.mackseverini.Vertex;
import movida.mackseverini.IList;
import movida.mackseverini.List;
import movida.mackseverini.IKeyList;
import movida.mackseverini.KeyList;
import movida.mackseverini.ArrayList;


// Class used to virtually implements an array without its costraints
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

		int num_inserted = 0;
    for (int i = 0; i < cast.length - 1; i++){
      for (int j = i + 1; j < cast.length; j++){
        // TODO: add Arch
				if (this.addArch(cast[i], cast[j], movie))
					num_inserted++;
        // this.addArch(new CollabArch(cast[i], cast[j], movie));
      }
    }

		return (num_inserted != 0) ? true : false;
  }

	public Person [] getAdiacencesOf(Person actor){
		// IVertex<E, Double> input_vtx = null;
		IList<Integer> adiacences = null;
		for (int i = 0; i < this.verteces.length; i++){
			if (actor.compareTo(this.verteces.get(i).getValue()) == 0){
				adiacences = this.verteces.get(i).getAdiacence();
				break;
			}
		}

		Person [] output = new Person [adiacences.getSize()];
		int i = 0;
		for (INode2<Integer> iter = (INode2<Integer>)adiacences.getHead(); iter != null; iter = (INode2<Integer>)iter.getNext(), i++)
			output[i] = this.verteces.get(iter.getValue()).getValue();


		return output;
	}

	// TODO: check if the arch is already present
	//		True: Add the movie to the Arch
	//		False: Add the arch as it is
  public boolean addArch(Person actor1, Person actor2, Movie movie){
    if (actor1 == null || actor2 == null || movie == null)
      return false;

		// create a pair with the verteces
		GraphPair<Integer> nodes = this.findVerteces(this.verteces, actor1, actor2);
		System.out.println("node!! ");
		nodes.print();

		// System.out.println("What???? ");
		Integer pos = this.findArch(this.arches, nodes);
		if (pos != null){
			// System.out.println("Can't understand?? ");
			if (pos > -1){
				System.out.println("arch found: ");
				// CollabArch ar = (CollabArch)this.arches.getAt(pos);
				// ar.print();
				Double score = ((CollabArch<Integer>)this.arches.getAt(pos)).incWeight(movie);
				System.out.println("Adding: " + actor1.getName() + " <=> " + actor2.getName() + "\tvotes: " + score + "\tmovie: " + movie.getTitle());
				((CollabVertex)this.verteces.get(nodes.getFirstValue())).upsertAdiacence(nodes.getSecondValue(), score);
		    if (nodes.getFirstValue() != nodes.getSecondValue())    // if the verteces are equal don't add it twice
		      ((CollabVertex)this.verteces.get(nodes.getSecondValue())).upsertAdiacence(nodes.getFirstValue(), score);

				return true;
			}
		}
		System.out.println("imma in ");

		// System.out.println("Why ");
    return this.addArchAndAdiacences(this.arches, this.verteces, new CollabArch<Integer>(nodes.getFirstValue(), nodes.getSecondValue(), movie), Double.valueOf(movie.getVotes()));
  }

	public boolean delMovie(Movie movie){
    if (movie == null)
      return false;

		Person [] cast = movie.getCast();
		int num_deleted = 0;
    for (int i = 0; i < cast.length - 1; i++){
      for (int j = i + 1; j < cast.length; j++){
        // TODO: add Arch
				if (this.delArch(cast[i], cast[j], movie))
					num_deleted++;
        // this.addArch(new CollabArch(cast[i], cast[j], movie));
      }
    }

		return (num_deleted != 0) ? true : false;
  }

	// @Override
	public boolean delArch(Person actor1, Person actor2, Movie movie){
	// public boolean delArch(IArch<E, K> arch){
		if (actor1 == null || actor2 == null || movie == null)
      return false;

    // create a pair with the verteces
    GraphPair<Integer> nodes = this.findVerteces(this.verteces, actor1, actor2);
		System.out.println("DEL OF:");
		nodes.print();

		if (nodes == null)
			return false;

		// System.out.println("Can't understand?? ");
		Integer pos = this.findArch(this.arches, nodes);
		if (pos != null){
			if (pos > -1){
				System.out.println("arch found: ");
				// CollabArch ar = (CollabArch)this.arches.getAt(pos);
				// ar.print();
				Double score = ((CollabArch<Integer>)this.arches.getAt(pos)).decWeight(movie);
				if (score == -1){
					System.out.println("Deleting: " + actor1.getName() + " <=> " + actor2.getName() + "\tvotes: " + score + "\tmovie: " + movie.getTitle());
					this.delArchAndAdiacences(this.verteces, this.arches, new CollabArch<Integer>(nodes.getFirstValue(), nodes.getSecondValue(), movie));
				}
				else{
					System.out.println("Updating: " + actor1.getName() + " <=> " + actor2.getName() + "\tvotes: " + score + "\tmovie: " + movie.getTitle());
		      this.verteces.get(nodes.getFirstValue()).updAdiacence(nodes.getSecondValue(), score);
					if (nodes.getFirstValue() != nodes.getSecondValue())    // if the verteces are equal don't add it twice
			      this.verteces.get(nodes.getSecondValue()).updAdiacence(nodes.getFirstValue(), score);
				}

				return true;
			}
		}

		return false;
  }


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

	public boolean addVertex(Person vertex){
    if (vertex == null)
      return false;

		// System.out.println("Found??? ");
    if (!this.checkAndAddVertex(this.verteces, new CollabVertex(vertex)))
			return false;

    this.numVertex++;

    return true;
  }

  public Person [] visitStartingFrom(Person actor){
    Integer pos_actor = null;                                      // pos of the selected vertex
    if ((pos_actor = this.findVertex(this.verteces, actor)) == null)
      return null;

    Array<Person> output = new Array<Person>(this.numVertex);
    Queue<Integer> Q = new Queue<Integer>(); // Queue
    Array<Boolean> bfsActors = new Array<Boolean>(50);
    // Array<IArch<E,K>> A = new Array<IArch<E,K>>(this.numArch);        // output array
    // IArch<E,K> arch = new Arch<E, K>(vertex, vertex, ((INode2<IArch<Integer, K>>)this.arches.getHead()).getValue().getWeight());                               // temporary arch


    // adding a random weight. at the end it will be resetted back to null
    // random weight are needed because with null it will all crash.
    // the random weight won't affect the algorithm in any way
    BFSinitizialization(this.verteces, Q, bfsActors, output, pos_actor);

    output = BFSmain(this.verteces, Q, bfsActors, output);

		Person [] temp = new Person [output.length];
		for (int i = 0; i < output.length; i++)
			temp[i] = output.get(i);

		return temp;
  }

	public Collaboration[] getCollaborationMST(Person actor){
		Array<IArch<Person, ArrayList<Movie>>> output = this.MSTPrim(actor);

		if (output == null)
			return null;

		Collaboration [] temp = new Collaboration [output.length];
		CollabArch<Person> iter = null;
		for (int i = 0; i < output.length; i++){
			iter = (CollabArch<Person>)output.get(i);
			if (iter != null){
				// iter.print();
				temp[i] = new Collaboration(iter.getFirstVertex(), iter.getSecondVertex(), iter.getWeight());
			}
		}

		return temp;
	}

	// TODO: need to add weight to arches
  // TODO: do compareTo for object without operator(-)
  // it return the Minimum Spinnig Tree of the graph using Primm's algorithm
  @Override
  public Array<IArch<Person, ArrayList<Movie>>> MSTPrim(Person vertex){
    Integer pos_vertex = null;                                      // pos of the selected vertex
    if ((pos_vertex = this.findVertex(this.verteces, vertex)) == null)
      return null;

		// System.out.println("pos_vertex at first: " + 	pos_vertex);
    Array<IArch<Person,ArrayList<Movie>>> A = new Array<IArch<Person,ArrayList<Movie>>>(this.numArch);        // output array
    IArch<Person,ArrayList<Movie>> arch = new CollabArch<Person>(vertex, vertex, ((INode2<IArch<Integer, ArrayList<Movie>>>)this.arches.getHead()).getValue().getWeight());                               // temporary arch
    PriorityQueue<Integer, Double> PQ = new PriorityQueue<Integer, Double>(); // PriorityQueue

    // adding a random weight. at the end it will be resetted back to null
    // random weight is needed because with null it will all crash.
    // the random weight won't affect the algorithm in any way
    this.MSTinitizialization(A, PQ, vertex, pos_vertex, ((CollabArch)arch).getScore());
		A.set(0, new CollabArch<Person>(arch));            // add the arch to the output arch
    arch.reset();
    // A = MSTmain(A, PQ, arch, vertex, pos_vertex);
    // till PQ is empty;  reset the temporary arch
    for(Integer pos_arch = 0, last_arch = 1; !PQ.isEmpty(); arch.reset()){
			// System.out.println("Yes: " + last_arch);
      // find min and delete it
      pos_vertex = PQ.findMin();
      PQ.delMin();
      PQ.print();
      arch.setSecondVertex(this.verteces.get(pos_vertex).getValue());        // set second arch vertex to the selected one

      // for each adiacence of the vertex
      for (IKeyNode<Integer, Double> iter = (IKeyNode<Integer, Double>)this.verteces.get(pos_vertex).getAdiacence().getHead(); iter != null; iter = (IKeyNode<Integer, Double>)iter.getNext(), arch.setWeight(null)){
				// System.out.println("Iter:\tValue: " + iter.getValue() + "\tKey: " + iter.getKey());
        // checks values
        if (this.MSTchecks(this.verteces, iter, pos_vertex)){
					// System.out.println("checks are ok");
          pos_arch = this.MSTsetArch(this.verteces, A, PQ, iter, arch);
          last_arch = this.MSTaction(A, PQ, iter, (CollabArch<Person>)arch, pos_arch, last_arch, pos_vertex);
        }
      }
    }

    // setting the first vertex to null
    A.get(0).setWeight(null);
		// for (int i = 0; i < A.length; i++)
		// 	if(A.get(i) != null)
		// 		A.get(i).print();
		// 	else
		// 		System.out.println("ARCH: null");
    return A;
  }

	// @Override
	protected Integer MSTaction(Array<IArch<Person,ArrayList<Movie>>> A, PriorityQueue<Integer, Double> PQ, IKeyNode<Integer, Double> iter, CollabArch<Person> arch, Integer pos_arch, Integer last_arch, Integer pos_vertex){
    // if there's no weight associated to the vertex
    if (arch.getWeight() == null){
      // System.out.println("last_arch: " + last_arch);
			// PQ.insert(iter.getValue(), iter.getKey());      // insert the vertex to the PriorityQueue
			PQ.insert(iter.getValue(), iter.getKey());      // insert the vertex to the PriorityQueue
      arch.setWeight(((CollabArch<Integer>)this.searchArch(this.arches, new GraphPair<Integer>(iter.getValue(), pos_vertex))).getWeight());                  // set the weight of the arch
      A.set(last_arch, new CollabArch<Person>(arch));       // add the arch to the output arch
      return last_arch+1;                                            // increment pos of the last arch in the output array
    }
    // else if weight of the adiacence minor than the weight of the arch AND the vertex of the adiacence is in the PriorityQueue
    else if (iter.getKey().compareTo(((CollabArch<Person>)A.get(pos_arch)).getScore()) < 0 && PQ.check(iter.getValue())){
      A.get(pos_arch).setWeight(((CollabArch<Integer>)this.searchArch(this.arches, new GraphPair<Integer>(iter.getValue(), pos_vertex))).getWeight());                                   // set weight with the adiacence
      A.get(pos_arch).setSecondVertex(this.verteces.get(pos_vertex).getValue());        // set the new second Vertex
      PQ.decreaseKey(iter.getValue(), iter.getKey());                             // decreaseKey by weight of the adiacence weight
    }

    return last_arch;
  }

  // TODO:
  public class CollabArch<E extends Comparable<E>> extends Arch<E, ArrayList<Movie>>{
    public CollabArch(E a1, E a2, Movie m){
      this.vertex1 = a1;
      this.vertex2 = a2;
      this.weight = new ArrayList<Movie>();
      this.weight.add(m);
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
			if (this.weight == null)
				return -1.0;

			if (this.weight.isEmpty())
				return -1.0;

      Double score = 0.0;

      for (Movie m : this.weight)
        score += m.getVotes();

      return score / this.weight.size();
    }

    public Double incWeight (Movie m) {
			System.out.println("Add of movie: " + m + " to:");
			this.print();
      if (this.weight == null)
        this.initWeight(m);
      else{
				int index = this.findMovie(m);

				if (index == -1)
					this.weight.add(m);
				else
					this.weight.set(index, m);
			}

			return this.getScore();
    }

		private int findMovie(Movie input){
			int index = -1, i = 0;
			for (Movie film : this.weight){
				if (film.compareTo(input) == 0)
					index = i;
				i++;
			}

			return index;
		}

    public Double decWeight (Movie m) {
      if (this.weight != null)
        this.weight.remove(m);

			return this.getScore();
    }

    public void initWeight(Movie m){
      this.weight = new ArrayList<Movie>();
      this.weight.add(m);
    }

    public int compareTo (CollabArch input){
			if (input == null)
				return -2;

			int i = 0;
			if ((i = super.compareTo(input)) != 0)
				return i;

			return (input.getScore() == this.getScore()) ? 0 : ((input.getScore() < this.getScore()) ? 1 : -1);
    }

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

	public class CollabVertex extends Vertex<Person, Double>{
		public CollabVertex(Person actor){
	    this.value = actor;
	    this.adiacence = new KeyList<Integer, Double, Integer>();
	  }

		// public void addAdiacence (Integer v, Movie movie) { this.addScore(v, movie.getVotes()); }
		//
		// public boolean addMovie (Integer v, Movie movie) { this.addScore(v, movie.getVotes());}

		public boolean delMovie (Integer v, Movie m) {
			if (m == null)
				return false;

			Double score = this.adiacence.searchKey(v);

			if (score == null)
				return false;
			// WARNING: not really checked
			return this.adiacence.updElKey(v, Math.abs(((score == null) ? 0 : score) - m.getVotes()));
		}

  }
}
