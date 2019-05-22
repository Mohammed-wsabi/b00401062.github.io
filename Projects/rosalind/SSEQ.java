import java.io.*;
import java.util.*;

public class SSEQ {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		List<String> reads = new ArrayList<>();
		String read = null;
		while (true) {
			if (!in.hasNext()) {
				reads.add(read);
				break;
			}
			String line = in.next();
			if (line.charAt(0) == '>') {
				if (read != null)
					reads.add(read);
				read = "";
			} else
				read += line;
		}
		String seq = reads.get(0), pat = reads.get(1);
		int i = 0, j = 0;
		for (; j < pat.length(); j++)
			for (; i < seq.length(); i++)
				if (seq.charAt(i) == pat.charAt(j)) {
					System.out.println(++i);
					break;
				}
		in.close();
	}
}
