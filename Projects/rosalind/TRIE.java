import java.io.*;
import java.util.*;

public class TRIE {
	private static class Node {
		Node[] c = new Node[4];
		void add(String s) {
			if (s.length() == 0) return;
			int i = "ACGT".indexOf(s.charAt(0));
			if (c[i] == null)
				c[i] = new Node();
			c[i].add(s.substring(1));
		}
		void traverse() {
			List<Node> nodes = new ArrayList<>();
			int head = 0;
			nodes.add(this);
			do {
				Node node = nodes.get(head);
				for (int i = 0; i < 4; i++)
					if (node.c[i] != null) {
						nodes.add(node.c[i]);
						System.out.printf(
							"%d %d %c\n",
							head + 1,
							nodes.size(),
							"ACGT".charAt(i)
						);
					}
			} while (++head < nodes.size());
		}
	}
	public static void main(String[] args) throws IOException {
		Scanner stdin = new Scanner(System.in);
		Node root = new Node();
		while (stdin.hasNextLine())
			root.add(stdin.nextLine());
		root.traverse();
	}
}
