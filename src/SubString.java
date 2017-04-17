import java.util.Arrays;

import com.progress.common.property.MergeUtility.createGroupException;


public class SubString {
	
	public static void main(String[] args) {
		String text = "abcabytttabxyabcabcabydrybh";
		String pattern = "abcaby";
		KMP kmp = new KMP();
		System.out.println(Arrays.toString(kmp.createLPSTable(pattern.toCharArray())));
		System.out.println(kmp.substring(text.toCharArray(), pattern.toCharArray()));

	}
	
	static class KMP{
		/**
		 * 
		 * @param pattern
		 * @return
		 */
		int[] createLPSTable(char [] pattern) {
			int[] lps = new int[pattern.length];
			int j = 0;
			for(int i = 1; i < pattern.length; ) {
				if (pattern[i] == pattern[j]) {
					lps[i] = j + 1;
					i++;
					j++;
				} else {
					if (j != 0){
						j = lps[j - 1];
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
