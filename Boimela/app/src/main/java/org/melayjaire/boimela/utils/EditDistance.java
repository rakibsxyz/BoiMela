package org.melayjaire.boimela.utils;

/** This class is used to find the phonetic matches of book keyword search */
public class EditDistance {
	
	/** Finds match between two strings in terms of edit distance. Result is a value between 0 and 1 */
	public static double similarity(String s1, String s2) {
		if (s1.length() < s2.length()) { // s1 should always be bigger
			String swap = s1;
			s1 = s2;
			s2 = swap;
		}
		int bigLen = s1.length();
		if (bigLen == 0) {
			return 1.0; /* both strings are zero length */
		}
		return (bigLen - computeEditDistance(s1, s2)) / (double) bigLen;
	}

	/** Computes the edit distance of two strings. */
	public static int computeEditDistance(String s1, String s2) {
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();

		int[] costs = new int[s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			int lastValue = i;
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0)
					costs[j] = j;
				else {
					if (j > 0) {
						int newValue = costs[j - 1];
						if (s1.charAt(i - 1) != s2.charAt(j - 1))
							newValue = Math.min(Math.min(newValue, lastValue),
									costs[j]) + 1;
						costs[j - 1] = lastValue;
						lastValue = newValue;
					}
				}
			}
			if (i > 0)
				costs[s2.length()] = lastValue;
		}
		return costs[s2.length()];
	}

	public static void printDistance(String s1, String s2) {
		System.out.println(s1 + "-->" + s2 + ": " + computeEditDistance(s1, s2)
				+ " (" + similarity(s1, s2) + ")");
	}
	
	public static void main(String[] args) {
		EditDistance.printDistance("H", "Humayun Ahmed");
		EditDistance.printDistance("Sharifurrrr Rahhaman", "Sharifur Rahman");
	}
}
