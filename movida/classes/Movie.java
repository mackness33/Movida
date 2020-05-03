/*
 * Copyright (C) 2020 - Angelo Di Iorio
 *
 * Progetto Movida.
 * Corso di Algoritmi e Strutture Dati
 * Laurea in Informatica, UniBO, a.a. 2019/2020
 *
*/
package movida.classes;

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
public class Movie {

	private String title;
	private Integer year;
	private Integer votes;
	private Person[] cast;
	private Person director;

	public Movie(String title, Integer year, Integer votes,
			Person[] cast, Person director) {
		this.title = title;
		this.year = year;
		this.votes = votes;
		this.cast = cast;
		this.director = director;
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
		String s = "Title => " + this.title + "\n\rYear => " + this.year + "\n\rVotes => " + this.votes + "\n\rDirector => " + this.director.getName() + "\n\rCast => ";

		System.out.println("Cast: " + this.cast.length);
		for (Person p : this.cast){
			if (p == null)
				break;

			s += p.getName() + ", ";
		}

		return s;
	}
}
