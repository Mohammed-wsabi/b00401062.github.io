package codechef;

import java.io.BufferedWriter;

public class POSPREFS {
    private static double formula(int x) {
        return (Math.sqrt(1 + 4 * x) - 1) / 2;
    }

    private static int[] solve(int n, int k) {
        int[] a = new int[Math.max(n + 1, 10)];
        int m = k == n ? n : (int) formula((k + 1) * (k + 2) / 2);
        for (int i = 1; i <= m; i++) {
            a[i] = i;
        }
        for (int i = m + 1; i <= n; i++) {
            a[i] = -i;
        }
        if (m * m + m <= k * (k + 1) / 2) {
            a[k + 1] = k + 1;
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        Scanner stdin = new Scanner(System.in);
        BufferedWriter stdout = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = stdin.nextInt();
        while (t-- > 0) {
            int n = stdin.nextInt();
            int k = stdin.nextInt();
            int[] a = solve(n, k);
            for (int i = 1; i <= n; i++) {
                stdout.write(String.format("%d ", a[i]));
            }
            stdout.write('\n');
        }
        stdin.close();
        stdout.flush();
        stdout.close();
    }
}
