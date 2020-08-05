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

    if ((list_key = ((KeyList<IList<K>>)list).getByKey(hash_key)) == null){
      ((KeyList<IList<K>>)list).addTail(hash_key, new KeyList(hash_key));
      list_key = ((KeyList<IList<K>>)list).getTail().getValue();
    }

    ((KeyList<K>)list_key).addTail(this.size, key);
    return true;
  }

  protected <K extends Comparable<K>> boolean delHashKey(K key, IList<IList<K>> list, Integer dom_pos){
    Integer hash_key = this.hash(key);
    IList<K> node = null;

    if ((node = ((KeyList<IList<K>>)list).getByKey(hash_key)) != null){
      ((KeyList<K>)node).delByKey(dom_pos);

      if (node.getSize() <= 0)
        list.delEl(node);

      return true;
    }

    return false;
  }

  protected <K extends Comparable<K>> IList<E> searchByHashKey(K input, IList<IList<K>> key_hash){
    Integer key = this.hash(input);
    IList<K> node = null;
    IList<E> output = new KeyList<E>();


    if ((node = ((KeyList<IList<K>>)key_hash).getByKey(key)) != null)
      for (KeyNode<K> iter = (KeyNode<K>)node.getHead(); iter != null; iter = (KeyNode<K>)iter.getNext())
        if (input.compareTo(iter.getValue()) == 0)
            output.addTail(this.dom.get(iter.getKey()));

    return (output.getSize() <= 0) ? null : output;
  }

  protected <K extends Comparable<K>> IList<E> searchMostOfHashKey(Integer num, IList<K> key_hash){
    IList<E> output = new KeyList<E>();
    int i = 0;

    for (KeyNode<K> iter = (KeyNode<K>)key_hash.getHead(); iter != null && i < num; iter = (KeyNode<K>)iter.getNext(), i++)
      output.addTail(this.dom.get(iter.getKey()));

    return (output.getSize() <= 0) ? null : output;
  }

  protected IList<E> searchContainsHashKey(String input, IList<IList<String>> key_hash){
    IList<E> output = new KeyList<E>();
    int i = 0;

    for (INode2<IList<String>> iter = key_hash.getHead(); iter != null; iter = iter.getNext())
      for (KeyNode<String> iterNode = (KeyNode<String>)iter.getValue().getHead(); iterNode != null; iterNode = (KeyNode<String>)iterNode.getNext())
        if (iterNode.getValue().contains(input))
          output.addTail(this.dom.get(iterNode.getKey()));

    return (output.getSize() <= 0) ? null : output;
  }
}
