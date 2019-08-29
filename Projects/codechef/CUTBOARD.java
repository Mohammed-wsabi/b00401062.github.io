import java.io.*;
import java.util.*;

class CUTBOARD {
	public static void main (String[] args) throws Exception {
		Scanner stdin = new Scanner(System.in);
		int T = stdin.nextInt();
		while (T-- > 0) {
		final int N = stdin.nextInt(), M = stdin.nextInt();
			System.out.println((N-1)*(M-1));
		}
	}
}
