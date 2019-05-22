import java.io.*;
import java.util.*;

public class EVAL {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		final int n = in.nextInt();
		final String read = in.next();
		final int k = read.length();
		final int a = (int) read.chars().filter(A::equals).count();
		final int c = (int) read.chars().filter(C::equals).count();
		final int g = (int) read.chars().filter(G::equals).count();
		final int t = (int) read.chars().filter(T::equals).count();
		while (in.hasNext()) {
			final double p = in.nextDouble();
			System.out.println((n-k+1) * Math.pow(p/2, c+g) * Math.pow((1-p)/2, a+t));
		}
	}
}
