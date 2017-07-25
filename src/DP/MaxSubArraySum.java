package DP;
import java.util.ArrayList;
import java.util.List;

//Kadane's algo
//O(n)
public class MaxSubArraySum {
	static int [] F = new int[30];
	public static void main(String[] args) {
		int[] arr = {2, -9, 5, 1, -4, 6, 0, -7, 8};
		// For all positive nos. we don't need kadane's. We 
		// can simply add all nos.
		System.out.println(findMaxSubArraySum(arr));
		System.out.println(func(arr));
	}

	private static int findMaxSubArraySum(int[] arr) {
		int size = arr.length;
		int global_max = arr[0];//global max
		int local_max = arr[0]; 
		List<Integer> contributingElements = new ArrayList<>();
		System.out.println("Init: Curr max = " + local_max + " Global Max = " + global_max);
		for (int i = 1; i < size ; i++){
			System.out.println("Starting at index  " + i + " Curr Element = " + arr[i]);
			System.out.println("Compare curr elem and curr elem + arr[i]  i.e. " +  arr[i] + " with " + local_max + arr[i]);
			// local_max is current element or current elem + curr max till now
			local_max = Math.max(arr[i], local_max + arr[i]);
			//if curr_max > global_max, update global max
			global_max = Math.max(global_max, local_max);

			System.out.println("Thus: Curr max = " + local_max + " Global Max = " + global_max);
		}
		return local_max;
	}


	public static int func(int[] arr) {

		//F[0] = arr[0];
		int sumEndingHere = arr[0];
		//int max = arr[0];
		int sumSoFar = arr[0];
		for (int n = 1; n < arr.length; n++){
			//F[n] = Math.max(F[n-1] + arr[n], arr[n]);
			sumEndingHere = Math.max(sumEndingHere + arr[n], arr[n]);
			//if (F[n] > max) max =  F[n];
			if (sumEndingHere > sumSoFar)  sumSoFar = sumEndingHere;
		}
		return sumSoFar;
	}

}
