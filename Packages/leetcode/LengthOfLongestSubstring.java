class Solution {
	public int lengthOfLongestSubstring(String s) {
		int length = 0;
		String substring = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			substring = substring.substring(substring.indexOf(c)+1) + c;
			length = Math.max(length, substring.length());
		}
		return length;
	}
}
