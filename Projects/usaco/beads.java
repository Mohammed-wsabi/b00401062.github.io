/*
ID: rombin82
LANG: JAVA
TASK: beads
*/

import java.io.*;
import java.util.*;

public class beads {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("beads.in"));
		PrintWriter stdout = new PrintWriter(new File("beads.out"));
		Runtime.getRuntime().addShutdownHook(new Thread(() -> stdout.flush()));
		int max = 0;
		int N = in.nextInt();
		String necklace = in.next();
		necklace += necklace;
		for (int i = 0; i < N; i++) {
			char c = necklace.charAt(i);
			int state = (c == 'w') ? 0 : 1;
			int current = 0;
			int j = i;
			while (state <= 2) {
				while (j < N + i && (necklace.charAt(j) == c || necklace.charAt(j) == 'w')) {
					current++;
					j++;
				}
				state++;
				c = necklace.charAt(j);
			}
			max = Math.max(max, current);
		}
		stdout.println(max);
	}
}
