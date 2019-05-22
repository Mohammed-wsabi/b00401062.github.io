import java.io.*;
import java.util.*;

public class IEV {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		double ans = 0;
		ans += 2 * (in.nextInt() + in.nextInt() + in.nextInt());
		ans += 1.5 * in.nextInt() + in.nextInt();
		System.out.println(ans);
		in.close();
	}
}
