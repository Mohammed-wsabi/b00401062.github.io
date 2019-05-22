import java.io.*;
import java.util.*;

class BINIM {
	private static final Integer ZERO = new Integer((int) '0'), ONE = new Integer((int) '1');
	private static final String[] PLAYERS = new String[] { "Dee", "Dum" };
	public static void main (String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while (T-- > 0) {
			int N = in.nextInt();
			int turn = in.next().equals("Dum") ? 1 : 0;
			int[] counts = new int[2];
			while (N-- > 0) {
				String stack = in.next();
				final Integer who = stack.charAt(0) == '1' ? ONE : ZERO;
				counts[stack.charAt(0)-'0'] += stack.chars().filter(who::equals).count();
			}
			System.out.println(counts[turn] > counts[1-turn] ? PLAYERS[turn] : PLAYERS[1-turn]);
		}
	}
}
