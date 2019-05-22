import java.io.*;
import java.util.*;

public class PPER {
	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(System.in);
		final int n = in.nextInt(), k = in.nextInt();
		int ans = 1;
		for (int i = n; i > n-k; i--)
			ans = ans * i % 1000000;
		System.out.println(ans);
		in.close();
	}
}
