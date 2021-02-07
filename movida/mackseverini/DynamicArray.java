package movida.mackseverini;

import movida.mackseverini.Queue;
import java.util.Arrays;

// Extension of Array that manage also the length of the array
public class DynamicArray<E extends Comparable<E>> extends movida.mackseverini.Array<E>{

	private final Object[] arr;
	public final int length;

	// constructor
	public DynamicArray(int length)
	{
		// Creates a new Object array of specified length
		arr = new Object[length];
		this.length = length;
	}

	// constructor
	public DynamicArray(Array<E> shallow)
	{
		// Creates a new Object array of specified length
		arr = shallow.toPrimitive();
		this.length = shallow.length;
	}

	public DynamicArray(DynamicArray<E> shallow)
	{
		// Creates a new Object array of specified length
		arr = shallow.toPrimitive();
		this.length = shallow.length;
	}

	public DynamicArray trim() {
		Queue<Integer> Q = new Queue();

		for(Integer i = 0; i < this.length; i++)
			if (arr[i] != null)
				Q.enqueue(i);

		DynamicArray<E> trimmed = new DynamicArray(Q.getSize());

		int size_queue = Q.getSize();
		for(int i = 0; i < size_queue; i++)
			trimmed.set(i, (E)arr[Q.dequeue()]);

		return trimmed;
	}

	// ensure the array has enough space by creating a new one bigger
	public DynamicArray<E> ensure() {
		DynamicArray<E> scaled = new DynamicArray(this.length + this.alfa());

		// copy the old array on the new one
		for(int i = 0; i < this.length; i++)
			scaled.set(i, (E)arr[i]);

		return scaled;
	}

	// the costant that you add or subtract from the new one
	protected int alfa(){
		return this.length * 3/5;
	}

	// spare unused space of the array by creating a new one smaller
	public DynamicArray<E> spare() {
		Queue<Integer> Q = new Queue();

		// enqueue the values not null of the array
		for(Integer i = 0; i < this.length; i++)
			if (arr[i] != null)
				Q.enqueue(i);


		boolean check_size = ((this.length - this.alfa()) >= Q.getSize());
		DynamicArray<E> spared = new DynamicArray((check_size) ? this.length - this.alfa() : this.length);
		int newLength = (check_size) ? Q.getSize() : this.length;

		// insert them on the same order
		for(int i = 0; i < newLength; i++)
			spared.set(i, (E)arr[(check_size) ? Q.dequeue() : i]);

		return spared;
	}
}
