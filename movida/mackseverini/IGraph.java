package movida.mackseverini;

import java.util.Arrays;

import movida.mackseverini.IArch;
import movida.mackseverini.IList;
import movida.mackseverini.IKeyList;

// interface that describe the basic operation of a generic Graph
public interface IGraph<E extends Comparable<E>, K extends Comparable<K>>{
  public Array<IVertex<E, K>> getVerteces ();
  public Array<IArch<E, K>> getArches();

  public int numVerteces();
  public int numArches();

  public boolean addVertex(E vertex);

  public boolean addArch(IArch<E, K> arch);
  public boolean addArch(E vertex1, E vertex2, K weight);

  public boolean delVertex(E vertex);
  public boolean delArch(IArch<E, K> arch);

  public boolean containsArch(IArch<E, K> arch);
  public boolean containsVertex(E vertex);

  public Array<E> BFS(E vertex);      // Breadth First Search
  public Array<IArch<E, K>> MSTPrim(E vertex, boolean isMin); // Max(Min) Spinning Tree
}
