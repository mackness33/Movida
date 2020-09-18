package movida.mackseverini;

import java.util.concurrent.ThreadLocalRandom;

import movida.mackseverini.Node2;
import movida.mackseverini.List;
import movida.mackseverini.Array;


// Class that implements methods for hash with multiple keys
public class KeyHash<E extends Comparable<E>> extends Hash2<E>{
  /*
   * For each key there's a list of lists that virtually operate as a hash.
   * Each node of the hash has;
   * -> KEY: position in the array of the parent element
   * -> VALUE: the value of the field of the parent element
   */

  // add a key value to the keyhash
  protected <K extends Comparable<K>> boolean addHashKey(K key, IList<IList<K>> list){
    Integer hash_key = this.hash(key);
    IList<K> list_key = null;

    // check if the list of the hashed value of the key in input already exist
    if ((list_key = ((KeyList<IList<K>, Integer, Integer>)list).getByKey(hash_key)) == null){
      // if not create it
      ((KeyList<IList<K>, Integer, Integer>)list).addTail(hash_key, new KeyList(hash_key));
      list_key = ((KeyList<IList<K>, Integer, Integer>)list).getTail().getValue();
    }

    // else add at the end
    ((KeyList<K, Integer, Integer>)list_key).addTail(this.size, key);
    return true;
  }

  // delete of the node that has the key in input
  protected <K extends Comparable<K>> boolean delHashKey(K key, IList<IList<K>> list, Integer dom_pos){
    Integer hash_key = this.hash(key);
    IList<K> node = null;

    // check if the list of the hashed value of the key in input already exist
    if ((node = ((IKeyList<IList<K>, Integer, Integer>)list).getByKey(hash_key)) != null){
      // delete the node by knowing the position in the principal array
      ((IKeyList<K, Integer, Integer>)node).delByKey(dom_pos);

      // delete the whole list if it's empty
      if (node.getSize() <= 0)
        list.delEl(node);

      return true;
    }

    return false;
  }

  // update a hash by knowing the old key and the new one
  protected <K extends Comparable<K>> boolean updHashKey(K old, K key, IList<IList<K>> list, Integer dom_pos){
    // delete the old node with the old key
    this.delHashKey(old, list, dom_pos);
    // add a new node with the new key
    this.addHashKey(key, list);

    return true;
  }

  // search all the node with the same key in input
  protected <K extends Comparable<K>> IList<E> searchByHashKey(K input, IList<IList<K>> key_hash){
    Integer key = this.hash(input);
    IList<E> output = new KeyList<E, Integer, Integer>();
    IList<K> node = null;

    // check if the list of the hashed value of the key in input already exist
    if ((node = ((IKeyList<IList<K>, Integer, Integer>)key_hash).getByKey(key)) != null)
      // iterate for each node of the list
      for (IKeyNode<K, Integer> iter = (IKeyNode<K, Integer>)node.getHead(); iter != null; iter = (IKeyNode<K, Integer>)iter.getNext())
        if (input.compareTo(iter.getValue()) == 0)
          // add to the output list if is the same as the input
          output.addTail(this.dom.get(iter.getKey()));

    // if the output is empty return null
    return (output.getSize() <= 0) ? null : output;
  }

  // get the first NUM nodes. The list in input must be already sorted
  protected <K extends Comparable<K>> IList<E> searchMostOfHashKey(Integer num, IList<K> key_hash){
    IList<E> output = new KeyList<E, Integer, Integer>();
    int i = 0;

    // iterate for each node
    for (IKeyNode<K, Integer> iter = (IKeyNode<K, Integer>)key_hash.getHead(); iter != null && i < num; iter = (IKeyNode<K, Integer>)iter.getNext(), i++)
      // add to the output list
      output.addTail(this.dom.get(iter.getKey()));

      // if the output is empty return null
    return (output.getSize() <= 0) ? null : output;
  }

  // get all the node containing the input
  // WARNING: the input and the list of list must be Strings
  protected IList<E> searchContainsHashKey(String input, IList<IList<String>> key_hash){
    IList<E> output = new KeyList<E, Integer, Integer>();
    int i = 0;

    // iterate for each hashed list
    for (INode2<IList<String>> iter = key_hash.getHead(); iter != null; iter = iter.getNext())
      // iterate for each node of the list
      for (IKeyNode<String, Integer> iterNode = (IKeyNode<String, Integer>)iter.getValue().getHead(); iterNode != null; iterNode = (IKeyNode<String, Integer>)iterNode.getNext())
        // check if it contains the input
        if (iterNode.getValue().contains(input))
          // add to the output
          output.addTail(this.dom.get(iterNode.getKey()));

    // if the output is empty return null
    return (output.getSize() <= 0) ? null : output;
  }
}
