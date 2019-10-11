package codechef;

import java.io.*;
import java.util.*;

class CHNUM {
    public static void main (String[] args) throws IOException {
        Scanner stdin = new Scanner(System.in);
        int t = stdin.nextInt();
        while (t-- > 0) {
            int n = stdin.nextInt();
            int[] c = new int[3];
            for (int i = 0; i < n; i++)
                c[(int) Math.signum(stdin.nextInt()) + 1]++;
            System.out.printf("%d %d\n", Math.max(c[0], c[2]) + c[1], Arrays.stream(c).filter(a -> a > 0).min().getAsInt());
        }
    }
}
