package movida.mackseverini;

import java.util.Arrays;
import movida.mackseverini.Array;
import movida.mackseverini.Node;
import movida.mackseverini.Type;

public class Set<K extends Comparable<K>, E> {

	protected Array<SetLeaderNode<E>> arr;
	protected Array<SetNode<E>> els;
	protected Array<Integer> keys;
	protected final Integer MAX_LENGTH = 50;
	protected final String key_name;
	protected final Class<K> key_type = K.getClass();
	protected Integer size;

	// constructor
	public Set (){
		this.key_name = "self";
		this.arr = new Array<SetLeaderNode<E>>(this.MAX_LENGTH);

		for (int i = 0; i < this.arr.length; i++)
      this.arr.set(i, new SetLeaderNode<E>());
	}

	public Set (int length){
		this.size = 0;
		this.key_name = "self";
		this.arr = new Array<SetLeaderNode<E>>((length < this.MAX_LENGTH) ? length : this.MAX_LENGTH);
		for (int i = 0; i < this.arr.length; i++)
      this.arr.set(i, new SetLeaderNode<E>());
	}

	public Set (int length, String name){
		this.size = 0;
		this.key_name = name;
		this.arr = new Array<SetLeaderNode<E>>(this.MAX_LENGTH);

		for (int i = 0; i < this.arr.length; i++)
      this.arr.set(i, new SetLeaderNode<E>());
	}

	public Set (String name){
		this.size = 0;
		this.key_name = name;
		this.arr = new Array<SetLeaderNode<E>>(this.MAX_LENGTH);

		for (int i = 0; i < this.arr.length; i++)
      this.arr.set(i, new SetLeaderNode<E>());
	}


	public void makeSet (E element){
		Integer key = this.hash(element);
		this.els[key] = new SetNode(key, element);
		SetNode<T> newNode = this.els[key];
		this.arr[key] = new SetLeaderNode(newNode, newNode, 1, key);
		size++;
	}


	// Function to get Object present at index i in the array
	public E find(E element) {
		SetNode<E> temp = this.els[this.hash(element)];
		return temp.getSize() <= 0 ? null : temp.getLeader().getValue();
	}

	// Function to get Object present at index i in the array
	public Node<E> findLeader(E element) {
		SetNode<E> elNode = this.els[this.hash(element)].getLeader();
		return temp.getKey() <= 0 ? null : this.arr[temp.getKey()];
	}

	public Integer hash (E element){
		K value = element.get(this.key_name);
		if (value instanceof Integer)
			return Math.abs(value % this.MAX_LENGTH);
		else if (value instanceof String)
			return Math.abs(Integer.valueOf(value) % this.MAX_LENGTH);
		else {
			String [] svalue = value.toString().split("@");
			if (svalue.length > 1)
				return Math.abs(Integer.valueOf(svalue[svalue.length - 1]) & this.MAX_LENGTH);

			return Math.abs(Integer.valueOf(svalue[0]) & this.MAX_LENGTH)
		}
	}

	// Function to set a value e at index i in the array
	public void union (E x, E y) {
		SetLeaderNode<E> xSet = this.findLeader(x);
		SetLeaderNode<E> ySet = this.findLeader(y);

		if (xSet.getSize() >= ySet.getSize()){
			xSet.addNode(ySet);
			ySet = null;
		}
		else{
			ySet.addNode(xSet);
			xSet = null;
		}

		return;
	}

	@Override
	public String toString () {
		return Arrays.toString(arr);
	}

	@Override
	public void print () {
		SetLeaderNode<T> leader;
		for (int i = 0; i < arr.length; i++){
			leader = arr.get(i);
			if (leader.getKey() >= 0)
				leader.print();
		}
	}

	private class SetLeaderNode<T extends Comparable<T>>{
		protected SetNode<T> head;
		protected SetNode<T> tail;
		protected Integer key;
		protected Integer size;

		public SetLeaderNode (){
			this.head = null;
			this.tail = null;
			this.size = 0;
			this.key = -1;
    }

		public SetLeaderNode (SetNode<T> h, SetNode<T> t, int s, int k){
			this.head = h;
			this.tail = t;
			this.size = s;
			this.key = k;
    }

		public SetLeaderNode (SetLeaderNode<T> L){
			this.head = L.getHead();
			this.tail = L.getTail();
			this.size = L.getSize();
			this.key = L.getKey();
    }

		public void newLeader(SetNode<T> l){ this.head.setLeader(l); }

		public SetNode<T> getHead () { return this.next; }
		public SetNode<T> getTail () { return this.tail; }
		public Integer getSize () { return this.size; }
		public Integer getKey () { return this.key; }

		public void addNode (SetLeaderNode<T> leader) {
			this.tail.setNext(leader.getHead());
			leader.newLeader(this.head);
			this.tail = leader.getTail();
		}

		public void setNext (SetNode<T> n) { this.next = n; }
		public void setTail (SetNode<T> t) { this.tail = t; }
		public void setSize (Integer s) { this.size = s; }

    public void print (){
      if(this.head != null)
        this.head.printAll();
    }
  }

	private class SetNode<E> extends Node<E>{
		private Integer key;
    private SetNode<E> leader;

    public SetNode (){
      super();
      this.leader = this;
		}

    public SetNode (Node<E> el){
      super(el);
			this.leader = this;
		}

		public SetNode (Node<E> el, SetNode<E> l){
      super(el);
			this.leader = l;
		}

		public SetNode (Integer k, E v, SetNode<E> n, SetNode<E> l){
      super(k, v);
			this.next = (Node<E>) n;
			this.leader = l;
		}

		public SetNode<E> getLeader () { return this.leader; }
		public Integer getKey () { return this.key; }

		public void setKey (Integer k) { this.key = k; }
    public void setLeader (SetNode<E> l) {
			this.leader = l;
			this.key = l.getKey();
			this.next.setLeader(l);
		}

		public Node<E> getNode () { return (Node<E>) this; }
  }
}
