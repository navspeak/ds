package array;

public class SmallestMissingNumber {
	//http://www.geeksforgeeks.org/find-the-first-missing-number/
	//	Given a sorted array of n distinct integers where each integer is in the range from 0 to m-1 and m > n. Find the smallest number that is missing from the array.
	//
	//	Examples
	//	Input: {0, 1, 2, 6, 9}, n = 5, m = 10
	//	Output: 3
	//
	//	Input: {4, 5, 10, 11}, n = 4, m = 12
	//	Output: 0
	//
	//	Input: {0, 1, 2, 3}, n = 4, m = 5
	//	Output: 4
	//
	//	Input: {0, 1, 2, 3, 4, 5, 6, 7, 10}, n = 9, m = 11
	//	Output: 8


	// F(arr, start, end) - Modified Binary Search
	// O(log n)
	public static int findSmallestMissingNumber(int[] arr, int start, int end){
		if (start != arr[start]) return start; // base case
		int mid = (start + end)/2;
		if (arr[mid] == mid) // left half is all right
			return findSmallestMissingNumber(arr, mid + 1, end);
		else 
			return findSmallestMissingNumber(arr, start, mid);
	}
	
	public static void main(String[] args) {
		int a[] = {0, 1, 2, 3, 4, 5, 6, 7, 10};
		System.out.println(findSmallestMissingNumber(a, 0, a.length -1));
	}
}
