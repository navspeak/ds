
public class MinHeap<T extends Comparable> extends Heap<T> {

	public MinHeap(Class<T> clazz) {
		super(clazz);
	}
	
	public MinHeap(Class<T> clazz, int size ) {
		super(clazz, size);
	}

	
	//              10
	//        15          20
	//  17          8 <-
	//
    //   10, 15, 20, 17, 8 <- (we add 8 and sift up)
	
	@Override
	protected void siftup() {
		// Till there is a parent, and
		// Till till it is out of order i.e. parentIndex is greater than child
		int index = getCount() - 1;
		while(hasParent(index) && elements[getParentIndex(index)].compareTo(elements[index]) > 0 ){
			swap(index, getParentIndex(index));
			index = getParentIndex(index);
		}
	}

	
	//              10 (17)<- we remove this
	//         15          20
	//  17 <-        
	//
    //   10, 15, 20, 17
	@Override
	protected void siftdown() {
		int index = 0;
		// Till it has children, we just need to check the left children
		while(hasLeftChild(index)){ // in array impl we will always have left and then right
			int smallerChildIndex = getLeftChildIndex(index); // let's assume smaller child is left
			if (elements[smallerChildIndex].compareTo(elements[getRightChildIndex(index)]) > 0){
				smallerChildIndex = getRightChildIndex(index);
			}
			if (elements[index].compareTo(elements[smallerChildIndex]) < 0) // if element at index is smaller than children, we are done
				break;
			else {
				swap(index, smallerChildIndex);
			}
			index = smallerChildIndex;
		}
		
	}

	@Override
	public T getMin() throws Heap.HeapEmptyException {
		return peek();

	}

	@Override
	public T getMax() throws Heap.HeapEmptyException {
		int firstLeafIndex = getFirstLeafIndex();
		T max = elements[firstLeafIndex];
		for (int i = firstLeafIndex; i < getCount(); i++){
			if (max.compareTo(elements[i]) < 0)
				max = elements[i];
		}
		return max;
	}

}
