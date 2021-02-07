package movida.mackseverini;

// class that add a key field to a Node
public class KeyNode<E extends Comparable<E>, K extends Comparable<K>> extends movida.mackseverini.Node2<E> implements movida.mackseverini.IKeyNode<E, K>{
  protected K key;

  public KeyNode(){
    super();
    this.key = null;
  }

  public KeyNode(K k, E v){
    super(v);
    this.key = k;
  }

  public KeyNode(K k, E v, KeyNode<E, K> n){
    super(v, n);
    this.key = k;
  }

  @Override
  public K getKey() { return this.key; }

  @Override
  public void setKey (K k) { this.key = k; }

  // CLEAN
  @Override
  public void print(){ System.out.println("KeyNode: KEY => " + this.key + " VALUE => " + this.value); }

  // CLEAN
  public void printAll(){
    System.out.println("KeyList: KEY => " + this.key + " VALUE => " + this.value);

    if (this.value instanceof IList)
      ((KeyList)this.value).printAll();

    if (this.next != null)
      ((KeyNode)this.next).printAll();
  }
}
