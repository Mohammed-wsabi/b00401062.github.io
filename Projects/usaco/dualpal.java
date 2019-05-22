/*
ID: rombin82
LANG: JAVA
TASK: dualpal
*/

import java.io.*;
import java.util.*;

public class dualpal {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("dualpal.in"));
		PrintWriter stdout = new PrintWriter(new File("dualpal.out"));
		Runtime.getRuntime().addShutdownHook(new Thread(() -> stdout.flush()));
		int N = in.nextInt();
		int S = in.nextInt();
		while (N-- > 0) {
			boolean flag = true;
			while (flag) {
				int num = ++S;
				if (isDualPal(num)) {
					stdout.println(num);
					flag = false;
					break;
				}
			}
		}
	}
	private static boolean isDualPal(int num) {
		int count = 0;
		for (int b = 2; b <= 10; b++)
			if (isPalindrome(Integer.toString(num, b)))
				count++;
		return count >= 2;
	}
	private static boolean isPalindrome(String str) {
		return new StringBuffer(str).reverse().toString().equals(str);
	}
}
