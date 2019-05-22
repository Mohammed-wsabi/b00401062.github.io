import java.io.*;
import java.util.*;

class CUTBOARD {
	public static void main (String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while (T-- > 0) {
		final int N = in.nextInt(), M = in.nextInt();
			System.out.println((N-1)*(M-1));
		}
	}
}
