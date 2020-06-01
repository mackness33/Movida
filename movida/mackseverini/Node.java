package movida.mackseverini;

public class Node<T extends Comparable<T>> implements movida.mackseverini.INode<T>{
  private int key;
  private T value;
  private Node<T> next;

  public Node(){
    this.key = 0;
    this.next = null;
    this.value = null;
  }

  public Node(int k, T v){
    this.key = k;
    this.value = v;
    this.next = null;
  }

  public Node(int k, T v, Node<T> n){
    this.key = k;
    this.value = v;
    this.next = n;
  }

  @Override
  public int getKey () { return this.key; }
  @Override
  public T getValue () { return this.value; }
  @Override
  public Node<T> getNext () { return this.next; }

  // NECESSERARY?
  @Override
  public void setKey (int k) { this.key = k; }
  @Override
  public void setValue (T v) { this.value = v; }

  public void setNext (Node<T> n) { this.next = n; }

  public void print(){
    System.out.println("Node: KEY => " + this.key + "  VALUE => " + this.value);

    if(this.next != null)
      this.next.print();
    }
}
