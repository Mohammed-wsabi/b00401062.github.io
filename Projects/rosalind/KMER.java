import java.io.*;
import java.util.*;
import java.util.stream.*;

public class KMER {
	private static int str2int(String str) {
		return IntStream.range(0, 4).map(x -> (int) Math.pow(4, 3-x) * "ACGT".indexOf(str.charAt(x))).sum();
	}
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String read = "";
		final int[] counts = new int[256];
		in.next();
		while (in.hasNext()) {
			read += in.next();
			for (int i = 0; i < read.length()-3; i++)
				counts[str2int(read.substring(i, i+4))]++;
			read = read.substring(read.length()-3);
		}
		System.out.println(Arrays.stream(counts).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
	}
}
