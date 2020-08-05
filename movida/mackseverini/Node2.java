package movida.mackseverini;

public class Node2<T extends Comparable<T>> implements movida.mackseverini.INode2<T>{
  protected T value;
  protected INode2<T> next;

  public Node2(){
    this.value = null;
    this.next = null;
  }

  public Node2(Node2<T> shallow){
    this.value = shallow.getValue();
    this.next = shallow.getNext();
  }

  public Node2(T v){
    this.value = v;
    this.next = null;
  }

  public Node2(T v, Node2<T> n){
    this.value = v;
    this.next = n;
  }


  @Override
  public T getValue () { return this.value; }
  @Override
  public INode2<T> getNext () { return this.next; }


  @Override
  public void setValue (T v) { this.value = v; }
  @Override
  public void setNext (INode2<T> n) { this.next = n; }
  public void setNext (Node2<T> n) { this.next = n; }

  // @Override
  // public int compareTo (Node2<T> input) {
  //   return ((Integer)input.hashCode()).compareTo(this.hashCode());
  // }

  public void print(){
    System.out.println("Node2: VALUE => " + this.value);
  }

  public void printAll(){
    this.print();

    if (this.next != null)
      ((Node2<T>)this.next).printAll();
  }
}
