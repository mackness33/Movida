//CLEAN:
package movida.mackseverini;

import java.lang.Integer;

public class IntegerTest implements Comparable<IntegerTest>, movida.mackseverini.IType{
  public Integer value;

  public IntegerTest(Integer i){ value = new Integer(i); }
  public IntegerTest(int i){ value = new Integer(i); }

  @Override
  public int compareTo (IntegerTest input) {
    return this.value.compareTo(input.get("value"));
  }

  @Override
  public <Integer> Integer get(String name){
    if (name == "value")
      return this.value;
  }

}
