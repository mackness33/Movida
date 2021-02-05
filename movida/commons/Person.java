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
import movida.mackseverini.INode2;

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

	public Person(String name) {
		this.name = name;
		this.type = false;
		this.movies = null;
	}

	public String getName(){ return this.name; }

	// check if the person is an actor or a director
	public boolean isActor(){ return this.type;	}

	// add a new index of a movie
	public void addMovie(Integer movie_id){ this.movies.addTail(movie_id); }

	// get all the indexes of the movies in which the person worked on
	public IList<Integer> getMovies(){ return this.movies; }

	// get the size of the list of indexes of movies
	public Integer getMovieSize(){ return this.movies.getSize(); }

	public String toString(){ return "Name : " + this.name; }

	// CLEAN
	public void print(){
		System.out.println("Name => " + this.name);
		System.out.print("Movies => ");
		for (INode2<Integer> iter = movies.getHead(); iter != null; iter = iter.getNext())
			System.out.print(iter.getValue() + " + ");
	}

	@Override
	public int compareTo(Person obj){	return this.name.compareTo(obj.getName()); }

}
