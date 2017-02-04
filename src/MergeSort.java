import java.util.Arrays;


public class MergeSort {

	public static void main(String[] args) {
		int[] array = new int[] {15,3,9,8,5,2,7,1};
		System.out.println("Original Array : " + Arrays.toString(array));
		mergesort(array);
		System.out.println("Sorted Array : " + Arrays.toString(array));

	}

	private static void mergesort(int[] array) {
		mergesort(array, new int[array.length], 0, array.length - 1);
	}

	private static void mergesort(int[] array, int[] temp, int leftStart, int rightEnd) {
		if(leftStart >= rightEnd) return;

		int mid = (leftStart + rightEnd)/2;
		mergesort(array, temp, leftStart, mid);
		mergesort(array, temp, mid + 1, rightEnd);
		merge(array, temp, leftStart, rightEnd);
	}

	private static void merge(int[] array, int[] temp, int leftStart, int rightEnd) {
		int leftEnd = (leftStart + rightEnd)/2;
		int rightStart = leftEnd + 1;
		int size = rightEnd - leftEnd + 1;
		
		int left = leftStart;
		int right = rightStart;
		int index = leftStart;
		
		while (left <= leftEnd && right <= rightEnd){
			if (array[left] <= array[right]){
				temp[index] = array[left];
				left++;
			} else {
				temp[index] = array[right];
				right++;
			}
			index++;
		}
		System.arraycopy(array, left, temp, index, leftEnd - left + 1);
		System.arraycopy(array, right, temp, index, rightEnd - right + 1);
		System.arraycopy(temp, leftStart, array, leftStart, size);
	}

}
