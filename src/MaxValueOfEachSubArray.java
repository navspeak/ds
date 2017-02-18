import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


// The time complexity of this algorithm is O(n) 
// Note that in this algorithm every element is compared at most twice for remove operation from the rear-end 
// and every element on an average is compared only once for remove operation from the front-end of the 'list'.
// Therefore, for every element of the array there are constant number of operations that are performed on it making time complexity as O(n). 
// The extra space used by this algorithm is O(k). 

// This can also be solved using AVL tree.
//1. Create a BST from first 'k' elements of the input array.
//2. Find node with maximum value from the BST created in step #1. Print this node's value. 
//2. This would represent an element with maximum value from first sub-array of size 'k'.
//4. Now starting from i = 0 upto i = n-k-1
//    a. Search and delete element with value array[i] from the BST.
//    b. Insert node with value as array[i+k] into the BST. Now this BST represents next sub-array of size 'k'.
//    c. Find node with maximum value from the BST. Print this node's value.
// Time Complexity is O(nlogk), n: size of input array, k: sub-array size
// Space Complexity is O(k)

public class MaxValueOfEachSubArray {

	public static void main(String[] args) {
		int arr[] = {9,6,11,8,10,5,14,13,93,14};
	/*	9,6,11,8      = 11
		6,11,8,10     = 11
		11,8,10,5     = 11
		8,10,5,14     = 14
		10,5,14,13    = 14
		5,14,13,93    = 93
		14,13,93,14   = 93 */
		
		List<Integer> output = MaxValueOfEachSubArray(arr, 4);
		System.out.println(Arrays.toString(output.toArray()));
		
		List<Integer> output2 = MaxValueOfEachSubArray2(arr, 4);
		System.out.println(Arrays.toString(output2.toArray()));
	}

	// O(N) time and O(K) space - use Dequeue
	public static List<Integer> MaxValueOfEachSubArray(int[] arr, int size) {
		Deque<Integer> list = new ArrayDeque<Integer>();
		List<Integer> output = new ArrayList<Integer>();
		// Step 1: Adding indices to the list for first 'k' elements
		// End of this step, the first of the list will contain max of first k elemens
		for (int i = 0 ; i < size; i++){
			//1.Remove unuseful element i.e if arr[i] > arr[list.getLast()]
			//2. add i to list
			while (!list.isEmpty() && arr[i] > arr[list.getLast()])
				list.removeLast();
			list.addLast(i);
		}
		// Step 2: Adding indices one by one to the list for rem. elems.
		for (int i = size; i < arr.length; i++){
			//1. Add list.front to the list
			//2. Check if list.front belongs to the list
			//3. check if arr[i] > list.end. If yes remove
			//4. add i to list
			output.add(arr[list.getFirst()]);
			// for i = 5 and k =4 the current window will include
			// indices 2,3,4,5, so remove if it doesn't belong
			while (!list.isEmpty() && list.getFirst() < i -size + 1){
				list.removeFirst();
			}
			while (!list.isEmpty() && arr[i] > arr[list.getLast()])
				list.removeLast();
			list.addLast(i);
		}
		output.add(arr[list.getFirst()]);
		return output;
	}

	// O(Nlogk) time and O(K) space - use AVL (in fact Red Black tree as TreeSet uses Red Black Tree)
	public static List<Integer> MaxValueOfEachSubArray2(int[] arr, int size) {
		SortedSet<Integer> set = new TreeSet<Integer>();
		List<Integer> output = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			set.add(arr[i]);
		}
		
		for (int i = size; i < arr.length; i++) {
			output.add(set.last());
			set.remove(arr[i-size]);
			set.add(arr[i]);
		}	
		
		output.add(set.last());
		return output;
	}

}
