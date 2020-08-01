/*
 * Copyright (C) 2020 - Angelo Di Iorio
 *
 * Progetto Movida.
 * Corso di Algoritmi e Strutture Dati
 * Laurea in Informatica, UniBO, a.a. 2019/2020
 *
*/
package movida.commons;
import movida.mackseverini.IList;
import movida.mackseverini.List;

/**
 * Classe usata per rappresentare una persona, attore o regista,
 * nell'applicazione Movida.
 *
 * Una persona � identificata in modo univoco dal nome
 * case-insensitive, senza spazi iniziali e finali, senza spazi doppi.
 *
 * Semplificazione: <code>name</code> � usato per memorizzare il nome completo (nome e cognome)
 *
 * La classe pu� essere modicata o estesa ma deve implementare il metodo getName().
 *
 */
public class Person implements Comparable<Person>{

	private String name;
	private boolean type;
	private IList<Integer> movies;

	public Person(String name, boolean type, Integer movie) {
		this.name = name;
		this.type = type;
		this.movies = new List<Integer>(movie);
	}

	public Person(String name, boolean type) {
		this.name = name;
		this.type = type;
		this.movies = new List<Integer>();
	}

	public String getName(){ return this.name; }

	public boolean isActor(){ return this.type;	}

	public void addMovie(Integer movie_id){ this.movies.addTail(movie_id); }

	public IList<Integer> getMovies(){ return this.movies; }

	public Integer getMovieSize(){ return this.movies.getSize(); }

	public String toString(){ return "Name : " + this.name; }

	@Override
	public int compareTo(Person obj){	return obj.name.compareTo(this.name); }

}
