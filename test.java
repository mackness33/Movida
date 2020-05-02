import movida.classes.MovidaDB;
import java.io.File;

public class test {

  public static void main(String args[]){
    MovidaDB mb = new MovidaDB();

    mb.loadFromFile(new File("movida/assets/esempio-leggero.txt"));
  }
}
