/*
ID: rombin82
LANG: JAVA
TASK: palsquare
*/

import java.io.*;
import java.util.*;

public class palsquare {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("palsquare.in"));
		PrintWriter stdout = new PrintWriter(new File("palsquare.out"));
		Runtime.getRuntime().addShutdownHook(new Thread(() -> stdout.flush()));
		int B = in.nextInt();
		for (int i = 1; i <= 300; i++)
			if (isPalindrome(Integer.toString(i * i, B)))
				stdout.println(Integer.toString(i, B).toUpperCase() + " " + Integer.toString(i * i, B).toUpperCase());
	}
	private static boolean isPalindrome(String str) {
		return new StringBuffer(str).reverse().toString().equals(str);
	}
}
