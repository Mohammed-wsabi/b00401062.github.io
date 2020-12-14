package codechef;

import java.util.Scanner;
import java.util.stream.Collectors;

public class HXOR {
    private static void solve(int[] a, int x) {
        final int n = a.length;
        int i = 0;
        do {
            for (i = 0; i < n - 2; i++) {
                if (a[i] != 0) break;
            }
            int p = a[i] == 0 ? 0 : (int) (Math.log(a[i]) / Math.log(2));
            int j = i + 1;
            for (; j < n - 1; j++) {
                if ((a[j] & (1 << p)) != 0) break;
            }
            if (i == n - 2 && j == n - 1 && a[i] == 0) break;
            a[i] ^= (1 << p);
            a[j] ^= (1 << p);
        } while (--x > 0);
        if (n == 2 && x % 2 == 1) {
            a[0] ^= 1;
            a[1] ^= 1;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner stdin = new Scanner(System.in);
        int t = stdin.nextInt();
        while (t-- > 0) {
            int n = stdin.nextInt();
            int x = stdin.nextInt();
            stdin.nextLine();
            int[] a = Arrays.stream(
                stdin.nextLine().split(" ")
            ).mapToInt(Integer::valueOf).toArray();
            solve(a, x);
            System.out.println(
                Arrays.stream(a)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "))
            );
        }
        stdin.close();
    }
}
