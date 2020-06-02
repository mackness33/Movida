package movida.mackseverini;



public class IntegerTest extends Integer implements movida.mackseverini.IType{
  @Override
  public <?> get(String name){
    if (name == "value")
      return new Integer(this.intValue());
  }

}
