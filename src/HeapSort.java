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
[12, 35, 87, 26, 9, 28, 7]
calling heapify on : [12, 35, 87, 26, 9, 28, 7] index = 2 end = 6
After Heapify : [87, 35, 28, 26, 9, 12, 7]
calling heapify on : [7, 35, 28, 26, 9, 12, 87] index = 2 end = 5
After Heapify : [35, 26, 28, 7, 9, 12, 87]
calling heapify on : [12, 26, 28, 7, 9, 35, 87] index = 1 end = 4
After Heapify : [28, 26, 12, 7, 9, 35, 87]
calling heapify on : [9, 26, 12, 7, 28, 35, 87] index = 1 end = 3
After Heapify : [26, 9, 12, 7, 28, 35, 87]
calling heapify on : [7, 9, 12, 26, 28, 35, 87] index = 0 end = 2
After Heapify : [12, 9, 7, 26, 28, 35, 87]
calling heapify on : [7, 9, 12, 26, 28, 35, 87] index = 0 end = 1
After Heapify : [9, 7, 12, 26, 28, 35, 87]
calling heapify on : [7, 9, 12, 26, 28, 35, 87] index = 0 end = 0
After Heapify : [7, 9, 12, 26, 28, 35, 87]
[7, 9, 12, 26, 28, 35, 87]
 *
 */

public class HeapSort {


	public static void main(String[] args) {
		int arr [] = {12, 35, 87, 26, 9, 28, 7};
		System.out.println(Arrays.toString(arr));
		//heapify(arr, getParentIndex(arr.length - 1), arr.length - 1);
		heapsort(arr);
		System.out.println(Arrays.toString(arr));
		//87, 35, 28, 26, 9, 12, 7
		//7, 35, 28, 26, 9, 12, 87
	}

    // Step 1: 
	public static void heapsort(int [] arr){
		int parentIndex = getParentIndex(arr.length - 1);
		int endOfArray = arr.length - 1;
		while(endOfArray >= 0){
			System.out.println("calling heapify on : " + Arrays.toString(arr) + " index = " + parentIndex + " end = " + endOfArray);
			heapify(arr, parentIndex, endOfArray);
			System.out.println("After Heapify : " + Arrays.toString(arr));
			swap(arr, 0, endOfArray);
			endOfArray--;
			parentIndex = getParentIndex(endOfArray);
		}


	}

	private static void heapify(int[] arr, int index, int endofarray)
	{
		if (!hasLeftChild(index, endofarray))
			return;

		int parentIndex = index;
		while (hasLeftChild(parentIndex, endofarray)){
			int greaterIndex = getLeftChildIndex(parentIndex);
			if (hasRightChild(parentIndex, endofarray)){
				greaterIndex = (arr[getLeftChildIndex(parentIndex)] > arr[getRightChildIndex(parentIndex)])? getLeftChildIndex(parentIndex) : getRightChildIndex(parentIndex);
			}
			if (arr[parentIndex] < arr[greaterIndex]){
				swap(arr, parentIndex, greaterIndex);
				parentIndex =  greaterIndex;
			} else {
				parentIndex--;
			}
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
