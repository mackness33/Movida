package movida.mackseverini;

import movida.mackseverini.Arch;
import movida.mackseverini.IArch;
import movida.mackseverini.PriorityQueue;
import movida.mackseverini.Vertex;
import movida.mackseverini.IList;
import movida.mackseverini.List;
import movida.mackseverini.IKeyList;
import movida.mackseverini.KeyList;

import java.util.Arrays;

// Class used to virtually implements an array without its costraints
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

  public void printVerteces();

  public Array<E> BFS(E vertex);
  public Array<IArch<E, K>> MSTPrim(E vertex);
}
