/*
 * Copyright (C) 2020 - Angelo Di Iorio
 *
 * Progetto Movida.
 * Corso di Algoritmi e Strutture Dati
 * Laurea in Informatica, UniBO, a.a. 2019/2020
 *
*/
package movida.commons;

/**
 * Classe usata per rappresentare un film
 * nell'applicazione Movida.
 *
 * Un film � identificato in modo univoco dal titolo
 * case-insensitive, senza spazi iniziali e finali, senza spazi doppi.
 *
 * La classe pu� essere modicata o estesa ma deve implementare tutti i metodi getter
 * per recupare le informazioni caratterizzanti di un film.
 *
 */
public class Movie implements Comparable<Movie>{

	private String title;
	private Integer year;
	private Integer votes;
	private Person[] cast;
	private Person director;

	public Movie(String title, Integer year, Integer votes, Person[] cast, Person director) {
		this.title = title;
		this.year = year;
		this.votes = votes;
		this.cast = cast;
		this.director = director;
	}

	public Movie(Movie obj){
		this.title = obj.title;
		this.year = obj.year;
		this.votes = obj.votes;
		this.cast = obj.cast;
		this.director = obj.director;
	}

	public String getTitle() {
		return this.title;
	}

	public Integer getYear() {
		return this.year;
	}

	public Integer getVotes() {
		return this.votes;
	}

	public Person[] getCast() {
		return this.cast;
	}

	public Person getDirector() {
		return this.director;
	}

	public String toString(){
		String output = "Title: " + this.title + "\n\rYear: " + this.year + "\n\rDirector: " + this.director.getName() + "\n\rCast: ";

		for (Person p : this.cast){
			if (p == null)
				break;
			output += p.getName() + ", ";
		}

		output = output.substring(0, output.length() - 2);	// delete the last ", "

		output = output + "\n\rVotes: " + this.votes;

		return output;
	}

	public void update(Movie obj){
		this.title = obj.title;
		this.year = obj.year;
		this.votes = obj.votes;
		this.cast = obj.cast;
		this.director = obj.director;
	}

	@Override
	public int compareTo(Movie obj){ return obj.title.compareTo(this.title); }
}
