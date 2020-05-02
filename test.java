package Movida;

import classes.MovidaDB.MovidaDB;
import java.io.File;

public class test {

  public static void main(String args[]){
    MovidaDB mb = new MovidaDB();

    mb.loadFromFile(new File("assets/esempio-leggero.txt"));
  }
}
