import java.io.*;
import java.util.*;

class XORAGN {
	public static void main (String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int t = Integer.parseInt(in.nextLine());
		while (t-- > 0) {
			int n = Integer.parseInt(in.nextLine());
			System.out.println(
				Arrays.stream(in.nextLine().split(" "))
				.mapToInt(Integer::parseInt)
				.map(x -> x * 2)
				.reduce((x1, x2) -> x1 ^ x2)
				.getAsInt()
			);
		}
	}
}
