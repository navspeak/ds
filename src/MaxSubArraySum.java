//Kadane's algo
//O(n)
public class MaxSubArraySum {
	public static void main(String[] args) {
		int[] arr = {2, -9, 5, 1, -4, 6, 0, -7, 8};
		// For all positive nos. we don't need kadane's. We 
		// can simply add all nos.
		System.out.println(findMaxSubArraySum(arr));
	}

	private static int findMaxSubArraySum(int[] arr) {
		int size = arr.length;
		int global_max = arr[0];//global max
		int local_max = arr[0]; 
		for (int i = 1; i < size ; i++){
			// local_max is current element or current elem + curr max till now
			local_max = Math.max(arr[i], local_max + arr[i]);
			//if curr_max > global_max, update global max
			global_max = Math.max(global_max, local_max);
		}
		return local_max;
	}

}
