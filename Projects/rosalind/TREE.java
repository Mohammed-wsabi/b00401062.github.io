import java.io.*;
import java.util.*;

public class TREE {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine());
		while (in.hasNextLine()) {
			in.nextLine();
			n--;
		}
		System.out.println(n);
		in.close();
	}
}
