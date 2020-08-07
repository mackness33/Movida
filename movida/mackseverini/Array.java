package movida.mackseverini;

import java.util.Arrays;

// Class used to virtually implements an array without its costraints
public class Array<E> {

	private final Object[] arr;
	public final int length;

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
}
