package movida.mackseverini;

import movida.mackseverini.IntHash;

public class IntHash implements movida.mackseverini.IHash<Integer> {
  private IntNode [] dom;

  // constructor resides
  public IntHash(Integer [] array) {}
  public IntHash() {
    this.dom = new IntNode [50];
    this.init_app();
  }

  public void init_app() {
    for (int i = 0; i < this.dom.length; i++)
      this.dom[i] = new IntNode(i, -1);
  }

  protected IntNode hash (int input){
    return new IntNode(Math.abs(input), input * 100);
  }

  @Override
  public boolean insert(Integer obj){
    IntNode node = hash(obj);
    // System.out.println("Node: KEY => " + node.getKey() + "  VALUE => " + node.getValue() );
    dom[node.getKey()] = node;

    return true;
  }

  @Override
  public boolean delete(Integer obj){
    return true;
  }

  @Override
  public Integer search(Integer obj){
    return -1;
  }

  @Override
  public boolean update(Integer obj){
    return true;
  }

  public void print (){
    System.out.println("Length: " + this.dom.length);
    for (int i = 0; i < this.dom.length; i++){
      // System.out.println("i: " + i);
      // System.out.println("Node: " + this.dom[i]);
      // System.out.println("KEY => " + this.dom[i].getKey());
      // System.out.println("VALUE => " + this.dom[i].getValue() );
      System.out.println("Node: KEY => " + this.dom[i].getKey() + "  VALUE => " + this.dom[i].getValue() );
    }
  }
}
