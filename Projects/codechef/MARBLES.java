package codechef;

import java.util.Scanner;

public class MARBLES {
    private static long choose(int n, int k) {
        long res = 1;
        for (int i = 0; i < k; i++) {
            res = res * (n - i) / (i + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int T = stdin.nextInt();
        while (T-- > 0) {
            int N = stdin.nextInt();
            int K = stdin.nextInt();
            System.out.println(choose(N - 1, K - 1));
        }
        stdin.close();
    }
}
