import java.io.*;
import java.util.*;

public class LCSQ {
	private static String lcs(String s, String t) {
		int[][] table = new int[s.length()+1][t.length()+1];
		for (int i = 1; i <= s.length(); i++)
			for (int j = 1; j <= t.length(); j++)
				table[i][j] = s.charAt(i-1) == t.charAt(j-1) ? table[i-1][j-1]+1 : Math.max(table[i-1][j], table[i][j-1]);
		StringBuilder lcs = new StringBuilder();
		int i = s.length(), j = t.length();
		while (i > 0 && j > 0) {
			if (s.charAt(i-1) == t.charAt(j-1)) {
				lcs.append(s.charAt(i-1));
				i--; j--;
			} else if (table[i][j] == table[i-1][j])
				i--;
			else if (table[i][j] == table[i][j-1])
				j--;
		}
		return lcs.reverse().toString();
	}
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String s = "", t = "";
		String line = in.next();
		while (in.hasNext()) {
			if ((line = in.next()).charAt(0) == '>')
				break;
			s += line;
		}
		while (in.hasNext())
			t += in.next();
		System.out.println(lcs(s, t));
	}
}
