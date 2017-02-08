//Kadane's algo
//O(n)
public class MaxSubArraySum {
	public static void main(String[] args) {
		int[] arr = {2, -9, 5, 1, -4, 6, 0, -7, 8};
		System.out.println(findMaxSubArraySum(arr));
	}

	private static int findMaxSubArraySum(int[] arr) {
		int size = arr.length;
		int max_so_far = arr[0];
		int curr_max = arr[0];
		for (int i = 1; i < size ; i++){
			curr_max = Math.max(arr[i], curr_max + arr[i]);
			max_so_far = Math.max(max_so_far, curr_max);
		}
		return max_so_far;
	}

}
