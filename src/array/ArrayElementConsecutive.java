package array;

public class ArrayElementConsecutive {
	public static void main(String[] args) {
		int input1[] = { 5, 8, 2, 1 };
		int input2[] = { 2, 4, 2, 1 };
		int input3[] = { 2, 4, 3, 1 };
		int input4[] = { 2, 4, 3, -1 };
		int input5[] = { 0, 1, 2, -1 };
		System.out.println(isConsecutive(input1)); //False
		System.out.println(isConsecutive(input2)); //False
		System.out.println(isConsecutive(input3)); //True
		System.out.println(isConsecutive(input4)); //False
		System.out.println(isConsecutive(input5)); //True
		
	}

	//Time Complexity: O(n)
	//Extra Space: O(n)
	//Works for positive and negative numbers 
	private static boolean isConsecutive(int[] input) {
		
		int[] minmax = getMinAndMax(input);
		int min = minmax[0];
		int max = minmax[1];
		if (max - min + 1 != input.length)
			return false;
		boolean[] visited = new boolean[input.length];
		for (int i = 0; i < input.length; i++){
			int correctIndex = input[i] - min;
			/* If we see an element again, then return false */
			if (visited[correctIndex] == true)
				return false;
			/* If visited first time, then mark the element as visited */
			visited[correctIndex] = true;
		}
		return true;

	}
	
	//Time Complexity: O(n)
	//Extra Space: O(1)
	//Works for positive and negative numbers 
	private static boolean isConsecutive2(int[] input) {
		
		int[] minmax = getMinAndMax(input);
		int min = minmax[0];
		int max = minmax[1];
		if (max - min + 1 != input.length)
			return false;
		// for arr[i], find correct index and make element at that index negative
		// if element at that index is already negative, it means some element has already made it negative
		// so there is a duplicate
		for (int i = 0; i < input.length; i++){
			int correctIndex = Math.abs(input[i]) - min;
			if (input[correctIndex] < 0)
				return false;
			else
				input[correctIndex] = - input[correctIndex];
		}
		return true;

	}
	
//	Find the sum of the array.
//	If given array elements are consecutive that means they are in AP. 
//  So, find min element i.e. first term of AP then calculate ap_sum = n/2 * [2a +(n-1)*d] where d = 1. So, ap_sum = n/2 * [2a +(n-1)]
//	Compare both sums. Print Yes if equal, else No.
//  O(n) and O(1)
//  Better than above solutions, handles both positive and negative cases
	
// Equilibrium Index
//1) Initialize leftsum  as 0
//	2) Get the total sum of the array as sum
//	3) Iterate through the array and for each index i, do following.
//	    a)  Update sum to get the right sum.  
//	           sum = sum - arr[i] 
//	       // sum is now right sum
//	    b) If leftsum is equal to sum, then return current index. 
//	    c) leftsum = leftsum + arr[i] // update leftsum for next iteration.
//	4) return -1 // If we come out of loop without returning then
//	             // there is no equilibrium index
//	http://www.geeksforgeeks.org/equilibrium-index-of-an-array/

// Print Duplicate in an array (making negative approach)
//	http://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/

	private static int[] getMinAndMax(int[] input) {
		int min = input[0];
		int max = input[0];
		for (int i = 1; i < input.length; i++){
			if (input[i] < min) min= input[i];
			if (input[i] > max) max= input[i];		
		}
		int[] minMax = new int[2];
		minMax[0] = min;
		minMax[1] = max;
		return minMax;
	}

}
