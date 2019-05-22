import java.io.*;
import java.util.*;

public class IPRB {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int k = in.nextInt(), m = in.nextInt(), n = in.nextInt();
		int all = (k + m + n) * (k + m + n - 1) / 2;
		double pmm = (double) m * (m-1) / 8 / all;
		double pmn = (double) m * n / 2 / all;
		double pnn = (double) n * (n-1) / 2 / all;
		System.out.println(1 - pmm - pmn - pnn);
		in.close();
	}
}
