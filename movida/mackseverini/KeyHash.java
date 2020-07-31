package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node2;
import movida.mackseverini.List;
import movida.mackseverini.Array;
import movida.mackseverini.Set;
import movida.commons.Movie;
import movida.commons.Person;

// BUG: Comparable cannot be used.
// SOLUTION: Comparable cannot be used.
public class KeyHash<E extends Comparable<E>> extends Hash2<E>{
  protected <K extends Comparable<K>> boolean addHashKey(K key, IList<IList<K>> list){
    Integer hash_key = this.hash(key);
    IList<K> list_key = null;

    if ((list_key = ((HashList<IList<K>>)list).getByKey(hash_key)) == null){
      ((HashList<IList<K>>)list).addTail(hash_key, new HashList(hash_key));
      list_key = ((HashList<IList<K>>)list).getTail().getValue();
    }

    ((HashList<K>)list_key).addTail(this.size, key);
    return true;
  }

  protected <K extends Comparable<K>> boolean delHashKey(K key, IList<IList<K>> list, Integer dom_pos){
    Integer hash_key = this.hash(key);
    IList<K> node = null;

    if ((node = ((HashList<IList<K>>)list).getByKey(hash_key)) != null){
      ((HashList<K>)node).delByKey(dom_pos);

      if (node.getSize() <= 0)
        list.delEl(node);

      return true;
    }

    return false;
  }

  protected <K extends Comparable<K>> IList<E> searchByHashKey(K input, IList<IList<K>> key_hash){
    Integer key = this.hash(input);
    IList<K> node = null;
    IList<E> output = new HashList<E>();


    if ((node = ((HashList<IList<K>>)key_hash).getByKey(key)) != null){
      // System.out.println("Node: " + node);
      for (HashNode<K> iter = (HashNode<K>)node.getHead(); iter != null; iter = (HashNode<K>)iter.getNext()){
        // System.out.println("Iter: " + iter);
        // System.out.println("Iter value: " + iter.getValue());
        // System.out.println("At the pos: " + this.dom.get(iter.getKey()));
        if (input.compareTo(iter.getValue()) == 0){
          // System.out.println("Key: " + iter.getKey());
          output.addTail(this.dom.get(iter.getKey()));
        }
      }
    }

    // System.out.println("Size: " + output.getSize());

    // if (output.getHead().getValue() == null)
    //   ((HashNode<E>)output.getHead()).printAll();
    // else
      // System.out.println("HEAD IS NULL: " + this.dom.get(1));

    return (output.getSize() <= 0) ? null : output;
  }

  protected <K extends Comparable<K>> IList<E> searchMostOfHashKey(Integer num, IList<K> key_hash){
    IList<E> output = new HashList<E>();
    int i = 0;

    for (HashNode<K> iter = (HashNode<K>)key_hash.getHead(); iter != null && i < num; iter = (HashNode<K>)iter.getNext(), i++){
      System.out.println("ITER: " + iter.getValue());
      output.addTail(this.dom.get(iter.getKey()));
    }

    System.out.println("OUT: " + output.getSize());

    return (output.getSize() <= 0) ? null : output;
  }

  protected IList<E> searchContainsHashKey(String input, IList<IList<String>> key_hash){
    IList<E> output = new HashList<E>();
    int i = 0;

    for (INode2<IList<String>> iter = key_hash.getHead(); iter != null; iter = iter.getNext())
      for (HashNode<String> iterNode = (HashNode<String>)iter.getValue().getHead(); iterNode != null; iterNode = (HashNode<String>)iterNode.getNext())
        if (iterNode.getValue().contains(input))
          output.addTail(this.dom.get(iterNode.getKey()));

    return (output.getSize() <= 0) ? null : output;
  }
}
