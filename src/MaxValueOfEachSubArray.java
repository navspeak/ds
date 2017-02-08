import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;


// The time complexity of this algorithm is O(n) 
// Note that in this algorithm every element is compared at most twice for remove operation from the rear-end 
// and every element on an average is compared only once for remove operation from the front-end of the 'list'.
// Therefore, for every element of the array there are constant number of operations that are performed on it making time complexity as O(n). 
// The extra space used by this algorithm is O(k). 


public class MaxValueOfEachSubArray {

	public static void main(String[] args) {
		int arr[] = {9,6,11,8,10,5,14,13,93,14};
		List<Integer> output = MaxValueOfEachSubArray(arr, 4);
		System.out.println(Arrays.toString(output.toArray()));
	}

	private static List<Integer> MaxValueOfEachSubArray(int[] arr, int size) {
		Deque<Integer> list = new ArrayDeque<Integer>();
		List<Integer> output = new ArrayList<Integer>();
		// Step 1:
		for (int i = 0 ; i < size; i++){
			//1.Remove unuseful element i.e if arr[i] > arr[list.getLast()]
			//2. add i to list
			while (!list.isEmpty() && arr[i] > arr[list.getLast()])
				list.removeLast();
			list.addLast(i);
		}
		// Step 2:	
		for (int i = size; i < arr.length; i++){
			//1. Add list.front to the list
			//2. Check if list.front belongs to the list
			//3. check if arr[i] > list.end. If yes remove
			//4. add i to list
			output.add(arr[list.getFirst()]);
			while (!list.isEmpty() && list.getFirst() < i -size + 1){
				list.removeFirst();
			}
			while (!list.isEmpty() && arr[i] > arr[list.getLast()])
				list.removeLast();
			list.addLast(i);
		}
		return output;
	}

}
