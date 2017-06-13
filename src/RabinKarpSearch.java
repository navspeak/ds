
public class RabinKarpSearch {

	int prime = 101;

	// Worst case O(n*m) but if hash is unique O(n)
	public int patternSearch(char[] text, char[] pattern){
		System.out.println("Text = " + new String(text));
		System.out.println("pattern = " + new String(pattern));
		long patternHash = calcHash(pattern, pattern.length);
		long substringHash = calcHash(text, pattern.length);
		for (int i =0 ; i < text.length - pattern.length; i++){
			System.out.println("Checking for substring = " +(new String(text)).subSequence(i, i+ pattern.length));
			int oldval = text[i == 0? 0: i - 1];
			int newval = text[i+ pattern.length -1];
			System.out.println("Oldval = " + (char)oldval);
			System.out.println("newval = " + (char)newval);
			substringHash = i == 0 ? substringHash : recalculateHash(substringHash, oldval, newval, pattern.length);
			if (patternHash == substringHash) {
				for (int j = 0; j < pattern.length; j++){
					if (text[i+j] != pattern[j])
						break;
				}
				return i;
			}
		}
		return -1;
	}

	private long recalculateHash(long hash, int oldval, int newval, int len) {
		// X = hash - oldval
		// X / prime
		// X + newval * prime^(len-1)
		return (long) ((hash - oldval)/prime + newval*Math.pow(prime, len - 1));
	}

	private long calcHash(char[] pattern, int length) {
		// Hash = a*prime^0 + a*prime^1+...
		long hash = 0;
		for (int i = 0; i< length; i++ ){
			hash+= pattern[i] * Math.pow(prime, i);//calcAsc(pattern[i]) * Math.pow(prime, i);
		}
		return hash;
	}
	
	public static void main(String[] args) {
		RabinKarpSearch rks = new RabinKarpSearch();
		//long hash = rks.calcHash("abe".toCharArray(), 3);
		
		//System.out.println(rks.recalculateHash(hash, 1, 4, 3));
	    System.out.println(rks.patternSearch("abeda".toCharArray(), "ed".toCharArray()));
	}
}
