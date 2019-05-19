import java.io.*;
import java.util.*;

class SMRSTR {
	public static void main (String[] args) throws IOException {
		Scanner stdin = new Scanner(System.in);
		int t = stdin.nextInt();;
		while (t-- > 0) {
			int n = stdin.nextInt();
			int q = stdin.nextInt();
			long p = 1;
			while (n-- > 0 && p <= (int) 1e9)
				p *= stdin.nextInt();
			stdin.nextLine();
			while (q-- > 0)
				System.out.printf("%d ", stdin.nextInt() / p);
			System.out.println();
		}
	}
}