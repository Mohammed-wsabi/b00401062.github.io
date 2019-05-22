/*
ID: rombin82
LANG: JAVA
TASK: money
*/

import java.io.*;
import java.util.*;

public class money {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("money.in"));
		PrintWriter stdout = new PrintWriter(new File("money.out"));
		Runtime.getRuntime().addShutdownHook(new Thread(() -> stdout.flush()));
		int V = in.nextInt();
		int N = in.nextInt();
		int[] coins = new int[V];
		for (int v = 0; v < V; v++)
			coins[v] = in.nextInt();
		in.close();
		int[] counts = new int[N + 1];
		counts[0] = 1;
		for (int v = 0; v < V; v++)
			for (int n = coins[v]; n <= N; n++)
				counts[n] += counts[n - coins[v]];
		stdout.println(counts[N]);
	}
}
