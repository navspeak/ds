import java.util.Arrays;

import com.progress.common.property.MergeUtility.createGroupException;


public class SubString {
// Runtime complexity - O(m + n) where m is length of text and n is length of pattern
// Space complexity - O(n)
	public static void main(String[] args) {
		String text = "abcabytttabxyabcabcabydrybh";
		String pattern = "abcaby";
		KMP kmp = new KMP();
		System.out.println(Arrays.toString(kmp.createLPSTable(pattern.toCharArray())));
		System.out.println(kmp.substring(text.toCharArray(), pattern.toCharArray()));

	}
	
	static class KMP{
		/**
		 * Time/space complexity is O(size of pattern)
		 * @param pattern
		 * @return
		 */
		int[] createLPSTable(char [] pattern) {
		    // [0|1|2|3|4|5|6|7]
			//  a|b|c|d|a|b|c|a <= Arr
			// [0|0|0|0|1|2|3|1] <= LPS
			// start with index = 0 and i = 1
			// Is arr[i] == arr[index] (b == a)
			// YES :
			//      lps[i] = index + 1; index++; i++
			// No - 
			//       index = lps[index -1]
			//       (Handles is index = 0, lps[i] = 0, i++
			int[] lps = new int[pattern.length];
			int index = 0;
			for(int i = 1; i < pattern.length; ) {
				if (pattern[i] == pattern[index]) {
					lps[i] = index + 1;
					i++;
					index++;
				} else {
					if (index != 0){
						index = lps[index - 1];
					} else {
						lps[i] = 0;
						i++;
					}
				}
			}
			return lps;
		}
		
		/**
		 * 
		 * @param text
		 * @param pattern
		 * @return the index in the text at which the pattern matches
		 * e.g. abcad and pattern is ca -> 2
		 * -1 if not pattern match
		 * O(text.len + pattern.len)
		 */
		 int substring(char[] text, char[] pattern){
			int lps[] = createLPSTable(pattern);
			int i = 0;
			int j = 0;
			while (i < text.length && j < pattern.length){
				if (text[i] == pattern[j]){
					i++; 
					j++;
				} else {
					if (j!= 0){
						j = lps[j - 1];
					} else {
						i++;
					}
				}
			}
			if (j == pattern.length)
				return i - j;
			else
				return -1;
		}

	}
	
	
	
	
}
