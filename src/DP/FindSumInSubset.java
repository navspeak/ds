import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//Given a set of non-negative integers, and a value sum, 
//determine if there is a subset of the given set with sum equal to given sum.

//Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
//Output:  True  //There is a subset (4, 5) with sum 9.

public class FindSumInSubset {

	public static void main(String[] args) {
		int [][] setArray = {{1, 2, 4, 5, 9}, {3, 34, 4, 12, 5, 2}};
		int sum = 15;

		System.out.println(hasSubSetSum(setArray[0],15));
		System.out.println(hasSubSetSum(setArray[0],9));
		System.out.println(hasSubSetSum(setArray[1],15));
		System.out.println(hasSubSetSum(setArray[1],8));
	}

	private static boolean hasSubSetSum(int[] input, int sum) {
		// Set up a truth matrix of 
		// rows = (input.len + 1) Plus 1 for empty set
		// and col = (sum + 1) Plus 1 as we consider 0 to sum

		// For input = { 1, 20, 3, 6 } and sum = 7
		// Truth table looks like:
		//       | 0 1 2 3 4 5 6 7
		//------------------------
		//  0  0 | T F F F F F F F
		//  1  1 | T F F F F F F F
		//  2 20 | T F F F F F F F
		//  3  3 | T F F F F F F F
		//  4  6 | T F F F F F F F
		
		// NOTE: since we have added 0, while looking at current element from input use input[row - 1]

		// First column will have all false except for input[0][0] because empty set can't add up to any number > 0
		// First row will have all true because an empty set can add up to zero
		// Start from row = 1 and col = 1 matrix[row][col] = matrix[row -1][col] || matrix[row - 1][col - input[row - 1]]
		
		// Time and space Complexity : O(N*sum). Is solved by Dynamic programming and is has pseudo-polynomial time complexity

		boolean [][] matrix = new boolean[input.length + 1][sum + 1]; 

		for (int i = 0; i <= input.length ; i++)
			matrix[i][0] = true;

		for (int row = 1; row <= input.length ; row++){
			for (int col = 1; col <= sum; col++){
				
				matrix[row][col] = matrix[row -1][col];
				
				if (matrix[row][col] == false && col >= input[row - 1]) // row - 1 because we added 0
					matrix[row][col] = matrix[row -1][col - input[row - 1]];
			}
		}

        System.out.println("========================================");
		System.out.println("Input : " + Arrays.toString(input));
		for (boolean[] boolItr : matrix){
			System.out.print("\n  ");
			for (boolean b : boolItr){
				if (b){
					System.out.print(1 + " ");
				} else {
					System.out.print(0 + " ");				
				}

			}
		}

		boolean hasSubsetSum = matrix[input.length ][sum];
		int row = input.length;
		int col = sum;
		List<Integer> subset = new ArrayList<Integer>();
		if (hasSubsetSum){
			while (row > 0) {
				if ( matrix[row][col]) {
					if (!matrix[row - 1][col] ){
						subset.add(input[row - 1]);
						col = col - input[row - 1];
					}
				}
				row--;
			}
		}
		System.out.println();
		System.out.println(sum + " : " + Arrays.toString(subset.toArray()));
        System.out.println("========================================");
		return hasSubsetSum;

		//Another variation is given an array is it possible to split it up into 2 equal
		// sum partitions. Partition need not be equal sized. Just equal sum.
		// in this case we calculate the sum of all the numbers in input. SUM = input[0] + input[1] +...+ input[n]
		// if SUM % 2 ! = 0, equal sum partition is not possible
		// Other wise return matrix[input.length][SUM/2]
		
	}
	// Sample outputs:
/*	========================================
			Input : [1, 2, 4, 5, 9]

			  1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
			  1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
			  1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 
			  1 1 1 1 1 1 1 1 0 0 0 0 0 0 0 0 
			  1 1 1 1 1 1 1 1 1 1 1 1 1 0 0 0 
			  1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 
			15 : [9, 4, 2]
			========================================
			true
			========================================
			Input : [1, 2, 4, 5, 9]

			  1 0 0 0 0 0 0 0 0 0 
			  1 1 0 0 0 0 0 0 0 0 
			  1 1 1 1 0 0 0 0 0 0 
			  1 1 1 1 1 1 1 1 0 0 
			  1 1 1 1 1 1 1 1 1 1 
			  1 1 1 1 1 1 1 1 1 1 
			9 : [5, 4]
			========================================
			true
			========================================
			Input : [3, 34, 4, 12, 5, 2]

			  1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
			  1 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 
			  1 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 
			  1 0 0 1 1 0 0 1 0 0 0 0 0 0 0 0 
			  1 0 0 1 1 0 0 1 0 0 0 0 1 0 0 1 
			  1 0 0 1 1 1 0 1 1 1 0 0 1 0 0 1 
			  1 0 1 1 1 1 1 1 1 1 1 1 1 0 1 1 
			15 : [12, 3]
			========================================
			true
			========================================
			Input : [3, 34, 4, 12, 5, 2]

			  1 0 0 0 0 0 0 0 0 
			  1 0 0 1 0 0 0 0 0 
			  1 0 0 1 0 0 0 0 0 
			  1 0 0 1 1 0 0 1 0 
			  1 0 0 1 1 0 0 1 0 
			  1 0 0 1 1 1 0 1 1 
			  1 0 1 1 1 1 1 1 1 
			8 : [5, 3]
			========================================*/

}
