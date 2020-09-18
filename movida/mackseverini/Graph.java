package movida.mackseverini;

import java.util.Arrays;

// Class used to virtually implements an array without its costraints
public class Graph<E extends Comparable<E>, K extends Comparable<K>> {
  protected Array<E> nodes;
  protected int size;                       // Max position occupied in the array
  protected int length;                     // Counts the num of the elements currently in the hash

	// constructor
	public Array(int length)
	{
		// Creates a new Object array of specified length
		arr = new Object[length];
		this.length = length;
	}

	// constructor
	public Array(Array<E> shallow)
	{
		// Creates a new Object array of specified length
		arr = shallow.toPrimitive();
		this.length = shallow.length;
	}

	// Function to get Object present at index i in the array
	@SuppressWarnings("unchecked")
	public E get(int i) {
		final E e = (E)arr[i];
		return e;
	}

	// Function to set a value e at index i in the array
	@SuppressWarnings("unchecked")
	public void set(int i, E e) {
		arr[i] = e;
	}

	@Override
	public String toString() {
		return Arrays.toString(arr);
	}

	// Function to converts the array in the actual array[] version
	public E[] toPrimitive(){
		final E[] primitive = (E[])arr;
		return primitive;
	}

  // Class used for list with keys. It extends the List class by adding methods for keys
  public class GraphList<E extends Comparable<E>> extends movida.mackseverini.List<E> implements movida.mackseverini.IKeyList<E>{
    protected Integer key;

    public KeyList (){
      this.head = null;
      this.tail = null;
      this.size = 0;
      this.key = null;
    }

    public KeyList (Integer k, Integer el_key, E el){
      this.head = new KeyNode<E>(el_key, el);
      this.tail = this.head;
      this.size = 1;
      this.key = k;
    }

    public KeyList (Integer k){
      this.head = null;
      this.tail = null;
      this.size = 0;
      this.key = k;
    }

    public KeyList (KeyList<E> shallow){
      this.head = (KeyNode<E>)shallow.getHead();
      this.tail = (KeyNode<E>)shallow.getTail();
      this.size = shallow.getSize();
      this.key = shallow.getKey();
    }

    @Override
    public void print(){ System.out.println("KeyList: KEY => " + this.key + " HEAD => " + this.head); }

    public void printAll(){
      System.out.println("KeyList: KEY => " + this.key + " HEAD => " + this.head);
      if (this.head != null && this.head instanceof KeyNode)
        ((KeyNode<E>)this.head).printAll();
    }

    @Override
    public Integer getKey() { return this.key; }

    @Override
    public void setKey (Integer k) { this.key = k; }

    // Get the element by having a key as the input
    @Override
    public E getByKey (Integer k){
      if (this.size <= 0)
        return null;

      // iterate all the list
      for (KeyNode<E> iter = (KeyNode<E>)this.head; iter != null; iter = (KeyNode<E>)iter.getNext())
        if (iter.getKey() == k)
          return iter.getValue();

      return null;
    }

    // Delete a element by having a key as the input
    @Override
    public boolean delByKey (Integer k){
      if (this.size <= 0)
        return false;

      // check wheter the key is the first or last element
      if (k == ((KeyNode<E>)this.head).getKey()){
        this.delHead();
        return true;
      }
      else if (k == ((KeyNode<E>)this.tail).getKey()){
        this.delTail();
        return true;
      }

      // check to see if there's more then one element
      if((KeyNode<E>)this.head.getNext() == null)
        return false;

      int i = 0;

      // iterate all the elements of the list
      for (KeyNode<E> prev = (KeyNode<E>)this.head, iter = (KeyNode<E>)this.head.getNext(); iter.getNext() != null; iter = (KeyNode<E>)iter.getNext(), prev = (KeyNode<E>)prev.getNext()){
        // if the key match, delete the node
        if (iter.getKey() == k){
          prev.setNext(iter.getNext());
          iter = null;
          this.size--;
          return true;
        }
      }

      return false;
    }

    @Override
    // get the key by having the element as the input
    public Integer searchKey (E el){
      if (this.size <= 0)
        return null;

      // compare the element with the first and last node of the list
      if (el.compareTo(this.head.getValue()) == 0)
        return ((KeyNode<E>)this.head).getKey();
      else if (el.compareTo(this.tail.getValue()) == 0)
        return ((KeyNode<E>)this.tail).getKey();


      // check to see if there's more then one element
      if((KeyNode<E>)this.head.getNext() == null)
        return null;

      int i = 1;
      // iterate all the list to compare the element
      for (KeyNode<E> iter = (KeyNode<E>)this.head.getNext(); iter.getNext() != null && i < this.size; iter = (KeyNode<E>)iter.getNext(), i++)
        if (el.compareTo(iter.getValue()) == 0)
          return ((KeyNode<E>)iter).getKey();

      return null;
    }

    @Override
    // add element and key as the last node of the list
    public void addTail (Integer k, E el){
      if (this.size <= 0){
        this.head = new KeyNode<E>(k, el);
        this.tail = this.head;
        this.size = 1;
        return;
      }

      KeyNode<E> temp = new KeyNode<E>(k, el);
      this.tail.setNext(temp);
      this.tail = temp;
      this.size++;
    }

    @Override
    // add element and key as the first node of the list
    public void addHead (Integer k, E el){
      if (this.size <= 0){
        this.head = new KeyNode<E>(k, el);
        this.tail = this.head;
        this.size = 1;
        return;
      }

      KeyNode<E> temp = new KeyNode<E>(k, el);
      temp.setNext(this.head);
      this.head = temp;
      this.size++;
    }

    @Override
    // add element and key at a specified position of the list
    public void addBlue (Integer k, E el, Integer pos){
      // check if the position is valid
      if (pos <= 0 || pos >= size){
        if (pos == 0){
          this.addHead(k, el);
          return;
        }
        else if (pos == size){
          this.addTail(k, el);
          return;
        }

        return;
      }

      int i = 1;
      KeyNode<E> iter = null;
      // iterate all the list to get to the position
      for (iter = (KeyNode<E>)this.head; iter.getNext() != null && i < pos; iter = (KeyNode<E>)iter.getNext(), i++);

      // if right, add to the list
      if (i == pos){
        KeyNode<E> temp = new KeyNode<E>(k, el);
        temp.setNext((KeyNode<E>)iter.getNext());
        iter.setNext(temp);
        this.size++;
      }
    }

    // Update an element by having its key as the input
    public void updByKey (E el, int key){
      if (this.size <= 0)
        return;

      // compare the element with the first and last node of the list
      if (key == ((KeyNode<E>)this.head).getKey())
        this.head.setValue(el);
      else if (key == ((KeyNode<E>)this.tail).getKey())
        this.tail.setValue(el);

      // check to see if there's more then one element
      if((KeyNode<E>)this.head.getNext() == null)
        return;


      // iterate all the list to compare the key
      for (IKeyNode<E> iter = (IKeyNode<E>)this.head.getNext(); iter.getNext() != null; iter = (IKeyNode<E>)iter.getNext())
        if (iter.getKey() == key)
          iter.setValue(el);
    }
  }


  protected class Arch<E extends Comparable<E>, K extends Comparable<K>> extends KeyNode<E, K>{
		protected E value2;

    public Arch(){
      super();
      this.key = null;
    }

    public Arch(K k, E v){
      super(v);
      this.key = k;
    }

    public Arch(K k, E v, KeyNode<E, K> n){
      super(v, n);
      this.key = k;
    }

    @Override
    public E getSecondValue() { return this.value2; }

    @Override
    public void setSecondValue (E v) { this.value2 = v; }

    @Override
    public void print(){ System.out.println("Arch: KEY => " + this.key + " VALUE => " + this.value + " VALUE2 => " + this.value2); }

    public void printAll(){
      System.out.println("KeyList: KEY => " + this.key + " VALUE => " + this.value + " VALUE2 => " + this.value2);

      if (this.value instanceof IList)
        ((KeyList)this.value).printAll();

      if (this.next != null)
        ((KeyNode)this.next).printAll();
    }
  }
}
