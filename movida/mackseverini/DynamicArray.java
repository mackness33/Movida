package movida.mackseverini;

import movida.mackseverini.Queue;
import java.util.Arrays;

// Class used to virtually implements an array without its costraints
public class DynamicArray<E extends Comparable<E>> {

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

	public DynamicArray<E> ensure() {
		DynamicArray<E> scaled = new DynamicArray(this.length + this.alfa());

		for(int i = 0; i < this.length; i++)
			scaled.set(i, (E)arr[i]);

		return scaled;
	}

	protected int alfa(){
		return this.length * 3/5;
	}

	public DynamicArray<E> spare() {
		Queue<Integer> Q = new Queue();
		
		for(Integer i = 0; i < this.length; i++)
			if (arr[i] != null)
				Q.enqueue(i);

		boolean check_size = ((this.length - this.alfa()) >= Q.getSize());		
		DynamicArray<E> spared = new DynamicArray((check_size) ? this.length - this.alfa() : this.length);
		int newLength = (check_size) ? Q.getSize() : this.length;

		for(int i = 0; i < newLength; i++)
			spared.set(i, (E)arr[(check_size) ? Q.dequeue() : i]);

		return spared;
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
}
