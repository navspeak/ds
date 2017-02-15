import java.util.Arrays;

/**
 * HEAP SORT USES OPERATIONS ON A HEAP TO GET A SORTED LIST
 * INSERTION INTO A HEAP IS DONE N TIMES TO GET ALL THE ELEMENTS IN HEAP FORM
 * REMOVAL OF THE MAXIMUM ELEMENT IS DONE N TIMES,FOLLOWED BY HEAPIFY
 * INSERTION AND REMOVAL HAVE LOG N TIME COMPLEXITY SO DOING IT FOR N ELEMENTS MEANS:
 * O(NLOGN)
 * @author knavneet
 *
 */

/**
 * Sample run:
[12, 35, 87, 111, 26, 9, 28, 7, 100, 67, 9, 204]
calling heapify on : [12, 35, 87, 111, 26, 9, 28, 7, 100, 67, 9, 204] end = 11
After Heapify      : [204, 111, 12, 35, 67, 87, 28, 7, 100, 26, 9, 9]
calling heapify on : [9, 111, 12, 35, 67, 87, 28, 7, 100, 26, 9, 204] end = 10
After Heapify      : [111, 9, 87, 100, 67, 12, 28, 7, 35, 26, 9, 204]
calling heapify on : [9, 9, 87, 100, 67, 12, 28, 7, 35, 26, 111, 204] end = 9
After Heapify      : [100, 9, 87, 9, 67, 12, 28, 7, 35, 26, 111, 204]
calling heapify on : [26, 9, 87, 9, 67, 12, 28, 7, 35, 100, 111, 204] end = 8
After Heapify      : [87, 67, 26, 35, 9, 12, 28, 7, 9, 100, 111, 204]
calling heapify on : [9, 67, 26, 35, 9, 12, 28, 7, 87, 100, 111, 204] end = 7
After Heapify      : [67, 9, 28, 35, 9, 12, 26, 7, 87, 100, 111, 204]
calling heapify on : [7, 9, 28, 35, 9, 12, 26, 67, 87, 100, 111, 204] end = 6
After Heapify      : [35, 7, 28, 9, 9, 12, 26, 67, 87, 100, 111, 204]
calling heapify on : [26, 7, 28, 9, 9, 12, 35, 67, 87, 100, 111, 204] end = 5
After Heapify      : [28, 9, 26, 9, 7, 12, 35, 67, 87, 100, 111, 204]
calling heapify on : [12, 9, 26, 9, 7, 28, 35, 67, 87, 100, 111, 204] end = 4
After Heapify      : [26, 9, 12, 9, 7, 28, 35, 67, 87, 100, 111, 204]
calling heapify on : [7, 9, 12, 9, 26, 28, 35, 67, 87, 100, 111, 204] end = 3
After Heapify      : [12, 9, 7, 9, 26, 28, 35, 67, 87, 100, 111, 204]
calling heapify on : [9, 9, 7, 12, 26, 28, 35, 67, 87, 100, 111, 204] end = 2
After Heapify      : [9, 9, 7, 12, 26, 28, 35, 67, 87, 100, 111, 204]
calling heapify on : [7, 9, 9, 12, 26, 28, 35, 67, 87, 100, 111, 204] end = 1
After Heapify      : [9, 7, 9, 12, 26, 28, 35, 67, 87, 100, 111, 204]
calling heapify on : [7, 9, 9, 12, 26, 28, 35, 67, 87, 100, 111, 204] end = 0
After Heapify      : [7, 9, 9, 12, 26, 28, 35, 67, 87, 100, 111, 204]
[7, 9, 9, 12, 26, 28, 35, 67, 87, 100, 111, 204]
 */

public class HeapSort {

	public static void main(String[] args) {
		int arr [] = {12, 35, 87, 111, 26, 9, 28, 7, 100, 67, 9, 204};
		System.out.println(Arrays.toString(arr));
		heapsort(arr);
		System.out.println(Arrays.toString(arr));
	}

	// Step 1: Heapify an Array
	// Step 2. Remove Max Element, reduce array boundry and Heapify again (siftdown actually)
	public static void heapsort(int [] arr){
		int endOfArray = arr.length - 1;
		while(endOfArray >= 0){
			System.out.println("calling heapify on : " + Arrays.toString(arr) + " end = " + endOfArray);
			heapify(arr,  endOfArray);
			System.out.println("After Heapify      : " + Arrays.toString(arr));
			swap(arr, 0, endOfArray);
			endOfArray--;
		}


	}
    
	// input array and end of array
	private static void heapify(int[] arr,  int endofarray)
	{
		int index = getParentIndex(endofarray); // last parent index
		if (!hasLeftChild(index, endofarray))
			return;

		while (hasLeftChild(index, endofarray)){
			int greaterIndex = getLeftChildIndex(index);
			if (hasRightChild(index, endofarray)){
				greaterIndex = (arr[getLeftChildIndex(index)] > arr[getRightChildIndex(index)])? getLeftChildIndex(index) : getRightChildIndex(index);
			}
			if (arr[index] < arr[greaterIndex]){
				swap(arr, index, greaterIndex);
			} 
			index--;
		}
	}


private static void swap(int[] arr, int i, int j) {
	int tmp = arr[i];
	arr[i] = arr[j];
	arr[j] = tmp;
}

private static int getRightChildIndex(int i) {
	return (2*i + 2);
}

private static int getLeftChildIndex(int i) {
	return (2*i + 1);
}

private static int getParentIndex(int i) {
	return (i - 1)/2;
}

private static boolean hasLeftChild(int index, int endofarray) {
	return (index >= 0 && getLeftChildIndex(index)<=endofarray);
}

private static boolean hasRightChild(int index, int endofarray) {
	return (index >= 0 && getRightChildIndex(index)<=endofarray);
}

private boolean hasParent(int index) {
	return (getParentIndex(index)>=0);
}

}
