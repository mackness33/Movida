package movida.mackseverini;

import java.util.Arrays;
import movida.mackseverini.Array;
import movida.mackseverini.Node2;
import movida.mackseverini.List;
import movida.mackseverini.IList;

public class Set<K extends Comparable<K>, E extends Comparable<E>> {

	protected List<IList<E>> les;
	protected Array<SetNode<E>> els;
	protected Array<Integer> keys;
	protected final Integer MAX_LENGTH = 500;
	protected final String key_name;
	// protected final Class<K> key_type;
	protected Integer size;

	// constructor
	public Set (){
		this.size = 0;
		this.key_name = "self";

		this.les = new List<IList<E>>();
		this.els = new Array<SetNode<E>>(this.MAX_LENGTH);

		this.reset();
	}

	public Set (int length){
		this.size = 0;
		this.key_name = "self";
		// this.key_type = K.getClass();
		this.les = new List<IList<E>>();
		this.els = new Array<SetNode<E>>(this.MAX_LENGTH);

		this.reset();
	}

	public Set (int length, String name){
		this.size = 0;
		this.key_name = name;
		// this.key_type = K.getClass();
		this.les = new List<IList<E>>();
		this.els = new Array<SetNode<E>>(this.MAX_LENGTH);

		this.reset();
	}

	public Set (String name){
		this.size = 0;
		this.key_name = name;
		// this.key_type = K.getClass();
		this.les = new List<IList<E>>();
		this.els = new Array<SetNode<E>>(this.MAX_LENGTH);

		this.reset();
	}


	public void makeSet (K filter, E element){
		Integer key = this.hash(filter);
		this.els.set(key, new SetNode(key, element));
		this.les.addTail(new SetLeaderNode(this.els.get(key), key));
		this.size++;
	}

	public Integer getSize() { return this.size; }


	// Function to get Object present at index i in the array
	public E find(K filter) {
		SetNode<E> temp = this.els.get(this.hash(filter));
		System.out.println("TEMP: ");
		temp.print();
		System.out.println("KEY: " + temp.getKey());
		return temp.getKey() < 0 ? null : temp.getLeader().getValue();
	}

	// Function to get Object present at index i in the array
	protected SetLeaderNode<E> findLeader(K filter) {
		SetNode<E> temp = this.els.get(this.hash(filter));
		return temp == null ? null : (temp.getKey() <= -1 ? null : this.getLeaderbyKey(temp.getKey()));
	}

	protected IList<E> findSet(K filter) {
		SetNode<E> temp = this.els.get(this.hash(filter));
		return temp == null ? null : (temp.getKey() <= -1 ? null : this.getLeaderbyKey(temp.getKey()));
	}

	public Integer hash (K filter){
		System.out.println("FILTER: " + filter);
		// System.out.println("instance: " + (filter instanceof Integer));
		if (filter instanceof Integer){
			return Math.abs(((Integer)filter) % this.MAX_LENGTH);
		}
		else if (filter instanceof String)
			return Math.abs((filter.hashCode()) % this.MAX_LENGTH);

		return Math.abs(filter.hashCode() & this.MAX_LENGTH);
	}

	// Function to set a value e at index i in the array
	public void union (K x, K y) {
		SetLeaderNode<E> xSet = this.findLeader(x);
		SetLeaderNode<E> ySet = this.findLeader(y);
		System.out.println("UNION of: " + x + " & " + y);

		if (xSet == null || ySet == null || xSet == ySet)
			return;

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
			this.les.delEl(ySet);
			ySet = null;
		}
		else{
			ySet.addNode(xSet);
			this.les.delEl(xSet);
			xSet = null;
		}

		return;
	}

	public void print () {
		for (INode2<IList<E>> leader = this.les.getHead(); leader != null; leader = leader.getNext()){
			((SetLeaderNode<E>)leader.getValue()).print();
		}
	}

	protected SetLeaderNode<E> getLeaderbyKey(int key){
		INode2<IList<E>> iter = this.les.getHead();

    if (iter == null)
			return null;

    for (; iter != null; iter = iter.getNext())
			if (((SetLeaderNode<E>)iter.getValue()).getKey() == key)
				return (SetLeaderNode<E>)iter.getValue();

    return null;

	}

	public void reset () {
		for (int i = 0; i < this.MAX_LENGTH; i++){
			this.els.set(i, null);
		}
	}

	private class SetLeaderNode<E extends Comparable<E>> extends movida.mackseverini.List<E>{
		protected Integer key;

		public SetLeaderNode (){
			super();
			this.key = -1;
    }

		public SetLeaderNode (int k){
			super();
			this.key = k;
    }

		public SetLeaderNode (int k, E el){
			super(el);
			this.key = k;
    }

		public SetLeaderNode (SetNode<E> l, int k){
			// super();
			this.head = l;
			this.tail = l;
			this.key = k;
			this.size = 1;
    }

		public SetLeaderNode (SetLeaderNode<E> L){
			super((List<E>)L);
			this.key = L.getKey();
    }

		public void newLeader(SetNode<E> l){ ((SetNode<E>)this.head).setLeader(l); }

		public Integer getKey () { return this.key; }

		// BUG: change count!!!!
		public void addNode (SetLeaderNode<E> leader) {
			if (leader.getHead() == null)
				return;

			this.tail.setNext((SetNode<E>)leader.getHead());
			leader.newLeader((SetNode<E>)this.head);
			this.tail = (SetNode<E>)leader.getTail();
			this.size += leader.getSize();
		}

		public void setHead (SetNode<E> h) { this.head = h; }
		public void setTail (SetNode<E> t) { this.tail = t; }
		public void setSize (Integer s) { this.size = s; }

		public List<E> toList(){
			return (List<E>)this;
		}

    public void print (){
			System.out.println("LEADER: ");
      if(this.head != null)
        ((SetNode<E>)this.head).printAll();
    }
  }

	protected class SetNode<E extends Comparable<E>> extends Node2<E>{
		protected SetNode<E> leader;
		protected Integer key;

    public SetNode (){
      super();
			this.next = null;
			this.leader = this;
      this.key = -1;
		}

		public SetNode (Integer k, E v){
      super(v);
			this.next = null;
			this.leader = this;
			this.key = k;
		}

		public void printAll(){
			this.print();

			if (this.next != null)
				((SetNode<E>)this.next).printAll();
		}

		public void print(){
			System.out.println("Node2: KEY => " + this.key + "	VALUE => " + this.value);
		}

		public SetNode<E> getLeader () { return this.leader; }
		public Integer getKey () { return this.key; }

		public void setNext (SetNode<E> n) { this.next = n; }
		public void setKey (Integer k) { this.key = k; }
    public void setLeader (SetNode<E> l) {
			this.leader = l;
			this.key = l.getKey();
			if (this.next != null)
				((SetNode<E>)this.next).setLeader(this.leader);
		}

		public Node2<E> getNode () { return (Node2<E>) this; }
  }
}
