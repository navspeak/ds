package com.nav.linear;

import java.util.Arrays;

public class MinJumpToReachEnd {

	// 0  1  2  3  4  5  6  7  8  9 - Index
	//==============================
	// 2  3  1  1  2  4  2  0  1  1 - Array
//	   ---
//	      ---------
//	               ---
//	                  ------------
	                  


//	        [0, 1, 1, 2, 2, 3, 3, 4, 4, 4]
//			[-1, 0, 0, 1, 1, 4, 4, 5, 5, 5]
//			No of jumps = 4
//			Indices:
//			9
//			5
//			4
//			1

	
	public static void main(String[] args) {
		int arr[] = {2,3,1,1,2,4,2,0,1,1};
		int jumps[] = new int[arr.length];
		jumps[0] = 0;
		int indices[] = new int[arr.length];
		indices[0] = -1;
		compute(arr, jumps, indices);
		System.out.println(Arrays.toString(jumps));
		System.out.println(Arrays.toString(indices));
		System.out.println("No of jumps = " + jumps[arr.length -1 ]);
		System.out.println("Indices hop:" );
		int index = arr.length - 1;
		while (index > 0) {
			System.out.println(index);
			index = indices[index];
			if (index > arr.length -1 )
				throw new RuntimeException();
		}
	}

	private static void compute(int[] arr, int[] jumps, int[] indices) {
		int n = arr.length;
		for (int i = 1; i < n; i++){
			for (int j = 0; j <i; j++) {
				// can we reach i from j
				if (j + arr[j] >= i) { //yes we can
					if (jumps[i] == 1)
						continue;
					if (jumps[i] == 0 || (jumps[j] + 1 < jumps[i]) ) {
						jumps[i] = jumps[j] + 1;
						indices[i] = j;
					} 
				}
			}
		}


	}

	// 0  1  2  3  4  5  6  7  8  9 - Index
	//==============================
	// 2  3  1  1  2  4  2  0  1  1 - Array
	//	i = 1
	//			j = 0
	//            (if (0+2) >= 1)
	//				jumps   = { 0 , 1}
	//	  			indices = {-1 , 0}
	//	i = 2
	//			j = 0
	//           (if (0+2) >= 2)	
	//				jumps   = { 0 , 1, 1}
	//	  			indices = {-1 , 0, 0}
	//			j = 1
	//            (if (1+3) >= 2)
	//				jumps   = { 0 , 1, 1}
	//  			indices = {-1 , 0, 0, 1}
	//	i = 3
	//			j = 0
	//            (if (0+2) >= 3)
	//			j = 1
	//            (if (1+3) >= 3)
	//				    jumps   = { 0 , 1, 1, 2}
	//  				indices = {-1 , 0, 0, 1}

}
