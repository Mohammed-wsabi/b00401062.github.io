/*
ID: rombin82
LANG: JAVA
TASK: test
*/

import java.io.*;
import java.util.*;

public class test {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("test.in"));
		PrintWriter stdout = new PrintWriter(new File("test.out"));
		Runtime.getRuntime().addShutdownHook(new Thread(() -> stdout.flush()));
		stdout.println(in.nextInt() + in.nextInt());
	}
}
