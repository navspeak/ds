import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;


public class QuickSort {

    /**
	 * Quick sort
	 * -------------
	 * O(NlogN) - best  - partion is in the middle [n/2] p [n/2] or [n/2] p [n/2+/-1]
	 * O(N2) - worst - [0] p [n]
	 * Non-Adaptive
	 * not stable sort
	 * https://www.khanacademy.org/computing/computer-science/algorithms/quick-sort/a/analysis-of-quicksort
	 */

	public static void quicksort(int[] array){
		quicksort(array, 0, array.length - 1);
	}

	public static void quicksort(int [] array, int left, int right){
		// 1. Check if left >= right; return
		// 2. Find a pivot = element [(left+right)/2]
		// 3. call partition and obtain the partitionIndex
		// 4. call quicksort on left partition
		// 5. call quicksort on right partion

		if (left >= right) return;

		int pivot = array[(left + right)/2];

		int pivotIndex = partitionVerbose(array, left, right, pivot);
		//int pivotIndex = partition(array, left, right, pivot);

		quicksort(array, left, pivotIndex - 1 );

		quicksort(array,  pivotIndex, right);


	}

	public static int partition(int [] array, int left, int right, int pivot){

		// Loop : while left <= right
		// Move left to the spot where array[left] > pivot
		// Move right to the spot where array[right] < pivot
		// it could be that left has crossed right so check if left <= right
		// swap and left++ and right++

		while (left <= right){
			while (array[left] < pivot) left++;
			while (array[right] > pivot) right--;
			if (left <= right){
				swap(array, left, right);
				left++;
				right--;
			}
		}
		return left;
	}


	private static void swap(int[] array, int left, int right) {
		int tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;
	}

	public static void main(String[] args) {
		int [] array = new int[] {15,3,9,8,5,2,7,1};
		System.out.println("Original Array : " + Arrays.toString(array));
		quicksort(array);
		System.out.println("Sorted Array : " + Arrays.toString(array));
	}
	//===================================================================================

	// Partition with verbose logging to explain the partition step
	public static int partitionVerbose (int [] array, int left, int right, int pivot){
		// 0. Loop: till Left <= Right (Do it till left doesn't cross right)
		// 1. move left till element at left needs swapping i.e. Element[left] > pivot
		// 2. move Right till element at left needs swapping i.e. Element[Right] < pivot
		// 3. Swap and move left and right pointers
		// return left
		System.out.println("===============================================");
		System.out.println("Left = " + left + " Right = " + right + " and Pivot = " + pivot);
		StringBuilder sb = new StringBuilder();
		int leftFixed = left;
		int rightFixed = right;
		sb.append("Working on Partition [");
		for (int i = left ; i <= right; i++ )
			sb.append(array[i] +  ", ");
		System.out.print(sb.append("]").toString().replaceAll(", ]", ""));
		System.out.print("]\n");
		while (left <= right){
			while (array[left] < pivot) {
				System.out.println("        " + array[left] + " <= " + pivot) ;
				left++;
				System.out.println("        " + "Thus, left++ " + left);
			}
			while (array[right] > pivot) {
				System.out.println("        " + array[right] + " > " + pivot) ;
				right--;
				System.out.println("        Thus right++ " + right);
			}
			System.out.println("Left = " + left + " and Right = " + right);
			if (left <= right){
				System.out.println("Swaping " + array[left]  + " and " + array[right]);
				swap (array, left, right);
				System.out.println("Array          : " + Arrays.toString(array));
				left++;
				right--;
				System.out.println("Left = " + left + " and Right = " + right);
			}

		}

		sb = new StringBuilder();
		sb.append("After the Partition [");
		for (int i = leftFixed ; i <= rightFixed; i++ )
			sb.append(array[i] +  ", ");
		System.out.print(sb.append("]").toString().replaceAll(", ]", ""));
		System.out.print("]\n");
		System.out.println("Returning " + left);
		System.out.println("===============================================");
		return left;
	}

	/*
	 * Explanation with Example
	 * 
Original Array : [15, 3, 9, 8, 5, 2, 7, 1]
===============================================
Left = 0 Right = 7 and Pivot = 8
Working on Partition [15, 3, 9, 8, 5, 2, 7, 1]
Left = 0 and Right = 7
Swaping 15 and 1
Array          : [1, 3, 9, 8, 5, 2, 7, 15]
Left = 1 and Right = 6
        3 <= 8
        Thus, left++ 2
Left = 2 and Right = 6
Swaping 9 and 7
Array          : [1, 3, 7, 8, 5, 2, 9, 15]
Left = 3 and Right = 5
Left = 3 and Right = 5
Swaping 8 and 2
Array          : [1, 3, 7, 2, 5, 8, 9, 15]
Left = 4 and Right = 4
        5 <= 8
        Thus, left++ 5
Left = 5 and Right = 4
After the Partition [1, 3, 7, 2, 5, 8, 9, 15]
Returning 5
===============================================
===============================================
Left = 0 Right = 4 and Pivot = 7
Working on Partition [1, 3, 7, 2, 5]
        1 <= 7
        Thus, left++ 1
        3 <= 7
        Thus, left++ 2
Left = 2 and Right = 4
Swaping 7 and 5
Array          : [1, 3, 5, 2, 7, 8, 9, 15]
Left = 3 and Right = 3
        2 <= 7
        Thus, left++ 4
Left = 4 and Right = 3
After the Partition [1, 3, 5, 2, 7]
Returning 4
===============================================
===============================================
Left = 0 Right = 3 and Pivot = 3
Working on Partition [1, 3, 5, 2]
        1 <= 3
        Thus, left++ 1
Left = 1 and Right = 3
Swaping 3 and 2
Array          : [1, 2, 5, 3, 7, 8, 9, 15]
Left = 2 and Right = 2
        5 > 3
        Thus right++ 1
Left = 2 and Right = 1
After the Partition [1, 2, 5, 3]
Returning 2
===============================================
===============================================
Left = 0 Right = 1 and Pivot = 1
Working on Partition [1, 2]
        2 > 1
        Thus right++ 0
Left = 0 and Right = 0
Swaping 1 and 1
Array          : [1, 2, 5, 3, 7, 8, 9, 15]
Left = 1 and Right = -1
After the Partition [1, 2]
Returning 1
===============================================
===============================================
Left = 2 Right = 3 and Pivot = 5
Working on Partition [5, 3]
Left = 2 and Right = 3
Swaping 5 and 3
Array          : [1, 2, 3, 5, 7, 8, 9, 15]
Left = 3 and Right = 2
After the Partition [3, 5]
Returning 3
===============================================
===============================================
Left = 5 Right = 7 and Pivot = 9
Working on Partition [8, 9, 15]
        8 <= 9
        Thus, left++ 6
        15 > 9
        Thus right++ 6
Left = 6 and Right = 6
Swaping 9 and 9
Array          : [1, 2, 3, 5, 7, 8, 9, 15]
Left = 7 and Right = 5
After the Partition [8, 9, 15]
Returning 7
===============================================
===============================================
Left = 5 Right = 6 and Pivot = 8
Working on Partition [8, 9]
        9 > 8
        Thus right++ 5
Left = 5 and Right = 5
Swaping 8 and 8
Array          : [1, 2, 3, 5, 7, 8, 9, 15]
Left = 6 and Right = 4
After the Partition [8, 9]
Returning 6
===============================================
Sorted Array : [1, 2, 3, 5, 7, 8, 9, 15]

	 */

}
