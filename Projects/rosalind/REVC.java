import java.io.*;
import java.util.*;

public class REVC {
	private static final Map<Character, Character> COMPLEMENT = new HashMap<Character, Character>() {{
		put('A', 'T'); put('C', 'G'); put('G', 'C'); put('T', 'A');
	}};
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String seq = in.nextLine();
		System.out.println(new StringBuilder(seq).reverse().chars().mapToObj(x -> (char) x).map(COMPLEMENT::get).map(String::valueOf).collect(Collectors.joining()));
		in.close();
	}
}
