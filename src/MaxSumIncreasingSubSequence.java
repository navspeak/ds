package com.nav.linear;

import java.util.Arrays;

public class MaxSumIncreasingSubSequence {
//	
//			Init Max Sum Array : [4, 6, 1, 3, 8, 4, 6]
//			Init Indices Array : [0, 1, 2, 3, 4, 5, 6]
//			=======================
//			Final Max Sum Array : [4, 10, 1, 4, 18, 8, 14]
//			Final Indices Array : [0, 0, 2, 2, 1, 3, 5]
//			Max Sum is 18
//			Contributing members 
//			Index : 4 value = 8
//			Index : 1 value = 6

	public static void main(String[] args) {
		int[] arr = {4,6,1,3,8,4,6};
		int [] sum = Arrays.copyOf(arr, arr.length);
		int [] indices = new int[arr.length];
		for (int i = 0 ; i< arr.length; i++){
			indices[i] = i;
		}
		
		System.out.println("Actual Array : " + Arrays.toString(arr));
		System.out.println("Init Max Sum Array : " + Arrays.toString(sum));
		System.out.println("Init Indices Array : " + Arrays.toString(indices));
		System.out.println("=======================");
		compute(arr, sum, indices);
		System.out.println("Final Max Sum Array : " + Arrays.toString(sum));
		System.out.println("Final Indices Array : " + Arrays.toString(indices));
		
		int max = sum[0];
		int maxIndex = 0;
		for (int i = 0; i < arr.length; i++){
			if (max < sum[i]){
				maxIndex = i;
				max = sum[i];
			}
		}
		
		System.out.println("Max Sum is " + max);
		System.out.println("Contributing members ");
		int index = maxIndex;
		while (index > 0) {
			System.out.println("Index : " + index + " value = " + arr[index]);
			index = indices[index];
			if (index >= arr.length || index < 0 ){
				throw new RuntimeException();
			}
		 }
	}

	private static void compute(int[] arr, int[] sum, int[] indices) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i ; j++){
				if (arr[j] < arr[i]) { // it is a part of of increasing subsequence
					if (sum[j] + arr[i] > sum[i]){
						sum[i] = sum[j] + arr[i];
					    indices[i] = j;
					}
				}
			}
		}
		
	}

}
