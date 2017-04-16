
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

// Instead of Heap we use TreeMap which gives us flexibility 
// to compute mean, median, mode together 
public class MeanMedianMode {

	static class Bucket {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		int count = 0; 
		Integer modeKey = null;
		int modeVal = 0;
		int sum = 0;
		boolean reversibleOrder = false;//stores elements like a max heap

		public void add(int num) {
			map.compute(num, (key, value) -> {
				int retVal;
				if (value == null){
					retVal = 1;
				}
				else {
					retVal = value + 1;
				}
				if (modeKey == null || (retVal > modeVal) ){
					modeKey = key;
					modeVal = retVal;
				}
				sum = sum + key;
				count++;
				return retVal;
			});
		}

		public int peek(){
			if (reversibleOrder) {//MaxHeap kind of behavior
				return map.lastKey();
			}
			else
				return map.firstKey();
		}

		public int remove(){
			int retNum;
			if (reversibleOrder) {//MaxHeap kind of behavior
				retNum = map.lastKey();
			}
			else
				retNum = map.firstKey();

			map.compute(retNum, (key, value) -> {
				Integer retVal = null;
				if (key == modeKey)
					modeVal = modeVal - 1;
				if (value > 1) {
					retVal = value -1;
				} 
				sum = sum -key;
				count--;
				return retVal;	
			});

			return retNum;
		}

	}

	public static void compute() throws FileNotFoundException{

		//Scanner in = new Scanner(new File("C:/input.txt"));
		Scanner in = new Scanner("11 2 6 7 1 6 11 7 1 1 8 2 2 2 2");
		//1 1 1 2 2 2 2 2 6 6 7 7 8 11 11
		Bucket lowers = new Bucket();
		lowers.reversibleOrder = true;
		Bucket highers = new Bucket();
		while (in.hasNext()){
			if (in.hasNextInt()) {
				int num = in.nextInt();
				System.out.println("Adding ->" + num);
				add(num, lowers, highers);
				balance(lowers, highers);
				printMeanMedianMode(lowers, highers);

			} 
		}
	}

	private static void printMeanMedianMode(Bucket lowers, Bucket highers) {
		Bucket biggerBucket = lowers.count > highers.count ? lowers : highers;
		Bucket smallerBucket = lowers.count > highers.count ? highers : lowers;
		double mean = 0;
		double median = 0;
		int mode = 0;
		// Mean calculation
		int totalCount = (biggerBucket.count > 0 ? biggerBucket.count : 0 ) +
				(smallerBucket.count > 0 ? smallerBucket.count : 0 );
		if (totalCount == 0)
			throw new RuntimeException("Total Count should not be zero");
		mean = (biggerBucket.sum + smallerBucket.sum) / (double)totalCount;
		
		//median calculation
		if (biggerBucket.count == smallerBucket.count)
			median = ( (double)biggerBucket.peek() + smallerBucket.peek() )/ 2.0;
		else
			median = biggerBucket.peek();
		
		// mode calculation
		mode = (biggerBucket.modeKey == smallerBucket.modeKey) ? (biggerBucket.modeVal + smallerBucket.modeVal):
			Math.max(biggerBucket.modeVal, smallerBucket.modeVal);

		System.out.print("Median = " + median);
		System.out.print(" Mode = " + mode);
		System.out.print(" Mean = " + mean);
		System.out.println();
		System.out.println("==============================");

	}

	private static void balance(Bucket lowers, Bucket highers) {
		Bucket biggerBucket = lowers.count > highers.count ? lowers : highers;
		Bucket smallerBucket = lowers.count > highers.count ? highers : lowers;
		if (biggerBucket.count - smallerBucket.count > 1)
			smallerBucket.add(biggerBucket.remove());
	}

	private static void add(int num, Bucket lowers, Bucket highers) {
		if (lowers.count == 0)
			lowers.add(num);
		else {
			if (num > lowers.peek())
				highers.add(num);
			else
				lowers.add(num);
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		MeanMedianMode.compute();
		//		Bucket bucket = new Bucket();
		//		bucket.add(10);
		//		bucket.add(5);
		//		bucket.add(10);
		//		bucket.add(3);
		//		bucket.add(10);
		//		bucket.add(9);
		//		bucket.add(1);
		//		bucket.add(9);
		//		bucket.add(10);
		//		bucket.reversibleOrder = true;
		//		System.out.println("");
		//		bucket.remove();
	}

}
