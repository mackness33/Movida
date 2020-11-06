package movida.mackseverini;

import movida.mackseverini.Arch;
import movida.mackseverini.PriorityQueue;
import movida.mackseverini.Vertex;
import movida.mackseverini.Pair;
import movida.mackseverini.IList;
import movida.mackseverini.List;
import movida.mackseverini.IKeyList;
import movida.mackseverini.KeyList;

import java.util.Arrays;

// Class used to virtually implements an array without its costraints
public class IGraph<E extends Comparable<E>>{
  public Array<Vertex<E, Double>> getVerteces ();
  public Array<Arch<E, Double>> getArches();

  public int numVerteces();
  public int numArches();

  public boolean addVertex(E vertex);

  public boolean addArch(Arch<E, Double> arch);
  public boolean addArch(E vertex1, E vertex2, Double weight);

  public boolean delVertex(E vertex);
  protected void delArchOfVertex(Integer vertex);
  public boolean delArch(Arch<E, Double> arch);

  public boolean searchArch(Arch<E, Double> arch);
  public boolean searchVertex(E vertex);

  public void printVerteces();

  public Array<Arch<E, Double>> MSTPrim(E vertex);
}
