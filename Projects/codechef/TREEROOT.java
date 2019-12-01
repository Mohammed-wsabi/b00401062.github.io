package codechef;

import java.util.Scanner;

public class TREEROOT {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int T = stdin.nextInt();
        while (T-- > 0) {
            int sumRoot = 0;
            int sumCldr = 0;
            int N = stdin.nextInt();
            while (N-- > 0) {
                sumRoot += stdin.nextInt();
                sumCldr += stdin.nextInt();
            }
            System.out.println(sumRoot - sumCldr);
        }
        stdin.close();
    }
}
