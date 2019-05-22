import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;
import java.net.*;

public class MPRT {
	private static final Pattern pattern = Pattern.compile("(?=N[^P][ST][^P])");
	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(System.in);
		PrintStream stdout = System.out;
		while (in.hasNext()) {
			String id = in.next(), line = null;
			StringBuilder seq = new StringBuilder();
			URL url = new URL("http://www.uniprot.org/uniprot/" + id + ".fasta");
			Scanner urlin = new Scanner(url.openStream());
			urlin.nextLine();
			while (urlin.hasNext())
				seq.append(urlin.next());
			Matcher matcher = pattern.matcher(seq);
			List<Integer> list = new ArrayList<>();
			while (matcher.find())
				list.add(matcher.start() + 1);
			if (list.size() != 0) {
				stdout.println(id);
				stdout.println(list.stream().map(String::valueOf).collect(Collectors.joining(" ")));
			}
			urlin.close();
		}
		in.close();
	}
}
