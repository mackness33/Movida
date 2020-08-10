//CLEAN:

package movida.mackseverini;

public class Example implements movida.mackseverini.IIntMap{

  public Example() {
    System.out.println("Example created!");
  }

  @Override
  public boolean insert(Integer obj){
    return true;
  }

  @Override
  public boolean delete(Integer obj){
    return true;
  }

  @Override
  public Integer search(Integer obj){
    return 0;
  }

  @Override
  public boolean update(Integer obj){
    return true;
  }
}
