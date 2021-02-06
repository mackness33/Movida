package movida.commons;

import java.util.ArrayList;

public class Collaboration {

	Person actorA;
	Person actorB;
	ArrayList<Movie> movies;

	public Collaboration(Person actorA, Person actorB, ArrayList<Movie> movies) {
		this.actorA = actorA;
		this.actorB = actorB;
		this.movies = movies;
	}

	public Person getActorA() {
		return actorA;
	}

	public Person getActorB() {
		return actorB;
	}

	public Double getScore(){
		if (this.movies == null)
			return -1.0;

		if (this.movies.isEmpty())
			return -1.0;

		Double score = 0.0;

		for (Movie m : movies)
			score += m.getVotes();

		return score / movies.size();
	}


	// CLEAN:
	public void print(){
		System.out.println("Collaboration:  ACTOR A => " + this.actorA + "  ACTOR B => " + this.actorB + "  SCORE => " + this.getScore());
	}

	// CLEAN:
	private void printMovies(){
    int count = 0;
    for (Movie i : this.movies){
      System.out.println("Pos: " + count + " Movie: " + i);
      count++;
    }
  }

}
