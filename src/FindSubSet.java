import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class FindSubSet {
	
	public static void populateSubSetList(List<Integer> origList, List<List<Integer>> subsetList){
		//If original List is empty add an empty set and return - Base case
		//Remove one element from original List and store it as currElement
		//call populateSubSetList with the changed original list and subsetlist
		//Now we will iterate thru the subsetlist and add the current Element
		
		//assume the Original List is [1,2,3]
		//On first back tracking the subsetList will be [[]] 
		//   The current Element will be 3, so we get [[3],[]]
		
		//On second back tracking the subsetList will be [[],[3]]
		//   The current Element will be 2, so we get [[2],[2,3],[],[3]]
		
		//On third back tracking the subsetList will be  [[2],[2,3],[],[3]]
		//   The current Element will be 1, so we get [[2,1],[2,3,1],[1],[3,1],[2],[2,3],[],[3]]
		
		// Time Complexity is O(2^n)
				
		
		if (origList.isEmpty()) {
			subsetList.add(new ArrayList<Integer>());
			return;
		}
		
		Integer currElem = origList.remove(0);
		populateSubSetList(origList, subsetList);
		List<List<Integer>> listToIterateThru = new ArrayList<List<Integer>>();
		listToIterateThru.addAll(subsetList);
		for(List<Integer> iter: listToIterateThru){
			List<Integer> tmp = new ArrayList<Integer>();
			tmp.add(currElem);
			tmp.addAll(iter);
			subsetList.add(tmp);
		}
	}
	
	private static void print(List<List<Integer>> subsetlist) {
		for(List<Integer> itr : subsetlist){
			Collections.sort(itr);
			System.out.print("{");
			for(int i : itr){
				System.out.print(i + ",");
			}
			System.out.println("}");
		}
	}
	
	public static void populateSubsetBinary(List<Integer> origList, List<List<Integer>> subsetlist){
		
		// start from i = 0 we generate binary till 2^n - 1, where n = origList.length
		// for each i, check which bit is set
		// e.g. 3 in binary 0101 has 0th and 2nd bit set
		// so  {a[0],a[2]} is one set
		int n = origList.size();
		for (int i = 0; i < (1<< n); i++){
			List<Integer> subset = new ArrayList<Integer>();
			for (int j = 0; j< n; j++) {
				int checkBit = 1 << j;
				if ((i & checkBit) == checkBit)
					subset.add(origList.get(j));
			}
			subsetlist.add(subset);
		}
	}
	
	
	public static void main(String[] args) {
		List<Integer> origList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
		List<List<Integer>> subsetlist = new ArrayList<List<Integer>>();
		//populateSubSetList(origList, subsetlist);
		populateSubsetBinary(origList, subsetlist);
		print(subsetlist);
		//[[], [4], [3], [3, 4], [2], [2, 4], [2, 3], [2, 3, 4], [1], [1, 4], [1, 3], [1, 3, 4], [1, 2], [1, 2, 4], [1, 2, 3], [1, 2, 3, 4]]
		System.out.println(Arrays.deepToString(subsetlist.toArray()));
	}

}
