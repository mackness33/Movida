//CLEAN:
package movida.mackseverini;

public class Node<T extends Comparable<T>> implements movida.mackseverini.INode<T>, Comparable<Node<T>>{
  protected Integer key;
  protected T value;
  protected Node<T> next;

  public Node(){
    this.key = -1;
    this.value = null;
    this.next = null;
  }

  public Node(Node<T> shallow){
    this.key = shallow.getKey();
    this.value = shallow.getValue();
    this.next = shallow.getNext();
  }

  public Node(Integer k, T v){
    this.key = k;
    this.value = v;
    this.next = null;
  }

  public Node(Integer k, T v, Node<T> n){
    this.key = k;
    this.value = v;
    this.next = n;
  }

  @Override
  public Integer getKey () { return this.key; }
  @Override
  public T getValue () { return this.value; }
  @Override
  public Node<T> getNext () { return this.next; }

  @Override
  public void setKey (Integer k) { this.key = k; }
  @Override
  public void setValue (T v) { this.value = v; }
  @Override
  public void setNext (Node<T> n) { this.next = n; }

  @Override
  public int compareTo (Node<T> input) {
    int res = this.key.compareTo(input.getKey());
    return (res == 0) ? this.value.compareTo(input.getValue()) : res;
  }

  public void print(){
    System.out.println("Node: KEY => " + this.key + "  VALUE => " + this.value);
  }

  public void printAll(){
    this.print();

    if (this.next != null)
      this.next.printAll();
  }
}
