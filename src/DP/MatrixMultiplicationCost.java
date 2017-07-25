package DP;

public class MatrixMultiplicationCost {
	
	// P = [30, 5, 10, 10, 30] 
	// 30x5,5x10,10*10,10*30
	
	// m[i,j] = m[i,k] + m[k+1,j] + P[i-1]P[k]P[j] where i<=k<j
	//m[i,j] = cost of multiplying Ai...Aj
	
	// How many scalar multiplication does it take to get A1 or A2 or ... ?
	// A1 = m[1,1] = 0, A2 = m[2,2] = 0, A3 = m[3,3], A4 = m[4,4]=0 
	
	//Fill the table for i=1,j=2 i=2,j=3 ...
	// i=1;j=2 (k=1,2)....
	
	
//	   0 1 2 3 4
//   0 0 v v v v 
//   1 - 0 v v v
//   2 - - 0 v v
//   3 - - - 0 v
//   4 - - - - 0
	
	
	 public static void main(String args[]){
	        MatrixMultiplicationCost mmc = new MatrixMultiplicationCost();
	        //Find the multiplication cost of 
	        int arr[] = {4,2,3,5,3};
	        int cost = mmc.findCost(arr);
	        System.out.print(cost);
	    }
//O(n3) - time, o(n2) - space
	private int findCost(int[] arr) {
		int temp[][] = new int[arr.length][arr.length];
        int q = 0;
        for(int l=2; l < arr.length; l++){
            for(int i=0; i < arr.length - l; i++){
                int j = i + l;
                temp[i][j] = 1000000;
                for(int k=i+1; k < j; k++){
                    q = temp[i][k] + temp[k][j] + arr[i]*arr[k]*arr[j];
                    if(q < temp[i][j]){
                        temp[i][j] = q;
                    }
                }
            }
        }
        return temp[0][arr.length-1];
	}

}
