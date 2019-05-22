/*
ID: rombin82
LANG: JAVA
TASK: milk
*/

import java.io.*;
import java.util.*;

public class milk {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("milk.in"));
		PrintWriter stdout = new PrintWriter(new File("milk.out"));
		Runtime.getRuntime().addShutdownHook(new Thread(() -> stdout.flush()));
		int N = in.nextInt();
		final int M = in.nextInt();
		int[][] farmers = new int[M][2];
		for (int i = 0; i < M; i++) {
			farmers[i][0] = in.nextInt();
			farmers[i][1] = in.nextInt();
		}
		Arrays.sort(farmers, (a, b) -> a[0] - b[0]);
		int cost = 0;
		for (int i = 0; i < M && 0 < N; i++) {
			cost += Math.min(N, farmers[i][1]) * farmers[i][0];
			N -= farmers[i][1];
		}
		stdout.println(cost);
	}
}
