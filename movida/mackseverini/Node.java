package movida.mackseverini;

public class Node<T extends Comparable<T>> implements movida.mackseverini.INode<T>{
  private int key;
  private T value;

  public Node(){
    this.key = 0;
    this.value = null;
  }

  public Node(int k, T v){
    this.value = v;
    this.key = k;
  }

  @Override
  public int getKey () { return this.key; }
  @Override
  public T getValue () { return this.value; }

  @Override
  public void setKey (int k) { this.key = k; }
  @Override
  public void setValue (T v) { this.value = v; }

  public void print(){
    try{
      System.out.println("Node: KEY => " + this.key + "  VALUE => ");
      this.value.print();
    }
    catch(Exception e){
      System.out.println("Node: KEY => " + this.key + "  VALUE => " + this.value);
    }
  }
}
