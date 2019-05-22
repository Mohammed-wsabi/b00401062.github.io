import java.io.*;
import java.util.*;

class CHEFWORK {
	public static void main (String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] c = new int[n];
		for (int i = 0; i < n; i++)
			c[i] = in.nextInt();
		int[] min = new int[4];
		Arrays.fill(min, 100000);
		for (int i = 0; i < n; i++) {
			int t = in.nextInt();
			min[t] = Math.min(min[t], c[i]);
		}
		System.out.println(min[1] + min[2] < min[3] ? min[1] + min[2] : min[3]);
	}
}
