import java.io.*;
import java.util.*;

public class RNA {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String seq = in.nextLine();
		System.out.print(seq.replaceAll("T", "U"));
		in.close();
	}
}
