import java.lang.reflect.Array;
import java.util.Arrays;


public abstract class Heap<T extends Comparable>{

	T[] elements;
	int MAX_SIZE = 40;
	int count = 0;

	// constructor
	public Heap(Class<T> clazz) {
		elements = (T[]) Array.newInstance(clazz, MAX_SIZE);
	}

	// constructor
	public Heap(Class<T> clazz, int size) {
		elements = (T[]) Array.newInstance(clazz, size);
	}
	
	protected int getCount() 	{ return count; }
	protected boolean isFull(){ return (getCount() >= elements.length);}
	protected boolean isEmpty(){ return getCount() == 0;}

	protected int getLeftChildIndex(int parentIndex){ return (parentIndex * 2 + 1); }
	protected int getRightChildIndex(int parentIndex){ return (parentIndex * 2 + 2); }
	protected int getParentIndex(int childIndex){ return (childIndex - 1) / 2; }
	
	protected boolean hasLeftChild(int parentIndex) { return getLeftChildIndex(parentIndex) < getCount(); }
	protected boolean hasRightChild(int parentIndex) { return getRightChildIndex(parentIndex) < getCount(); }
	protected boolean hasParent(int childIndex) { return getParentIndex(childIndex) >= 0; }
	
	protected void swap (int index1, int index2) { 
		T tmp = elements[index1];
		 elements[index1] =  elements[index2];
		 elements[index2] = tmp;
	}
	
	public void increaseSize(){
		elements = Arrays.copyOf(elements, elements.length * 2);
	}
	
	// get highest priority
	public  T peek() throws HeapEmptyException{ 
		if (getCount() == 0) throw new HeapEmptyException();
		return elements[0];
	}
	// remove highest priority
	public  T poll() throws HeapEmptyException{ 
		if (getCount() == 0) throw new HeapEmptyException();
		T ret = elements[0];
		elements[0]=elements[count - 1];
		elements[count - 1] = null; // not necessary, but helps avoid confusion in toString()
		count--;
		siftdown();
		return ret;
	}
	// add or offer
	public void add(T item) throws HeapFullException{
		if (getCount() >= elements.length) throw new HeapFullException();
		elements[count] = item; // add element at end
		count++;                // increment the size
		siftup();        // sift up the position at which the element is added.
	}
	
	protected abstract void siftup(); // siftup works on count - 1 always
	protected abstract void siftdown(); // siftdown works on 0 always
	
	public abstract T getMin() throws HeapEmptyException;
	public abstract T getMax() throws HeapEmptyException;
	public int getFirstLeafIndex() throws HeapEmptyException{
		if (getCount() == 0) throw new HeapEmptyException();
		int lastIndex = (getCount() - 1);
		int lastParentIndex = (lastIndex - 1)/2;
		return lastParentIndex + 1;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(elements);
	}
	//hasLeftChild

	public static class HeapEmptyException extends Exception {

	}
	public static class HeapFullException extends Exception{

	}


}
