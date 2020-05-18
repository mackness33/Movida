package movida.mackseverini;

public class IntNode implements movida.mackseverini.INode<Integer>{
  private int key;
  private Integer value;

  public IntNode(){
    this.key = this.value = 0;
  }

  public IntNode(int k, int v){
    this.key = k;
    this.value = v;
  }

  @Override
  public int getKey () { return this.key; }
  @Override
  public Integer getValue () { return this.value; }

  // NECESSERARY?
  @Override
  public void setKey (int k) { this.key = k; }
  @Override
  public void setValue (Integer v) { this.value = v; }
}
