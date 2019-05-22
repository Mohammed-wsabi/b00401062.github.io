/*
ID: rombin82
LANG: JAVA
TASK: ride
*/

import java.io.*;
import java.util.*;

public class ride {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("ride.in"));
		PrintWriter stdout = new PrintWriter(new File("ride.out"));
		Runtime.getRuntime().addShutdownHook(new Thread(() -> stdout.flush()));
		String comet = in.next();
		String group = in.next();
		stdout.println(hash(comet) == hash(group) ? "GO" : "STAY");
	}
	private static int hash(String str) {
		int hash = 1;
		for (int i = 0; i < str.length(); i++)
			hash = (hash * (str.charAt(i) - 'A' + 1)) % 47;
		return hash;
	}
}
