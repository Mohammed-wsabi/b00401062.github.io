import java.io.*;
import java.util.*;

class BSTOPS {
	private static class Node {
		Node lt, rt;
		int x;
		Node(int x) { this.x = x; }
	}
	static Node insert(Node root, int x, long p) {
		if (root == null) {
			System.out.println(p);
			return new Node(x);
		} else if (x < root.x)
			root.lt = insert(root.lt, x, p * 2);
		else if (x > root.x)
			root.rt = insert(root.rt, x, p * 2 + 1);
		return root;
	}
	static Node delete(Node root, int x, long p) {
		if (x < root.x)
			root.lt = delete(root.lt, x, p * 2);
		else if (x > root.x)
			root.rt = delete(root.rt, x, p * 2 + 1);
		else {
			if (p > 0)
				System.out.println(p);
			if (root.lt == null)
				return root.rt;
			else if (root.rt == null)
				return root.lt;
			else {
				Node successor = root.rt;
				while (successor.lt != null)
					successor = successor.lt;
				root.x = successor.x;
				root.rt = delete(root.rt, successor.x, -1);
			}
		}
		return root;
	}
	public static void main (String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Node root = new Node(Integer.MIN_VALUE);
		while (n-- > 0) {
			char c = in.next().charAt(0);
			int x = in.nextInt();
			switch (c) {
			case 'i':
				insert(root, x, 0);
				break;
			case 'd':
				delete(root, x, 0);
				break;
			}
		}
	}
}
