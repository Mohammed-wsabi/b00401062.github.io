import java.io.*;
import java.util.*;
import java.util.stream.*;

public class PDST {
    private static long hamming(String s, String t) {
        final int l = s.length();
        return IntStream.range(0, l).filter(i -> s.charAt(i) != t.charAt(i)).count();
    }
    public static void main(String[] args) throws IOException {
        final Scanner in = new Scanner(System.in);
        final List<String> reads = new ArrayList<>();
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
        final int n = reads.size();
        final int l = reads.get(0).length();
        double[][] d = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = i+1; j < n; j++)
                d[i][j] = d[j][i] = (double) hamming(reads.get(i), reads.get(j)) / l;
        for (int i = 0; i < n; i++)
            System.out.println(Arrays.stream(d[i]).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }
}
