package movida.mackseverini;

public class IntNode implements movida.mackseverini.INode<Integer>{
  private int key;
  private Integer value;
  private IntNode next;

  public IntNode(){
    this.key = this.value = 0;
    this.next = null;
  }

  public IntNode(int k, int v){
    this.key = k;
    this.value = v;
    this.next = null;
  }

  public IntNode(int k, int v, IntNode n){
    this.key = k;
    this.value = v;
    this.next = n;
  }

  @Override
  public int getKey () { return this.key; }
  @Override
  public Integer getValue () { return this.value; }
  @Override
  public IntNode getNext () { return this.next; }

  // NECESSERARY?
  @Override
  public void setKey (int k) { this.key = k; }
  @Override
  public void setValue (Integer v) { this.value = v; }
  
  public void setNext (IntNode n) { this.next = n; }

  public void print(){
    System.out.println("Node: KEY => " + this.key + "  VALUE => " + this.value);

    if(this.next != null)
      this.next.print();
  }
}
