package movida.mackseverini;

import java.util.Arrays;
import movida.mackseverini.Array;
import movida.mackseverini.Node;

public class Set<E> {

	protected Array<SetLeaderNode<E>> arr;
	protected final Integer MAX_LENGTH;
	// constructor
	public Set ()
	{
		this.MAX_LENGTH = 50;
		// Creates a new Object array of specified length
		this.arr = new Array<SetLeaderNode<E>>(this.MAX_LENGTH);
		for (int i = 0; i < this.arr.length; i++)
      this.arr.set(i, new SetLeaderNode<E>());
	}

	public Set (int length)
	{
		this.MAX_LENGTH = length;
		// Creates a new Object array of specified length
		this.arr = new Array<SetLeaderNode<E>>(this.MAX_LENGTH);
	}

	public void makeSet (Integer pass, E element){
		Integer key = this.hash(pass);
		this.arr[key] = new SetLeaderNode(key, element);
	}

	// Function to get Object present at index i in the array
	public E find(Integer pass) {
		SetLeaderNode<E> temp = this.arr[this.hash(pass)];
		return temp.getSize() <= 0 ? null : temp.getHead().getValue();
	}

	// Function to get Object present at index i in the array
	public Node<E> findNode(Integer pass) {
		SetLeaderNode<E> temp = this.arr[this.hash(pass)];
		return temp.getSize() <= 0 ? null : temp.getHead().getNode();
	}

	Integer hash (Integer pass){
    return Math.abs(pass);
	}

	// Function to set a value e at index i in the array
	void union (Integer x, Integer y) {
		SetLeaderNode<E> xSet = this.arr[this.hash(x)]);
		SetLeaderNode<E> ySet = this.arr[this.hash(y)]);

		if (xSet.getSize() >= ySet.getSize()){
			ySet.setLeader(xSet.getHead());
			ySet = null;
		}
		else{
			xSet.setLeader(ySet.getHead());
			xSet = null;
		}

		return;
	}

	@Override
	public String toString () {
		return Arrays.toString(arr);
	}

	private class SetLeaderNode<T extends Comparable<T>>{
		protected SetNode<T> head;
		protected SetNode<T> tail;
		protected Integer size;

		public SetLeaderNode (){
			this.head = null;
			this.tail = null;
			this.size = 0;
    }

		public SetLeaderNode (SetNode<T> h, SetNode<T> t){
			this.head = h;
			this.tail = t;
			for (Node<T> start = h; start == null; start = start.getNext())
				this.size++;
    }

		public SetLeaderNode (SetNode<T> h, SetNode<T> t, int s){
			this.head = h;
			this.tail = t;
			this.size = s;
    }

		public void newLeader(SetNode<T> l){ this.head.setLeader(l); }

		public SetNode<T> getHead () { return this.next; }
		public SetNode<T> getTail () { return this.tail; }
		public Integer getSize () { return this.size; }

		public void setNext (SetNode<T> n) { this.next = n; }
		public void setTail (SetNode<T> t) { this.tail = t; }
		public void setSize (Integer s) { this.size = s; }

    public void print (){
      super.print();

      if(this.next != null)
        this.next.print();
    }
  }

	private class SetNode<E> extends Node<E>{
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

		public SetNode (Integer k, Integer v, SetNode<E> n, SetNode<E> l){
      super(k, v);
			this.next = (Node<E>) n;
			this.leader = l;
		}

    public SetNode<E> getLeader () { return this.leader; }

    public void setLeader (SetNode<E> l) { this.leader = l; this.next.setLeader(l); }

		public Node<E> getNode () { return (Node<E>) this; }
  }
}
