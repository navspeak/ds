package com.nav;

import java.util.ArrayList;
import java.util.List;

public class Anagram {

	// Str = ABC
	// A = A
	// AB = AB BA
	// ABC = ABC ACB CAB

	// F(ABC) = A + F(BC)
	// F(BC) = B + F(C)

	//            F(ABC)
	//     A                  F(BC)
	//                    B           F(C)
	//                                  


	public  List<String> findAnagram(String str){
		if (str.length() == 1){
			List<String> potentialanagrams = new ArrayList<>();
			potentialanagrams.add(str);
			return potentialanagrams;
		}
		// get one element
		char currElement = str.charAt(0);
		String substring = str.substring(1, str.length() );
		List<String> potentialanagrams = findAnagram(substring);
		List<String> anagramList = new ArrayList<>();
		insertAtAllIndex(potentialanagrams, currElement, anagramList);
		return anagramList;

	}

	private void insertAtAllIndex(List<String> potentialAnagrams, char chr, List<String> anagramList){
		for (String str : potentialAnagrams){
			for (int i = 0; i <= str.length(); i++){
				StringBuilder sb = new StringBuilder(str);
				if (str.length() == i)
					sb.append(chr);
				else
					sb.insert(i, chr);
				anagramList.add(sb.toString());
					
			}
		}
	}

	public static void main(String[] args) {

		String str = "ABC";
		Anagram anagram = new Anagram();
		List<String> anagrams  = anagram.findAnagram(str);
		anagrams.stream().forEach(System.out::println);


	}

}
