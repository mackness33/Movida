package movida.mackseverini;

import java.util.Arrays;
import movida.mackseverini.Array;
import movida.mackseverini.Node;

public class Set<K extends Comparable<K>, E extends Comparable<E>> {

	protected Array<SetLeaderNode<E>> les;
	protected Array<SetNode<E>> els;
	protected Array<Integer> keys;
	protected final Integer MAX_LENGTH = 50;
	protected final String key_name;
	// protected final Class<K> key_type;
	protected Integer size;

	// constructor
	public Set (){
		this.size = 0;
		this.key_name = "self";
		// this.key_type = K.getClass();
		this.les = new Array<SetLeaderNode<E>>(this.MAX_LENGTH);
		this.els = new Array<SetNode<E>>(this.MAX_LENGTH);

		for (int i = 0; i < this.MAX_LENGTH; i++)
		// this.els.set(i, new SetNode<E>());
      this.els.set(i, null);

		for (int i = 0; i < this.MAX_LENGTH; i++)
		// this.les.set(i, new SetLeaderNode<E>());
      this.les.set(i, null);
	}

	public Set (int length){
		this.size = 0;
		this.key_name = "self";
		// this.key_type = K.getClass();
		this.les = new Array<SetLeaderNode<E>>(this.MAX_LENGTH);
		for (int i = 0; i < this.MAX_LENGTH; i++)
			this.les.set(i, null);
      // this.les.set(i, new SetLeaderNode<E>());

		this.els = new Array<SetNode<E>>(this.MAX_LENGTH);

		for (int i = 0; i < this.MAX_LENGTH; i++)
			this.els.set(i, null);

      // this.els.set(i, new SetNode<E>());

	}

	public Set (int length, String name){
		this.size = 0;
		this.key_name = name;
		// this.key_type = K.getClass();
		this.les = new Array<SetLeaderNode<E>>(this.MAX_LENGTH);
		this.els = new Array<SetNode<E>>(this.MAX_LENGTH);

		for (int i = 0; i < this.MAX_LENGTH; i++)
			this.els.set(i, null);
			// this.els.set(i, new SetNode<E>());

		for (int i = 0; i < this.MAX_LENGTH; i++)
			this.les.set(i, null);

      // this.les.set(i, new SetLeaderNode<E>());
	}

	public Set (String name){
		this.size = 0;
		this.key_name = name;
		// this.key_type = K.getClass();
		this.les = new Array<SetLeaderNode<E>>(this.MAX_LENGTH);
		this.els = new Array<SetNode<E>>(this.MAX_LENGTH);

		for (int i = 0; i < this.MAX_LENGTH; i++)
			this.els.set(i, null);
			// this.els.set(i, new SetNode<E>());

		for (int i = 0; i < this.MAX_LENGTH; i++)
			this.les.set(i, null);

      // this.les.set(i, new SetLeaderNode<E>());
	}


	public void makeSet (K filter, E element){
		Integer key = this.hash(filter);
		this.els.set(key, new SetNode(key, element));
		SetNode<E> newNode = this.els.get(key);
		this.les.set(key, new SetLeaderNode(newNode, newNode, 1, key));
		size++;
	}


	// Function to get Object present at index i in the array
	public E find(K filter) {
		SetNode<E> temp = this.els.get(this.hash(filter));
		System.out.println("TEMP: ");
		temp.print();
		System.out.println("KEY: " + temp.getKey());
		return temp.getKey() < 0 ? null : temp.getLeader().getValue();
	}

	// Function to get Object present at index i in the array
	public SetLeaderNode<E> findLeader(K filter) {
		SetNode<E> temp = this.els.get(this.hash(filter));
		return temp.getKey() <= 0 ? null : this.les.get(temp.getKey());
	}

	public Integer hash (K filter){
		System.out.println("FILTER: " + filter);
		// System.out.println("instance: " + (filter instanceof Integer));
		if (filter instanceof Integer){
			System.out.println("LENGTH: " + (((Integer)filter) % this.MAX_LENGTH));
			return Math.abs(((Integer)filter) % this.MAX_LENGTH);
		}
		else if (filter instanceof String)
			return Math.abs(Integer.valueOf((String)filter) % this.MAX_LENGTH);
		else {
			String [] svalue = filter.toString().split("@");
			if (svalue.length > 1)
				return Math.abs(Integer.valueOf(svalue[svalue.length - 1]) & this.MAX_LENGTH);

			return Math.abs(Integer.valueOf(svalue[0]) & this.MAX_LENGTH);
		}
	}

	// Function to set a value e at index i in the array
	public void union (K x, K y) {
		SetLeaderNode<E> xSet = this.findLeader(x);
		SetLeaderNode<E> ySet = this.findLeader(y);

		System.out.println("X: ");
		xSet.print();
		System.out.println("Y: ");
		ySet.print();

		System.out.println("X size: " + xSet.getSize());
		System.out.println("Y size: " + ySet.getSize());


		if (xSet.getSize() < 0 || ySet.getSize() < 0)
			return;

		if (xSet.getSize() >= ySet.getSize()){
			xSet.addNode(ySet);
			this.les.set(this.hash(y), null);
			ySet = null;
		}
		else{
			ySet.addNode(xSet);
			this.les.set(this.hash(x), null);
			xSet = null;
		}

		return;
	}

	public void print () {
		SetLeaderNode<E> leader;
		for (int i = 0; i < les.length; i++){
			leader = les.get(i);
			if (leader != null)
				if (leader.getKey() >= 0)
					leader.print();
		}
	}

	private class SetLeaderNode<E extends Comparable<E>>{
		protected SetNode<E> head;
		protected SetNode<E> tail;
		protected Integer key;
		protected Integer size;

		public SetLeaderNode (){
			this.head = null;
			this.tail = null;
			this.size = 0;
			this.key = -1;
    }

		public SetLeaderNode (SetNode<E> h, SetNode<E> t, int s, int k){
			this.head = h;
			this.tail = t;
			this.size = s;
			this.key = k;
    }

		public SetLeaderNode (SetLeaderNode<E> L){
			this.head = L.getHead();
			this.tail = L.getTail();
			this.size = L.getSize();
			this.key = L.getKey();
    }

		public void newLeader(SetNode<E> l){ this.head.setLeader(l); }

		public SetNode<E> getHead () { return this.head; }
		public SetNode<E> getTail () { return this.tail; }
		public Integer getSize () { return this.size; }
		public Integer getKey () { return this.key; }

		// BUG: change count!!!!
		public void addNode (SetLeaderNode<E> leader) {
			this.tail.setNext(leader.getHead());
			leader.newLeader(this.head);
			this.tail = leader.getTail();
		}

		public void setHead (SetNode<E> h) { this.head = h; }
		public void setTail (SetNode<E> t) { this.tail = t; }
		public void setSize (Integer s) { this.size = s; }

    public void print (){
			System.out.println("LEADER: ");
			this.head.print();
      if(this.head != null)
        this.head.printAll();
    }
  }

	private class SetNode<E extends Comparable<E>> extends Node<E>{
    private SetNode<E> leader;
		protected SetNode<E> next;

    public SetNode (){
      super();
      this.leader = this;
			this.next = null;
		}

		@SuppressWarnings("unchecked")
    public SetNode (Node<E> el){
      super(el);
			this.next = null;
			this.leader = this;
		}

		@SuppressWarnings("unchecked")
		public SetNode (Node<E> el, SetNode<E> l){
      super(el);
			this.next = new SetNode(el.getNext(), l);
			this.leader = l;
		}

		@SuppressWarnings("unchecked")
		public SetNode (Integer k, E v, SetNode<E> n, SetNode<E> l){
      super(k, v);
			this.next = n;
			this.leader = l;
		}

		public SetNode (Integer k, E v){
      super(k, v);
			this.next = null;
			this.leader = this;
		}

		public void printAll(){
			this.print();

			if (this.next != null)
				this.next.printAll();
		}

		public SetNode<E> getLeader () { return this.leader; }
		public SetNode<E> setNext () { return this.next; }

		public void setNext (SetNode<E> n) { this.next = n; }
    public void setLeader (SetNode<E> l) {
			this.leader = l;
			this.key = l.getKey();
			if (this.next != null)
				this.next.setLeader(this.leader);
		}

		public Node<E> getNode () { return (Node<E>) this; }
  }
}
