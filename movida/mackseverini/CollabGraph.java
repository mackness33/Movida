package movida.mackseverini;


import movida.commons.Movie;
import movida.commons.Person;
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

	// protected boolean addArchAndAdiacences(IList<IArch<Integer, ArrayList<Movie>>> list_of_arch, Array<IVertex<Person, Double>> list_of_vtx, IArch<Integer, Array<Movie>> arch){
	// 	if (arch == null)
	// 		return false;
	//
	// 	// add the arch and add adiacences to the verteces
	// 	System.out.println("Before the add??? ");
	// 	list_of_arch.addHead(arch);
	// 	System.out.println("After the add??? ");
	// 	System.out.println("first: " + arch.getFirstVertex());
	// 	System.out.println("second: " + arch.getSecondVertex());
	// 	System.out.println("node one: " + list_of_vtx.get(arch.getFirstVertex()));
	// 	list_of_vtx.get(arch.getFirstVertex()).addAdiacence(arch.getSecondVertex(), arch.getWeight());
	// 	arch.print();
	// 	if (arch.getFirstVertex() != arch.getSecondVertex())    // if the verteces are equal don't add it twice
	// 		list_of_vtx.get(arch.getSecondVertex()).addAdiacence(arch.getFirstVertex(), arch.getWeight());
	// 	this.numArch++;         // increment number of arches
	// 	this.arches.print();
	//
	// 	return true;
	// }

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
				CollabArch ar = (CollabArch)this.arches.getAt(pos);
				ar.print(); 
				Double score = (ar).incWeight(movie);
				System.out.println("Adding: " + actor1.getName() + " <=> " + actor2.getName() + "\tvotes: " + score + "\tmovie: " + movie.getTitle());
				((CollabVertex)this.verteces.get(nodes.getFirstValue())).upsertAdiacence(nodes.getSecondValue(), score);
		    if (nodes.getFirstValue() != nodes.getSecondValue())    // if the verteces are equal don't add it twice
		      ((CollabVertex)this.verteces.get(nodes.getSecondValue())).upsertAdiacence(nodes.getFirstValue(), score);

				return true;
			}
		}
		System.out.println("imma in ");

		// System.out.println("Why ");
    return this.addArchAndAdiacences(this.arches, this.verteces, new CollabArch(nodes.getFirstValue(), nodes.getSecondValue(), movie), Double.valueOf(movie.getVotes()));
  }

	// protected void updArchAndAdiacences(IList<IArch<Integer, K>> list_of_arch, Array<IVertex<E, K>> list_of_vtx, Movie movie, GraphPair<Integer> nodes, Integer pos){
	// 	((CollabArch)list.getAt(pos)).incWeight(movie);
	// 	((CollabVertex)list_of_vtx.get(nodes.getFirstValue())).addMovie(nodes.getSecondValue(), movie);
	// 	if (nodes.getFirstValue() != nodes.getSecondValue())    // if the verteces are equal don't add it twice
	// 		((CollabVertex)list_of_vtx.get(nodes.getSecondValue())).addMovie(nodes.getFirstValue(), movie);
	// }

	// @Override
  // public boolean addVertex(Person vertex){
  //   if (vertex == null)
  //     return false;
	//
  //   // if already present do not add up
  //   for (int i = 0; i < this.verteces.length; i++)
  //     if (this.verteces.get(i) != null)
  //       if (vertex.compareTo(this.verteces.get(i).getValue()) == 0)
  //         return false;
	//
  //   // set a the new Vertex and increment number of vertex
  //   this.verteces.set(this.numVertex, new CollabVertex(vertex));
  //   this.numVertex++;
	//
  //   return true;
  // }
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

  // TODO:
  public class CollabArch extends Arch<Integer, ArrayList<Movie>>{
    public CollabArch(Integer a1, Integer a2, Movie m){
      this.vertex1 = a1;
      this.vertex2 = a2;
      this.weight = new ArrayList<Movie>();
      this.weight.add(m);
    }

    public CollabArch(Integer a1, Integer a2){
      this.vertex1 = a1;
      this.vertex2 = a2;
      this.weight = new ArrayList<Movie>();
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

      return score / this.weight.size();
    }

    public Double incWeight (Movie m) {
			System.out.println("Add of movie: " + m + " to:");
			this.print();
      if (this.weight == null)
        this.initWeight(m);
      else
        this.weight.add(m);

			return this.getScore();
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
			System.out.println("Arch: WEIGHT => ");
			this.weight.print();
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
