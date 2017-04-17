import java.util.Arrays;


public class ArrayProblems {
	
//Given an array of stock prices, find the maximum profit that can be earned by doing a single transaction of buy and sell in the given period of time.
//	e.g:
//		100, 80, 120, 130, 70, 60, 100, 125
//
//		Profit: 65
	
	public static int findMaxProfit1(int[] buyingPrice){
		int minBuyingPrice = Integer.MAX_VALUE;
		int profit = 0;
		
		for (int i = 0; i < buyingPrice.length; i++){
			profit = Math.max(profit, buyingPrice[i] - minBuyingPrice);
			minBuyingPrice = Math.min(minBuyingPrice, buyingPrice[i]);
		}
		return profit;
	}


// Kadane's algo
	public static int findMaxSubArraySum(int[] arr){
		
		int curr_max = arr[0];
		int global_max = arr[0];
		
		for (int i = 0; i < arr.length; i++){
			curr_max = Math.max(arr[i], curr_max + arr[i]);
			global_max = Math.max(global_max, curr_max);
		}
				
		return global_max;
	}
	
	public static void main(String[] args) {
		int [] arr = {100, 80, 120, 130, 70, 60, 100, 125};
		System.out.println(findMaxProfit1(arr));
		
		int[] arr1 = {2, -9, 5, 1, -4, 6, 0, -7, 8};
		System.out.println(findMaxSubArraySum(arr1));
	}

}
