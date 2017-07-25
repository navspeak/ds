package DP;

public class LongestCommonSubSequence {
    
	// O(mn) space O(mn) time
	public static void main(String[] args) {
		String s1 = "ABCDAF";
		String s2 = "ACDCF";
		int[][] lcstable= new int[s1.length() + 1][s2.length() + 1];
		int max = populateLCSTable(lcstable, s1.toCharArray(), s2.toCharArray());
		System.out.println("Length of Longest common Subsequence is " + max);
		printSubSequence(lcstable, s1.toCharArray());

	}
	
	// table:
	//     0 1 2 3 4 5  .... s2.len = 2 here 
	//     - A C D C F
	// 0 - 0 0 0 0 0 0 
	// 1 A 0 1 1 1 1 1 
	// 2 B 0 1 1 1 1 1 
	// 3 C 0 1 2 2 2 2 
	// 4 D 0 1 2 3 3 3 
	// 5 A 0 1 2 3 3 3 
	// 6 F 0 1 2 3 3 4	
	// .
	// .
	// s1.len = 6 here

	private static void printSubSequence(int[][] t, char[] s) {
		int row = t.length - 1;
		int col = t[0].length -1 ;
		while (row > 0) {
			if (t[row][col]!=t[row-1][col-1]){
				System.out.println(s[row-1]);
				// go diagonal
				row--; col --;
			} else if (t[row][col -1] >= t[row -1][col]){
				// go left; (row is given preference if we are using first string)
				row --;
			} else {
				// go up
				col --;
			}
			if (row <= 0 || col <= 0)
				break;
			
		}
	}

	private static int populateLCSTable(int[][] t, char[] s1, char[] s2) {
		int max = 0;
		for (int i = 1; i < t.length; i++){
			for (int j = 1; j < t[0].length; j++){
				int indexIntoS1 = i - 1;
				int indexIntoS2 = j - 1;
				if (s1[indexIntoS1] == s2[indexIntoS2])
					t[i][j] = 1+ t[i-1][j-1];
				else
					t[i][j] = Math.max(t[i-1][j], t[i][j-1]);
				if (t[i][j] > max )
					max = t[i][j];
			}
		}
		return max;
	}
}
