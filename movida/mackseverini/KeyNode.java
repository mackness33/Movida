package movida.mackseverini;

public class KeyNode<E extends Comparable<E>> extends movida.mackseverini.Node2<E> implements movida.mackseverini.IKeyNode<E>{
  protected Integer key;

  public KeyNode(){
    super();
    this.key = -1;
  }

  public KeyNode(int k, E v){
    super(v);
    this.key = k;
  }

  public KeyNode(int k, E v, KeyNode<E> n){
    super(v, n);
    this.key = k;
  }

  @Override
  public Integer getKey() { return this.key; }

  @Override
  public void setKey (Integer k) { this.key = k; }

  @Override
  public void print(){
    // if (this.value != null)
    System.out.println("KeyNode: KEY => " + this.key + " VALUE => " + this.value);
  }

  public void printAll(){
    System.out.println("KeyList: KEY => " + this.key + " VALUE => " + this.value);

    if (this.value instanceof IList)
      ((KeyList<E>)this.value).printAll();

    if (this.next != null)
      ((KeyNode<E>)this.next).printAll();
  }

  // @Override
  // public String toString(){
  //   return "KeyNode: KEY => " + this.key + " VALUE => " + this.value;
  // }
}
