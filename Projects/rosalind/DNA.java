import java.io.*;
import java.util.*;

public class DNA {
	private static final char[] DNABASES = new char[] { 'A', 'C', 'G', 'T' };
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String seq = in.nextLine();
		for (final char base : DNABASES)
			System.out.println(seq.chars().filter(x -> x == base).count());
		in.close();
	}
}
