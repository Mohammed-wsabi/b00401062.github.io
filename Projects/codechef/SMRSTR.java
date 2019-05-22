import java.io.*;
import java.util.*;

class SMRSTR {
	public static void main (String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();;
		while (t-- > 0) {
			int n = in.nextInt();
			int q = in.nextInt();
			long p = 1;
			while (n-- > 0 && p <= (int) 1e9)
				p *= in.nextInt();
			in.nextLine();
			while (q-- > 0)
				System.out.printf("%d ", in.nextInt() / p);
			System.out.println();
		}
	}
}
